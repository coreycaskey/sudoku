package app.virtual_games.sudoku.views;

import app.virtual_games.sudoku.controllers.GameController;
import app.virtual_games.sudoku.handlers.MainMenuDifficultyDropdownHandler;
import app.virtual_games.sudoku.handlers.ReturnToMainMenuHandler;
import app.virtual_games.sudoku.handlers.StartNewPuzzleButtonHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Win dialog.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class WinDialog extends Dialog
{
  /**
   * Initializes win dialog.
   */
  public WinDialog()
  {
    super();
    this.addDialogStyling("large-dialog");
    this.hideCloseButtonContainer();
    this.addContentContainer(this.buildContentContainer());
  }

  /** Private Helper Methods **/

  /**
   * Builds content container with {@link #buildTitle}, {@link #buildTimerContainer},
   * {@link #buildDifficultyContainer}, and {@link #buildButtonContainer}.
   *
   * @return VBox : content container
   */
  private VBox buildContentContainer()
  {
    var contentContainer = new VBox();

    contentContainer.getStyleClass().addAll("content-container", "large-content-container");
    contentContainer.getChildren().add(this.buildTitle());
    contentContainer.getChildren().add(this.buildTimerContainer());
    contentContainer.getChildren().add(this.buildDifficultyContainer());
    contentContainer.getChildren().add(this.buildButtonContainer());

    return contentContainer;
  }

  /**
   * Builds title.
   *
   * @return Label : title
   */
  private Label buildTitle()
  {
    var title = new Label("Congratulations!");

    title.getStyleClass().addAll("dialog-title", "win-dialog-title");

    return title;
  }

  /**
   * Builds timer container with {@link #buildTimerDescriptionContainer} and {@link #buildTimerLabel}.
   *
   * @return VBox : timer container
   */
  private VBox buildTimerContainer()
  {
    var timerContainer = new VBox();

    timerContainer.getStyleClass().add("win-dialog-timer-container");
    timerContainer.getChildren().add(this.buildTimerDescriptionContainer());
    timerContainer.getChildren().add(this.buildTimerLabel());

    return timerContainer;
  }

  /**
   * Builds timer description container.
   *
   * @return HBox : timer description container
   */
  private HBox buildTimerDescriptionContainer()
  {
    var timerDescriptionContainer = new HBox();

    var timerDescriptionBeginning = new Label("You solved this ");
    var timerDescriptionMiddle = new Label(GameController.getCurrentDifficultyName().toUpperCase());
    var timerDescriptionEnd = new Label(" puzzle in:");

    timerDescriptionBeginning.getStyleClass().add("timer-description-label");
    timerDescriptionMiddle.getStyleClass().add("puzzle-difficulty-label");
    timerDescriptionEnd.getStyleClass().add("timer-description-label");

    timerDescriptionContainer.getStyleClass().add("timer-description-container");
    timerDescriptionContainer.getChildren().addAll(timerDescriptionBeginning, timerDescriptionMiddle,
        timerDescriptionEnd);

    return timerDescriptionContainer;
  }

  /**
   * Builds timer label.
   *
   * @return Label : timer label
   */
  private Label buildTimerLabel()
  {
    var timerLabel = new Label(GameController.formatWinTime());

    timerLabel.getStyleClass().add("win-dialog-timer-label");

    return timerLabel;
  }

  /**
   * Builds difficulty container with {@link #buildDifficultyLabel} and
   * {@link #buildDifficultyDropdown}.
   *
   * @return HBox : difficulty container
   */
  private HBox buildDifficultyContainer()
  {
    var difficultyContainer = new HBox();

    difficultyContainer.getStyleClass().add("difficulty-container");
    difficultyContainer.getChildren().add(this.buildDifficultyLabel());
    difficultyContainer.getChildren().add(this.buildDifficultyDropdown());

    return difficultyContainer;
  }

  /**
   * Builds difficulty label.
   *
   * @return Label : difficulty label
   */
  private Label buildDifficultyLabel()
  {
    var difficultyLabel = new Label("Puzzle Difficulty: ");

    difficultyLabel.getStyleClass().add("difficulty-label");

    return difficultyLabel;
  }

  /**
   * Builds difficulty dropdown.
   *
   * @return ComboBox<String> : difficulty dropdown
   */
  private ComboBox<String> buildDifficultyDropdown()
  {
    var difficultyDropdown = new ComboBox<String>(GameController.getPuzzleDifficultyNames());

    // ComboBox default style classes are .combo-box-base and .combo-box

    difficultyDropdown.getSelectionModel().select(GameController.getCurrentDifficultyName());
    difficultyDropdown.setOnAction(new MainMenuDifficultyDropdownHandler()); // TODO: generalize name for handler ?

    return difficultyDropdown;
  }

  /**
   * Builds button container with {@link #buildStartNewPuzzleButton} and
   * {@link #buildReturnToMainMenuButton}.
   *
   * @return HBox : button container
   */
  private HBox buildButtonContainer()
  {
    var dialogButtonContainer = new HBox();

    dialogButtonContainer.getStyleClass().addAll("button-container", "multiple-option-button-container");
    dialogButtonContainer.getChildren().add(this.buildStartNewPuzzleButton());
    dialogButtonContainer.getChildren().add(this.loadReturnToMainMenuButton());

    return dialogButtonContainer;
  }

  /**
   * Builds start new puzzle button.
   *
   * @return Button : start new puzzle button
   */
  private Button buildStartNewPuzzleButton()
  {
    var startNewPuzzleButton = new Button("Start New Puzzle");

    startNewPuzzleButton.getStyleClass().addAll("dialog-button", "win-dialog-button");
    startNewPuzzleButton.setOnAction(new StartNewPuzzleButtonHandler());

    return startNewPuzzleButton;
  }

  /**
   * Builds return to main menu button.
   *
   * @return Button : return to main menu button
   */
  private Button loadReturnToMainMenuButton()
  {
    var returnToMainMenuButton = new Button("Main Menu");

    returnToMainMenuButton.getStyleClass().addAll("dialog-button", "win-dialog-button");
    returnToMainMenuButton.setOnAction(new ReturnToMainMenuHandler());

    return returnToMainMenuButton;
  }
}
