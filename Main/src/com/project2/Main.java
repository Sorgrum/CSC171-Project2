package com.project2;

/*
*
* Student Name: Marcelo Gheiler
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

public class Main {

	public static void main(String[] args) {

		//////////////////////////////////////////////////////////////////////
		// VARIABLE TO USE FOR TESTING ONLY                                 //
		// WHEN THE PROGRAM IS COMPLETED THIS SECTION SHOULD BE REMOVED     //
		String homeTeamName = "Tomahawks";                                  //
		String awayTeamName = "Colts";                                      //
		//////////////////////////////////////////////////////////////////////

		Scanner input = new Scanner(System.in);
		/*
	    System.out.print("What is the name of your team: ");
	    String homeName = scanner.nextLine();

	    System.out.print("What is the name of my team: ");
	    String awayName = scanner.nextLine();
	    */

		FootballGame game = new FootballGame(new FootballTeam(homeTeamName), new FootballTeam(awayTeamName));
		game.startGame();

	}

}

