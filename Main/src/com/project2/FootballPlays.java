package com.project2;

/**
 * Student Name: Marcelo Gheiler
 * Filename: FootballPlays
 * Date: 10/24/15
 * TA Name: Colin Pronovost
 * Assignment:
 * Lab Day: Monday
 * Lab Time: 5PM
 * Lab Location: CSB 703
 * I affirm that I have not given or received any unauthorized help on this assignment, and that this work is my own
 */
public class FootballPlays {

	FootballTeam homeTeam, awayTeam;

	public FootballPlays(FootballTeam homeTeam, FootballTeam awayTeam, FootballField field) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
	}

	public void initialKickoff(FootballField field, FootballTeam kickingTeam, FootballTeam receivingTeam) {

		if (kickingTeam.equals(homeTeam)) {
			// The home team starts at the 35 yard line
			field.setLineOfScrimmage(35);
		}
	}

}
