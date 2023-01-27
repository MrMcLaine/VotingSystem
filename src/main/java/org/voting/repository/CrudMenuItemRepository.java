package org.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.voting.entity.MenuItem;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMenuItemRepository extends JpaRepository<MenuItem, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM MenuItem m WHERE m.id=:id AND m.restaurant.id=:restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Query("SELECT m FROM MenuItem m WHERE m.restaurant.id=:restaurant_id AND m.dateMenuItem=:date")
    List<MenuItem> getMenuForDate(@Param("restaurant_id") int id, @Param("date") LocalDate date);

    @Query("SELECT m FROM MenuItem m WHERE m.restaurant.id=:restaurant_id order by m.dateMenuItem")
    List<MenuItem> getHistoryOfMenu(@Param("restaurant_id") int id);

}
