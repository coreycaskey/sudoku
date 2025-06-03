package app.virtual_games.sudoku.handlers;

import app.virtual_games.sudoku.controllers.GameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Event handler for exit application button.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class ExitApplicationDialogButtonHandler implements EventHandler<ActionEvent>
{
  /**
   * Opens exit application dialog.
   *
   * @param clickEvent : button click event
   */
  @Override
  public void handle(ActionEvent clickEvent)
  {
    GameController.openExitApplicationDialog();
  }
}
