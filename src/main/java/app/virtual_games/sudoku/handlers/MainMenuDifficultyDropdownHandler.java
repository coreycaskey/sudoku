package app.virtual_games.sudoku.handlers;

import app.virtual_games.sudoku.controllers.GameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

// TODO: this is used in multiple places - can the name be generalized ?
/**
 * Event handler for difficulty dropdown.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class MainMenuDifficultyDropdownHandler implements EventHandler<ActionEvent>
{
  /**
   * Opens difficulty dropdown.
   *
   * @param selectionEvent : dropdown selection event
   */
  @Override
  @SuppressWarnings("unchecked")
  public void handle(ActionEvent selectionEvent)
  {
    GameController.setCurrentDifficulty(((ComboBox<String>) selectionEvent.getSource()).getValue());
  }
}
