package app.virtual_games.sudoku.handlers;

import app.virtual_games.sudoku.controllers.GameController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;


/**
 *
 * Custom event handler for the Main Menu difficulty dropdown selection event.
 *
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class MainMenuDifficultyDropdownHandler implements EventHandler<ActionEvent>
{
  /**
   *
   * Initiates updating the current puzzle difficulty.
   *
   * @param selectionEvent : Main Menu difficulty dropdown selection event
   *
   */
  @Override
  @SuppressWarnings("unchecked")
  public void handle(ActionEvent selectionEvent)
  {
    GameController.setCurrentDifficulty(((ComboBox<String>) selectionEvent.getSource()).getValue());
  }
}
