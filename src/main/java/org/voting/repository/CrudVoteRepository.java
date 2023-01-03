package org.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.voting.entity.Vote;

import java.time.LocalDate;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote,Integer> {

    @Query("select v FROM Vote v where v.votingDate=:votingDate and v.user.id=:userId")
    Vote getVoteByUser(@Param("userId") int userId, @Param("votingDate") LocalDate date);
}