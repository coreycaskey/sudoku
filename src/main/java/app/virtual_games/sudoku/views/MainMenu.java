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
 *
 * Loads the Main Menu for the JavaFX application.
 *
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class MainMenu extends StackPane
{
  /**
   *
   * Loads the Main Menu with an outer container.
   *
   */
  public MainMenu()
  {
    this.getStyleClass().add("app-background");
    this.getChildren().add(this.loadMainMenuOuterContainer());
  }


  /**  Private Helper Methods  **/


  /**
   *
   * Loads the Main Menu outer container with the following UI element(s):
   *
   * Open Dialog Button Container —> {@link #loadOpenDialogButtonContainer()}
   * Main Menu Inner Container —> {@link #loadMainMenuInnerContainer()}
   *
   * @return VBox : Main Menu outer container
   *
   */
  private VBox loadMainMenuOuterContainer()
  {
    var mainMenuOuterContainer = new VBox();

    mainMenuOuterContainer.getChildren().add(this.loadOpenDialogButtonContainer());
    mainMenuOuterContainer.getChildren().add(this.loadMainMenuInnerContainer());

    return mainMenuOuterContainer;
  }


  /**
   *
   * Loads the open dialog button container with the following UI element(s):
   *
   * Exit Application Dialog Button —> {@link #loadExitApplicationDialogButton()}
   * Info Dialog Button —> {@link #loadInfoDialogButton()}
   *
   * @return HBox : open dialog button container
   *
   */
  private HBox loadOpenDialogButtonContainer()
  {
    var openDialogButtonContainer = new HBox();

    openDialogButtonContainer.getStyleClass().add("open-dialog-button-container");
    openDialogButtonContainer.getChildren().add(this.loadExitApplicationDialogButton());
    openDialogButtonContainer.getChildren().add(this.loadInfoDialogButton());

    return openDialogButtonContainer;
  }


  /**
   *
   * Loads the Exit Application dialog button with the {@link ExitApplicationDialogButtonHandler}.
   *
   * @return Button : Exit Application dialog button
   *
   */
  private Button loadExitApplicationDialogButton()
  {
    var exitApplicationDialogButton = new Button();

    exitApplicationDialogButton.getStyleClass().addAll("open-dialog-button", "exit-application-dialog-button");
    exitApplicationDialogButton.setOnAction(new ExitApplicationDialogButtonHandler());

    return exitApplicationDialogButton;
  }


  /**
   *
   * Loads the Info dialog button with the {@link InfoDialogButtonHandler}.
   *
   * @return Button : Info dialog button
   *
   */
  private Button loadInfoDialogButton()
  {
    var infoDialogButton = new Button();

    infoDialogButton.getStyleClass().addAll("open-dialog-button", "info-dialog-button");
    infoDialogButton.setOnAction(new InfoDialogButtonHandler());

    return infoDialogButton;
  }


  /**
   *
   * Loads the Main Menu inner container with the following UI element(s):
   *
   * Main Menu Title —> {@link #loadMainMenuTitle()}
   * Main Menu Difficulty Container —> {@link #loadMainMenuDifficultyContainer()}
   * Start Button —> {@link #loadStartButton()}
   *
   * @return VBox : Main Menu inner container
   *
   */
  private VBox loadMainMenuInnerContainer()
  {
    var mainMenuInnerContainer = new VBox();

    mainMenuInnerContainer.getStyleClass().add("main-menu-inner-container");
    mainMenuInnerContainer.getChildren().add(this.loadMainMenuTitle());
    mainMenuInnerContainer.getChildren().add(this.loadMainMenuDifficultyContainer());
    mainMenuInnerContainer.getChildren().add(this.loadStartButton());

    return mainMenuInnerContainer;
  }


  /**
   *
   * Loads the Main Menu title.
   *
   * @return Label : Main Menu title
   *
   */
  private Label loadMainMenuTitle()
  {
    var mainMenuTitle = new Label("Do You Sudoku?");

    mainMenuTitle.getStyleClass().add("main-menu-title");

    return mainMenuTitle;
  }


  /**
   *
   * Loads the Main Menu difficulty container with the following UI element(s):
   *
   * Main Menu Difficulty Label —> {@link #loadMainMenuDifficultyLabel()}
   * Main Menu Difficulty Dropdown —> {@link #loadMainMenuDifficultyDropdown()}
   *
   * @return HBox : Main Menu difficulty container
   *
   */
  private HBox loadMainMenuDifficultyContainer()
  {
    var mainMenuDifficultyContainer = new HBox();

    mainMenuDifficultyContainer.getStyleClass().add("difficulty-container");
    mainMenuDifficultyContainer.getChildren().add(this.loadMainMenuDifficultyLabel());
    mainMenuDifficultyContainer.getChildren().add(this.loadMainMenuDifficultyDropdown());

    return mainMenuDifficultyContainer;
  }


  /**
   *
   * Loads the Main Menu difficulty label.
   *
   * @return Label : Main Menu difficulty label
   *
   */
  private Label loadMainMenuDifficultyLabel()
  {
    var mainMenuDifficultyLabel = new Label("Puzzle Difficulty: ");

    mainMenuDifficultyLabel.getStyleClass().add("difficulty-label");

    return mainMenuDifficultyLabel;
  }


  /**
   *
   * Loads the Main Menu difficulty dropdown with the {@link MainMenuDifficultyDropdownHandler}.
   *
   * @return ComboBox<String> : Main Menu difficulty dropdown
   *
   */
  private ComboBox<String> loadMainMenuDifficultyDropdown()
  {
    var mainMenuDifficultyDropdown = new ComboBox<String>(GameController.getPuzzleDifficulties());

    // ComboBox default style classes are .combo-box-base and .combo-box

    mainMenuDifficultyDropdown.getSelectionModel().select(GameController.getCurrentDifficultyName());
    mainMenuDifficultyDropdown.setOnAction(new MainMenuDifficultyDropdownHandler());

    return mainMenuDifficultyDropdown;
  }


  /**
   *
   * Loads the start button with the {@link StartButtonHandler}.
   *
   * @return Button : start button
   *
   */
  private Button loadStartButton()
  {
    var startButton = new Button("Start");

    startButton.getStyleClass().add("start-button");
    startButton.setOnAction(new StartButtonHandler());

    return startButton;
  }


  /**  Public Helper Methods  **/


  /**
   *
   * Loads the Exit Application dialog into the Main Menu.
   *
   */
  public void loadExitApplicationDialog()
  {
    this.getChildren().add(new ExitApplicationDialog());
  }


  /**
   *
   * Loads the Error dialog into the Main Menu.
   *
   */
  public void loadErrorDialog()
  {
    this.getChildren().add(new ErrorDialog(true));
  }


  /**
   *
   * Loads the Info dialog into the Main Menu.
   *
   */
  public void loadInfoDialog()
  {
    this.getChildren().add(new InfoDialog());
  }


  /**
   *
   * Removes the dialog from the Main Menu.
   *
   */
  public void closeDialog()
  {
    this.getChildren().remove(this.getChildren().size() - 1);
  }
}
