/** Program: Mile Stone 4
* File: OffensivePlayers.java
* Summary: Subclass of NFLPlayers - Contains stats that every Offensive NFL player shares.
* Author: Chris Hyde
* Date: November 26, 2017 
**/

import java.util.Random;

public class OffensivePlayer extends NFLPlayers{
	// No-Args Condtuctor
	public OffensivePlayer() {
		
	}
	
	//Constructor with Args
	public OffensivePlayer(String firstName, String lastName, String team,  int age, int weight, int heightFeet, int heightInches, int seasonsPlayed, int teamNumber, int proBowls, int gamesMissed, int positionIndex) {
		super(firstName, lastName, team,  age, weight, heightFeet, heightInches, seasonsPlayed, teamNumber, proBowls, gamesMissed);
		this.position = OFFENSIVE_POSITIONS[positionIndex]; // Set the position based on the index of Offensive Positions.
		fillOffensivePlayerStats();
	}
	
	// Class calls
	private Random rand = new Random();
	
	// Constants
	private static final String[] OFFENSIVE_POSITIONS= {"Quarterback", "Running Back", "Fullback", "Offensive Line", "Tight End", "Wide Receivers"};
	private static final String[] PASSING_STATS = {"RANK", "ATT", "COMP", "PCT", "YARDS", "AVG", "SACK", "INT", "TD"}; 
	private static final String[] RUSHING_STATS = {"RANK", "ATT", "LONG", "20+", "YARDS", "AVG", "FUM", "1DN","TD"}; 
	private static final String[] RECEIVING_STATS = {"RANK", "REC", "LONG", "YAC", "YARDS", "AVG", "FUM", "1DN", "TD"};
	
	// Variables
	private String position;
	private int[][][] offensivePlayerStats; // array of 2 double array
	
