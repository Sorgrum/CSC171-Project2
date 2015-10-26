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
    protected int firstDownLineDistance;
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
                    if (lineOfScrimmage + 10 >= 100) {
                        setFirstDownLine(100);
                    } else {
                        setFirstDownLine(lineOfScrimmage + 10);
                    }
                } else {
                    if (lineOfScrimmage - 10 <= 0) {
                        setFirstDownLine(0);
                    } else {
                        setFirstDownLine(lineOfScrimmage - 10);
                    }
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

    public void changeLineOfScrimmage(int offset) {
        if (offensiveTeam.equals(homeTeam)) {

            // Don't let them get pushed to their endzone
            if (getLineOfScrimmage() + offset < 1) {
                setLineOfScrimmage(1);
            }

            if (getFirstDownLineDistance() <= 0) {
                setCurrentDown(1);
                printFirstDownHappened();
            }
            setLineOfScrimmage(getLineOfScrimmage() + offset);
        } else {
            if (getLineOfScrimmage() - offset > 99) {
                setCurrentDown(99);
            }
            if (getFirstDownLineDistance() <= 0) {
                setCurrentDown(1);
                printFirstDownHappened();
            }
            setLineOfScrimmage(getLineOfScrimmage() - offset);
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
        return firstDownLineDistance;
    }

    public void setFirstDownLineDistance() {
        if (offensiveTeam.equals(homeTeam)) {
            this.firstDownLineDistance = (getFirstDownLine() - getLineOfScrimmage());
        } else {
            this.firstDownLineDistance = Math.abs((100 - getLineOfScrimmage()) - (100 - getFirstDownLine()));
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
        setFirstDownLineDistance();
    }

    protected void turnoverOnDowns() {
        if (offensiveTeam.equals(homeTeam)) {
            homeTeam.setIsOffense(false);
            awayTeam.setIsOffense(true);
            setOffensiveTeam(awayTeam);
            System.out.println();
            System.out.println("You are on the defense.");
            System.out.println();
            if (lineOfScrimmage - 10 <= 0) {
                setFirstDownLine(0);
            } else {
                setFirstDownLine(lineOfScrimmage - 10);
            }


        } else if (offensiveTeam.equals(awayTeam)) {
            homeTeam.setIsOffense(true);
            awayTeam.setIsOffense(false);
            setOffensiveTeam(homeTeam);
            System.out.println();
            System.out.println("You are on offense.");
            System.out.println();
            if (lineOfScrimmage + 10 >= 100) {
                setFirstDownLine(100);
            } else {
                setFirstDownLine(lineOfScrimmage + 10);
            }
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

    public void changeCurrentDown(int change) {
        if (getCurrentDown() + change >= 5) {
            turnoverOnDowns();
        } else {
            setCurrentDown(getCurrentDown() + change);
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

    public void scoreFieldGoal(FootballTeam scoringTeam) {
        scoringTeam.changeScore(3);
        System.out.println("The field goal succeeded! +3 points.");

        if (scoringTeam.equals(homeTeam)) {
            plays.initialKickoff(this, homeTeam, awayTeam);
        } else {
            plays.initialKickoff(this, homeTeam, awayTeam);
        }
    }

    public void missedFieldGoal(FootballTeam missedTeam) {
        if (missedTeam.equals(homeTeam)) {
            System.out.println("The " + homeTeam.getName() + " missed their field goal!");
            setLineOfScrimmage(80);
            turnoverOnDowns();
        } else {
            System.out.println("The " + awayTeam.getName() + " missed their field goal!");
            setLineOfScrimmage(20);
            turnoverOnDowns();
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

    protected String getCurrentDefensivePlay() {
        return defensivePlay;
    }

    protected String getCurrentOffensivePlay() {
        return offensivePlay;
    }

    protected void computePlayOutcome() {
        if (offensivePlay.equals("RUN_OFFENSE")) {
            if (offensiveTeam.equals(awayTeam)) {
                System.out.println("The " + awayTeam.getName() + " chose to run the ball");
            }
            if (defensivePlay.equals("RUN_DEFENSE")) {
                if (offensiveTeam.equals(homeTeam)) {
                    System.out.println("The " + awayTeam.getName() + " chose to defend against a run");
                }
                // Defense wins this play.
                int distance = ChanceCalc.randomNumber(-3, 3);

                // 10% chance to fumble
                if (ChanceCalc.outCome(10)) {
                    distance = ChanceCalc.randomNumber(-5, 0);
                    printFumbleHappened();
                    changeLineOfScrimmage(distance);
                    turnoverOnDowns();
                } else {
                    changeLineOfScrimmage(distance);
                    printAmountOfYardsGained(distance);
                }

            } else if (defensivePlay.equals("PASS_DEFENSE")) {
                if (offensiveTeam.equals(homeTeam)) {
                    System.out.println("The " + awayTeam.getName() + " chose to defend against a pass");
                }
                // Offense wins this play
                int distance = ChanceCalc.randomNumber(2, 14);

                // 5% chance to fumble
                if (ChanceCalc.outCome(5)) {
                    distance = ChanceCalc.randomNumber(-5, 0);
                    changeLineOfScrimmage(distance);
                    printFumbleHappened();
                    turnoverOnDowns();
                } else {
                    changeLineOfScrimmage(distance);
                    printAmountOfYardsGained(distance);
                }

            } else if (defensivePlay.equals("BLITZ_DEFENSE")) {
                if (offensiveTeam.equals(homeTeam)) {
                    System.out.println("The " + awayTeam.getName() + " chose to defend against a blitz");
                }
                // Offense wins this play
                int distance = ChanceCalc.randomNumber(5, 15);

                // 5% chance to fumble
                if (ChanceCalc.outCome(5)) {
                    distance = ChanceCalc.randomNumber(-5, 0);
                    printFumbleHappened();
                    changeLineOfScrimmage(distance);
                    turnoverOnDowns();
                } else {
                    changeLineOfScrimmage(distance);
                    printAmountOfYardsGained(distance);
                }

            } else {
                if (offensiveTeam.equals(homeTeam)) {
                    System.out.println("The " + awayTeam.getName() + " chose to defend against a field goal");
                }
                if (getCurrentDown() == 4) {
                    double success = 1 / (Math.pow(1.5, getFirstDownLineDistance()));
                    if (ChanceCalc.outCome(success)) {

                        int distance = ChanceCalc.randomNumber(getFirstDownLineDistance(), getFirstDownLineDistance() + 5);
                        changeLineOfScrimmage(distance);
                        printAmountOfYardsGained(distance);

                    }
                } else {
                    int distance = ChanceCalc.randomNumber(0, 15);
                    changeLineOfScrimmage(distance);
                    printAmountOfYardsGained(distance);
                }
            }
        } else if (offensivePlay.equals("PASS_OFFENSE")) {
            if (offensiveTeam.equals(awayTeam)) {
                System.out.println("The " + awayTeam.getName() + " chose to pass the ball");
            }
            if (defensivePlay.equals("RUN_DEFENSE")) {
                if (offensiveTeam.equals(homeTeam)) {
                    System.out.println("The " + awayTeam.getName() + " chose to defend against a run");
                }

                // Offense wins
                // Check to see if the pass completes, if not see if the ball was intercepted, if not, the pass failed
                if (ChanceCalc.outCome(80)) {
                    int distance = ChanceCalc.randomNumber(5, 20);
                    changeLineOfScrimmage(distance);
                    printAmountOfYardsGained(distance);
                } else if (ChanceCalc.outCome(5)) {
                    int distance = ChanceCalc.randomNumber(-10, 0);
                    changeLineOfScrimmage(distance);
                    printInterceptionHappened();
                    printAmountOfYardsGained(distance);
                    turnoverOnDowns();
                } else {
                    // Redundant but here for clarity's sake
                    int distance = 0;
                    printAmountOfYardsGained(distance);
                    changeLineOfScrimmage(distance);
                }
            } else if (defensivePlay.equals("PASS_DEFENSE")) {
                if (offensiveTeam.equals(homeTeam)) {
                    System.out.println("The " + awayTeam.getName() + " chose to defend against a pass");
                }
                // Defense wins
                if (ChanceCalc.outCome(30)) {
                    int distance = ChanceCalc.randomNumber(3, 10);
                    changeLineOfScrimmage(distance);
                    printAmountOfYardsGained(distance);
                } else if (ChanceCalc.outCome(10)) {
                    int distance = ChanceCalc.randomNumber(-40, 0);
                    changeLineOfScrimmage(distance);
                    printAmountOfYardsGained(distance);
                    printInterceptionHappened();
                    turnoverOnDowns();
                } else {
                    // Redundant but here for clarity's sake
                    int distance = 0;
                    changeLineOfScrimmage(distance);
                }
            } else if (defensivePlay.equals("BLITZ_DEFENSE")) {
                if (offensiveTeam.equals(homeTeam)) {
                    System.out.println("The " + awayTeam.getName() + " chose to defend against a pass");
                }
                if (ChanceCalc.outCome(50)) {
                    int distance = ChanceCalc.randomNumber(10, 30);
                    changeLineOfScrimmage(distance);
                    printAmountOfYardsGained(distance);
                } else if (ChanceCalc.outCome(20)) {
                    int distance = ChanceCalc.randomNumber(-15, -10);
                    printInterceptionHappened();
                    printAmountOfYardsGained(distance);
                    turnoverOnDowns();
                    changeLineOfScrimmage(distance);
                }
            } else {
                if (offensiveTeam.equals(homeTeam)) {
                    System.out.println("The " + awayTeam.getName() + " chose to defend against a field goal");
                }
                if (getCurrentDown() == 4) {
                    double success = 1 / (Math.pow(1.5, getFirstDownLineDistance()));
                    if (ChanceCalc.outCome(success)) {

                        int distance = ChanceCalc.randomNumber(getFirstDownLineDistance(), getFirstDownLineDistance()
                                + 10);
                        changeLineOfScrimmage(distance);

                    }
                }

                // If it isn't the fourth down
                int distance = ChanceCalc.randomNumber(0, 40);
                changeLineOfScrimmage(distance);
            }
        } else if (offensivePlay.equals("PUNT_OFFENSE")) {
            if (offensiveTeam.equals(awayTeam)) {
                System.out.println("The " + awayTeam.getName() + " chose to punt the ball");
            }
            // The outcome is the same for a run defense and pass defense
            if (defensivePlay.equals("RUN_DEFENSE")) {
                if (offensiveTeam.equals(homeTeam)) {
                    System.out.println("The " + awayTeam.getName() + " chose to defend against a run");
                }
                // Check if it is a touchback first
                int distance = ChanceCalc.randomNumber(40, 60);

                if (offensiveTeam.equals(homeTeam)) {
                    if (getLineOfScrimmage() + distance >= 100) {
                        // It's a touchback
                        printTouchbackHappened();
                        setLineOfScrimmage(80);
                    }
                } else {
                    if (getLineOfScrimmage() - distance <= 0) {
                        // It's a touchback
                        printTouchbackHappened();
                        setLineOfScrimmage(20);
                    }
                }

                changeLineOfScrimmage(distance);
                turnoverOnDowns();
            } else if (defensivePlay.equals("PASS_DEFENSE")) {
                if (offensiveTeam.equals(homeTeam)) {
                    System.out.println("The " + awayTeam.getName() + " chose to defend against a pass");
                }
                // Check if it is a touchback first
                int distance = ChanceCalc.randomNumber(40, 60);

                if (offensiveTeam.equals(homeTeam)) {
                    if (getLineOfScrimmage() + distance >= 100) {
                        // It's a touchback
                        printTouchbackHappened();
                        setLineOfScrimmage(80);
                    }
                } else {
                    if (getLineOfScrimmage() - distance <= 0) {
                        // It's a touchback
                        printTouchbackHappened();
                        setLineOfScrimmage(20);
                    }
                }

                changeLineOfScrimmage(distance);
                turnoverOnDowns();
            } else if (defensivePlay.equals("BLITZ_DEFENSE")) {
                if (offensiveTeam.equals(homeTeam)) {
                    System.out.println("The " + awayTeam.getName() + " chose to defend against a blitz");
                }
                int distance = ChanceCalc.randomNumber(30, 50);
                int returnDistance = ChanceCalc.randomNumber(0, 10);
                if (ChanceCalc.outCome(20)) {
                    printBlockHappened();
                    changeLineOfScrimmage(ChanceCalc.randomNumber(-20, -10));
                    turnoverOnDowns();
                } else {
                    if (offensiveTeam.equals(homeTeam)) {
                        if (getLineOfScrimmage() + distance >= 100) {
                            // It's a touchback
                            printTouchbackHappened();
                            setLineOfScrimmage(80);
                        } else {
                            printAmountOfYardsGained(distance - returnDistance);
                            changeLineOfScrimmage(distance - returnDistance);
                        }
                    } else {
                        if (getLineOfScrimmage() - distance <= 0) {
                            // It's a touchback
                            printTouchbackHappened();
                            setLineOfScrimmage(20);
                        } else {
                            printAmountOfYardsGained(distance - returnDistance);
                            changeLineOfScrimmage(distance - returnDistance);
                        }
                    }


                }
            } else {
                if (offensiveTeam.equals(homeTeam)) {
                    System.out.println("The " + awayTeam.getName() + " chose to defend against a field goal");
                }
                if (ChanceCalc.outCome(5)) {
                    int distance = ChanceCalc.randomNumber(-20, -10);
                    printBlockHappened();
                    changeLineOfScrimmage(distance);
                    turnoverOnDowns();
                } else {
                    int distance = ChanceCalc.randomNumber(40, 60);
                    int returnDistance = ChanceCalc.randomNumber(10, 25);

                    if (offensiveTeam.equals(homeTeam)) {
                        if (getLineOfScrimmage() + distance >= 100) {
                            // It's a touchback
                            printTouchbackHappened();
                            setLineOfScrimmage(80);
                        } else {
                            printAmountOfYardsGained(distance - returnDistance);
                            changeLineOfScrimmage(distance - returnDistance);
                        }
                    } else {
                        if (getLineOfScrimmage() - distance <= 0) {
                            // It's a touchback
                            printTouchbackHappened();
                            setLineOfScrimmage(20);
                        } else {
                            printAmountOfYardsGained(distance - returnDistance);
                            changeLineOfScrimmage(distance - returnDistance);
                        }
                    }

                }
            }
        } else {
            if (offensiveTeam.equals(awayTeam)) {
                System.out.println("The " + awayTeam.getName() + " attempted a field goal.");
            }
            if (defensivePlay.equals("RUN_DEFENSE")) {
                if (offensiveTeam.equals(homeTeam)) {
                    if (offensiveTeam.equals(homeTeam)) {
                        System.out.println("The " + awayTeam.getName() + " chose to defend against a run");
                    }
                    if (ChanceCalc.outCome(100 - 1.7 * (110 - getLineOfScrimmage()))) {
                        scoreFieldGoal(offensiveTeam);
                    } else {
                        missedFieldGoal(offensiveTeam);
                    }
                } else {
                    if (ChanceCalc.outCome(100 - 1.7 * (getLineOfScrimmage() + 10))) {
                        scoreFieldGoal(offensiveTeam);
                    } else {
                        missedFieldGoal(offensiveTeam);
                    }
                }
            } else if (defensivePlay.equals("PASS_DEFENSE")) {
                if (offensiveTeam.equals(homeTeam)) {
                    if (offensiveTeam.equals(homeTeam)) {
                        System.out.println("The " + awayTeam.getName() + " chose to defend against a pass");
                    }
                    if (ChanceCalc.outCome(100 - 1.7 * (110 - getLineOfScrimmage()))) {
                        scoreFieldGoal(offensiveTeam);
                    } else {
                        missedFieldGoal(offensiveTeam);
                    }
                } else {
                    if (ChanceCalc.outCome(100 - 1.7 * (getLineOfScrimmage() + 10))) {
                        scoreFieldGoal(offensiveTeam);
                    } else {
                        missedFieldGoal(offensiveTeam);
                    }
                }
            } else if (defensivePlay.equals("BLITZ_DEFENSE")) {
                if (offensiveTeam.equals(homeTeam)) {
                    System.out.println("The " + awayTeam.getName() + " chose to defend against a blitz");
                }
                if (offensiveTeam.equals(homeTeam)) {
                    if (ChanceCalc.outCome(100 - 1.7 * (110 - getLineOfScrimmage()))) {
                        scoreFieldGoal(offensiveTeam);
                    } else if (ChanceCalc.outCome(20)) {
                        printBlockHappened();
                        turnoverOnDowns();
                    }
                } else {
                    if (ChanceCalc.outCome(100 - 1.7 * (getLineOfScrimmage() + 10))) {
                        scoreFieldGoal(offensiveTeam);
                    } else if (ChanceCalc.outCome(20)) {
                        printBlockHappened();
                        turnoverOnDowns();
                    }
                }
            } else {
                if (offensiveTeam.equals(homeTeam)) {
                    System.out.println("The " + awayTeam.getName() + " chose to defend against a field goal");
                }
                if (offensiveTeam.equals(homeTeam)) {
                    if (ChanceCalc.outCome(100 - 1.7 * (110 - getLineOfScrimmage()))) {
                        scoreFieldGoal(offensiveTeam);
                    } else if (ChanceCalc.outCome(5)) {
                        int distance = ChanceCalc.randomNumber(-15, -8);
                        printAmountOfYardsGained(distance);
                        printBlockHappened();
                        changeLineOfScrimmage(distance);
                        turnoverOnDowns();
                    } else {
                        int distanceFromGoal = (110 - getLineOfScrimmage());
                        int distance = ChanceCalc.randomNumber(distanceFromGoal / 2, distanceFromGoal - 1);
                        int returnDistance = ChanceCalc.randomNumber(10, 20);
                        printAmountOfYardsGained(distance - returnDistance);
                        changeLineOfScrimmage(distance - returnDistance);
                    }
                } else {
                    if (ChanceCalc.outCome(100 - 1.7 * (getLineOfScrimmage() + 10))) {
                        scoreFieldGoal(offensiveTeam);
                    } else if (ChanceCalc.outCome(5)) {
                        int distance = ChanceCalc.randomNumber(-15, -8);
                        printAmountOfYardsGained(distance);
                        printBlockHappened();
                        changeLineOfScrimmage(distance);
                        turnoverOnDowns();
                    } else {
                        int distanceFromGoal = (getLineOfScrimmage() + 10);
                        int distance = ChanceCalc.randomNumber(distanceFromGoal / 2, distanceFromGoal - 1);
                        int returnDistance = ChanceCalc.randomNumber(10, 20);
                        printAmountOfYardsGained(distance - returnDistance);
                        changeLineOfScrimmage(distance - returnDistance);
                    }
                }
            }
        }
        changeCurrentDown(1);
    }


    public void printAmountOfYardsGained(int distance) {
        if (offensiveTeam.equals(homeTeam)) {
            if (distance >= 1) {
                System.out.println("The " + homeTeam.getName() + " gained " + Math.abs(distance) + " yards");
            } else if (distance < 0) {
                System.out.println("The " + homeTeam.getName() + " lost " + Math.abs(distance) + " yards");
            }
        } else if (offensiveTeam.equals(awayTeam)) {
            if (distance >= 1) {
                System.out.println("The " + awayTeam.getName() + " gained " + Math.abs(distance) + " yards");
            } else if (distance < 0) {
                System.out.println("The " + awayTeam.getName() + " lost " + Math.abs(distance) + " yards");
            }
        }
        System.out.println();
    }

    /**
     * This lets the user know that a fumble occurred and which team is now on offense.
     * If a fumble as a result of a play, this print statement should appear before the turnover
     */
    public void printFumbleHappened() {
        if (offensiveTeam.equals(homeTeam)) {
            System.out.println("Fumble! " + awayTeam.getName() + " is now on defense");
        } else {
            System.out.println("Fumble! " + homeTeam.getName() + " is now on offense");
        }
    }

    public void printFirstDownHappened() {
        if (offensiveTeam.equals(homeTeam)) {
            System.out.println("The " + homeTeam.getName() + " reached a first down!");
        } else {
            System.out.println("The " + awayTeam.getName() + " reached a first down!");
        }
    }

    /**
     * Prints that a block happened.
     * This should appear before the turnover
     */
    public void printBlockHappened() {
        if (offensiveTeam.equals(homeTeam)) {
            System.out.println("The " + awayTeam.getName() + " blocked your play!");
        } else {
            System.out.println("The " + homeTeam.getName() + " blocked the " + awayTeam.getName() + "'s play!");
        }
    }

    /**
     * Prints that an interception happened
     * This should appear before the turnover
     */
    public void printInterceptionHappened() {
        if (offensiveTeam.equals(homeTeam)) {
            System.out.println("The " + awayTeam.getName() + " intercepted the ball!");
        } else {
            System.out.println("The " + homeTeam.getName() + " intercepted the " + awayTeam.getName() + "'s ball!");
        }
    }

    public void printTouchbackHappened() {
        if (offensiveTeam.equals(homeTeam)) {
            System.out.println("The " + homeTeam.getName() + " kicked the ball into the " + awayTeam.getName() + "'s " +
                    "endzone for a touchback!");

        } else {
            System.out.println("The " + awayTeam.getName() + " kicked the ball into the " + homeTeam.getName() + "'s " +
                    "endzone for a touchback!");
        }
    }
    // Constructor
    public FootballField(FootballTeam homeTeam, FootballTeam awayTeam, FootballPlays plays) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.plays = plays;
    }
}
