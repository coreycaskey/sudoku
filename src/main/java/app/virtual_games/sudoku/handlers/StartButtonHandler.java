package app.virtual_games.sudoku.handlers;

import app.virtual_games.sudoku.controllers.GameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Event handler for start button.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class StartButtonHandler implements EventHandler<ActionEvent>
{
  /**
   * Transitions to game screen.
   *
   * @param clickEvent : button click event
   */
  @Override
  public void handle(ActionEvent clickEvent)
  {
    GameController.transitionToGameScreen();
  }
}
