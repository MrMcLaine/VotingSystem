package org.voting.web.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.voting.entity.Restaurant;
import org.voting.service.RestaurantService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = RestaurantProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantProfileRestController {

    public static final String REST_URL = "/profile/restaurants";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService service;

    @GetMapping
    public List<Restaurant> getAll(@RequestParam(required = false) boolean withMenu) {
        List<Restaurant> restaurants = service.getAll();
        if (withMenu) {
            return restaurants.stream()
                    .map(r -> service.getWithMeals(r.getId()))
                    .collect(Collectors.toList());
        }
        log.info("getAll");
        return restaurants;
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id, @RequestParam(required = false) boolean withMenu) {
        return withMenu ? service.getWithMeals(id) : service.get(id);
    }
}
