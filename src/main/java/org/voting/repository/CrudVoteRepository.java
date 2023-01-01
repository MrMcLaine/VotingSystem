package org.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.voting.entity.Vote;

import java.time.LocalDateTime;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote,Integer> {

    @Query("select v FROM Vote v where v.votingDateTime=:votingDateTime and v.user.id=:userId")
    Vote get(@Param("userId") int userId, @Param("votingDateTime") LocalDateTime dateTime);
}