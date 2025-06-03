package app.virtual_games.sudoku.models;

import app.virtual_games.sudoku.handlers.TimerButtonHandler;

import javafx.scene.control.Button;

/**
 * Custom child class of {@link Button} that starts and stops the game progress.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class TimerButton extends Button
{
  private boolean isPaused;

  /**
   * Loads the button's base styling and event handler. Initializes {@link #isPaused}.
   */
  public TimerButton()
  {
    this.isPaused = false;
    this.setOnAction(new TimerButtonHandler());
    this.addStyling("timer-button", "pause-button");
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
   * Removes a collection of CSS class names from the button
   *
   * @param styling : CSS class names
   */
  public void removeStyling(String... styling)
  {
    this.getStyleClass().removeAll(styling);
  }

  /** Getters and Setters **/

  /**
   * Retrieves {@link #isPaused}.
   *
   * @return boolean
   */
  public boolean getIsPaused()
  {
    return this.isPaused;
  }

  /**
   * Updates {@link #isPaused}.
   *
   * @param shouldPause
   */
  public void setIsPaused(boolean shouldPause)
  {
    this.isPaused = shouldPause;
  }
}
