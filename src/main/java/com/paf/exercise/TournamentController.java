package com.paf.exercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paf.exercise.dao.Player;
import com.paf.exercise.dao.PlayersRepo;
import com.paf.exercise.dao.Tournament;
import com.paf.exercise.dao.TournamentRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TournamentController {

	
	private static final Logger log = LoggerFactory.getLogger(TournamentController.class);
	
	@Autowired
	TournamentRepo tourRepo;
	
	@Autowired
	PlayersRepo playerRepo;
	
	/**
	 * This method is to get all tournaments from tournament table
	 * @return List of Tournaments
	 */
	@RequestMapping(value = "/getAllTournaments", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Tournament> getAllTournaments() throws Exception{
		List<Tournament> list = new ArrayList<>();
		
		Iterable<Tournament> itrTournament = tourRepo.findAll();
		for (Tournament tournament : itrTournament) {
			list.add(tournament);
		}
		log.debug(list.toString());
		return list;
	}
	
	
	
	/**
	 * This method is to add player to tournaments
	 * @return List of Tournaments
	 * @throws Exception 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping(value = "/addPlayerIntoTournament", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addPlayerIntoTournament(@RequestBody String requestBody) throws Exception {
		  ObjectMapper mapper = new ObjectMapper();
          Map<String, String> jsonMap = mapper.readValue(requestBody, Map.class);
		  Player playerObj = new Player(); playerObj.setPlayer_name((String) jsonMap.get("playerName"));
		  Tournament t = new Tournament(); t.setTournament_id(Integer.parseInt(jsonMap.get("tournament_id").toString()));
		  playerObj.setTournament_id(t);
		  log.info("Player created : "+playerObj.toString());
		  playerRepo.save(playerObj);
	}
	
	/**
	 * This method is to update reward points of tournament
	 * @return List of Tournaments
	 */
	@RequestMapping(value = "/updateTournament", method=RequestMethod.PUT)
	public void updateTournament(@RequestParam Integer tournament_id, @RequestParam Integer reward_amount) throws Exception{
		tourRepo.updateRewardPointInTournament(reward_amount, tournament_id);
		log.info("Tournameny of id "+tournament_id+" is updated with reward amount as "+reward_amount);
	}
	
	/**
	 * This method is to create tournament
	 * @return List of Tournaments
	 * @throws Exception 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping(value = "/addTournament", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createTournament(@RequestBody String requestBody) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
        Map<String, String> jsonMap = mapper.readValue(requestBody, Map.class);
		Tournament tournamentObj = new Tournament();
		tournamentObj.setReward_amount(Integer.parseInt(jsonMap.get("rewardAmount")));
		tournamentObj.setTournament_name(jsonMap.get("tournamentName"));
		log.info("Tournament created : "+tournamentObj.toString());
		tourRepo.save(tournamentObj);
	}
	
	/**
	 * This method is to remove tournament
	 * @return List of Tournaments
	 */
	@RequestMapping(value = "/removeTournament", method=RequestMethod.DELETE)
	public void removeTournament(@RequestParam Integer tournament_id) throws Exception{
		tourRepo.delete(tournament_id);
		log.info("Tournament of id "+tournament_id+" removed successfully");
	}
	
	
	/**
	 * This method is to remove player from tournament
	 * @return List of Tournaments
	 */
	@RequestMapping(value = "/removePlayerFromTournament", method=RequestMethod.DELETE)
	public void removePlayerFromTournament(@RequestParam Integer tournament_id, @RequestParam Integer player_id) throws Exception{
		playerRepo.deletePlayerFromTournaments(tournament_id, player_id);
		log.info("PLayer of id "+player_id+" removed successfully");
	}
	
	/**
	 * This method is to get players of tournament
	 * @return List of Tournaments
	 */
	@RequestMapping(value = "/getPlayersOfTournament", method=RequestMethod.GET)
	public List<Player> getPlayersOfTournament(@RequestParam Integer tournament_id) throws Exception{
		List<Player> list = playerRepo.getPlayerByTournamentId(tournament_id);
		return list;
	}
	
}
