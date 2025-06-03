package app.virtual_games.sudoku.handlers;

import app.virtual_games.sudoku.controllers.GameController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Custom event handler for the highlighted hint cell.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class HintCellHandler implements EventHandler<ActionEvent>
{
  /**
   * Initiates clearing the background color of the current hint cell.
   *
   * @param event : base event
   */
  @Override
  public void handle(ActionEvent event)
  {
    if (GameController.getCurrentHintCell() != null)
    {
      GameController.getCurrentHintCell().unhighlightHintCell();
      GameController.setCurrentHintCell(null);
    }
  }
}
