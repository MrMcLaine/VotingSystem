package org.voting.web.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.voting.entity.Meal;
import org.voting.entity.Restaurant;
import org.voting.service.MealService;
import org.voting.service.RestaurantService;
import org.voting.to.RestaurantTo;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.voting.util.ValidationUtil.assureIdConsistent;
import static org.voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = RestaurantAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantAdminRestController {

    public static final String REST_URL = "/rest/admin/restaurants";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MealService mealService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody Restaurant restaurant) {
        log.info("create {}", restaurant);
        checkNew(restaurant);
        Restaurant createdRestaurant = restaurantService.create(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(createdRestaurant.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(createdRestaurant);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        restaurantService.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int id, @RequestBody Restaurant restaurant) {
        log.info("update {}", id);
        assureIdConsistent(restaurant, id);
        restaurantService.update(restaurant);
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("getAll {}");
        return restaurantService.getAll();
    }

    @GetMapping("/{id}/history")
    public RestaurantTo getWithHistoryOfMeals(@PathVariable int id) {
        log.info("getWithHistoryOfMeals by restaurant with id {}", id);
        List<Meal> meals = mealService.getHistoryOfMenu(id);
        return new RestaurantTo(restaurantService.get(id), meals);
    }

    @GetMapping("/{id}/history/{date}")
    public RestaurantTo getWithOnePastDayHistoryOfMeals(@PathVariable int id, @PathVariable LocalDate date) {
        log.info("getWithOnePastDayHistoryOfMeals by restaurant with id {}", id);
        List<Meal> meals = mealService.getOnePastDayHistoryMenu(id, date);
        return new RestaurantTo(restaurantService.get(id), meals);
    }
}
