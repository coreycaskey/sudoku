package app.virtual_games.sudoku.handlers;

import app.virtual_games.sudoku.controllers.GameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

/**
 * Event handler for start new puzzle button.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class StartNewPuzzleButtonHandler implements EventHandler<ActionEvent>
{
  /**
   * Starts new sudoku with the selected difficulty
   *
   * @param event : base event
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
    } else
    {
      gameScreenDifficultyDropdown.setValue(currentDifficultyName); // calls GameScreenDifficultyDropdownHandler
    }

    GameController.closeGameScreenDialog();
  }
}
