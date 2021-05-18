package app.virtual_games.sudoku.models;

import app.virtual_games.sudoku.handlers.NumberButtonHandler;

import javafx.scene.control.Button;


/**
 *
 * Custom child class of {@link Button} that shows the occurrences of a sudoku value.
 *
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class NumberButton extends Button
{
  private int value;


  /**
   *
   * Initializes the following variable(s):
   *
   *  — {@link #value}
   *
   * Loads the button's base styling, display information, and event handler.
   *
   * @param value : corresponding sudoku value
   *
   */
  public NumberButton(int value)
  {
    this.value = value;
    this.setText(Integer.toString(value));
    this.setOnAction(new NumberButtonHandler());
    this.addStyling("game-button", "number-button");
  }


  /**  Public Helper Methods  **/


  /**
   *
   * Adds a collection of CSS class names to the button.
   *
   * @param styling : collection of CSS class names
   *
   */
  public void addStyling(String... styling)
  {
    this.getStyleClass().addAll(styling);
  }


  /**
   *
   * Removes a collection of CSS class names from the button.
   *
   * @param styling : collection of CSS class names
   *
   */
  public void removeStyling(String... styling)
  {
    this.getStyleClass().removeAll(styling);
  }


  /**  Getters and Setters  **/


  /**
   *
   * Retrieves {@link #value}.
   *
   * @return int : corresponding sudoku value
   *
   */
  public int getValue()
  {
    return this.value;
  }
}
