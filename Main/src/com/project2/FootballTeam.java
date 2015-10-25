package com.project2;

/**
 * Student Name: Marcelo Gheiler
 * Filename: FootballTeam
 * Date: 10/23/15
 * TA Name: Colin Pronovost
 * Assignment:
 * Lab Day: Monday
 * Lab Time: 5PM
 * Lab Location: CSB 703
 * I affirm that I have not given or received any unauthorized help on this assignment, and that this work is my own
 */
public class FootballTeam {


	/**
	 * The name of this FootballTeam.
	 */
	protected String name;
    protected Boolean isOffense;
    protected Boolean isDefense;
	/**
	 * Construct and return a new FootballTeam with the given name.
	 */
	public FootballTeam(String name) {
		this.name = name;
	}

	/**
	 * Return the name of this FootballTeam.
	 */
	public String getName() {
		return name;
	}

    public Boolean getIsOffense() {
        return isOffense;
    }

    public void setIsOffense(Boolean isOffense) {
        this.isOffense = isOffense;
    }
}
