package app.virtual_games.sudoku.views;

import app.virtual_games.sudoku.controllers.GameController;

import app.virtual_games.sudoku.handlers.CloseMainMenuDialogHandler;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


/**
 *
 * Loads the Info dialog for the JavaFX application.
 *
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class InfoDialog extends Dialog
{
  private static String infoDialogRowClass = "info-dialog-row";
  private static String infoDialogElementContainerClass = "info-dialog-element-container";
  private static String infoDialogInstructionsContainerClass = "info-dialog-instructions-container";
  private static String infoDialogInstructionsClass = "info-dialog-instructions";
  private static String infoDialogLargeRowClass = "info-dialog-large-row";
  private static String gameButtonClass = "game-button";
  private static String infoDialogInstructionsButtonClass = "info-dialog-instructions-button";

  /**
   *
   * Loads the parent {@link Dialog} with the Info dialog elements.
   *
   */
  public InfoDialog()
  {
    super();

    this.addDialogStyling("large-dialog");
    this.addContentContainer(this.loadInfoDialogOuterContentContainer());
    this.addCloseDialogButtonContainerStyling("large-close-dialog-button-container");
    this.addCloseDialogButtonHandler(new CloseMainMenuDialogHandler());
  }


  /**  Private Helper Methods  **/


  /**
   *
   * Loads the Info dialog outer content container with the following UI element(s):
   *
   * Info Dialog Outer Content Pane —> {@link #loadInfoDialogOuterContentPane()}
   *
   * @return VBox : Info dialog outer content container
   *
   */
  private VBox loadInfoDialogOuterContentContainer()
  {
    var infoDialogOuterContentContainer = new VBox();

    infoDialogOuterContentContainer.getChildren().add(this.loadInfoDialogOuterContentPane());

    return infoDialogOuterContentContainer;
  }


  /**
   *
   * Loads the Info dialog outer content pane with the following UI element(s):
   *
   * Info Dialog Inner Content Container —> {@link #loadInfoDialogInnerContentContainer()}
   * Info Dialog Inner Content Pane —> {@link #loadInfoDialogInnerContentPane()}
   *
   * @return StackPane : Info dialog outer content pane
   *
   */
  private StackPane loadInfoDialogOuterContentPane()
  {
    var infoDialogOuterContentPane = new StackPane();

    infoDialogOuterContentPane.getChildren().add(this.loadInfoDialogInnerContentContainer());
    infoDialogOuterContentPane.getChildren().add(this.loadInfoDialogInnerContentPane());

    return infoDialogOuterContentPane;
  }


  /**
   *
   * Loads the Info dialog inner content container with the following UI element(s):
   *
   * Info Dialog Title —> {@link #loadInfoDialogTitle()}
   * Info Dialog Difficulty Container —> {@link #loadInfoDialogDifficultyContainer()}
   * Info Dialog Timer Container —> {@link #loadInfoDialogTimerContainer()}
   * Info Dialog Writing Tool Container —> {@link #loadInfoDialogWritingToolContainer()}
   * Info Dialog Hint Container —> {@link #loadInfoDialogHintContainer()}
   * Info Dialog Restart Container —> {@link #loadInfoDialogRestartContainer()}
   * Info Dialog New Puzzle Container —> {@link #loadInfoDialogNewPuzzleContainer()}
   * Info Dialog Number Container —> {@link #loadInfoDialogNumberContainer()}
   *
   * @return VBox : Info dialog inner content container
   *
   */
  private VBox loadInfoDialogInnerContentContainer()
  {
    var infoDialogInnerContentContainer = new VBox();

    infoDialogInnerContentContainer.getStyleClass().addAll("content-container", "large-content-container", "info-dialog-content-container");
    infoDialogInnerContentContainer.getChildren().add(this.loadInfoDialogTitle());
    infoDialogInnerContentContainer.getChildren().add(this.loadInfoDialogDifficultyContainer());
    infoDialogInnerContentContainer.getChildren().add(this.loadInfoDialogTimerContainer());
    infoDialogInnerContentContainer.getChildren().add(this.loadInfoDialogWritingToolContainer());
    infoDialogInnerContentContainer.getChildren().add(this.loadInfoDialogHintContainer());
    infoDialogInnerContentContainer.getChildren().add(this.loadInfoDialogRestartContainer());
    infoDialogInnerContentContainer.getChildren().add(this.loadInfoDialogNewPuzzleContainer());
    infoDialogInnerContentContainer.getChildren().add(this.loadInfoDialogNumberContainer());

    return infoDialogInnerContentContainer;
  }


  /**
   *
   * Loads the Info dialog title.
   *
   * @return Label : Info dialog title
   *
   */
  private Label loadInfoDialogTitle()
  {
    var infoDialogTitle = new Label("Sudoku Overview");

    infoDialogTitle.getStyleClass().add("dialog-title");

    return infoDialogTitle;
  }


  /**
   *
   * Loads the Info dialog difficulty container with the following UI element(s):
   *
   * Info Dialog Difficulty Dropdown Container —> {@link #loadInfoDialogDifficultyDropdownContainer()}
   * Info Dialog Difficulty Instructions Container —> {@link #loadInfoDialogDifficultyInstructionsContainer()}
   *
   * @return HBox : Info dialog difficulty container
   *
   */
  private HBox loadInfoDialogDifficultyContainer()
  {
    var infoDialogDifficultyContainer = new HBox();

    infoDialogDifficultyContainer.getStyleClass().addAll(infoDialogRowClass, "info-dialog-small-row");
    infoDialogDifficultyContainer.getChildren().add(this.loadInfoDialogDifficultyDropdownContainer());
    infoDialogDifficultyContainer.getChildren().add(this.loadInfoDialogDifficultyInstructionsContainer());

    return infoDialogDifficultyContainer;
  }


  /**
   *
   * Loads the Info dialog difficulty dropdown container with the following UI element(s):
   *
   * Info Dialog Difficulty Dropdown —> {@link #loadInfoDialogDifficultyDropdown()}
   *
   * @return HBox : Info dialog difficulty dropdown container
   *
   */
  private HBox loadInfoDialogDifficultyDropdownContainer()
  {
    var infoDialogDifficultyDropdownContainer = new HBox();

    infoDialogDifficultyDropdownContainer.getStyleClass().add(infoDialogElementContainerClass);
    infoDialogDifficultyDropdownContainer.getChildren().add(this.loadInfoDialogDifficultyDropdown());

    return infoDialogDifficultyDropdownContainer;
  }


  /**
   *
   * Loads the Info dialog difficulty dropdown.
   *
   * @return ComboBox<String> : Info dialog difficulty dropdown
   *
   */
  private ComboBox<String> loadInfoDialogDifficultyDropdown()
  {
    var infoDialogDifficultyDropdown = new ComboBox<String>(GameController.getPuzzleDifficulties());

    infoDialogDifficultyDropdown.getStyleClass().add("info-dialog-difficulty-dropdown");
    infoDialogDifficultyDropdown.getSelectionModel().select("Easy");

    return infoDialogDifficultyDropdown;
  }


  /**
   *
   * Loads the Info dialog difficulty instructions container with the following UI element(s):
   *
   * Info Dialog Difficulty Instructions —> {@link #loadInfoDialogDifficultyInstructions()}
   *
   * @return HBox : Info dialog difficulty instructions container
   *
   */
  private HBox loadInfoDialogDifficultyInstructionsContainer()
  {
    var infoDialogDifficultyInstructionsContainer = new HBox();

    infoDialogDifficultyInstructionsContainer.getStyleClass().add(infoDialogInstructionsContainerClass);
    infoDialogDifficultyInstructionsContainer.getChildren().add(this.loadInfoDialogDifficultyInstructions());

    return infoDialogDifficultyInstructionsContainer;
  }


  /**
   *
   * Loads the Info dialog difficulty instructions.
   *
   * @return Label : Info dialog difficulty instructions
   *
   */
  private Label loadInfoDialogDifficultyInstructions()
  {
    var infoDialogDifficultyInstructions = new Label();

    infoDialogDifficultyInstructions.getStyleClass().add(infoDialogInstructionsClass);
    infoDialogDifficultyInstructions.setText("This dropdown will load a new sudoku puzzle of the selected difficulty.");

    return infoDialogDifficultyInstructions;
  }


  /**
   *
   * Loads the Info dialog timer container with the following UI element(s):
   *
   * Info Dialog Timer Button Container —> {@link #loadInfoDialogTimerButtonContainer()}
   * Info Dialog Timer Instructions Container —> {@link #loadInfoDialogTimerInstructionsContainer()}
   *
   * @return HBox : Info dialog timer container
   *
   */
  private HBox loadInfoDialogTimerContainer()
  {
    var infoDialogTimerContainer = new HBox();

    infoDialogTimerContainer.getStyleClass().addAll(infoDialogRowClass, "info-dialog-small-row");
    infoDialogTimerContainer.getChildren().add(this.loadInfoDialogTimerButtonContainer());
    infoDialogTimerContainer.getChildren().add(this.loadInfoDialogTimerInstructionsContainer());

    return infoDialogTimerContainer;
  }


  /**
   *
   * Loads the Info dialog timer button container with the following UI element(s):
   *
   * Info Dialog Play Button —> {@link #loadInfoDialogPlayButton()}
   * Info Dialog Pause Button —> {@link #loadInfoDialogPauseButton()}
   *
   * @return HBox : Info dialog timer button container
   *
   */
  private HBox loadInfoDialogTimerButtonContainer()
  {
    var infoDialogTimerInstructionsContainer = new HBox();

    infoDialogTimerInstructionsContainer.getStyleClass().addAll(infoDialogElementContainerClass);
    infoDialogTimerInstructionsContainer.getChildren().add(this.loadInfoDialogPlayButton());
    infoDialogTimerInstructionsContainer.getChildren().add(this.loadInfoDialogPauseButton());

    return infoDialogTimerInstructionsContainer;
  }


  /**
   *
   * Loads the Info dialog play button.
   *
   * @return Button : Info dialog play button
   *
   */
  private Button loadInfoDialogPlayButton()
  {
    var playButton = new Button();

    playButton.getStyleClass().addAll("timer-button", "play-button");

    return playButton;
  }


  /**
   *
   * Loads the Info dialog pause button.
   *
   * @return Button : Info dialog pause button
   *
   */
  private Button loadInfoDialogPauseButton()
  {
    var pauseButton = new Button();

    pauseButton.getStyleClass().addAll("timer-button", "pause-button");

    return pauseButton;
  }


  /**
   *
   * Loads the Info dialog timer instructions container with the following UI element(s):
   *
   * Info Dialog Timer Instructions —> {@link #loadInfoDialogTimerInstructions()}
   *
   * @return HBox : Info dialog timer instructions container
   *
   */
  private HBox loadInfoDialogTimerInstructionsContainer()
  {
    var infoDialogTimerInstructionsContainer = new HBox();

    infoDialogTimerInstructionsContainer.getStyleClass().add(infoDialogInstructionsContainerClass);
    infoDialogTimerInstructionsContainer.getChildren().add(this.loadInfoDialogTimerInstructions());

    return infoDialogTimerInstructionsContainer;
  }


  /**
   *
   * Loads the Info dialog timer instructions.
   *
   * @return Label : Info dialog timer instructions
   *
   */
  private Label loadInfoDialogTimerInstructions()
  {
    var infoDialogTimerInstructions = new Label();

    infoDialogTimerInstructions.getStyleClass().add(infoDialogInstructionsClass);
    infoDialogTimerInstructions.setText("These buttons will pause and resume your game progress.");

    return infoDialogTimerInstructions;
  }


  /**
   *
   * Loads the Info dialog writing tool container with the following UI element(s):
   *
   * Info Dialog Writing Tool Button Container —> {@link #loadInfoDialogWritingToolButtonContainer()}
   * Info Dialog Writing Tool Instructions Container —> {@link #loadInfoDialogWritingToolInstructionsContainer()}
   *
   * @return HBox : Info dialog writing tool container
   *
   */
  private HBox loadInfoDialogWritingToolContainer()
  {
    var infoDialogWritingToolContainer = new HBox();

    infoDialogWritingToolContainer.getStyleClass().addAll(infoDialogRowClass, infoDialogLargeRowClass);
    infoDialogWritingToolContainer.getChildren().add(this.loadInfoDialogWritingToolButtonContainer());
    infoDialogWritingToolContainer.getChildren().add(this.loadInfoDialogWritingToolInstructionsContainer());

    return infoDialogWritingToolContainer;
  }


  /**
   *
   * Loads the Info dialog writing tool button container with the following UI element(s):
   *
   * Pen Button —> {@link #loadInfoDialogPenButton()}
   *
   * @return VBox : Info dialog writing tool button container
   *
   */
  private VBox loadInfoDialogWritingToolButtonContainer()
  {
    var infoDialogWritingToolButtonContainer = new VBox();

    infoDialogWritingToolButtonContainer.getStyleClass().add(infoDialogElementContainerClass);
    infoDialogWritingToolButtonContainer.getChildren().add(this.loadInfoDialogPenButton());

    return infoDialogWritingToolButtonContainer;
  }


  /**
   *
   * Loads the Info dialog pen button.
   *
   * @return Button : Info dialog pen button
   *
   */
  private Button loadInfoDialogPenButton()
  {
    var penButton = new Button("Pen");

    penButton.setGraphic(new ImageView(new Image(this.getClass().getResourceAsStream("../../../../img/pen-icon.png"), 30, 30, true, true)));
    penButton.getStyleClass().addAll(gameButtonClass, infoDialogInstructionsButtonClass);

    return penButton;
  }


  /**
   *
   * Loads the Info dialog writing tool instructions container with the following UI element(s):
   *
   * Info Dialog Writing Tool Instructions —> {@link #loadInfoDialogWritingToolInstructions()}
   *
   * @return HBox : Info dialog writing tool instructions container
   *
   */
  private HBox loadInfoDialogWritingToolInstructionsContainer()
  {
    var writingToolInstructionsContainer = new HBox();

    writingToolInstructionsContainer.getStyleClass().add(infoDialogInstructionsContainerClass);
    writingToolInstructionsContainer.getChildren().add(this.loadInfoDialogWritingToolInstructions());

    return writingToolInstructionsContainer;
  }


  /**
   *
   * Loads the Info dialog writing tool instructions.
   *
   * @return Label : Info dialog writing tool instructions
   *
   */
  private Label loadInfoDialogWritingToolInstructions()
  {
    var infoDialogWritingToolInstructions = new Label();

    infoDialogWritingToolInstructions.getStyleClass().add(infoDialogInstructionsClass);
    infoDialogWritingToolInstructions.setText("The pen, pencil, and eraser buttons will change the current writing tool. "
        + "Use the pen for permanent guesses. (Be careful —— there is a time penalty for incorrect inputs.) "
        + "Use the pencil for non-permanent guesses. "
        + "Use the eraser for clearing cells that contain incorrect values or notes.");

    return infoDialogWritingToolInstructions;
  }


  /**
   *
   * Loads the Info dialog hint container with the following UI element(s):
   *
   * Info Dialog Hint Button Container —> {@link #loadInfoDialogHintButtonContainer()}
   * Info Dialog Hint Instructions Container —> {@link #loadInfoDialogHintInstructionsContainer()}
   *
   * @return HBox : Info dialog hint container
   *
   */
  private HBox loadInfoDialogHintContainer()
  {
    var infoDialogHintContainer = new HBox();

    infoDialogHintContainer.getStyleClass().addAll(infoDialogRowClass, infoDialogLargeRowClass);
    infoDialogHintContainer.getChildren().add(this.loadInfoDialogHintButtonContainer());
    infoDialogHintContainer.getChildren().add(this.loadInfoDialogHintInstructionsContainer());

    return infoDialogHintContainer;
  }


  /**
   *
   * Loads the Info dialog hint button container with the following UI element(s):
   *
   * Info Dialog Hint Button —> {@link #loadInfoDialogHintButton()}
   *
   * @return HBox : Info dialog hint button container
   *
   */
  private HBox loadInfoDialogHintButtonContainer()
  {
    var infoDialogHintButtonContainer = new HBox();

    infoDialogHintButtonContainer.getStyleClass().addAll(infoDialogElementContainerClass);
    infoDialogHintButtonContainer.getChildren().add(this.loadInfoDialogHintButton());

    return infoDialogHintButtonContainer;
  }


  /**
   *
   * Loads the Info dialog hint button.
   *
   * @return Button : Info dialog hint button
   *
   */
  private Button loadInfoDialogHintButton()
  {
    var infoDialogHintButton = new Button("Hint");

    infoDialogHintButton.setGraphic(new ImageView(new Image(this.getClass().getResourceAsStream("../../../../img/hint-icon.png"), 30, 30, false, true)));
    infoDialogHintButton.getStyleClass().addAll(gameButtonClass, infoDialogInstructionsButtonClass);

    return infoDialogHintButton;
  }


  /**
   *
   * Loads the Info dialog hint instructions container with the following UI element(s):
   *
   * Info Dialog Hint Instructions —> {@link #loadInfoDialogHintInstructions()}
   *
   * @return HBox : Info dialog hint instructions container
   *
   */
  private HBox loadInfoDialogHintInstructionsContainer()
  {
    var infoDialogHintInstructionsContainer = new HBox();

    infoDialogHintInstructionsContainer.getStyleClass().add(infoDialogInstructionsContainerClass);
    infoDialogHintInstructionsContainer.getChildren().add(this.loadInfoDialogHintInstructions());

    return infoDialogHintInstructionsContainer;
  }


  /**
   *
   * Loads the Info dialog hint instructions.
   *
   * @return Label : Info dialog hint instructions
   *
   */
  private Label loadInfoDialogHintInstructions()
  {
    var infoDialogHintInstructions = new Label();

    infoDialogHintInstructions.getStyleClass().add(infoDialogInstructionsClass);
    infoDialogHintInstructions.setText("This button will give you sudoku puzzle hints, though you will also incur a time penalty.");

    return infoDialogHintInstructions;
  }


  /**
   *
   * Loads the Info dialog restart container with the following UI element(s):
   *
   * Info Dialog Restart Button Container —> {@link #loadInfoDialogRestartButtonContainer()}
   * Info Dialog Restart Instructions Container —> {@link #loadInfoDialogRestartInstructionsContainer()}
   *
   * @return HBox : Info dialog restart container
   *
   */
  private HBox loadInfoDialogRestartContainer()
  {
    var infoDialogRestartContainer = new HBox();

    infoDialogRestartContainer.getStyleClass().addAll(infoDialogRowClass, infoDialogLargeRowClass);
    infoDialogRestartContainer.getChildren().add(this.loadInfoDialogRestartButtonContainer());
    infoDialogRestartContainer.getChildren().add(this.loadInfoDialogRestartInstructionsContainer());

    return infoDialogRestartContainer;
  }


  /**
   *
   * Loads the Info dialog restart button container with the following UI element(s):
   *
   * Info Dialog Restart Button —> {@link #loadInfoDialogRestartButton()}
   *
   * @return HBox : Info dialog restart button container
   *
   */
  private HBox loadInfoDialogRestartButtonContainer()
  {
    var infoDialogRestartButtonContainer = new HBox();

    infoDialogRestartButtonContainer.getStyleClass().addAll(infoDialogElementContainerClass);
    infoDialogRestartButtonContainer.getChildren().add(this.loadInfoDialogRestartButton());

    return infoDialogRestartButtonContainer;
  }


  /**
   *
   * Loads the Info dialog restart button.
   *
   * @return Button : Info dialog restart button
   *
   */
  private Button loadInfoDialogRestartButton()
  {
    var infoDialogRestartButton = new Button("Restart");

    infoDialogRestartButton.setGraphic(new ImageView(new Image(this.getClass().getResourceAsStream("../../../../img/restart-icon.png"), 30, 30, true, true)));
    infoDialogRestartButton.getStyleClass().addAll(gameButtonClass, "restart-button", infoDialogInstructionsButtonClass);

    return infoDialogRestartButton;
  }


  /**
   *
   * Loads the Info dialog restart instructions container with the following UI element(s):
   *
   * Info Dialog Restart Instructions —> {@link #loadInfoDialogRestartInstructions()}
   *
   * @return HBox : Info dialog restart instructions container
   *
   */
  private HBox loadInfoDialogRestartInstructionsContainer()
  {
    var infoDialogRestartInstructionsContainer = new HBox();

    infoDialogRestartInstructionsContainer.getStyleClass().add(infoDialogInstructionsContainerClass);
    infoDialogRestartInstructionsContainer.getChildren().add(this.loadInfoDialogRestartInstructions());

    return infoDialogRestartInstructionsContainer;
  }


  /**
   *
   * Loads the Info dialog restart instructions.
   *
   * @return Label : Info dialog restart instructions
   *
   */
  private Label loadInfoDialogRestartInstructions()
  {
    var infoDialogRestartInstructions = new Label();

    infoDialogRestartInstructions.getStyleClass().add(infoDialogInstructionsClass);
    infoDialogRestartInstructions.setText("This button will reset the sudoku puzzle but won't reset the game timer.");

    return infoDialogRestartInstructions;
  }


  /**
   *
   * Loads the Info dialog new puzzle container with the following UI element(s):
   *
   * Info Dialog New Puzzle Button Container —> {@link #loadInfoDialogNewPuzzleButtonContainer()}
   * Info Dialog New Puzzle Instructions Container —> {@link #loadInfoDialogNewPuzzleInstructionsContainer()}
   *
   * @return HBox : Info dialog new puzzle container
   *
   */
  private HBox loadInfoDialogNewPuzzleContainer()
  {
    var infoDialogNewPuzzleContainer = new HBox();

    infoDialogNewPuzzleContainer.getStyleClass().addAll(infoDialogRowClass, infoDialogLargeRowClass);
    infoDialogNewPuzzleContainer.getChildren().add(this.loadInfoDialogNewPuzzleButtonContainer());
    infoDialogNewPuzzleContainer.getChildren().add(this.loadInfoDialogNewPuzzleInstructionsContainer());

    return infoDialogNewPuzzleContainer;
  }


  /**
   *
   * Loads the Info dialog new puzzle button container with the following UI element(s):
   *
   * Info Dialog New Puzzle Button —> {@link #loadInfoDialogNewPuzzleButton()}
   *
   * @return HBox : Info dialog new puzzle button container
   *
   */
  private HBox loadInfoDialogNewPuzzleButtonContainer()
  {
    var infoDialogNewPuzzleButtonContainer = new HBox();

    infoDialogNewPuzzleButtonContainer.getStyleClass().addAll(infoDialogElementContainerClass);
    infoDialogNewPuzzleButtonContainer.getChildren().add(this.loadInfoDialogNewPuzzleButton());

    return infoDialogNewPuzzleButtonContainer;
  }


  /**
   *
   * Loads the Info dialog new puzzle button.
   *
   * @return Button : Info dialog new puzzle button
   *
   */
  private Button loadInfoDialogNewPuzzleButton()
  {
    var infoDialogNewPuzzleButton = new Button("New Game");

    infoDialogNewPuzzleButton.setGraphic(new ImageView(new Image(this.getClass().getResourceAsStream("../../../../img/new-puzzle-icon.png"), 30, 30, true, true)));
    infoDialogNewPuzzleButton.getStyleClass().addAll(gameButtonClass, "new-puzzle-button", infoDialogInstructionsButtonClass);

    return infoDialogNewPuzzleButton;
  }


  /**
   *
   * Loads the Info dialog new puzzle instructions container with the following UI element(s):
   *
   * Info Dialog New Puzzle Instructions —> {@link #loadInfoDialogNewPuzzleInstructions()}
   *
   * @return HBox : Info dialog new puzzle instructions
   *
   */
  private HBox loadInfoDialogNewPuzzleInstructionsContainer()
  {
    var infoDialogNewPuzzleInstructionsContainer = new HBox();

    infoDialogNewPuzzleInstructionsContainer.getStyleClass().add(infoDialogInstructionsContainerClass);
    infoDialogNewPuzzleInstructionsContainer.getChildren().add(this.loadInfoDialogNewPuzzleInstructions());

    return infoDialogNewPuzzleInstructionsContainer;
  }


  /**
   *
   * Loads the Info dialog new puzzle instructions.
   *
   * @return Label : Info dialog new puzzle instructions
   *
   */
  private Label loadInfoDialogNewPuzzleInstructions()
  {
    var infoDialogNewPuzzleInstructions = new Label();

    infoDialogNewPuzzleInstructions.getStyleClass().add(infoDialogInstructionsClass);
    infoDialogNewPuzzleInstructions.setText("This button will load a new sudoku puzzle of the current difficulty.");

    return infoDialogNewPuzzleInstructions;
  }


  /**
   *
   * Loads the Info dialog number container with the following UI element(s):
   *
   * Info Dialog Number Button Container —> {@link #loadInfoDialogNumberButtonContainer()}
   * Info Dialog Number Instructions Container —> {@link #loadInfoDialogNumberInstructionsContainer()}
   *
   * @return HBox : Info dialog number container
   *
   */
  private HBox loadInfoDialogNumberContainer()
  {
    var infoDialogNumberContainer = new HBox();

    infoDialogNumberContainer.getStyleClass().addAll(infoDialogRowClass, infoDialogLargeRowClass);
    infoDialogNumberContainer.getChildren().add(this.loadInfoDialogNumberButtonContainer());
    infoDialogNumberContainer.getChildren().add(this.loadInfoDialogNumberInstructionsContainer());

    return infoDialogNumberContainer;
  }


  /**
   *
   * Loads the Info dialog number button container with the following UI element(s):
   *
   * Info Dialog Number Button —> {@link #loadInfoDialogNumberButton()}
   *
   * @return HBox : Info dialog number button container
   *
   */
  private HBox loadInfoDialogNumberButtonContainer()
  {
    var infoDialogNumberButtonContainer = new HBox();

    infoDialogNumberButtonContainer.getStyleClass().add(infoDialogElementContainerClass);
    infoDialogNumberButtonContainer.getChildren().add(this.loadInfoDialogNumberButton());

    return infoDialogNumberButtonContainer;
  }


  /**
   *
   * Loads the Info dialog number button.
   *
   * @return Button : Info dialog number button
   *
   */
  private Button loadInfoDialogNumberButton()
  {
    var infoDialogNumberButton = new Button("#");

    infoDialogNumberButton.getStyleClass().addAll(gameButtonClass, "number-button", "info-dialog-number-button");

    return infoDialogNumberButton;
  }


  /**
   *
   * Loads the Info dialog number instructions container with the following UI element(s):
   *
   * Info Dialog Number Instructions —> {@link #loadInfoDialogNumberInstructions()}
   *
   * @return HBox : Info dialog number instructions container
   *
   */
  private HBox loadInfoDialogNumberInstructionsContainer()
  {
    var infoDialogNumberInstructionsContainer = new HBox();

    infoDialogNumberInstructionsContainer.getStyleClass().add(infoDialogInstructionsContainerClass);
    infoDialogNumberInstructionsContainer.getChildren().add(this.loadInfoDialogNumberInstructions());

    return infoDialogNumberInstructionsContainer;
  }


  /**
   *
   * Loads the Info dialog number instructions.
   *
   * @return Label : Info dialog number instructions
   *
   */
  private Label loadInfoDialogNumberInstructions()
  {
    var infoDialogNumberInstructions = new Label();

    infoDialogNumberInstructions.getStyleClass().add(infoDialogInstructionsClass);
    infoDialogNumberInstructions.setText("These types of buttons (each showing a number between 1 and 9) will display all occurrences of their value in the sudoku puzzle. "
        + "If the button is disabled, all occurrences of that value have been found.");

    return infoDialogNumberInstructions;
  }


  /**
   *
   * Loads the Info dialog inner content pane.
   *
   * @return Pane : Info dialog inner content pane
   *
   */
  private Pane loadInfoDialogInnerContentPane()
  {
    var loadDialogInnerContentPane = new Pane();

    loadDialogInnerContentPane.getStyleClass().add("info-dialog-inner-content-pane");

    return loadDialogInnerContentPane;
  }
}
