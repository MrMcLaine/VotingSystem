package org.voting.web.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.voting.entity.Restaurant;
import org.voting.service.MenuItemService;
import org.voting.service.RestaurantService;
import org.voting.to.RestaurantTo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantProfileRestController {

    public static final String REST_URL = "/rest/profile/restaurants";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuItemService menuItemService;

    @GetMapping()
    public List<Restaurant> getAll() {
        log.info("getAll");
        return restaurantService.getAll();
    }

    @GetMapping("/withMenu")
    public List<RestaurantTo> getAllWithMenu() {
        log.info("getAllWithMenu");
        LocalDate now = LocalDate.now();
        List<RestaurantTo> restaurantsTo = new ArrayList<>();
        for (Restaurant r : restaurantService.getAll()) {
            restaurantsTo.add(new RestaurantTo(r, menuItemService.getMenuForDate(r.getId(), now)));
        }
        return restaurantsTo;
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        log.info("get restaurant with id {}", id);
        return restaurantService.get(id);
    }

    @GetMapping("/{id}/withMenu")
    public RestaurantTo getWithMenu(@PathVariable int id) {
        return new RestaurantTo(restaurantService.get(id), menuItemService.getMenuForDate(id, LocalDate.now()));
    }
}
