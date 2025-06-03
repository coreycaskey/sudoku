package app.virtual_games.sudoku.handlers;

import app.virtual_games.sudoku.controllers.GameController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Custom event handler for the Return to Main Menu dialog confirm button click event.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class ReturnToMainMenuHandler implements EventHandler<ActionEvent>
{
  /**
   * Initiates transitioning to the Main Menu.
   *
   * @param clickEvent : Return to Main Menu dialog confirm button click event
   */
  @Override
  public void handle(ActionEvent clickEvent)
  {
    GameController.stopGameTimer();
    GameController.stopTimePenaltyTimer();
    GameController.stopHintCellTimer();
    GameController.loadMainMenu();
  }
}
