package app.virtual_games.sudoku.handlers;

import app.virtual_games.sudoku.controllers.GameController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


/**
 *
 * Custom event handler for the start button click event.
 *
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class StartButtonHandler implements EventHandler<ActionEvent>
{
  /**
   *
   * Initiates transitioning to the Game Screen.
   *
   * @param clickEvent : start button click event
   *
   */
  @Override
  public void handle(ActionEvent clickEvent)
  {
    GameController.transitionToGameScreen();
  }
}
