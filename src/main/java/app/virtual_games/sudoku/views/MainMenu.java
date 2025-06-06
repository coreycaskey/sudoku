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
    this.setId("main-menu");
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
    var dropdown = GameController.getPuzzleDifficultyDropdown();

    dropdown.getSelectionModel().select(GameController.getCurrentDifficultyLabel());
    dropdown.setOnAction(new MainMenuDifficultyDropdownHandler());

    return dropdown;
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
   * Opens {@link ExitApplicationDialog}.
   */
  public void openExitApplicationDialog()
  {
    this.getChildren().add(new ExitApplicationDialog());
  }

  /**
   * Opens {@link InfoDialog}.
   */
  public void openInfoDialog()
  {
    this.getChildren().add(new InfoDialog());
  }

  /**
   * Opens {@link ErrorDialog}.
   */
  public void openErrorDialog()
  {
    this.getChildren().add(new ErrorDialog());
  }

  /**
   * Removes dialog from screen.
   */
  public void closeDialog()
  {
    this.getChildren().remove(this.getChildren().size() - 1);
  }
}
