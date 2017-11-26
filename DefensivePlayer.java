/** Program: Mile Stone 4
* File: DefensivePlayers.java
* Summary: Subclass of NFL Players - Contains stats that every Defensive NFL player shares.
* Author: Chris Hyde
* Date: November 26, 2017 
**/

import java.util.Random;

public class DefensivePlayer extends NFLPlayers {
	
	//no-args constructor
	public DefensivePlayer() {
		
	}
	
	// Constructor with args including super from parent class. 
	public DefensivePlayer(String firstName, String lastName, String team,  int age, int weight, int heightFeet, int heightInches, int seasonsPlayed, int teamNumber, int proBowls, int gamesMissed, int positionIndex) {
		super(firstName, lastName, team,  age, weight, heightFeet, heightInches, seasonsPlayed, teamNumber, proBowls, gamesMissed);
		this.position = DEFENSE_POSITIONS[positionIndex]; 
		this.playerStats = statGenerator(); //Generate all the stats on construction of the Defensive Player
	}
	
	//Class Calls
	private Random rand= new Random();
	
	
	//Instance variables
	private int interceptions;
	private int sacks;
	private int groupTackles; 
	private int soloTackles; 
	private int forcedFumbles;
	private int touchdowns;
	private int totalTackles;
	private int performanceRanking;
	private String position;
	private int[][] playerStats;
	private int[] careerTotal;
	
	
	
	// Constants
	private static final String[] DEFENSE_POSITIONS= {"Defensive Line", "Linebacker", "Cornerback", "Safety "}; 
	
	//Override the toString() method from superclass
	@Override
	public String toString() {
		String statHeader = String.format("%s,%-6s,%-6s,%-6s,%-6s,%-6s,%-6s,%-6s,%-6s,%-6s","SEASON","GAMES","RANK", "INT", "SACK", "AST", "SOLO", "TOT", "FF", "TD"); // Header for each row of stats
		return getPosition() + ": " + formatPlayerInfo() + "\n" + statHeader.replaceAll("[,]", "|") + "|\n" + formatAllStats() + "\n\n"; // The actual returned string for this overriden toString()
	}
	
	
	
	//Method to print the players all stats
	public String formatAllStats() {
		String allStats = ""; // String for holding all stats
		
		//Loop through all elements of playerStats and add them to allStats String. 
		for(int i = 0; i < getPlayerStats().length; i++) {
			allStats += String.format("%-6d", i + 1) + "|"; // Get Season #
			allStats += String.format("%-6d", getGamesPlayed()[i]) + "|"; // get games played that season
			for(int j = 0; j < getPlayerStats()[i].length; j++) {
				allStats += String.format("%-6d", getPlayerStats()[i][j]) + "|"; // Get each stat for that season
			}
			allStats += "\n"; // Next Line
		}
		
		//Return allStats when method is ran. 
		return allStats;
	}
	
	
	//Method to generate an array random set of stats based on seasons played
	public int[][] statGenerator() {
		int[][] seasonStats = new int[getSeasonsPlayed()][8];

		for (int i = 0; i < seasonStats.length; i++) {
			// The seed to generate the stats based on random average and number of games played that season
			int statSeed = rand.nextInt(10) + getGamesPlayed()[i];

			// Generate the stats
			setInterceptions(rand.nextInt(statSeed) / 2); // Make interception rarer by half
			setSacks(rand.nextInt(statSeed));
			setGroupTackles(rand.nextInt(statSeed * 2)); // Make groupTackles twice as likely
			setSoloTackles(rand.nextInt(statSeed));
			setForcedFumbles(rand.nextInt(statSeed));
			setTouchdowns(rand.nextInt(statSeed)); // Make touchdowns rarer by a quarter

			// set the value of the generated stat to the position in seasonStats[i][j]
			for (int j = 0; j < seasonStats[i].length; j++) {
				//Add a stat to to the subarray based on index.
				switch (j) {
				case 0:
					seasonStats[i][j] = getPerformanceRanking();
					break;
				case 1:
					seasonStats[i][j] = getInterceptions();
					break;
				case 2:
					seasonStats[i][j] = getSacks();
					break;
				case 3:
					seasonStats[i][j] = getGroupTackles();
					break;
				case 4:
					seasonStats[i][j] = getSoloTackles();
					break;
				case 5:
					seasonStats[i][j] = getTotalTackles();
					break;
				case 6:
					seasonStats[i][j] = getForcedFumbles();
					break;
				case 7:
					seasonStats[i][j] = getTouchdowns();
					break;
				}
			}

		}
		
		
		return seasonStats;
	}
	
	
	//  --- Instance Variables Getters and Setters ---
	// interceptions
	public int getInterceptions() {
		return interceptions;
	}

	public void setInterceptions(int interceptions) {
		this.interceptions = interceptions;
	}

	// sacks
	public int getSacks() {
		return sacks;
	}

	public void setSacks(int sacks) {
		this.sacks = sacks;
	}

	// groupTackles
	public int getGroupTackles() {
		return groupTackles;
	}

	public void setGroupTackles(int groupTackles) {
		this.groupTackles = groupTackles;
	}

	// soloTackles
	public int getSoloTackles() {
		return soloTackles;
	}

	public void setSoloTackles(int soloTackles) {
		this.soloTackles = soloTackles;
	}

	// forcedFumbles
	public int getForcedFumbles() {
		return forcedFumbles;
	}

	public void setForcedFumbles(int forcedFumbles) {
		this.forcedFumbles = forcedFumbles;
	}

	// touchdowns
	public int getTouchdowns() {
		return touchdowns;
	}

	public void setTouchdowns(int touchdowns) {
		this.touchdowns = touchdowns;
	}

	// totalTackles
	public int getTotalTackles() {
		return totalTackles = getGroupTackles() + getSoloTackles(); // calculate total tackles by adding group and solo tackles
	}
	//No setter as this totalTackles is the sum of solo and group tackles. 

	// PerformanceRanking
	public int getPerformanceRanking() {
		// Performance Ranking is all the defense stats averaged together. 
		int sumOfStats = getInterceptions() + getSacks() + getTotalTackles() + getForcedFumbles() + getTouchdowns();
		this.performanceRanking = sumOfStats / 5; // Divide the sum of all stats by the number of stats added together to get the average. 
		
		return this.performanceRanking; //return the result when you get the performance ranking. 
	}
	// No setter as Performance Ranking is set by the average of all seasonal stats

	// position
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	// DEFENCE_POSITONS
	public String[] getDEFENCE_POSITIONS() {
		return DEFENSE_POSITIONS; // returns the String array of all Defensive Positions
	}
	
	// playerStats
	public int[][] getPlayerStats(){
		return playerStats;
	}
	
	
	
	
	

}
