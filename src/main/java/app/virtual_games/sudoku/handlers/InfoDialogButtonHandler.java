package app.virtual_games.sudoku.handlers;

import app.virtual_games.sudoku.controllers.GameController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


/**
 *
 * Custom event handler for the Info dialog button click event.
 *
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class InfoDialogButtonHandler implements EventHandler<ActionEvent>
{
  /**
   *
   * Initiates loading the Info dialog.
   *
   * @param clickEvent : Info dialog button click event
   *
   */
  @Override
  public void handle(ActionEvent clickEvent)
  {
    GameController.loadInfoDialog();
  }
}
