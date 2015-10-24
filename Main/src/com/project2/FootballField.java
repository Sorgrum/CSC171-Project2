package com.project2;

/**
 * Student Name: Marcelo Gheiler
 * Filename: FootballField
 * Date: 10/23/15
 * TA Name: Colin Pronovost
 * Assignment:
 * Lab Day: Monday
 * Lab Time: 5PM
 * Lab Location: CSB 703
 * I affirm that I have not given or received any unauthorized help on this assignment, and that this work is my own
 */
public class FootballField {

	protected int fieldLength;
	protected int scorablefieldLength;

	protected int lineOfScrimmage;
	protected int firstDownLine;

	protected int quarterLength;
	protected int secondsRemaining;

	public void setLineOfScrimmage(int lineOfScrimmage) {
		this.lineOfScrimmage = lineOfScrimmage;
	}

	public void setFirstDownLine(int firstDownLine) {
		this.firstDownLine = firstDownLine;
	}

	public int getLineOfScrimmage() {
		return lineOfScrimmage;
	}

	public int getFirstDownLine() {
		return firstDownLine;
	}

	public int getFieldLength() {
		return fieldLength;
	}

	public int getScorablefieldLength() {
		return scorablefieldLength;
	}

	public int getSecondsRemaining() {
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
	 * Get the amount of seconds left in the quarter
	 */
	public int getTimeLeft() {
		return secondsRemaining;
	}

	public void updateState() {

	}
}
