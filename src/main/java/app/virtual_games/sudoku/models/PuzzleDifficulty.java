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
  private final String label;

  /**
   * Initializes {@link #id} and {@link #label}
   *
   * @param id    : identifier for puzzle difficulty
   * @param label : label of puzzle difficulty
   */
  PuzzleDifficulty(int id, String label)
  {
    this.id = id;
    this.label = label;
  }

  /** Public Helper Methods **/

  /**
   * Retrieves list of puzzle difficulties.
   *
   * @return PuzzleDifficulty[] : puzzle difficulties
   */
  public static PuzzleDifficulty[] getPuzzleDifficulties()
  {
    return PuzzleDifficulty.values();
  }

  /**
   * Retrieves enum instance with puzzle difficulty label.
   *
   * @param label : puzzle difficulty
   * @return PuzzleDifficulty : enum instance
   */
  public static PuzzleDifficulty getEnumInstance(String label)
  {
    return PuzzleDifficulty.valueOf(label.toUpperCase());
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
   * Retrieves {@link #label} for enum instance.
   *
   * @return String : label of puzzle difficulty
   */
  public String getLabel()
  {
    return this.label;
  }
}
