package app.virtual_games.sudoku.handlers;

import app.virtual_games.sudoku.controllers.GameController;
import app.virtual_games.sudoku.models.NumberButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Event handler for the number button.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class NumberButtonHandler implements EventHandler<ActionEvent>
{
  /**
   * Highlights all occurrences of the number value currently in the sudoku puzzle
   *
   * @param clickEvent : number button click event
   */
  @Override
  public void handle(ActionEvent clickEvent)
  {
    var numberButton = (NumberButton) clickEvent.getSource();

    GameController.unhighlightSudokuPuzzle();
    GameController.unclickNumberButton();
    GameController.setCurrentClickedNumberButton(numberButton);
    GameController.clickNumberButton(numberButton);
    GameController.resetCurrentClickedCell();
    GameController.highlightOccurrences();
    GameController.hideTimePenalty();
    GameController.stopTimePenaltyTimer();
    GameController.stopHintCellTimer();
    GameController.setCurrentHintCell(null);
  }
}
