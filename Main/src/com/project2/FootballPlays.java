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
    FootballField field;

	public FootballPlays(FootballTeam homeTeam, FootballTeam awayTeam, FootballField field) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
        this.field = field;
	}

	public void initialKickoff(FootballField field, FootballTeam kickingTeam, FootballTeam receivingTeam) {

		if (receivingTeam.equals(homeTeam)) {
			// The home team starts at the 35 yard line
            field.setLineOfScrimmage(35);
		} else {
            field.setLineOfScrimmage(field.getScorableFieldLength() - 35);
		}

        // Calculate how far the ball travels, between 40 and 80 yards
        int distance = ChanceCalc.randomNumber(40, 80);
        field.setCurrentDown(1);

        // The ball will be kicked either into the opposition's end zone, resulting in a touchback, which rewards no
        // points but the offensive team starts at their 20 yard line, or the ball will be played wherever it lands
        if (distance >= 65) {

            System.out.println("The kick traveled from the " + field.getLineOfScrimmage() + " to the end zone for a touchback.");

            if (receivingTeam.equals(homeTeam)) {
                field.setLineOfScrimmage(20);
            } else {
                field.setLineOfScrimmage(80);
            }
        } else if (distance < 65) {
            System.out.println("The kick traveled " + distance + " yards.");
            if (receivingTeam.equals(homeTeam)) {
                // The ball starts at the away team's 35 yard line--which is the 65 yard line-- and moves in the
                // negative direction
                field.setLineOfScrimmage(65 - distance);

            } else {
                // The ball starts at the 35 yard line and moves the distance in the positive direction
                field.setLineOfScrimmage(35 + distance);
            }
        }
        System.out.println("The offense will start at the " + field.getLineOfScrimmage() + " yard line on its " +
                field.getCurrentDownWithOrdinal() + " down.");
        System.out.println();

        if (field.getOffensiveTeam().equals(homeTeam)) {
            System.out.println("You are on the offense.");
            System.out.println();
        } else {
            System.out.println("You are on defense");
            System.out.println();
        }
    }

}
