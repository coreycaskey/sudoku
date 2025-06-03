package app.virtual_games.sudoku.handlers;

import app.virtual_games.sudoku.controllers.GameController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

/**
 * Custom event handler for the Game Screen difficulty dropdown selection event.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class GameScreenDifficultyDropdownHandler implements EventHandler<ActionEvent>
{
  /**
   * Initiates updating the puzzle difficulty and loading a new sudoku puzzle.
   *
   * @param selectionEvent : Game Screen difficulty dropdown selection event
   */
  @Override
  @SuppressWarnings("unchecked")
  public void handle(ActionEvent selectionEvent)
  {
    GameController.stopGameTimer();
    GameController.hideTimePenalty();
    GameController.stopTimePenaltyTimer();
    GameController.stopHintCellTimer();
    GameController.resetTimerLabel();
    GameController.setCurrentDifficulty(((ComboBox<String>) selectionEvent.getSource()).getValue());
    GameController.startNewSudoku();
  }
}
