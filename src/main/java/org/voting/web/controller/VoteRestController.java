package org.voting.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.voting.entity.Vote;
import org.voting.service.VoteService;
import org.voting.to.VoteTo;

import java.net.URI;

import static org.voting.SecurityUtil.authUserId;

@RestController
@RequestMapping(VoteRestController.VOTE_URL)
public class VoteRestController {

    public static final String VOTE_URL = "/profile/votes";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    @PutMapping
    public ResponseEntity<Vote> vote(@RequestParam int restaurantId) {
        Vote created = service.save(restaurantId, authUserId());
        log.info("save Vote with RestaurantId={}", restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .buildAndExpand(VOTE_URL + "/{id}").toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public VoteTo get() {
        Vote myVote = service.getTodayVoteByUser(authUserId());
        return myVote != null ? new VoteTo(myVote) : null;
    }
}
