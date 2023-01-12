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
import org.voting.service.MealService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.voting.util.ValidationUtil.assureIdConsistent;
import static org.voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = MealRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealRestController {

    public static final String REST_URL = "/rest/admin/restaurants/{id}/meals";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocations(@RequestBody Meal meal, @PathVariable int id) {
        log.info("create {}", meal);
        checkNew(meal);
        Meal created = service.create(meal, id);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(id, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{mealId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int mealId, @PathVariable int id) {
        log.info("delete {}", mealId);
        service.delete(mealId, id);
    }

    @PutMapping("/{mealId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Meal meal, @PathVariable int mealId, @PathVariable int id) {
        log.info("update {}", meal);
        assureIdConsistent(meal, mealId);
        service.update(meal, id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Meal> getActualMenu(@PathVariable int id) {
        log.info("getDateMenu with id {} by date {}", id, LocalDate.now());
        return service.getActualMenu(id);
    }

    @GetMapping("/{mealId}")
    public Meal get(@PathVariable int mealId, @PathVariable int id) {
        log.info("get {} restaurant id {}", mealId, id);
        return service.get(mealId, id);
    }
}
