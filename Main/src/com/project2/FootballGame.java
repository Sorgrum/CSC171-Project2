package com.project2;/*
*
* Student Name: Marcelo Gheiler
* Filename: Main.java
* Date: 2015-10-16
* TA Name: Colin Pronovost
* Assignment: 10
* Lab Day: Monday
* Lab Time: 5PM
* Lab Location: CSB 703
*
* I affirm that I have not given or received any unauthorized help on this assignment, and that this work is my own.
*
*/

import java.util.Scanner;

public class FootballGame {

	Scanner input = new Scanner(System.in);
	FootballField field = new FootballField();
	ComputerLogic computer = new ComputerLogic();

	protected FootballTeam homeTeam, awayTeam;

	protected int quarterLength;
	protected int secondsRemaining;

	protected int homeScore;
	protected int awayScore;

	protected FootballTeam leftTeam, rightTeam;


	/**
	 * Get the amount of seconds left in the quarter
	 */
	public int getTimeLeft() {
		return secondsRemaining;
	}

	/**
	 * Set the amount of seconds in each quarter
	 */
	public void setTimePerQuarter(int quarterLength) {
		// Convert the quarter length from minutes to seconds
		secondsRemaining = quarterLength * 60;
	}


	/**
	 * Construct and return a new com.project2.FootballGame.
	 * Start the game with the start() method.
	 */
	public FootballGame(FootballTeam homeTeam, FootballTeam awayTeam) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
	}

	/**
	 * Play this com.project2.FootballGame.
	 * A game starts with a coin toss to determine which team kicks off (the
	 * other team receives). Play starts with the kickoff and proceeds through
	 * four quarters. The team with the most points after four quarters is the
	 * winner.
	 */
	public void startGame() {


		System.out.print("How long should each game quarter be (in minutes)? ");
		int quarterLength = input.nextInt();
		// Add a nextLine to eat \n in buffer
		input.nextLine();
		setTimePerQuarter(quarterLength);


		System.out.println("Heads or Tails?");
		String coinFace = input.nextLine();

		// Did the user win the coin toss
		if (coinFace.equalsIgnoreCase(ChanceCalc.coinToss())) {

			System.out.println("It's " + coinFace + ", \n[kick/receive] Do you want to kick or receive?");
			String kickReceivePreference = input.nextLine();

			if (kickReceivePreference.equalsIgnoreCase("kick")) {
				initialKickoff(awayTeam, homeTeam);
			} else if (kickReceivePreference.equalsIgnoreCase("receive")) {
				initialKickoff(homeTeam, awayTeam);
			} else {
				System.out.println("Invalid input.");
			}

		} else {
			System.out.println("You didn't win the coin toss");
			if (computer.wantsToRecieve()) {
				System.out.println("The " + awayTeam.getName() + " have decided to receive the ball");

				// The kick will go from the home team to the away team
				initialKickoff(awayTeam, homeTeam);
			} else {
				System.out.println("The " + awayTeam.getName() + " have decided to kick the ball");
			}
		}

		for (int i = 1; i <= 4; i++) {
			playQuarter(i);
		}
		reportWinner();
	}

	protected void initialKickoff(FootballTeam reciever, FootballTeam kickers) {
		// TODO Make the ball go a distance between 40-80 yards
	}

	/**
	 * Play the given quarter of this Football Game.
	 * Quarters last 15 minutes and end after the play during which the time
	 * runs out.
	 */
	protected void playQuarter(int quaterLength) {

		/*this.quarterLength = quaterLength;
		secondsRemaining = 15 * 60;
		while (secondsRemaining > 0) {
			playAPlay();
		}*/
	}

	/**
	 * Report the winner of this com.project2.FootballGame, assuming it is over.
	 */
	protected void reportWinner() {

	}

	protected void playAPlay() {
		// TODO
		// Pick away team play (usually computer)
		// Pick home team play (usually ask user)
		// Compute outcome and update state (including clock)
	}

}

