package app.virtual_games.sudoku.handlers;

import app.virtual_games.sudoku.controllers.GameController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Custom event handler for the Close Game Screen Dialog click event.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class CloseGameScreenDialogHandler implements EventHandler<ActionEvent>
{
  /**
   * Initiates closing a Game Screen dialog.
   *
   * @param clickEvent : Close Game Screen Dialog click event
   */
  @Override
  public void handle(ActionEvent clickEvent)
  {
    GameController.startGameTimer();
    GameController.closeGameScreenDialog();
  }
}
