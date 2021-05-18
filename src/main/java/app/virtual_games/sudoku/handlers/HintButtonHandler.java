package app.virtual_games.sudoku.handlers;

import app.virtual_games.sudoku.controllers.GameController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


/**
 *
 * Custom event handler for the hint button click event.
 *
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class HintButtonHandler implements EventHandler<ActionEvent>
{
  /**
   *
   * Initiates loading the hint into the sudoku puzzle and displaying the time penalty.
   *
   * @param clickEvent : hint button click event
   *
   */
  @Override
  public void handle(ActionEvent clickEvent)
  {
    int timePenalty = GameController.getTimePenalty();

    GameController.unhighlightSudokuPuzzle();
    GameController.unclickNumberButton();
    GameController.setCurrentClickedNumberButton(null);
    GameController.hideTimePenalty();
    GameController.stopTimePenaltyTimer();
    GameController.showTimePenalty(timePenalty);
    GameController.startTimePenaltyTimer();
    GameController.incrementPlayingTime(timePenalty * ((long) 1000));
    GameController.updateTimerLabel();
    GameController.getHint();
  }
}
