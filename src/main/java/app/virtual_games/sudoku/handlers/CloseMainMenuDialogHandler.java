package app.virtual_games.sudoku.handlers;

import app.virtual_games.sudoku.controllers.GameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Event handler for close buttons in main menu dialogs.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class CloseMainMenuDialogHandler implements EventHandler<ActionEvent>
{
  /**
   * Closes main menu dialogs.
   *
   * @param clickEvent : button click event
   */
  @Override
  public void handle(ActionEvent clickEvent)
  {
    GameController.closeMainMenuDialog();
  }
}
