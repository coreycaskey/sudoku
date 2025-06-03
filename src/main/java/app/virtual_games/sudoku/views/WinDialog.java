package app.virtual_games.sudoku.views;

import app.virtual_games.sudoku.controllers.GameController;
import app.virtual_games.sudoku.handlers.MainMenuDifficultyDropdownHandler;
import app.virtual_games.sudoku.handlers.ReturnToMainMenuHandler;
import app.virtual_games.sudoku.handlers.WinDialogHandler;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * Loads the WinDialog for the JavaFX application.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class WinDialog extends Dialog
{
  /**
   * Initialize {@link Dialog} with additional elements.
   */
  public WinDialog()
  {
    super();
    this.addDialogStyling("large-dialog");
    this.removeCloseButtonContainer();
    this.addContentContainer(this.loadContentContainer());
  }

  /** Private Helper Methods **/

  /**
   * Loads the content container and populates {@link #loadDialogTitle()},
   * {@link #loadDialogTimerContainer()}, {@link #loadDialogDifficultyContainer()},
   * {@link #loadDialogButtonContainer()}
   *
   * @return VBox : content container
   */
  private VBox loadContentContainer()
  {
    var contentContainer = new VBox();

    contentContainer.getStyleClass().addAll("content-container", "large-content-container");
    contentContainer.getChildren().add(this.loadDialogTitle());
    contentContainer.getChildren().add(this.loadDialogTimerContainer());
    contentContainer.getChildren().add(this.loadDialogDifficultyContainer());
    contentContainer.getChildren().add(this.loadDialogButtonContainer());

    return contentContainer;
  }

  /**
   * Loads the dialog title.
   *
   * @return Label : dialog title
   */
  private Label loadDialogTitle()
  {
    var dialogTitle = new Label("Congratulations!");

    dialogTitle.getStyleClass().addAll("dialog-title", "win-dialog-title");

    return dialogTitle;
  }

  /**
   * Loads the dialog timer container.
   *
   * @return VBox : dialog timer container
   */
  private VBox loadDialogTimerContainer()
  {
    var dialogTimerContainer = new VBox();

    dialogTimerContainer.getStyleClass().add("win-dialog-timer-container");
    dialogTimerContainer.getChildren().add(this.loadDialogTimerDescriptionContainer());
    dialogTimerContainer.getChildren().add(this.loadDialogTimerLabel());

    return dialogTimerContainer;
  }

  /**
   * Loads the dialog timer description container.
   *
   * @return HBox : dialog timer description container
   */
  private HBox loadDialogTimerDescriptionContainer()
  {
    var dialogTimerDescriptionContainer = new HBox();

    var timerDescriptionBeginning = new Label("You solved this ");
    var timerDescriptionMiddle = new Label(GameController.getCurrentDifficultyName().toUpperCase());
    var timerDescriptionEnd = new Label(" puzzle in:");

    timerDescriptionBeginning.getStyleClass().add("timer-description-label");
    timerDescriptionMiddle.getStyleClass().add("puzzle-difficulty-label");
    timerDescriptionEnd.getStyleClass().add("timer-description-label");

    dialogTimerDescriptionContainer.getStyleClass().add("timer-description-container");
    dialogTimerDescriptionContainer.getChildren().addAll(timerDescriptionBeginning, timerDescriptionMiddle,
        timerDescriptionEnd);

    return dialogTimerDescriptionContainer;
  }

  /**
   * Loads the dialog timer label.
   *
   * @return Label : dialog timer label
   */
  private Label loadDialogTimerLabel()
  {
    var timerLabel = new Label(GameController.formatWinTime());

    timerLabel.getStyleClass().add("win-dialog-timer-label");

    return timerLabel;
  }

  /**
   * Loads the dialog difficulty container.
   *
   * @return HBox : dialog difficulty container
   */
  private HBox loadDialogDifficultyContainer()
  {
    var dialogDifficultyContainer = new HBox();

    dialogDifficultyContainer.getStyleClass().add("difficulty-container");
    dialogDifficultyContainer.getChildren().add(this.loadDialogDifficultyLabel());
    dialogDifficultyContainer.getChildren().add(loadDialogDifficultyDropdown());

    return dialogDifficultyContainer;
  }

  /**
   * Loads the dialog difficulty label.
   *
   * @return Label : dialog difficulty label
   */
  private Label loadDialogDifficultyLabel()
  {
    var dialogDifficultyLabel = new Label("Puzzle Difficulty: ");

    dialogDifficultyLabel.getStyleClass().add("difficulty-label");

    return dialogDifficultyLabel;
  }

  /**
   * Loads the dialog difficulty dropdown.
   *
   * @return ComboBox<String> : dialog difficulty dropdown
   */
  private ComboBox<String> loadDialogDifficultyDropdown()
  {
    var dialogDifficultyDropdown = new ComboBox<String>(GameController.getPuzzleDifficultyNames());

    // ComboBox default style classes are .combo-box-base and .combo-box

    dialogDifficultyDropdown.getSelectionModel().select(GameController.getCurrentDifficultyName());
    dialogDifficultyDropdown.setOnAction(new MainMenuDifficultyDropdownHandler());

    return dialogDifficultyDropdown;
  }

  /**
   * Loads the dialog button container.
   *
   * @return HBox : dialog button container
   */
  private HBox loadDialogButtonContainer()
  {
    var dialogButtonContainer = new HBox();

    dialogButtonContainer.getStyleClass().addAll("button-container", "multiple-option-button-container");
    dialogButtonContainer.getChildren().add(this.loadStartNewPuzzleButton());
    dialogButtonContainer.getChildren().add(this.loadReturnToMainMenuButton());

    return dialogButtonContainer;
  }

  /**
   * Loads the start new puzzle button with {@link WinDialogHandler}.
   *
   * @return Button : start new puzzle button
   */
  private Button loadStartNewPuzzleButton()
  {
    var startNewPuzzleButton = new Button("Start New Puzzle");

    startNewPuzzleButton.getStyleClass().addAll("dialog-button", "win-dialog-button");
    startNewPuzzleButton.setOnAction(new WinDialogHandler());

    return startNewPuzzleButton;
  }

  /**
   * Loads the return to main menu button with {@link ReturnToMainMenuHandler}.
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
