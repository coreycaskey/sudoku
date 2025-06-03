package app.virtual_games.sudoku.views;

import app.virtual_games.sudoku.controllers.GameController;
import app.virtual_games.sudoku.handlers.ExitApplicationDialogButtonHandler;
import app.virtual_games.sudoku.handlers.InfoDialogButtonHandler;
import app.virtual_games.sudoku.handlers.MainMenuDifficultyDropdownHandler;
import app.virtual_games.sudoku.handlers.StartButtonHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Main menu screen.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class MainMenu extends StackPane
{
  /**
   * Initializes main menu screen.
   */
  public MainMenu()
  {
    this.getStyleClass().add("application-background");
    this.getChildren().add(this.buildOuterContainer());
  }

  /** Private Helper Methods **/

  /**
   * Builds outer container with {@link #buildDialogButtonContainer} and {@link #buildInnerContainer}.
   *
   * @return VBox : outer container
   */
  private VBox buildOuterContainer()
  {
    var outerContainer = new VBox();

    outerContainer.getChildren().add(this.buildDialogButtonContainer());
    outerContainer.getChildren().add(this.buildInnerContainer());

    return outerContainer;
  }

  /**
   * Builds dialog button container with {@link #buildExitApplicationDialogButton} and
   * {@link #buildInfoDialogButton}.
   *
   * @return HBox : dialog button container
   */
  private HBox buildDialogButtonContainer()
  {
    var dialogButtonContainer = new HBox();

    dialogButtonContainer.getStyleClass().add("open-dialog-button-container");
    dialogButtonContainer.getChildren().add(this.buildExitApplicationDialogButton());
    dialogButtonContainer.getChildren().add(this.buildInfoDialogButton());

    return dialogButtonContainer;
  }

  /**
   * Builds exit application dialog button.
   *
   * @return Button : exit application dialog button
   */
  private Button buildExitApplicationDialogButton()
  {
    var exitApplicationDialogButton = new Button();

    exitApplicationDialogButton.getStyleClass().addAll("open-dialog-button", "exit-application-dialog-button");
    exitApplicationDialogButton.setOnAction(new ExitApplicationDialogButtonHandler());

    return exitApplicationDialogButton;
  }

  /**
   * Builds info dialog button.
   *
   * @return Button : info dialog button
   */
  private Button buildInfoDialogButton()
  {
    var infoDialogButton = new Button();

    infoDialogButton.getStyleClass().addAll("open-dialog-button", "info-dialog-button");
    infoDialogButton.setOnAction(new InfoDialogButtonHandler());

    return infoDialogButton;
  }

  /**
   * Builds inner container with {@link #buildTitle}, {@link #buildDifficultyContainer}, and
   * {@link #buildStartButton}.
   *
   * @return VBox : inner container
   */
  private VBox buildInnerContainer()
  {
    var innerContainer = new VBox();

    innerContainer.getStyleClass().add("main-menu-inner-container");
    innerContainer.getChildren().add(this.buildTitle());
    innerContainer.getChildren().add(this.buildDifficultyContainer());
    innerContainer.getChildren().add(this.buildStartButton());

    return innerContainer;
  }

  /**
   * Builds title.
   *
   * @return Label : title
   */
  private Label buildTitle()
  {
    var title = new Label("Do You Sudoku?");

    title.getStyleClass().add("main-menu-title");

    return title;
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
    difficultyDropdown.setOnAction(new MainMenuDifficultyDropdownHandler());

    return difficultyDropdown;
  }

  /**
   * Builds start button.
   *
   * @return Button : start button
   */
  private Button buildStartButton()
  {
    var startButton = new Button("Start");

    startButton.getStyleClass().add("start-button");
    startButton.setOnAction(new StartButtonHandler());

    return startButton;
  }

  /** Public Helper Methods **/

  /**
   * Loads {@link ExitApplicationDialog} on screen.
   */
  public void openExitApplicationDialog()
  {
    this.getChildren().add(new ExitApplicationDialog());
  }

  /**
   * Loads {@link InfoDialog} on screen.
   */
  public void openInfoDialog()
  {
    this.getChildren().add(new InfoDialog());
  }

  /**
   * Loads {@link ErrorDialog} on screen.
   */
  public void openErrorDialog()
  {
    // TODO: change from boolean to enum for argument ?
    this.getChildren().add(new ErrorDialog(true));
  }

  /**
   * Removes dialog from screen.
   */
  public void closeDialog()
  {
    // TODO: better way to do this ? ensure greater than 1 children size ?
    this.getChildren().remove(this.getChildren().size() - 1);
  }
}
