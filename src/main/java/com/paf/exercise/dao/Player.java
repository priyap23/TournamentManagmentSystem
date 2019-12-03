package com.paf.exercise.dao;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="players")
public class Player {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int player_id;
	
	@Column(name="player_name")
	public String player_name;
	
	@JoinColumn(name="tournament_id")
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	public Tournament tournament_id;
	
	public Tournament getTournament_id() {
		return tournament_id;
	}
	public void setTournament_id(Tournament tournament_id) {
		this.tournament_id = tournament_id;
	}
	public int getPlayer_id() {
		return player_id;
	}
	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}
	public String getPlayer_name() {
		return player_name;
	}
	public void setPlayer_name(String player_name) {
		this.player_name = player_name;
	}
	
	@Override
	public String toString() {
		return "Player [player_id=" + player_id + ", player_name=" + player_name + ", tournament_id=" + tournament_id
				+ "]";
	}
	
}
