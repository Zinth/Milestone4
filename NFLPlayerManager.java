/** Program: Mile Stone 4
* File: NFLPlayerManager.java
* Summary: Class that stores and creates NFL players from the NFLPlayers class
* Author: Chris Hyde
* Date: November 26, 2017 
**/

import java.util.ArrayList;
import java.util.Random;

public class NFLPlayerManager {
	
	//Basic constructor with no args
	public NFLPlayerManager() {
		
	}
	
	public NFLPlayerManager(int numOfPlayers) {
		createPlayers(numOfPlayers);
	}
	
	private Random random = new Random();
	
	// An array to store 6 NFLPlayers object
	private ArrayList<NFLPlayers> playerList = new ArrayList<>();
	
	
	// Method that randomly fills the playerList with NFLPlayers.
	public void createPlayers(int numOfPlayers) {
		// Array of first names for random player generation
		String[] firstNames = {"Stan", "Jim", "Bob", "George", "Henry", "Steve", "Troy", "Correy", "Conner" , "Harvey", "Chris", "Tim", "Jake", "Alan", "Adam"};
		//Array of last names for random player generation
		String[] lastNames = {"Harvey", "Connors", "Smith", "Henley", "Lorance", "Manning", "Dent", "White", "Black", "Fard", "Musk", "Bloom", "Newman", "Johnson"};
		// Array of every NFL team for random player Generation
		String[] teams = { "Arizona Cardinals", "Atlanta Falcons", "Baltimore Ravens", "Buffalo Bills",
				"Carolina Panthers", "Chicago Bears", "Cincinnati Bengals", "Cleveland Browns", "Dallas Cowboys",
				"Denver Broncos", "Detroit Lions", "Green Bay Packers", "Houston Texans", "Indianapolis Colts",
				"Jacksonville Jaguars", "Kansas City Chiefs", "Los Angeles Rams", "Los Angeles Chargers",
				"Miami Dolphins", "Minnesota Vikings", "New England Patriots", "New Orleans Saints", "New York Giants",
				"New York Jets", "Oakland Raiders", "Philadelphia Eagles", "Pittsburgh Steelers", "San Francisco 49ers",
				"Seattle Seahawks", "Tampa Bay Buccaneers", "Tennessee Titans", "Washington Redskins", };
		
		// Loop for creating Random Defensive Players
		for (int i = 0; i < numOfPlayers; i++) {
			int wieght = (random.nextInt(16) + 15) * 10; // Generate a random weight from 150lbs to 300lbs
			int heightFeet = random.nextInt(2) + 5; // Generate a random height in feet from 4 to 6
			int heightInches = random.nextInt(13); // Generate a random height in inches from 0 to 12
			int teamNumber = random.nextInt(99) + 1; // Generate a random team number from 1 to 100
			int seasonsPlayed = random.nextInt(19) + 1; // Generate a random seasons played between 1 and 20
			int gamesMissed = random.nextInt(seasonsPlayed * 8); // Generate a random number of games missed based on  half the number of games that could have been played.
			int age = (random.nextInt(7) + 18 + seasonsPlayed); // generate a random age based on min of age 18 to 24 then add seasons played.

			// Add a new defensive player to the playerList ArrayList using the above generated information
			setPlayerList(new DefensivePlayer(firstNames[random.nextInt(firstNames.length)],
					lastNames[random.nextInt(lastNames.length)], teams[random.nextInt(teams.length)], age, wieght,
					heightFeet, heightInches, seasonsPlayed, teamNumber, random.nextInt(5), gamesMissed,
					random.nextInt(4)));

			// Add a new Offensive Player to the playerList ArrayList using the above generated information
			setPlayerList(new OffensivePlayer(firstNames[random.nextInt(firstNames.length)],
					lastNames[random.nextInt(lastNames.length)], teams[random.nextInt(teams.length)], age, wieght,
					heightFeet, heightInches, seasonsPlayed, teamNumber, random.nextInt(5), gamesMissed,
					random.nextInt(6)));
			
		}
	}
	
	// toString Method.
	public String toString() {
		// Returns all elements in the ArrayList as thier toString() representation.
		return playerList.toString(); 
	}
	
	//Getter for the playerList Array
	public ArrayList<NFLPlayers> getPlayerList() {
		return this.playerList;
	}
	
	//Setter for changing an element of the playerList array
	public void setPlayerList(NFLPlayers player) {
		playerList.add(player);
	}
	

}
