package com.project2;

import java.util.Random;

/**
 * Student Name: Marcelo Gheiler
 * Filename: com.project2.ChanceCalc
 * Date: 10/23/15
 * TA Name: Colin Pronovost
 * Assignment:
 * Lab Day: Monday
 * Lab Time: 5PM
 * Lab Location: CSB 703
 * I affirm that I have not given or received any unauthorized help on this assignment, and that this work is my own
 * <p>
 * This class returns a boolean based on a percentage after being given a chance for a certain event to happen. For
 * example, if there a 5% chance of an event happening, this program will generate a random number to see if the
 * event happens.
 */
public class ChanceCalc {

	public static Boolean outCome(double percentage) {
		Random rn = new Random();
		int randInt = rn.nextInt(100) + 1;

		// The event happened
		return randInt <= percentage;
	}

	public static String coinToss() {

		// Generate a random number between 0-1, so you have a 50/50 chance
		if (Math.random() >= 0.5) {
			return "h";
		} else {
			return "t";
		}
	}

	public static int randomNumber(int start, int end) {
		Random rn = new Random();
		return rn.nextInt(end - start) + start;
	}

}
