package app.virtual_games.sudoku.models;

import app.virtual_games.sudoku.handlers.NumberButtonHandler;

import javafx.scene.control.Button;

/**
 * Custom child class of {@link Button} that highlights all occurrences of the number value
 * currently in the sudoku puzzle.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class NumberButton extends Button
{
  private int value;

  /**
   * Loads the button's base styling, display information, and event handler. Initializes
   * {@link #value}.
   *
   * @param value : value of 1-9
   */
  public NumberButton(int value)
  {
    this.value = value;
    this.setText(Integer.toString(value));
    this.setOnAction(new NumberButtonHandler());
    this.addStyling("game-button", "number-button");
  }

  /** Public Helper Methods **/

  /**
   * Adds a collection of CSS class names to the button.
   *
   * @param styling : CSS class names
   */
  public void addStyling(String... styling)
  {
    this.getStyleClass().addAll(styling);
  }

  /**
   * Removes a collection of CSS class names from the button.
   *
   * @param styling : CSS class names
   */
  public void removeStyling(String... styling)
  {
    this.getStyleClass().removeAll(styling);
  }

  /** Getters and Setters **/

  /**
   * Retrieves {@link #value}.
   *
   * @return int : value of 1-9
   */
  public int getValue()
  {
    return this.value;
  }
}
