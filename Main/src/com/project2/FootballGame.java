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

    protected Scanner input;
    protected FootballField field;
    protected FootballPlays plays;
    protected ComputerLogic computer;
	/**
	 * Construct and return a new com.project2.FootballGame.
	 * Start the game with the start() method.
	 */
	public FootballGame(FootballTeam homeTeam, FootballTeam awayTeam) {
        input = new Scanner(System.in);
        field = new FootballField(homeTeam, awayTeam, plays);
        plays = new FootballPlays(homeTeam, awayTeam, field);
        computer = new ComputerLogic();

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


		System.out.print("[1-60] How long should each game quarter be (in minutes)? ");
        while (!input.hasNextInt()) {
            input.next(); // Read and discard offending non-int input
            System.out.print("[1-60] Please enter a quarter length in minutes: "); // Re-prompt
        }

        int quarterLength = input.nextInt();

        // Force the user input to actually be what we are expecting.
        if (quarterLength < 1) {
            quarterLength = 1;
        } else if (quarterLength > 60) {
            quarterLength = 60;
        }

        field.setQuarterLength(quarterLength);
        // Add a nextLine to eat \n in buffer
        input.nextLine();

		System.out.print("[heads/tails] Coin toss to decide who goes first. Heads or Tails? ");
		String coinFace = input.nextLine();

		// Did the user win the coin toss
		if (coinFace.toLowerCase().startsWith(ChanceCalc.coinToss())) {

			System.out.println("You won the coin toss.");
            System.out.println();
            System.out.print("[kick/receive] Do you want to kick or receive? ");
			String kickReceivePreference = input.nextLine();

            if (kickReceivePreference.toLowerCase().startsWith("k")) {
				setFirstReceiver(awayTeam);
                setFirstKicker(homeTeam);
                setIsOffense(awayTeam);
            } else if (kickReceivePreference.toLowerCase().startsWith("r")) {
				setFirstReceiver(homeTeam);
                setFirstKicker(awayTeam);
                setIsOffense(homeTeam);
            } else {
				System.out.println("Invalid input.");
			}

		} else {
			System.out.println("You didn't win the coin toss");
            System.out.println();
            if (computer.wantsToReceive()) {
				System.out.println("The " + awayTeam.getName() + " have decided to receive the ball");

				// The kick will go from the home team to the away team
				setFirstReceiver(awayTeam);
                setFirstKicker(homeTeam);

                setIsOffense(awayTeam);
			} else {
				System.out.println("The " + awayTeam.getName() + " have decided to kick the ball");
				setFirstReceiver(homeTeam);
                setFirstKicker(awayTeam);

                setIsOffense(homeTeam);
			}
		}


		/*for (int i = 1; i <= 4; i++) {

			// Whoever chooses to receive first does so in the first and third quarters because during the second and
			// fourth the team that received first now kicks
			if (i == 1 || i == 3) {
				plays.initialKickoff(field, getFirstKicker(), getFirstReceiver());
			} else {
				plays.initialKickoff(field, getFirstReceiver(), getFirstKicker());
			}
			// This will set the number of downs to 1 and let the player know if they are on offense or defense
			turnoverOnDowns();
			playQuarter(i);
		}*/

        //////////////////////////////////////////////////////////////////////////
        // FOR TESTING ONLY                                                     //
            plays.initialKickoff(field, getFirstKicker(), getFirstReceiver());  //
            playQuarter(1);                                                     //
        //////////////////////////////////////////////////////////////////////////
		reportWinner();
	}

	/**
	 * Play the given quarter of this Football Game.
	 * Quarters last 15 minutes and end after the play during which the time
	 * runs out.
	 */
	protected void playQuarter(int quarterNumber) {
        field.setCurrentQuarter(quarterNumber);
        while (field.getSecondsRemaining() > 0) {
            playAPlay();
            field.updateState();
        }
	}

    /**
     * This method calls other methods to set the team to be offensive properly, and set the other team to not be on
     * the offense and to update the field with this info.
     * @param offensiveTeam
     */
    protected void setIsOffense(FootballTeam offensiveTeam) {
        if (offensiveTeam.equals(homeTeam)) {
            field.setOffensiveTeam(homeTeam);
            homeTeam.setIsOffense(true);
            awayTeam.setIsOffense(false);
        } else {
            field.setOffensiveTeam(awayTeam);
            homeTeam.setIsOffense(false);
            awayTeam.setIsOffense(true);
        }
    }

	/**
	 * Report the winner of this com.project2.FootballGame, assuming it is over.
	 */
	protected void reportWinner() {
        System.out.println();
    }

	protected void playAPlay() {
        field.updateState();
        field.considerPlayTime();
        System.out.println(field.getSecondsRemainingFormatted() + " remaining in the " + field
                .getCurrentQuarterAsText() + " quarter.");
        System.out.println("The ball is on the " + field.getLineOfScrimmage() + " yard line. " + field
                .getCurrentDownAsTextCapitalized() + " down and " + field.getFirstDownLineDistance() + " to go.");
        if (homeTeam.getIsOffense()) {
            System.out.print("[run/pass/punt/fg] " + homeTeam.getName() + " offensive play: ");
            field.setCurrentOffensivePlay(input.nextLine());
            field.setCurrentDefensivePlay(computer.choosePlay("DEFENSIVE", field));
        } else {
            System.out.print("[run/pass/blitz/kick] " + homeTeam.getName() + " defensive play: ");
            field.setCurrentDefensivePlay(input.nextLine());
            field.setCurrentOffensivePlay(computer.choosePlay("OFFENSIVE", field));
            System.out.println();
        }
        field.computePlayOutcome();
        // TODO
		// Pick away team play (usually computer)
		// Pick home team play (usually ask user)
		// Compute outcome and update state (including clock)

	}


	public void setFirstReceiver(FootballTeam firstReceiver) {
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