	@Override
	public String toString() {
		

		return getPosition() + ": " + formatPlayerInfo() + "\n" + formatOffensivePlayerStats() + "\n\n";
	}
	
	
	// Method to format offensivePlayerStats[][][] into a string
	public String formatOffensivePlayerStats() {
		String formatedStats = "";

		for (int i = 0; i < getOffensivePlayerStats().length; i++) {
			if (i == 0) {
				if (position == "Quarterback") {
					formatedStats += "PASSING:\n";
					formatedStats += String.format("%-6s,%-6s,%-6s,%-6s,%-6s,%-6s,%-6s,%-6s,%-6s,%-6s,%-6s", "SEASON", "GAMES", "RANK", "ATT", "COMP", "PCT", "YARDS", "AVG", "SACK", "INT", "TD").replaceAll("[,]", "|") +"|\n";
				} else {
					formatedStats += "RECEIVING:\n";
					formatedStats += String.format("%-6s,%-6s,%-6s,%-6s,%-6s,%-6s,%-6s,%-6s,%-6s,%-6s,%-6s", "SEASON", "GAMES", "RANK", "REC", "LONG", "YAC", "YARDS", "AVG", "FUM", "1DN", "TD").replaceAll("[,]", "|") +"|\n";
				}
			} else {
				formatedStats += "RUSHING:\n";
				formatedStats += String.format("%-6s,%-6s,%-6s,%-6s,%-6s,%-6s,%-6s,%-6s,%-6s,%-6s,%-6s", "SEASON", "GAMES", "RANK", "ATT", "LONG", "20+", "YARDS", "AVG", "FUM", "1DN","TD").replaceAll("[,]", "|") +"|\n";
				
			}
			for (int j = 0; j < getOffensivePlayerStats()[i].length; j++) {
				formatedStats += String.format("%-6d", j + 1) + "|"; // Get Season #
				formatedStats += String.format("%-6d", getGamesPlayed()[j]) + "|"; // get games played that season
				for (int k = 0; k < getOffensivePlayerStats()[i][j].length; k++) {
					formatedStats += String.format("%-6d", getOffensivePlayerStats()[i][j][k]) + "|"; // Get each stat
																										// for that
																										// season
				}
				formatedStats += "\n";
			}

		}
		
		return formatedStats;
	}
	
	
	// Method for generating passing stats
	public int[][] statGenerator(boolean passing, boolean receiving, boolean rushing){
		int[][] statArray = new int[getSeasonsPlayed()][9];
		boolean isPassing = passing;
		boolean isReceiving = receiving;
		boolean isRushing = rushing;
		
		// Optional arguments for an eighth stat
		boolean percentage = false; 
		boolean sum = false;
		
		//Variables
		int attempted = 0;
		int completed = 0;
		int yards = 0;
		int sacks = 0;
		int interceptions = 0;
		int touchdowns = 0;
		int percentCompleted = 0;
		int averageYards = 0;
		int longest = 0;
		int fumbles = 0;
		int firstDowns = 0;
		int carriesOver20 = 0;
		int receptions = 0;
		int yardsAfterCatch = 0;
		int seasonRanking = 0;
		
		
		for (int i = 0; i < statArray.length; i++) {
			//Generation seed based of games in that season
			int seed = rand.nextInt(getGamesPlayed()[i]) +1;

			// Generate the stats randomly based on the average per game stats
			// Passing Stats if passing is true
			if (isPassing) {
				attempted = (rand.nextInt(20) + 20) * seed; // average 40 pass attempts per game
				completed = rand.nextInt(attempted); // completed passes based on attempts
				yards = (rand.nextInt(250) + 150) * seed; // average 400 passing yards per game
				sacks = rand.nextInt(3) * seed; // average 2 sacks per game
				interceptions = rand.nextInt(2) * seed; // average 1 interception pass per game
				touchdowns = rand.nextInt(4) * seed; // average 3 passing touchdowns per game
				
				percentCompleted = ((completed * 100) / attempted); // completed divided by attempted passes to get percentage completed
				averageYards = yards / attempted; // Average Yards gain per pass attempt
				
				// The over ranking of the Quaterback
				seasonRanking = (percentCompleted  + touchdowns) / 10; 
			}
			
			// Rushing Stats if rushing is true
			if(isRushing) {
				attempted = (rand.nextInt(20) + 20) * seed; // average 40 rushing attempts per game
				longest = (rand.nextInt(56) + 4) * seed; // average 60 Logest rushing yards per game
				yards = (rand.nextInt(100) + 60) * seed; // average 160 rushing yards per game
				fumbles = rand.nextInt(3) * seed; // average 2 fumbles per game
				firstDowns = (rand.nextInt(13) + 1) * seed; // average 13 rushing firstdowns per game
				touchdowns = rand.nextInt(3) * seed; // average 2 rushing touchdowns per game
				carriesOver20 = rand.nextInt(6) * seed; // average 5 rushes over 20 yards per game
				averageYards = yards / attempted; // Average Yards gain per rush attempt
				
				// The over ranking for rushing 
				seasonRanking = (firstDowns + touchdowns + averageYards - fumbles) / 10; 
			}
			
			// Receiving stats if receiving is true
			if(isReceiving){
				receptions = (rand.nextInt(25) + 5) * (rand.nextInt(getGamesPlayed()[i]) +1); // average 30 receptions per game
				longest = (rand.nextInt(60) + 1) * (rand.nextInt(getGamesPlayed()[i]) +1); // average 60 longest receiving yards per game
				yards = (rand.nextInt(250) + 150) * (rand.nextInt(getGamesPlayed()[i]) +1); // average 400 receiving yards per game
				fumbles = rand.nextInt(3) * (rand.nextInt(getGamesPlayed()[i]) +1); // average 2 fumbles per game
				firstDowns = (rand.nextInt(11) + 8) * (rand.nextInt(getGamesPlayed()[i]) +1); // average 1 receiving fistdown per game
				touchdowns = rand.nextInt(3) * (rand.nextInt(getGamesPlayed()[i]) +1); // average 2 passing touchdowns per game
				yardsAfterCatch = rand.nextInt(12) * rand.nextInt(getGamesPlayed()[i]) + 1; // average 12 yards after catch
				averageYards = yards / receptions; // Average Yards gain per reception
				
				// The over ranking of the Quaterback
				seasonRanking = (receptions  + touchdowns - fumbles) / 10; 
			}
			
			//Season ranking is the average of all stats
			
			
			// set the value of the generated stat to the position in seasonStats[i][j]
			for (int j = 0; j < statArray[i].length; j++) {
				//Add a stat to to the subarray based on index.
				switch (j) {
				case 0:
					// all offensive stats share this variable
					statArray[i][j] = seasonRanking;
					break;
				case 1:
					// change variable based on if receiving is true
					if(isReceiving) {
						statArray[i][j] = receptions;
					}else {
						statArray[i][j] = attempted;
					}
					break;
				case 2:
					// change variable based on if passing is true
					if(isPassing) {
						statArray[i][j] = completed;
					}else {
						statArray[i][j] = longest;
					}
					break;
				case 3:
					// change variable based on which stat position is true
					if(isPassing) {
						statArray[i][j] = percentCompleted;
					}else if(isRushing){
						statArray[i][j] = carriesOver20;
					}else {
						statArray[i][j] = yardsAfterCatch;
					}
					break;
				case 4:
					// all offensive stats share this variable
					statArray[i][j] = yards;
					break;
				case 5:
					// all offensive stats share this variable
					statArray[i][j] = averageYards;
					break;
				case 6:
					// change variable based on if passing is true
					if(isPassing) {
						statArray[i][j] = sacks;
					}else {
						statArray[i][j] = fumbles;
					}
					break;
				case 7:
					// change variable based on if passing is true
					if(isPassing) {
						statArray[i][j] = interceptions;
					}else {
						statArray[i][j] = firstDowns;
					}
					break;
				case 8:
					// all offensive stats share this variable
					statArray[i][j] = touchdowns;
				}
			}

		}
		
		return statArray;
	}
	
