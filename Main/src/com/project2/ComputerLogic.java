package com.project2;

/**
 * Student Name: Marcelo Gheiler
 * Filename: ComputerLogic
 * Date: 10/23/15
 * TA Name: Colin Pronovost
 * Assignment:
 * Lab Day: Monday
 * Lab Time: 5PM
 * Lab Location: CSB 703
 * I affirm that I have not given or received any unauthorized help on this assignment, and that this work is my own
 */
public class ComputerLogic {

    public String choosePlay(String typeOfPlay, FootballField field) {
        // If they are within 30 yards of the goal, they may want to do a field goal
/*        if (100 - field.getLineOfScrimmage() <= 30) {
            if (typeOfPlay.equalsIgnoreCase("DEFENSIVE")) {
                switch (ChanceCalc.randomNumber(1, 4)) {
                    case 1:
                        return "RUN_DEFENSE";
                    case 2:
                        return "PASS_DEFENSE";
                    case 3:
                        return "BLITZ_DEFENSE";
                    case 4:
                        return "KICK_RETURN_DEFENSE";
                    default:
                        return "ERROR";
                }
            }
            // If you ware within 30 yards of the goal, the computer might want to try a field goal
        } else if (field.getLineOfScrimmage() <= 30) {
            if (typeOfPlay.equalsIgnoreCase("OFFENSIVE")) {
                switch (ChanceCalc.randomNumber(1, 4)) {
                    case 1:
                        return "RUN_OFFENSE";
                    case 2:
                        return "PASS_OFFENSE";
                    case 3:
                        return "PUNT_OFFENSE";
                    case 4:
                        return "FIELD_GOAL_OFFENSE";
                    default:
                        return "ERROR";
                }
            }
        } else {
            if (typeOfPlay.equalsIgnoreCase("DEFENSIVE")) {
                switch (ChanceCalc.randomNumber(1, 3)) {
                    case 1:
                        return "RUN_DEFENSE";
                    case 2:
                        return "PASS_DEFENSE";
                    case 3:
                        return "BLITZ_DEFENSE";
                    default:
                        return "ERROR";
                }
            }
            if (typeOfPlay.equalsIgnoreCase("OFFENSIVE")) {
                switch (ChanceCalc.randomNumber(1, 3)) {
                    case 1:
                        return "RUN_OFFENSE";
                    case 2:
                        return "PASS_OFFENSE";
                    case 3:
                        return "PUNT_OFFENSE";
                    default:
                        return "ERROR";
                }
            }
        }
        return "Error: Computer is neither offensive nor defensive";*/
        return "KICK_RETURN_DEFENSE";
    }

	public Boolean wantsToReceive() {
		// Give the computer a 75% chance to want to receive, because it's normally beneficial to want to receive first
		return ChanceCalc.outCome(75);
	}

}
