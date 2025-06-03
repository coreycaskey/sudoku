package app.virtual_games.sudoku.models;

import java.util.Arrays;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Enum for puzzle difficulties.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public enum PuzzleDifficulty
{
  EASY(1, "Easy"), MEDIUM(2, "Medium"), HARD(3, "Hard");

  private final int id;
  private final String name;

  /**
   * Initializes {@link #id} and {@link #name}
   *
   * @param id   : identifier for puzzle difficulty
   * @param name : name of puzzle difficulty
   */
  PuzzleDifficulty(int id, String name)
  {
    this.id = id;
    this.name = name;
  }

  /** Public Helper Methods **/

  /**
   * Builds an {@link ObservableList} of puzzle difficulty names.
   *
   * @return ObservableList<String> : puzzle difficulty names
   */
  public static ObservableList<String> getPuzzleDifficultyNames()
  {
    return Arrays.stream(PuzzleDifficulty.values()).map(difficulty -> difficulty.name)
        .collect(Collectors.toCollection(FXCollections::observableArrayList));
  }

  /**
   * Retrieves enum instance with puzzle difficulty name.
   *
   * @param name : puzzle difficulty
   * @return PuzzleDifficulty : enum instance
   */
  public static PuzzleDifficulty getEnumInstance(String name)
  {
    return PuzzleDifficulty.valueOf(name.toUpperCase());
  }

  /** Getters and Setters **/

  /**
   * Retrieves {@link #id} for enum instance.
   *
   * @return int : identifier for puzzle difficulty
   */
  public int getId()
  {
    return this.id;
  }

  /**
   * Retrieves {@link #name} for enum instance.
   *
   * @return String : name of puzzle difficulty
   */
  public String getName()
  {
    return this.name;
  }
}
