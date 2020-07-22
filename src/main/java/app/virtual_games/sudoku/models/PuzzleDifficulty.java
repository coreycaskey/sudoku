package app.virtual_games.sudoku.models;

import java.util.Arrays;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * 
 * Enum for the sudoku puzzle difficulties.
 * 
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public enum PuzzleDifficulty 
{
	EASY   (1, "Easy"),
	MEDIUM (2, "Medium"),
	HARD   (3, "Hard");

	private final int difficultyId;
	private final String difficultyName;
	
	
	/**
	 * 
	 * Initializes the following variable(s):
	 * 
	 *  — {@link #difficultyId}
	 *  — {@link #difficultyName}
	 * 
	 * @param difficultyId 	 : unique identifier for the puzzle difficulty (e.g. Easy —> 1)
	 * @param difficultyName : name of the puzzle difficulty (e.g. Easy)
	 * 
	 */
	PuzzleDifficulty(int difficultyId, String difficultyName) 
	{
		this.difficultyId = difficultyId;
		this.difficultyName = difficultyName;
	}
	
	
	/**  Public Helper Methods  **/
	
	
	/**
	 * 
	 * Builds an {@link ObservableList} of puzzle difficulty names.
	 * 
	 * @return ObservableList<String> : list of puzzle difficulty names
	 * 
	 */
	public static ObservableList<String> getPuzzleDifficulties() 
	{
		return Arrays.stream(PuzzleDifficulty.values())
					 .map(puzzleDifficulty -> puzzleDifficulty.difficultyName)
					 .collect(Collectors.toCollection(FXCollections::observableArrayList));
	}
	
	
	/**
	 * 
	 * Retrieves the enum instance that maps to the puzzle difficulty name.
	 *
	 * @param difficultyName 	: name of the puzzle difficulty
	 * 
	 * @return PuzzleDifficulty : corresponding enum instance
	 * 
	 */
	public static PuzzleDifficulty getEnumInstance(String difficultyName) 
	{
		return PuzzleDifficulty.valueOf(difficultyName.toUpperCase());
	}
	
	
	/**  Getters and Setters  **/
	
	
	/**
	 * 
	 * Retrieves the {@link #difficultyId} for the enum instance.
	 * 
	 * @return int : unique identifier for the puzzle difficulty
	 * 
	 */
	public int getDifficultyId() 
	{
		return this.difficultyId;
	}
	
	
	/**
	 * 
	 * Retrieves the {@link #difficultyName} for the enum instance.
	 * 
	 * @return String : name of the puzzle difficulty
	 * 
	 */
	public String getDifficultyName() 
	{
		return this.difficultyName;
	}
}