package app.virtual_games.sudoku.handlers;

import app.virtual_games.sudoku.controllers.GameController;
import app.virtual_games.sudoku.models.TimerButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


/**
 *
 * Custom event handler for the timer button click event.
 *
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class TimerButtonHandler implements EventHandler<ActionEvent>
{
  /**
   *
   * Starts and stops the game progress and timer.
   *
   * @param clickEvent : timer button click event
   *
   */
  @Override
  public void handle(ActionEvent clickEvent)
  {
    TimerButton timerButton = (TimerButton) clickEvent.getSource();

    if (timerButton.getIsPaused())           // play icon is clicked
    {
      GameController.startGameTimer();
      GameController.showSudokuCells();
      GameController.enableGameScreenElements();

      timerButton.addStyling("pause-button");
      timerButton.removeStyling("play-button");
    }
    else                      // pause icon is clicked
    {
      GameController.pauseGameTimer();
      GameController.unhighlightSudokuPuzzle();
      GameController.resetCurrentClickedCell();
      GameController.hideTimePenalty();
      GameController.stopTimePenaltyTimer();
      GameController.setCurrentHintCell(null);
      GameController.stopHintCellTimer();
      GameController.unclickNumberButton();
      GameController.setCurrentClickedNumberButton(null);
      GameController.hideSudokuCells();
      GameController.disableGameScreenElements();

      timerButton.addStyling("play-button");
      timerButton.removeStyling("pause-button");
    }

    timerButton.setIsPaused(!timerButton.getIsPaused());
  }
}
