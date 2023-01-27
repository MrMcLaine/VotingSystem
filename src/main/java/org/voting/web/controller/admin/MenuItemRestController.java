package org.voting.web.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.voting.entity.MenuItem;
import org.voting.service.MenuItemService;
import org.voting.to.MenuItemTo;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.voting.util.ValidationUtil.assureIdConsistent;
import static org.voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = MenuItemRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuItemRestController {

    public static final String REST_URL = "/rest/admin/restaurants/{id}/menu";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuItemService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuItem> createWithLocations(@RequestBody MenuItem menuItem, @PathVariable int id) {
        log.info("create {}", menuItem);
        checkNew(menuItem);
        MenuItem created = service.create(menuItem, id);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(id, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{menuItemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int menuItemId, @PathVariable int id) {
        log.info("delete {}", menuItemId);
        service.delete(menuItemId, id);
    }

    @PutMapping("/{menuItemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody MenuItem menuItem, @PathVariable int menuItemId, @PathVariable int id) {
        log.info("update {}", menuItem);
        assureIdConsistent(menuItem, menuItemId);
        service.update(menuItem, id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MenuItemTo> getActualMenu(@PathVariable int id) {
        log.info("getDateMenu with id {} by date {}", id, LocalDate.now());
        return service.getMenuForDate(id, LocalDate.now());
    }

    @GetMapping("/{menuItemId}")
    public MenuItemTo get(@PathVariable int menuItemId, @PathVariable int id) {
        log.info("get {} restaurant id {}", menuItemId, id);
        return service.get(menuItemId, id);
    }
}
