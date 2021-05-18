package app.virtual_games.sudoku.handlers;

import app.virtual_games.sudoku.controllers.GameController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


/**
 *
 * Custom event handler for the Exit Application dialog button click event.
 *
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class ExitApplicationDialogButtonHandler implements EventHandler<ActionEvent>
{
  /**
   *
   * Initiates loading the Exit Application dialog.
   *
   * @param clickEvent : Exit Application dialog button click event
   *
   */
  @Override
  public void handle(ActionEvent clickEvent)
  {
    GameController.loadExitApplicationDialog();
  }
}
