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
	protected int scorableFieldLength;

	protected int lineOfScrimmage;
	protected int firstDownLine;
    protected  int currentDown;

    protected int quarterLength;
	protected int secondsRemaining;

    protected FootballTeam offensiveTeam;
    protected FootballTeam defensiveTeam;

	public void setLineOfScrimmage(int lineOfScrimmage) {
        if (getCurrentDown() == 1) {
            setFirstDownLine(lineOfScrimmage + 10);
        }
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
		// The field consists of the 100 yards of actual field and 10 yards on either side for the end zones
		fieldLength = 120;
		return fieldLength;
	}

	public int getScorableFieldLength() {
		scorableFieldLength = 100;
		return scorableFieldLength;
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

    public int getCurrentDown() {
        return currentDown;
    }

    public String getCurrentDownWithOrdinal() {
        switch (currentDown) {
            case 1:
                return "1st";
            case 2:
                return "2nd";
            case 3:
                return "3rd";
            case 4:
                return "4th";
            default:
                return "Field Error: Invalid down number.";
        }
    }
    public void setCurrentDown(int currentDown) {
        this.currentDown = currentDown;
    }

    public FootballTeam getOffensiveTeam() {
        return offensiveTeam;
    }

    public void setOffensiveTeam(FootballTeam offensiveTeam) {
        this.offensiveTeam = offensiveTeam;
    }

    public FootballTeam getDefensiveTeam() {
        return defensiveTeam;
    }

    public void setDefensiveTeam(FootballTeam defensiveTeam) {
        this.defensiveTeam = defensiveTeam;
    }
}
