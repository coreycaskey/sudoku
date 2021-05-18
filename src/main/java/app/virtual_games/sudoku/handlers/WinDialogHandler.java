package app.virtual_games.sudoku.handlers;

import app.virtual_games.sudoku.controllers.GameController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;


/**
 *
 * Custom event handler for the Win dialog start new puzzle button click event.
 *
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class WinDialogHandler implements EventHandler<ActionEvent>
{
  /**
   *
   * Initiates loading a new sudoku puzzle.
   *
   * @param event : base event
   *
   */
  @Override
  public void handle(ActionEvent event)
  {
    ComboBox<String> gameScreenDifficultyDropdown = GameController.getGameScreenDifficultyDropdown();
    String currentDifficultyName = GameController.getCurrentDifficultyName();

    if (gameScreenDifficultyDropdown.getSelectionModel().getSelectedItem().equals(currentDifficultyName))
    {
      GameController.stopGameTimer();
      GameController.hideTimePenalty();
      GameController.stopTimePenaltyTimer();
      GameController.stopHintCellTimer();
      GameController.resetTimerLabel();
      GameController.startNewSudoku();
    }
    else
    {
      gameScreenDifficultyDropdown.setValue(currentDifficultyName); // calls GameScreenDifficultyDropdownHandler
    }

    GameController.closeGameScreenDialog();
  }
}
