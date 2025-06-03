package app.virtual_games.sudoku.handlers;

import app.virtual_games.sudoku.controllers.GameController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Custom event handler for the restart button click event.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class RestartButtonHandler implements EventHandler<ActionEvent>
{
  /**
   * Initiates resetting the sudoku puzzle.
   *
   * @param clickEvent : restart button click event
   */
  @Override
  public void handle(ActionEvent clickEvent)
  {
    GameController.hideTimePenalty();
    GameController.stopTimePenaltyTimer();
    GameController.stopHintCellTimer();
    GameController.setCurrentHintCell(null);
    GameController.unhighlightSudokuPuzzle();
    GameController.resetCurrentClickedCell();
    GameController.restartSudoku();
  }
}
