package app.virtual_games.sudoku.handlers;

import app.virtual_games.sudoku.controllers.GameController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


/**
 *
 * Custom event handler for the Return to Main Menu dialog button click event.
 *
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class ReturnToMainMenuDialogButtonHandler implements EventHandler<ActionEvent>
{
  /**
   *
   * Initiates displaying the Return to Main Menu dialog.
   *
   * @param clickEvent : Return to Main Menu dialog button click event
   *
   */
  @Override
  public void handle(ActionEvent clickEvent)
  {
    GameController.pauseGameTimer();
    GameController.loadReturnToMainMenuDialog();
  }
}
