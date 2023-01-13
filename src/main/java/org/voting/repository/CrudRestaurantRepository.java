package org.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.voting.entity.Restaurant;

import java.time.LocalDate;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT restaurant FROM Restaurant restaurant WHERE restaurant.id=?1")
    Restaurant get(int id, LocalDate date);

    @Query("SELECT restaurant FROM Restaurant restaurant WHERE restaurant.id=:id")
    Restaurant getWithHistoryOfMeals(@Param("id") int id);

    @Query("SELECT r FROM Restaurant r WHERE r.id = ?1")
    Restaurant get(int id);

    @Query("SELECT m FROM Meal m WHERE m.restaurant.id= :id")
    Restaurant getWithMeals(@Param("id") int id);
}
