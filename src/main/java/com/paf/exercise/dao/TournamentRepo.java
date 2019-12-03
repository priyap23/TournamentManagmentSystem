package com.paf.exercise.dao;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface TournamentRepo extends CrudRepository<Tournament, Integer>{

	@Transactional
    @Modifying
	@Query(value="update tournaments set reward_amount=?1 where tournament_id=?2",  nativeQuery = true)
	void updateRewardPointInTournament(Integer reward_amount, Integer tournament_id);
}

