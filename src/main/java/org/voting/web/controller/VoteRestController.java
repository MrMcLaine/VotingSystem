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
import java.util.ArrayList;
import java.util.List;

import static org.voting.SecurityUtil.authUserId;

@RestController
@RequestMapping(VoteRestController.REST_URL)
public class VoteRestController {

    public static final String REST_URL = "/rest/profile/votes";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    @PostMapping("/{restaurantId}")
    public ResponseEntity<Vote> vote(@PathVariable int restaurantId) {
        Vote created = service.save(restaurantId, authUserId());
        log.info("save Vote with RestaurantId={}", restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .buildAndExpand(REST_URL + "/{restaurantId}").toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/today")
    public VoteTo getTodayVote() {
        Vote myVote = service.getTodayVoteByUser(authUserId());
        return myVote != null ? new VoteTo(myVote) : null;
    }

    @GetMapping("/{restaurantId}")
    public List<VoteTo> getVotesByRestaurant(@PathVariable int restaurantId) {
        List<Vote> votes= service.getVotesByRestaurant(restaurantId);
        List<VoteTo> listToReturn = new ArrayList<>();
        for(Vote vote : votes) {
            listToReturn.add(new VoteTo(vote));
        }
        return listToReturn;
    }
}
