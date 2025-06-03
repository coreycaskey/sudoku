package app.virtual_games.sudoku.views;

import app.virtual_games.sudoku.controllers.GameController;
import app.virtual_games.sudoku.handlers.GameScreenDifficultyDropdownHandler;
import app.virtual_games.sudoku.handlers.HintButtonHandler;
import app.virtual_games.sudoku.handlers.NewPuzzleButtonHandler;
import app.virtual_games.sudoku.handlers.RestartButtonHandler;
import app.virtual_games.sudoku.handlers.ReturnToMainMenuDialogButtonHandler;
import app.virtual_games.sudoku.handlers.WritingToolClickHandler;
import app.virtual_games.sudoku.models.NumberButton;
import app.virtual_games.sudoku.models.Sudoku;
import app.virtual_games.sudoku.models.SudokuBlock;
import app.virtual_games.sudoku.models.SudokuCell;
import app.virtual_games.sudoku.models.TimerButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Loads the Game Screen for the JavaFX application.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class GameScreen extends StackPane
{
  private static final int PUZZLE_SIZE = 9;

  private Sudoku currentSudoku;

  private BorderPane gamePane;
  private ComboBox<String> difficultyDropdown;
  private Button timerButton;
  private Label timerLabel;
  private Label timePenaltyLabel;

  private ArrayList<Button> writingToolButtons;
  private ArrayList<Button> helperButtons;
  private ArrayList<NumberButton> numberButtons;
  private ArrayList<NumberButton> disabledNumberButtons;

  /**
   * Initializes the following variable(s): {@link #currentSudoku} {@link #timerButton}
   * {@link #timerLabel} {@link #timePenaltyLabel} {@link #numberButtons} {@link #writingToolButtons}
   * {@link #helperButtons} {@link #disabledNumberButtons} {@link #gamePane} Loads the Game Screen
   * with a border pane.
   *
   * @throws IllegalArgumentException
   * @param sudoku : current sudoku puzzle
   */
  public GameScreen(Sudoku sudoku) throws IllegalArgumentException
  {
    this.currentSudoku = sudoku;
    this.timerButton = this.loadGameScreenTimerButton();
    this.timerLabel = this.loadGameScreenTimerLabel();
    this.timePenaltyLabel = this.loadGameScreenTimePenaltyLabel();
    this.numberButtons = this.loadGameScreenNumberButtons();
    this.writingToolButtons = new ArrayList<>();
    this.helperButtons = new ArrayList<>();
    this.disabledNumberButtons = new ArrayList<>();
    this.gamePane = this.loadGameScreen();

    this.getStyleClass().add("application-background");
    this.getChildren().add(this.gamePane);
    this.setId("game-screen");
  }

  /** Private Helper Methods **/

  /**
   * Loads the Game Screen timer button.
   *
   * @return Button : Game Screen timer button
   */
  private TimerButton loadGameScreenTimerButton()
  {
    return new TimerButton();
  }

  /**
   * Loads the Game Screen timer label.
   *
   * @return Label : Game Screen timer label
   */
  private Label loadGameScreenTimerLabel()
  {
    var timer = new Label("00:00:00");

    timer.getStyleClass().add("timer-label");

    return timer;
  }

  /**
   * Loads the Game Screen time penalty label.
   *
   * @return Label : Game Screen time penalty label
   */
  private Label loadGameScreenTimePenaltyLabel()
  {
    var timePenalty = new Label("+00");

    timePenalty.getStyleClass().addAll("time-penalty-label", "time-penalty-label--hidden");

    return timePenalty;
  }

  /**
   * Loads the Game Screen number buttons.
   *
   * @return ArrayList<NumberButton> : list of number buttons
   */
  private ArrayList<NumberButton> loadGameScreenNumberButtons()
  {
    return IntStream.range(0, PUZZLE_SIZE).mapToObj(sudokuVal -> new NumberButton(sudokuVal + 1))
        .collect(Collectors.toCollection(ArrayList::new));
  }

  /**
   * Loads the Game Screen with the following UI element(s): Game Screen Top Position —>
   * {@link #loadGameScreenOuterTopPaneContainer()} Game Screen Bottom Position —>
   * {@link #loadGameScreenBottomPaneContainer()} Game Screen Left Position —>
   * {@link #loadGameScreenLeftPaneContainer()} Game Screen Right Position ->
   * {@link #loadGameScreenRightPaneContainer()} Game Screen Center Position —>
   * {@link #loadGameScreenCenterPaneContainer()}
   *
   * @throws IllegalArgumentException
   * @return BorderPane : Game Screen
   */
  private BorderPane loadGameScreen() throws IllegalArgumentException
  {
    var screen = new BorderPane();

    screen.setTop(this.loadGameScreenOuterTopPaneContainer());
    screen.setBottom(this.loadGameScreenBottomPaneContainer());
    screen.setLeft(this.loadGameScreenLeftPaneContainer());
    screen.setRight(this.loadGameScreenRightPaneContainer());
    screen.setCenter(this.loadGameScreenCenterPaneContainer());

    return screen;
  }

  /**
   * Loads the Game Screen outer top pane container with the following UI element(s): Dialog Button
   * Container —> {@link #loadDialogButtonContainer()} Game Screen Inner Top Pane Container —>
   * {@link #loadGameScreenInnerTopPaneContainer()}
   *
   * @return VBox : Game Screen outer top pane container
   */
  private VBox loadGameScreenOuterTopPaneContainer()
  {
    var gamePaneOuterTopPaneContainer = new VBox();

    gamePaneOuterTopPaneContainer.getStyleClass().add("outer-top-pane-container");
    gamePaneOuterTopPaneContainer.getChildren().add(this.loadDialogButtonContainer());
    gamePaneOuterTopPaneContainer.getChildren().add(this.loadGameScreenInnerTopPaneContainer());

    return gamePaneOuterTopPaneContainer;
  }

  /**
   * Loads the dialog button container with the following UI element(s): Return To Main Menu Dialog
   * Button —> {@link #loadReturnToMainMenuDialogButton()}
   *
   * @return HBox : dialog button container
   */
  private HBox loadDialogButtonContainer()
  {
    var dialogButtonContainer = new HBox();

    dialogButtonContainer.getStyleClass().add("open-dialog-button-container");
    dialogButtonContainer.getChildren().add(this.loadReturnToMainMenuDialogButton());

    return dialogButtonContainer;
  }

  /**
   * Loads the Return to Main Menu dialog button with the {@link ReturnToMainMenuDialogButtonHandler}.
   *
   * @return Button : Return to Main Menu dialog button
   */
  private Button loadReturnToMainMenuDialogButton()
  {
    var returnToMainMenuDialogButton = new Button();

    returnToMainMenuDialogButton.getStyleClass().addAll("open-dialog-button", "return-to-main-menu-dialog-button");
    returnToMainMenuDialogButton.setOnAction(new ReturnToMainMenuDialogButtonHandler());

    return returnToMainMenuDialogButton;
  }

  /**
   * Loads the Game Screen inner top pane container with the following UI element(s): Game Screen
   * Difficulty Container —> {@link #loadGameScreenDifficultyContainer()} Game Screen Timer Container
   * —> {@link #loadGameScreenTimerContainer()}
   *
   * @return HBox : Game Screen inner top pane container
   */
  private HBox loadGameScreenInnerTopPaneContainer()
  {
    var gamePaneInnerTopPaneContainer = new HBox();

    gamePaneInnerTopPaneContainer.getStyleClass().add("inner-top-pane-container");
    gamePaneInnerTopPaneContainer.getChildren().add(this.loadGameScreenDifficultyContainer());
    gamePaneInnerTopPaneContainer.getChildren().add(this.loadGameScreenTimerContainer());

    return gamePaneInnerTopPaneContainer;
  }

  /**
   * Loads the Game Screen difficulty container with the following UI element(s): Game Screen
   * Difficulty Label —> {@link #loadGameScreenDifficultyLabel()} Game Screen Difficulty Dropdown —>
   * {@link #loadGameScreenDifficultyDropdown()}
   *
   * @return HBox : Game Screen difficulty container
   */
  private HBox loadGameScreenDifficultyContainer()
  {
    this.difficultyDropdown = this.loadGameScreenDifficultyDropdown();

    var gamePaneDifficultyContainer = new HBox();

    gamePaneDifficultyContainer.getStyleClass().add("difficulty-container");
    gamePaneDifficultyContainer.getChildren().add(this.loadGameScreenDifficultyLabel());
    gamePaneDifficultyContainer.getChildren().add(this.difficultyDropdown);

    return gamePaneDifficultyContainer;
  }

  /**
   * Loads the Game Screen difficulty label.
   *
   * @return Label : Game Screen difficulty label
   */
  private Label loadGameScreenDifficultyLabel()
  {
    var gamePaneDifficultyLabel = new Label("Puzzle Difficulty: ");

    gamePaneDifficultyLabel.getStyleClass().add("difficulty-label");

    return gamePaneDifficultyLabel;
  }

  /**
   * Loads the Game Screen difficulty dropdown with the {@link GameScreenDifficultyDropdownHandler}.
   *
   * @return ComboBox<String> : Game Screen difficulty dropdown
   */
  private ComboBox<String> loadGameScreenDifficultyDropdown()
  {
    var gamePaneDifficultyDropdown = new ComboBox<String>(GameController.getPuzzleDifficultyNames());

    // ComboBox default style classes are .combo-box-base and .combo-box

    gamePaneDifficultyDropdown.getSelectionModel().select(GameController.getCurrentDifficultyName());
    gamePaneDifficultyDropdown.setOnAction(new GameScreenDifficultyDropdownHandler());

    return gamePaneDifficultyDropdown;
  }

  /**
   * Loads the Game Screen timer container with the following UI element(s): Timer Button —>
   * {@link #timerButton} Timer Label —> {@link #timerLabel} Time Penalty —> {@link #timePenalty}
   *
   * @return HBox : Game Screen timer container
   */
  private HBox loadGameScreenTimerContainer()
  {
    var gamePaneTimerContainer = new HBox();

    gamePaneTimerContainer.getStyleClass().add("timer-container");
    gamePaneTimerContainer.getChildren().add(this.timerButton);
    gamePaneTimerContainer.getChildren().add(this.timerLabel);
    gamePaneTimerContainer.getChildren().add(this.timePenaltyLabel);

    return gamePaneTimerContainer;
  }

  /**
   * Loads the Game Screen bottom pane container with the following UI element(s): Game Screen Number
   * Buttons —> {@link #numberButtons}
   *
   * @return HBox : Game Screen bottom pane container
   */
  private HBox loadGameScreenBottomPaneContainer()
  {
    var gamePaneBottomPaneContainer = new HBox();

    this.disableGameScreenNumberButtons();

    gamePaneBottomPaneContainer.getStyleClass().add("bottom-pane-container");
    gamePaneBottomPaneContainer.getChildren().addAll(this.numberButtons);

    return gamePaneBottomPaneContainer;
  }

  /**
   * Disables the Game Screen number buttons, if necessary.
   */
  private void disableGameScreenNumberButtons()
  {
    this.numberButtons.forEach(this::disableGameScreenNumberButton);
  }

  /**
   * Disables a Game Screen number button, if necessary.
   *
   * @param numberButton : number button
   */
  private void disableGameScreenNumberButton(NumberButton numberButton)
  {
    var valueOccurrences = (HashMap<Integer, Integer>) this.currentSudoku.getValueOccurrences();

    if (valueOccurrences.containsKey(numberButton.getValue())
        && valueOccurrences.get(numberButton.getValue()) == PUZZLE_SIZE)
    {
      numberButton.setDisable(true);

      this.disabledNumberButtons.add(numberButton);
    }
  }

  /**
   * Loads the Game Screen left pane container with the following UI element(s): Pen Button —>
   * {@link #loadPenButton()} Pencil Button —> {@link #loadPencilButton()} Eraser Button —>
   * {@link #loadEraserButton()}
   *
   * @throws IllegalArgumentException
   * @return VBox : Game Screen left pane container
   */
  private VBox loadGameScreenLeftPaneContainer() throws IllegalArgumentException
  {
    var gamePaneLeftPaneContainer = new VBox();

    var penButton = this.loadPenButton();
    var pencilButton = this.loadPencilButton();
    var eraserButton = this.loadEraserButton();

    this.writingToolButtons.add(penButton);
    this.writingToolButtons.add(pencilButton);
    this.writingToolButtons.add(eraserButton);

    gamePaneLeftPaneContainer.getStyleClass().add("left-pane-container");
    gamePaneLeftPaneContainer.getChildren().addAll(penButton, pencilButton, eraserButton);

    return gamePaneLeftPaneContainer;
  }

  /**
   * Loads the pen button with the {@link WritingToolClickHandler}.
   *
   * @throws IllegalArgumentException
   * @return Button : pen button
   */
  private Button loadPenButton() throws IllegalArgumentException
  {
    var penButton = new Button("Pen");

    penButton.getStyleClass().addAll("game-button", "icon-button");
    penButton.setGraphic(new ImageView(
        new Image(this.getClass().getClassLoader().getResourceAsStream("./img/pen-icon.png"), 40, 40, true, true)));
    penButton.setOnAction(new WritingToolClickHandler());

    return penButton;
  }

  /**
   * Loads the pencil button with the {@link WritingToolClickHandler}.
   *
   * @throws IllegalArgumentException
   * @return Button : pencil button
   */
  private Button loadPencilButton() throws IllegalArgumentException
  {
    var pencilButton = new Button("Pencil");

    pencilButton.getStyleClass().addAll("game-button", "icon-button");
    pencilButton.setGraphic(new ImageView(
        new Image(this.getClass().getClassLoader().getResourceAsStream("./img/pencil-icon.png"), 40, 40, true, true)));
    pencilButton.setOnAction(new WritingToolClickHandler());

    return pencilButton;
  }

  /**
   * Loads the eraser button with the {@link WritingToolClickHandler}.
   *
   * @throws IllegalArgumentException
   * @return Button : eraser button
   */
  private Button loadEraserButton() throws IllegalArgumentException
  {
    var eraserButton = new Button("Eraser");

    eraserButton.getStyleClass().addAll("game-button", "icon-button");
    eraserButton.setGraphic(new ImageView(
        new Image(this.getClass().getClassLoader().getResourceAsStream("./img/eraser-icon.png"), 40, 40, true, true)));
    eraserButton.setOnAction(new WritingToolClickHandler());

    return eraserButton;
  }

  /**
   * Loads the Game Screen right pane container with the following UI element(s): — Hint Button —>
   * {@link #loadHintButton()} — Restart Button —> {@link #loadRestartButton()} — New Puzzle Button —>
   * {@link #loadNewPuzzleButton()}
   *
   * @throws IllegalArgumentException
   * @return VBox : Game Screen right pane container
   */
  private VBox loadGameScreenRightPaneContainer() throws IllegalArgumentException
  {
    var gamePaneRightPaneContainer = new VBox();

    var hintButton = this.loadHintButton();
    var restartButton = this.loadRestartButton();
    var newPuzzleButton = this.loadNewPuzzleButton();

    this.helperButtons.add(hintButton);
    this.helperButtons.add(restartButton);
    this.helperButtons.add(newPuzzleButton);

    gamePaneRightPaneContainer.getStyleClass().add("right-pane-container");
    gamePaneRightPaneContainer.getChildren().addAll(hintButton, restartButton, newPuzzleButton);

    return gamePaneRightPaneContainer;
  }

  /**
   * Loads the hint button with the {@link HintButtonHandler}.
   *
   * @throws IllegalArgumentException
   * @return Button : hint button
   */
  private Button loadHintButton() throws IllegalArgumentException
  {
    var hintButton = new Button("Hint");

    hintButton.getStyleClass().addAll("game-button", "icon-button");
    hintButton.setGraphic(new ImageView(
        new Image(this.getClass().getClassLoader().getResourceAsStream("./img/hint-icon.png"), 40, 40, false, true)));
    hintButton.setOnAction(new HintButtonHandler());

    return hintButton;
  }

  /**
   * Loads the restart button with the {@link RestartButtonHandler}.
   *
   * @throws IllegalArgumentException
   * @return Button : restart button
   */
  private Button loadRestartButton() throws IllegalArgumentException
  {
    var restartButton = new Button("Restart");

    restartButton.getStyleClass().addAll("game-button", "icon-button");
    restartButton.setGraphic(new ImageView(
        new Image(this.getClass().getClassLoader().getResourceAsStream("./img/restart-icon.png"), 40, 40, true, true)));
    restartButton.setOnAction(new RestartButtonHandler());

    return restartButton;
  }

  /**
   * Loads the new puzzle button with the {@link NewPuzzleButtonHandler}.
   *
   * @throws IllegalArgumentException
   * @return Button : new puzzle button
   */
  private Button loadNewPuzzleButton() throws IllegalArgumentException
  {
    var newPuzzleButton = new Button("New Game");

    newPuzzleButton.getStyleClass().addAll("game-button", "icon-button");
    newPuzzleButton.setGraphic(new ImageView(new Image(
        this.getClass().getClassLoader().getResourceAsStream("./img/new-puzzle-icon.png"), 40, 40, true, true)));
    newPuzzleButton.setOnAction(new NewPuzzleButtonHandler());

    return newPuzzleButton;
  }

  /**
   * Loads the Game Screen center pane container with the sudoku puzzle grid.
   *
   * @return GridPane : Game Screen center pane container
   */
  private GridPane loadGameScreenCenterPaneContainer()
  {
    var gamePaneCenterPaneContainer = new GridPane();

    for (SudokuBlock sudokuBlock : this.currentSudoku.getUserPuzzle())
    {
      var sudokuBlockContainer = new GridPane();

      for (SudokuCell sudokuCell : sudokuBlock.getBlockCells())
      {
        GridPane.setConstraints(sudokuCell, sudokuCell.getBlockCol(), sudokuCell.getBlockRow());

        sudokuBlockContainer.getChildren().add(sudokuCell);
      }

      sudokuBlockContainer.getStyleClass().add("sudoku-block-container");

      GridPane.setConstraints(sudokuBlockContainer, sudokuBlock.getBlockCol(), sudokuBlock.getBlockRow());

      gamePaneCenterPaneContainer.getChildren().add(sudokuBlockContainer);
    }

    gamePaneCenterPaneContainer.getStyleClass().add("center-pane-container");

    return gamePaneCenterPaneContainer;
  }

  /** Public Helper Methods **/

  /**
   * Loads a new sudoku puzzle into the Game Screen center pane container.
   *
   * @param sudoku : new sudoku puzzle
   */
  public void loadNewSudoku(Sudoku sudoku)
  {
    this.currentSudoku = sudoku;
    this.numberButtons = this.loadGameScreenNumberButtons();
    this.disabledNumberButtons = new ArrayList<>();

    this.gamePane.setCenter(this.loadGameScreenCenterPaneContainer());
    this.gamePane.setBottom(this.loadGameScreenBottomPaneContainer());
  }

  /**
   * Loads the Win dialog into the Game Screen.
   */
  public void loadWinDialog()
  {
    this.getChildren().add(new WinDialog());
  }

  /**
   * Loads the Error dialog into the Game Screen.
   */
  public void openErrorDialog()
  {
    this.getChildren().add(new ErrorDialog());
  }

  /**
   * Loads the Return to Main Menu dialog into the Game Screen.
   */
  public void loadReturnToMainMenuDialog()
  {
    this.getChildren().add(new ReturnToMainMenuDialog());
  }

  /**
   * Removes the dialog from the Game Screen.
   */
  public void closeDialog()
  {
    this.getChildren().remove(this.getChildren().size() - 1);
  }

  /** Getters and Setters **/

  /**
   * Retrieves {@link #difficultyDropdown}.
   *
   * @return ComboBox<String> : difficulty dropdown
   */
  public ComboBox<String> getDifficultyDropdown()
  {
    return this.difficultyDropdown;
  }

  /**
   * Retrieves {@link #timerButton}.
   *
   * @return Button : timer button
   */
  public Button getTimerButton()
  {
    return this.timerButton;
  }

  /**
   * Retrieves {@link #timerLabel}.
   *
   * @return Label : timer label
   */
  public Label getTimerLabel()
  {
    return this.timerLabel;
  }

  /**
   * Retrieves {@link #timePenaltyLabel}.
   *
   * @return Label : time penalty label
   */
  public Label getTimePenaltyLabel()
  {
    return this.timePenaltyLabel;
  }

  /**
   * Retrieves {@link #writingToolButtons}.
   *
   * @return List<Button> : list of writing tool buttons
   */
  public List<Button> getWritingToolButtons()
  {
    return this.writingToolButtons;
  }

  /**
   * Retrieves {@link #helperButtons}.
   *
   * @return List<Button> : list of helper buttons
   */
  public List<Button> getHelperButtons()
  {
    return this.helperButtons;
  }

  /**
   * Retrieves {@link #numberButtons}.
   *
   * @return List<NumberButton> : list of number buttons
   */
  public List<NumberButton> getNumberButtons()
  {
    return this.numberButtons;
  }

  /**
   * Retrieves {@link #disabledNumberButtons}.
   *
   * @return List<NumberButton> : list of disabled number buttons
   */
  public List<NumberButton> getDisabledNumberButtons()
  {
    return this.disabledNumberButtons;
  }
}
