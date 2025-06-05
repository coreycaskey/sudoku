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
 * Info dialog.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class InfoDialog extends Dialog
{
  private static String baseRowClass = "info-dialog-row";
  private static String largeRowClass = "info-dialog-large-row";
  private static String elementContainerClass = "info-dialog-element-container";
  private static String instructionsContainerClass = "info-dialog-instructions-container";
  private static String instructionsButtonClass = "info-dialog-instructions-button";
  private static String instructionsContentClass = "info-dialog-instructions-content";
  private static String gameButtonClass = "game-button";

  /**
   * Initializes info dialog.
   */
  public InfoDialog()
  {
    super();
    this.addDialogStyling("large-dialog");
    this.addContentContainer(this.buildOuterContainer());
    this.addCloseButtonContainerStyling("large-close-button-container");
    this.addCloseDialogButtonHandler(new CloseMainMenuDialogHandler());
  }

  /** Private Helper Methods **/

  /**
   * Builds outer container with {@link #buildOuterContentPane}.
   *
   * @return VBox : outer container
   */
  private VBox buildOuterContainer()
  {
    var outerContainer = new VBox();

    outerContainer.getChildren().add(this.buildOuterContentPane());

    return outerContainer;
  }

  /**
   * Builds outer content pane with {@link #buildInnerContainer} and {@link #buildInnerContentPane}.
   *
   * @return StackPane : outer content pane
   */
  private StackPane buildOuterContentPane()
  {
    var outerContentPane = new StackPane();

    outerContentPane.getChildren().add(this.buildInnerContainer());
    outerContentPane.getChildren().add(this.buildInnerContentPane());

    return outerContentPane;
  }

  /**
   * Builds inner container with {@link #buildTitle}, {@link #buildDifficultyContainer},
   * {@link #buildTimerContainer}, {@link #buildWritingToolContainer}, {@link #buildHintContainer},
   * {@link #buildRestartContainer}, {@link #buildNewPuzzleContainer}, {@link #buildNumberContainer}.
   *
   * @return VBox : inner container
   */
  private VBox buildInnerContainer()
  {
    var innerContainer = new VBox();

    innerContainer.getStyleClass().addAll("content-container", "large-content-container",
        "info-dialog-content-container");
    innerContainer.getChildren().add(this.buildTitle());
    innerContainer.getChildren().add(this.buildDifficultyContainer());
    innerContainer.getChildren().add(this.buildTimerContainer());
    innerContainer.getChildren().add(this.buildWritingToolContainer());
    innerContainer.getChildren().add(this.buildHintContainer());
    innerContainer.getChildren().add(this.buildRestartContainer());
    innerContainer.getChildren().add(this.buildNewPuzzleContainer());
    innerContainer.getChildren().add(this.buildNumberContainer());

    return innerContainer;
  }

  /**
   * Builds title.
   *
   * @return Label : title
   */
  private Label buildTitle()
  {
    var title = new Label("Sudoku Overview");

    title.getStyleClass().add("dialog-title");

    return title;
  }

  /**
   * Builds difficulty container with {@link #buildDifficultyDropdownContainer} and
   * {@link #buildDifficultyInstructionsContainer}.
   *
   * @return HBox : difficulty container
   */
  private HBox buildDifficultyContainer()
  {
    var difficultyContainer = new HBox();

    difficultyContainer.getStyleClass().addAll(baseRowClass, "info-dialog-small-row");
    difficultyContainer.getChildren().add(this.buildDifficultyDropdownContainer());
    difficultyContainer.getChildren().add(this.buildDifficultyInstructionsContainer());

    return difficultyContainer;
  }

  /**
   * Builds difficulty dropdown container with {@link #buildDifficultyDropdown}.
   *
   * @return HBox : difficulty dropdown container
   */
  private HBox buildDifficultyDropdownContainer()
  {
    var difficultyDropdownContainer = new HBox();

    difficultyDropdownContainer.getStyleClass().add(elementContainerClass);
    difficultyDropdownContainer.getChildren().add(this.buildDifficultyDropdown());

    return difficultyDropdownContainer;
  }

  /**
   * Builds difficulty dropdown.
   *
   * @return ComboBox<String> : difficulty dropdown
   */
  private ComboBox<String> buildDifficultyDropdown()
  {
    var difficultyDropdown = GameController.getPuzzleDifficultyDropdown();

    difficultyDropdown.getStyleClass().add("info-dialog-difficulty-dropdown");
    difficultyDropdown.getSelectionModel().select("Easy");

    return difficultyDropdown;
  }

  /**
   * Builds difficulty instructions container with {@link #buildDifficultyInstructionsContent}.
   *
   * @return HBox : difficulty instructions container
   */
  private HBox buildDifficultyInstructionsContainer()
  {
    var difficultyInstructionsContainer = new HBox();

    difficultyInstructionsContainer.getStyleClass().add(instructionsContainerClass);
    difficultyInstructionsContainer.getChildren().add(this.buildDifficultyInstructionsContent());

    return difficultyInstructionsContainer;
  }

  /**
   * Builds difficulty instructions content.
   *
   * @return Label : difficulty instructions content
   */
  private Label buildDifficultyInstructionsContent()
  {
    var difficultyInstructions = new Label();

    difficultyInstructions.getStyleClass().add(instructionsContentClass);
    difficultyInstructions.setText("This dropdown will load a new sudoku puzzle of the selected difficulty.");

    return difficultyInstructions;
  }

  /**
   * Builds timer container with {@link #buildTimerButtonContainer} and
   * {@link #buildTimerInstructionsContainer}.
   *
   * @return HBox : timer container
   */
  private HBox buildTimerContainer()
  {
    var timerContainer = new HBox();

    timerContainer.getStyleClass().addAll(baseRowClass, "info-dialog-small-row");
    timerContainer.getChildren().add(this.buildTimerButtonContainer());
    timerContainer.getChildren().add(this.buildTimerInstructionsContainer());

    return timerContainer;
  }

  /**
   * Builds timer button container with {@link #buildPlayButton} and {@link #buildPauseButton}.
   *
   * @return HBox : timer button container
   */
  private HBox buildTimerButtonContainer()
  {
    var timerButtonContainer = new HBox();

    timerButtonContainer.getStyleClass().addAll(elementContainerClass);
    timerButtonContainer.getChildren().add(this.buildPlayButton());
    timerButtonContainer.getChildren().add(this.buildPauseButton());

    return timerButtonContainer;
  }

  /**
   * Builds play button.
   *
   * @return Button : play button
   */
  private Button buildPlayButton()
  {
    var playButton = new Button();

    playButton.getStyleClass().addAll("timer-button", "play-button");

    return playButton;
  }

  /**
   * Builds pause button.
   *
   * @return Button : pause button
   */
  private Button buildPauseButton()
  {
    var pauseButton = new Button();

    pauseButton.getStyleClass().addAll("timer-button", "pause-button");

    return pauseButton;
  }

  /**
   * Builds timer instructions container with {@link #buildTimerInstructions}.
   *
   * @return HBox : timer instructions container
   */
  private HBox buildTimerInstructionsContainer()
  {
    var timerInstructionsContainer = new HBox();

    timerInstructionsContainer.getStyleClass().add(instructionsContainerClass);
    timerInstructionsContainer.getChildren().add(this.buildTimerInstructions());

    return timerInstructionsContainer;
  }

  /**
   * Builds timer instructions.
   *
   * @return Label : timer instructions
   */
  private Label buildTimerInstructions()
  {
    var timerInstructions = new Label();

    timerInstructions.getStyleClass().add(instructionsContentClass);
    timerInstructions.setText("These buttons will pause and resume your game progress.");

    return timerInstructions;
  }

  /**
   * Builds writing tool container with {@link #buildWritingToolButtonContainer} and
   * {@link #buildWritingToolInstructionsContainer}.
   *
   * @return HBox : writing tool container
   */
  private HBox buildWritingToolContainer()
  {
    var writingToolContainer = new HBox();

    writingToolContainer.getStyleClass().addAll(baseRowClass, largeRowClass);
    writingToolContainer.getChildren().add(this.buildWritingToolButtonContainer());
    writingToolContainer.getChildren().add(this.buildWritingToolInstructionsContainer());

    return writingToolContainer;
  }

  /**
   * Builds writing tool button container with {@link #buildPenButton}.
   *
   * @return VBox : writing tool button container
   */
  private VBox buildWritingToolButtonContainer()
  {
    var writingToolButtonContainer = new VBox();

    writingToolButtonContainer.getStyleClass().add(elementContainerClass);
    writingToolButtonContainer.getChildren().add(this.buildPenButton());

    return writingToolButtonContainer;
  }

  /**
   * Builds pen button.
   *
   * @return Button : pen button
   */
  private Button buildPenButton()
  {
    var penButton = new Button("Pen");

    penButton.setGraphic(new ImageView(
        new Image(this.getClass().getResourceAsStream("../../../../img/pen-icon.png"), 30, 30, true, true)));
    penButton.getStyleClass().addAll(gameButtonClass, instructionsButtonClass);

    return penButton;
  }

  /**
   * Builds writing tool instructions container with {@link #buildWritingToolInstructions}.
   *
   * @return HBox : writing tool instructions container
   */
  private HBox buildWritingToolInstructionsContainer()
  {
    var writingToolInstructionsContainer = new HBox();

    writingToolInstructionsContainer.getStyleClass().add(instructionsContainerClass);
    writingToolInstructionsContainer.getChildren().add(this.buildWritingToolInstructions());

    return writingToolInstructionsContainer;
  }

  /**
   * Builds writing tool instructions.
   *
   * @return Label : writing tool instructions
   */
  private Label buildWritingToolInstructions()
  {
    var writingToolInstructions = new Label();

    writingToolInstructions.getStyleClass().add(instructionsContentClass);
    writingToolInstructions.setText("The pen, pencil, and eraser buttons will change the current writing tool. "
        + "Use the pen for permanent guesses. (Be careful——there is a time penalty for incorrect inputs.) "
        + "Use the pencil for non-permanent guesses. "
        + "Use the eraser for clearing cells that contain incorrect values or notes.");

    return writingToolInstructions;
  }

  /**
   * Builds hint container with {@link #buildHintButtonContainer} and
   * {@link #buildHintInstructionsContainer}.
   *
   * @return HBox : hint container
   */
  private HBox buildHintContainer()
  {
    var infoDialogHintContainer = new HBox();

    infoDialogHintContainer.getStyleClass().addAll(baseRowClass, largeRowClass);
    infoDialogHintContainer.getChildren().add(this.buildHintButtonContainer());
    infoDialogHintContainer.getChildren().add(this.buildHintInstructionsContainer());

    return infoDialogHintContainer;
  }

  /**
   * Builds hint button container with {@link #buildHintButton}.
   *
   * @return HBox : hint button container
   */
  private HBox buildHintButtonContainer()
  {
    var hintButtonContainer = new HBox();

    hintButtonContainer.getStyleClass().addAll(elementContainerClass);
    hintButtonContainer.getChildren().add(this.buildHintButton());

    return hintButtonContainer;
  }

  /**
   * Builds hint button.
   *
   * @return Button : hint button
   */
  private Button buildHintButton()
  {
    var hintBUtton = new Button("Hint");

    hintBUtton.setGraphic(new ImageView(
        new Image(this.getClass().getResourceAsStream("../../../../img/hint-icon.png"), 30, 30, false, true)));
    hintBUtton.getStyleClass().addAll(gameButtonClass, instructionsButtonClass);

    return hintBUtton;
  }

  /**
   * Builds hint instructions container with {@link #buildHintInstructions}.
   *
   * @return HBox : hint instructions container
   */
  private HBox buildHintInstructionsContainer()
  {
    var hintInstructionsContainer = new HBox();

    hintInstructionsContainer.getStyleClass().add(instructionsContainerClass);
    hintInstructionsContainer.getChildren().add(this.buildHintInstructions());

    return hintInstructionsContainer;
  }

  /**
   * Builds hint instructions.
   *
   * @return Label : hint instructions
   */
  private Label buildHintInstructions()
  {
    var hintInstructions = new Label();

    hintInstructions.getStyleClass().add(instructionsContentClass);
    hintInstructions
        .setText("This button will give you sudoku puzzle hints, though you will also incur a time penalty.");

    return hintInstructions;
  }

  /**
   * Builds restart container with {@link #buildRestartButtonContainer} and
   * {@link #buildRestartInstructionsContainer}.
   *
   * @return HBox : restart container
   */
  private HBox buildRestartContainer()
  {
    var restartContainer = new HBox();

    restartContainer.getStyleClass().addAll(baseRowClass, largeRowClass);
    restartContainer.getChildren().add(this.buildRestartButtonContainer());
    restartContainer.getChildren().add(this.buildRestartInstructionsContainer());

    return restartContainer;
  }

  /**
   * Builds restart button container with {@link #buildRestartButton}.
   *
   * @return HBox : restart button container
   */
  private HBox buildRestartButtonContainer()
  {
    var restartButtonContainer = new HBox();

    restartButtonContainer.getStyleClass().addAll(elementContainerClass);
    restartButtonContainer.getChildren().add(this.buildRestartButton());

    return restartButtonContainer;
  }

  /**
   * Builds restart button.
   *
   * @return Button : restart button
   */
  private Button buildRestartButton()
  {
    var restartButton = new Button("Restart");

    restartButton.setGraphic(new ImageView(
        new Image(this.getClass().getResourceAsStream("../../../../img/restart-icon.png"), 30, 30, true, true)));
    restartButton.getStyleClass().addAll(gameButtonClass, "restart-button", instructionsButtonClass);

    return restartButton;
  }

  /**
   * Builds restart instructions container with {@link #buildRestartInstructions}.
   *
   * @return HBox : restart instructions container
   */
  private HBox buildRestartInstructionsContainer()
  {
    var restartInstructionsContainer = new HBox();

    restartInstructionsContainer.getStyleClass().add(instructionsContainerClass);
    restartInstructionsContainer.getChildren().add(this.buildRestartInstructions());

    return restartInstructionsContainer;
  }

  /**
   * Builds restart instructions.
   *
   * @return Label : restart instructions
   */
  private Label buildRestartInstructions()
  {
    var restartInstructions = new Label();

    restartInstructions.getStyleClass().add(instructionsContentClass);
    restartInstructions.setText("This button will reset the sudoku puzzle but won't reset the game timer.");

    return restartInstructions;
  }

  /**
   * Builds new puzzle container with {@link #buildNewPuzzleButtonContainer} and
   * {@link #buildNewPuzzleInstructionsContainer}.
   *
   * @return HBox : new puzzle container
   */
  private HBox buildNewPuzzleContainer()
  {
    var newPuzzleContainer = new HBox();

    newPuzzleContainer.getStyleClass().addAll(baseRowClass, largeRowClass);
    newPuzzleContainer.getChildren().add(this.buildNewPuzzleButtonContainer());
    newPuzzleContainer.getChildren().add(this.buildNewPuzzleInstructionsContainer());

    return newPuzzleContainer;
  }

  /**
   * Builds new puzzle button container with {@link #buildNewPuzzleButton}
   *
   * @return HBox : new puzzle button container
   */
  private HBox buildNewPuzzleButtonContainer()
  {
    var newPuzzleButtonContainer = new HBox();

    newPuzzleButtonContainer.getStyleClass().addAll(elementContainerClass);
    newPuzzleButtonContainer.getChildren().add(this.buildNewPuzzleButton());

    return newPuzzleButtonContainer;
  }

  /**
   * Builds new puzzle button.
   *
   * @return Button : new puzzle button
   */
  private Button buildNewPuzzleButton()
  {
    var newPuzzleButton = new Button("New Game");

    newPuzzleButton.setGraphic(new ImageView(
        new Image(this.getClass().getResourceAsStream("../../../../img/new-puzzle-icon.png"), 30, 30, true, true)));
    newPuzzleButton.getStyleClass().addAll(gameButtonClass, "new-puzzle-button", instructionsButtonClass);

    return newPuzzleButton;
  }

  /**
   * Builds new puzzle instructions container with {@link #buildNewPuzzleInstructions}.
   *
   * @return HBox : new puzzle instructions
   */
  private HBox buildNewPuzzleInstructionsContainer()
  {
    var newPuzzleInstructionsContainer = new HBox();

    newPuzzleInstructionsContainer.getStyleClass().add(instructionsContainerClass);
    newPuzzleInstructionsContainer.getChildren().add(this.buildNewPuzzleInstructions());

    return newPuzzleInstructionsContainer;
  }

  /**
   * Builds new puzzle instructions.
   *
   * @return Label : new puzzle instructions
   */
  private Label buildNewPuzzleInstructions()
  {
    var newPuzzleInstructions = new Label();

    newPuzzleInstructions.getStyleClass().add(instructionsContentClass);
    newPuzzleInstructions.setText("This button will load a new sudoku puzzle of the current difficulty.");

    return newPuzzleInstructions;
  }

  /**
   * Builds number container with {@link #buildNumberButtonContainer} and
   * {@link #buildNumberInstructionsContainer}.
   *
   * @return HBox : number container
   */
  private HBox buildNumberContainer()
  {
    var numberContainer = new HBox();

    numberContainer.getStyleClass().addAll(baseRowClass, largeRowClass);
    numberContainer.getChildren().add(this.buildNumberButtonContainer());
    numberContainer.getChildren().add(this.buildNumberInstructionsContainer());

    return numberContainer;
  }

  /**
   * Builds number button container with {@link #buildNumberButton}.
   *
   * @return HBox : number button container
   */
  private HBox buildNumberButtonContainer()
  {
    var numberButtonContainer = new HBox();

    numberButtonContainer.getStyleClass().add(elementContainerClass);
    numberButtonContainer.getChildren().add(this.buildNumberButton());

    return numberButtonContainer;
  }

  /**
   * Builds number button.
   *
   * @return Button : number button
   */
  private Button buildNumberButton()
  {
    var numberButton = new Button("#");

    numberButton.getStyleClass().addAll(gameButtonClass, "number-button", "info-dialog-number-button");

    return numberButton;
  }

  /**
   * Builds number instructions container with {@link #buildNumberInstructions}.
   *
   * @return HBox : number instructions container
   */
  private HBox buildNumberInstructionsContainer()
  {
    var numberInstructionsContainer = new HBox();

    numberInstructionsContainer.getStyleClass().add(instructionsContainerClass);
    numberInstructionsContainer.getChildren().add(this.buildNumberInstructions());

    return numberInstructionsContainer;
  }

  /**
   * Builds number instructions.
   *
   * @return Label : number instructions
   */
  private Label buildNumberInstructions()
  {
    var numberInstructions = new Label();

    numberInstructions.getStyleClass().add(instructionsContentClass);
    numberInstructions.setText(
        "These types of buttons (each showing a number between 1 and 9) will display all occurrences of their value in the sudoku puzzle. "
            + "If the button is disabled, all occurrences of that value have been found.");

    return numberInstructions;
  }

  /**
   * Builds inner content pane.
   *
   * @return Pane : inner content pane
   */
  private Pane buildInnerContentPane()
  {
    var innerContentPane = new Pane();

    innerContentPane.getStyleClass().add("info-dialog-inner-content-pane");

    return innerContentPane;
  }
}
