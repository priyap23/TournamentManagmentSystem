package com.paf.exercise.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PlayersRepo extends CrudRepository<Player, Integer>{
	
	@Query(value = "select * from players where tournament_id=?1",  nativeQuery = true)
	List<Player> getPlayerByTournamentId(Integer playerTournamentId);
	
	@Transactional
	@Modifying
	@Query(value = "delete from players where tournament_id=?1 and player_id=?2", nativeQuery = true)
	void deletePlayerFromTournaments(Integer tournament_id, Integer player_id);
}
