package org.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.voting.entity.Vote;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote,Integer> {

    @Query("SELECT v FROM Vote v WHERE v.votingDate=:votingDate AND v.user.id=:userId")
    Vote getVoteByUser(@Param("userId") int userId, @Param("votingDate") LocalDate date);

    @Query("SELECT v FROM Vote v WHERE v.restaurant.id=:restaurantId ORDER BY v.votingDate DESC")
    List<Vote> getVotesByRestaurant(@Param("restaurantId") int restaurantId);
}