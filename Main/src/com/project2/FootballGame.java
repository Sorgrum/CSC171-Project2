package com.project2;
/**
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

	protected FootballTeam homeTeam, awayTeam;
	protected FootballTeam firstReceiver, firstKicker;


	protected int homeScore;
	protected int awayScore;

	protected FootballTeam leftTeam, rightTeam;

	Scanner input = new Scanner(System.in);
	FootballField field = new FootballField();
	FootballPlays plays = new FootballPlays(homeTeam, awayTeam, field);
	ComputerLogic computer = new ComputerLogic();





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
		field.setTimePerQuarter(quarterLength);


		System.out.println("Coin toss to decide who goes first. Heads or Tails?");
		String coinFace = input.nextLine();

		// Did the user win the coin toss
		if (coinFace.equalsIgnoreCase(ChanceCalc.coinToss())) {

			System.out.println("It's " + coinFace + ", \n[kick/receive] Do you want to kick or receive?");
			String kickReceivePreference = input.nextLine();

			if (kickReceivePreference.equalsIgnoreCase("kick")) {
				setFirstReceiver(awayTeam);
			} else if (kickReceivePreference.equalsIgnoreCase("receive")) {
				setFirstReceiver(homeTeam);
			} else {
				System.out.println("Invalid input.");
			}

		} else {
			System.out.println("You didn't win the coin toss");
			if (computer.wantsToReceive()) {
				System.out.println("The " + awayTeam.getName() + " have decided to receive the ball");

				// The kick will go from the home team to the away team
				setFirstReceiver(awayTeam);
			} else {
				System.out.println("The " + awayTeam.getName() + " have decided to kick the ball");
				setFirstReceiver(homeTeam);
			}
		}

		for (int i = 1; i <= 4; i++) {

			// Whoever chooses to receive first does so in the first and third quarters because during the second and
			// fourth the team that received first now kicks
			if (i == 1 || i == 3) {
				plays.initialKickoff(field, getFirstKicker(), getFirstReceiver());
			} else {
				plays.initialKickoff(field, getFirstReceiver(), getFirstKicker());
			}
			playQuarter(i);
		}
		reportWinner();
	}

	/**
	 * Play the given quarter of this Football Game.
	 * Quarters last 15 minutes and end after the play during which the time
	 * runs out.
	 */
	protected void playQuarter(int quarterNumber) {
		while (field.getSecondsRemaining() > 0) {
			playAPlay();
			field.updateState();
		}
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


	public void setFirstReceiver(FootballTeam firstReceiver) {
		if (firstReceiver.equals(homeTeam)) {
			firstKicker = awayTeam;
		} else {
			firstKicker = homeTeam;
			setFirstKicker(firstKicker);
		}

		this.firstReceiver = firstReceiver;
	}

	public FootballTeam getFirstReceiver() {
		return firstReceiver;
	}

	public void setFirstKicker(FootballTeam firstKicker) {
		this.firstKicker = firstKicker;
	}

	public FootballTeam getFirstKicker() {
		return firstKicker;
	}
}

