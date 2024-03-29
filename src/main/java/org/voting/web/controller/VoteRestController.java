package org.voting.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.voting.entity.Vote;
import org.voting.service.VoteService;
import org.voting.to.VoteTo;

import java.net.URI;
import java.util.List;

import static org.voting.SecurityUtil.authUserId;
import static org.voting.util.VotesUtil.convertListToVoteTo;
import static org.voting.util.VotesUtil.convertToVoteTo;

@RestController
@RequestMapping(VoteRestController.REST_URL)
public class VoteRestController {

    public static final String REST_URL = "/rest/profile/votes";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    @PostMapping()
    public ResponseEntity<Vote> createWithLocations(@RequestBody int restaurantId) {
        log.info("crate Vote with RestaurantId={}", restaurantId);
        Vote created = service.save(restaurantId, authUserId());
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{restaurantId}")
                .buildAndExpand(created.getRestaurant().getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<Vote> updateWithLocations(@RequestBody int restaurantId) {
        log.info("update Vote with RestaurantId={}", restaurantId);
        Vote created = service.save(restaurantId, authUserId());
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{restaurantId}")
                .buildAndExpand(created.getRestaurant().getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/today")
    public VoteTo getTodayVote() {
        Vote myVote = service.getTodayVoteByUser(authUserId());
        return myVote != null ? convertToVoteTo(myVote) : null;
    }

    @GetMapping()
    public List<VoteTo> getVotesByRestaurant(@RequestParam int restaurantId) {
        List<Vote> votes = service.getVotesByRestaurant(restaurantId);
        return convertListToVoteTo(votes);
    }
}
