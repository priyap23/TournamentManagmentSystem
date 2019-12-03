CREATE TABLE exercise(
  id INT PRIMARY KEY,
  tournament_id INT,
  reward_amount INT,
  player_id INT,
  player_name VARCHAR
);

CREATE TABLE tournaments(
 tournament_id INT PRIMARY KEY AUTO_INCREMENT,
 reward_amount INT,
 tournament_name VARCHAR
); 


CREATE TABLE players(
 player_id INT PRIMARY KEY AUTO_INCREMENT,
 player_name VARCHAR,
 tournament_id INT,
 FOREIGN KEY(tournament_id) REFERENCES tournaments(tournament_id) ON DELETE CASCADE
 ); 
 
