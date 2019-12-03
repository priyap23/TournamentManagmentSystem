package com.paf.exercise.dao;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tournaments")
public class Tournament {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name="tournament_id")
	public int tournament_id;
	
	@Column(name="reward_amount")
	public int reward_amount;
	
	@Column(name="tournament_name")
	public String tournament_name;
	
	//@OneToMany
	//@JoinColumn
	//private Set<Player> players;
	
	public int getTournament_id() {
		return tournament_id;
	}

	public void setTournament_id(int tournament_id) {
		this.tournament_id = tournament_id;
	}

	public int getReward_amount() {
		return reward_amount;
	}

	public void setReward_amount(int reward_amount) {
		this.reward_amount = reward_amount;
	}

	public String getTournament_name() {
		return tournament_name;
	}

	public void setTournament_name(String tournament_name) {
		this.tournament_name = tournament_name;
	}

	@Override
	public String toString() {
		return "Tournament [tournament_id=" + tournament_id + ", reward_amount=" + reward_amount + ", tournament_name="
				+ tournament_name + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
}
