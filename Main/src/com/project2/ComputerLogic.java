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

	public Boolean wantsToRecieve() {
		// Give the computer a 75% chance to want to receive, because it's normally beneficial to want to receive first
		return ChanceCalc.outCome(75);
	}

}
