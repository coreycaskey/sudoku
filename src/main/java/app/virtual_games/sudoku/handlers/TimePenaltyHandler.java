package app.virtual_games.sudoku.handlers;

import app.virtual_games.sudoku.controllers.GameController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Custom event handler for the time penalty label.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class TimePenaltyHandler implements EventHandler<ActionEvent>
{
  /**
   * Initiates hiding the time penalty label, if shown.
   *
   * @param event : base event
   */
  @Override
  public void handle(ActionEvent event)
  {
    if (GameController.getIsTimePenaltyShown())
    {
      GameController.hideTimePenalty();
      GameController.stopTimePenaltyTimer();
    }
  }
}
