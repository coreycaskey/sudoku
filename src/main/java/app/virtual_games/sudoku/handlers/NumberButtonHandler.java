package app.virtual_games.sudoku.handlers;

import app.virtual_games.sudoku.controllers.GameController;
import app.virtual_games.sudoku.models.NumberButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


/**
 *
 * Custom event handler for the number button click event.
 *
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class NumberButtonHandler implements EventHandler<ActionEvent>
{
  /**
   *
   * Highlights all occurrences of the sudoku value represented by the number button.
   *
   * @param clickEvent : number button click event
   *
   */
  @Override
  public void handle(ActionEvent clickEvent)
  {
    NumberButton numberButton = (NumberButton) clickEvent.getSource();

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
