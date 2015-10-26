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
    protected int currentDown;
    protected int currentQuarter;

    protected int quarterLength;
	protected int secondsRemaining;

    protected FootballTeam offensiveTeam;
    protected FootballTeam defensiveTeam;
    protected FootballTeam homeTeam;
    protected FootballTeam awayTeam;

    protected String defensivePlay;
    protected String offensivePlay;

    protected FootballPlays plays;

	public void setLineOfScrimmage(int lineOfScrimmage) {

        // The first down line should be computed whenever there is a first down.
        if (getCurrentDown() == 1) {
            if (getCurrentDown() == 1) {
                if (offensiveTeam.equals(homeTeam)) {
                    setFirstDownLine(lineOfScrimmage + 10);
                } else {
                    setFirstDownLine(lineOfScrimmage - 10);
                }
            }
        }

        // Since there are no safeties, don't let the teams go into their end zones
        if (offensiveTeam.equals(homeTeam) && lineOfScrimmage <= 0) {
            lineOfScrimmage = 1;
        } else if (offensiveTeam.equals(awayTeam) && lineOfScrimmage >= 100) {
            lineOfScrimmage = 99;
        } else {
            this.lineOfScrimmage = lineOfScrimmage;
        }
        if (offensiveTeam.equals(homeTeam) && lineOfScrimmage >= 100) {
            scoreTouchDown(homeTeam);
        } else if (offensiveTeam.equals(awayTeam) && lineOfScrimmage <= 0) {
            scoreTouchDown(awayTeam);
        }
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

    public int getFirstDownLineDistance() {
        if (offensiveTeam.equals(homeTeam)) {
            return (getFirstDownLine() - getLineOfScrimmage());
        } else {
            return (Math.abs(getFirstDownLine() - getLineOfScrimmage()));
        }
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

    protected void considerPlayTime() {
        secondsRemaining -= ChanceCalc.randomNumber(20, 40);
    }

    public void setSecondsRemaining(int secondsRemaining) {
        this.secondsRemaining = secondsRemaining;
    }

    public int getSecondsRemaining() {
		return secondsRemaining;
	}

    public int getQuarterLength() {
        return quarterLength;
    }

    public void setQuarterLength(int quarterLength) {
        this.quarterLength = quarterLength;
        setTimePerQuarter();
    }

    /**
	 * Set the amount of seconds in each quarter
	 */
	public void setTimePerQuarter() {

		// Convert the quarter length from minutes to seconds
		secondsRemaining = getQuarterLength() * 60;
        setSecondsRemaining(secondsRemaining);
    }

    public String getSecondsRemainingFormatted() {
        int minutes = secondsRemaining / 60;
        int seconds = secondsRemaining % 60;

        return (minutes + ":" + (seconds < 10 ? "0" : "") + seconds);
    }
	public void updateState() {

	}

    protected  void turnoverOnDowns() {
        if (offensiveTeam.equals(homeTeam)) {
            homeTeam.setIsOffense(false);
            awayTeam.setIsOffense(true);
            System.out.println();
            System.out.println("You are on the defense.");
            System.out.println();
        } else {
            awayTeam.setIsOffense(true);
            homeTeam.setIsOffense(false);
            System.out.println();
            System.out.println("You are on offense.");
            System.out.println();
        }
        setCurrentDown(1);
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

    public String getCurrentDownAsText() {
        switch (currentDown) {
            case 1:
                return "first";
            case 2:
                return "second";
            case 3:
                return "third";
            case 4:
                return "fourth";
            default:
                return "Field Error: Invalid down number.";
        }
    }
    public String getCurrentDownAsTextCapitalized() {
        switch (currentDown) {
            case 1:
                return "First";
            case 2:
                return "Second";
            case 3:
                return "Third";
            case 4:
                return "Fourth";
            default:
                return "Field Error: Invalid down number.";
        }
    }
    public void setCurrentDown(int currentDown) {
        if (currentDown == 5) {
            turnoverOnDowns();
        }
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

    public void setCurrentQuarter(int currentQuarter) {
        this.currentQuarter = currentQuarter;
    }

    public int getCurrentQuarter() {
        return currentQuarter;
    }

    public String getCurrentQuarterAsText() {
        switch (currentQuarter) {
            case 1:
                return "first";
            case 2:
                return "second";
            case 3:
                return "third";
            case 4:
                return "fourth";
            default:
                return "Field Error: Invalid quarter number.";
        }
    }

    public String getCurrentQuarterWithOrdinal() {
        switch (currentQuarter) {
            case 1:
                return "1st";
            case 2:
                return "2nd";
            case 3:
                return "3rd";
            case 4:
                return "4th";
            default:
                return "Field Error: Invalid quarter number.";
        }
    }

    public void scoreTouchDown(FootballTeam scoringTeam) {
        scoringTeam.changeScore(6);

        System.out.println("Touchdown! +6 points. Attempting extra point.");
        if (ChanceCalc.outCome(90)) {
            System.out.println("Extra point successful! +1 point.");
            scoringTeam.changeScore(1);
        }
    }

    ///////////////////////
    //
    //      PLAYS
    //
    //////////////////////////////////////////////

    protected void setCurrentDefensivePlay(String defensivePlay) {
        if (offensiveTeam.equals(awayTeam)) {
            if (defensivePlay.toLowerCase().startsWith("r")) {
                defensivePlay = "RUN_DEFENSE";
            } else if (defensivePlay.toLowerCase().startsWith("p")) {
                defensivePlay = "PASS_DEFENSE";
            } else if (defensivePlay.toLowerCase().startsWith("b")) {
                defensivePlay = "BLITZ_DEFENSE";
            } else {
                defensivePlay = "KICK_RETURN_DEFENSE";
            }
        }
        this.defensivePlay = defensivePlay;
    }

    protected void setCurrentOffensivePlay(String offensivePlay) {
        if (offensiveTeam.equals(homeTeam)) {
            if (offensivePlay.toLowerCase().startsWith("r")) {
                offensivePlay = "RUN_OFFENSE";
            } else if (offensivePlay.toLowerCase().startsWith("pa")) {
                offensivePlay = "PASS_OFFENSE";
            } else if (offensivePlay.toLowerCase().startsWith("pu")) {
                offensivePlay = "PUNT_OFFENSE";
            } else {
                offensivePlay = "FIELD_GOAL_OFFENSE";
            }
        }
        this.offensivePlay = offensivePlay;
    }

    protected String getCurrentDefensivePlay() { return defensivePlay; }
    protected String getCurrentOffensivePlay() { return offensivePlay; }

    protected void computePlayOutcome() {
        if (offensivePlay.equals("RUN_OFFENSE")) {
            if (defensivePlay.equals("RUN_DEFENSE")) {
                // Defense wins this play.
                int distance = ChanceCalc.randomNumber(-3, 3);

                // 10% chance to fumble
                if (ChanceCalc.outCome(10)) {
                    distance = ChanceCalc.randomNumber(-5, 0);
                    turnoverOnDowns();
                }
                setLineOfScrimmage(distance);
            } else if (defensivePlay.equals("PASS_DEFENSE")) {
                // Offense wins this play
                int distance = ChanceCalc.randomNumber(2, 14);

                // 5% chance to fumble
                if (ChanceCalc.outCome(5)) {
                    distance = ChanceCalc.randomNumber(-5, 0);
                    turnoverOnDowns();
                }
                setLineOfScrimmage(distance);
            } else if (defensivePlay.equals("BLITZ_DEFENSE")) {
                // Offense wins this play
                int distance = ChanceCalc.randomNumber(5, 15);

                // 5% chance to fumble
                if (ChanceCalc.outCome(5)) {
                    distance = ChanceCalc.randomNumber(-5, 0);
                    turnoverOnDowns();
                }
                setLineOfScrimmage(distance);
            } else {
                double success =  1/(Math.pow(1.5, getFirstDownLineDistance()));
                if (ChanceCalc.outCome(success)) {
                    int distance = ChanceCalc.randomNumber(getFirstDownLineDistance(), getFirstDownLineDistance() + 5);
                }
                System.out.println(success);

            }
        } else if (offensivePlay.equals("PASS_OFFENSE")) {
            if (defensivePlay.equals("RUN_DEFENSE")) {
            } else if (defensivePlay.equals("PASS_DEFENSE")) {
            } else if (defensivePlay.equals("BLITZ_DEFENSE")) {
            } else {
            }
        } else if (offensivePlay.equals("PUNT_OFFENSE")) {
            if (defensivePlay.equals("RUN_DEFENSE")) {
            } else if (defensivePlay.equals("PASS_DEFENSE")) {
            } else if (defensivePlay.equals("BLITZ_DEFENSE")) {
            } else {
            }
        } else {
            if (defensivePlay.equals("RUN_DEFENSE")) {
            } else if (defensivePlay.equals("PASS_DEFENSE")) {

            } else if (defensivePlay.equals("BLITZ_DEFENSE")) {
            } else {

            }
        }
        setCurrentDown(getCurrentDown() + 1);
    }


    // Constructor
    public FootballField(FootballTeam homeTeam, FootballTeam awayTeam, FootballPlays plays) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.plays = plays;
    }
}