	// This method sets generated stats to passing/rushing/receiving and then add two of them to the player depending on position.
	public void fillOffensivePlayerStats() {
		int[][] passing = statGenerator(true,false,false);
		int[][] rushing = statGenerator(false,true,false);
		int[][] receiving = statGenerator(false,false,true);
		int[][][] tempPlayerStats = new int[2][getSeasonsPlayed()][9];
		
		//Check if player is Quarterback
		if(getPosition() == "Quarterback") {
			tempPlayerStats[0] = passing; // set the first index of the three dimensional array to passing[][]
			tempPlayerStats[1] = rushing; // set the second index of the three dimentional array to rushing[][]
 		}else {
 			tempPlayerStats[0] = receiving; // set the first index of the three dimensional array to receiving[][]
			tempPlayerStats[1] = rushing; // set the second index of the three dimentional array to rushing[][]
 		}
		
		// Fill the offensivePlayerStats three dimensional array with tempPlayerStats.
		setOffensivePlayerStats(tempPlayerStats);
	}
	
	public int[][][] getOffensivePlayerStats(){
		return this.offensivePlayerStats;
	}
	
	// Getter/Setter position
	public void setPosition(int positionIndex) {
		this.position = OFFENSIVE_POSITIONS[positionIndex];
	}
	
	public String getPosition() {
		return this.position;
	}

	public static String[] getOffensivePositions() {
		return OFFENSIVE_POSITIONS;
	}

	public static String[] getPassingStats() {
		return PASSING_STATS;
	}

	public static String[] getRushingStats() {
		return RUSHING_STATS;
	}

	public static String[] getReceivingStats() {
		return RECEIVING_STATS;
	}

	public void setOffensivePlayerStats(int[][][] offensivePlayerStats) {
		this.offensivePlayerStats = offensivePlayerStats;
	}
	

	
}
