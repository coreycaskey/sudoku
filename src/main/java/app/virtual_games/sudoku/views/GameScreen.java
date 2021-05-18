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
 *
 * Loads the Game Screen for the JavaFX application.
 *
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class GameScreen extends StackPane
{
  private static final int PUZZLE_SIZE = 9;

  private Sudoku currentSudoku;

  private BorderPane gameScreen;
  private ComboBox<String> difficultyDropdown;
  private Button timerButton;
  private Label timerLabel;
  private Label timePenaltyLabel;

  private ArrayList<Button> writingToolButtons;
  private ArrayList<Button> helperButtons;
  private ArrayList<NumberButton> numberButtons;
  private ArrayList<NumberButton> disabledNumberButtons;


  /**
   *
   * Initializes the following variable(s):
   *
   *  — {@link #currentSudoku}
   *  — {@link #timerButton}
   *  — {@link #timerLabel}
   *  — {@link #timePenaltyLabel}
   *  — {@link #numberButtons}
   *  — {@link #writingToolButtons}
   *  — {@link #helperButtons}
   *  — {@link #disabledNumberButtons}
   *  — {@link #gameScreen}
   *
   * Loads the Game Screen with a border pane.
   *
   * @param sudoku : current sudoku puzzle
   *
   */
  public GameScreen(Sudoku sudoku) throws Exception
  {
    this.currentSudoku = sudoku;
    this.timerButton = this.loadGameScreenTimerButton();
    this.timerLabel = this.loadGameScreenTimerLabel();
    this.timePenaltyLabel = this.loadGameScreenTimePenaltyLabel();
      this.numberButtons = this.loadGameScreenNumberButtons();
    this.writingToolButtons = new ArrayList<>();
    this.helperButtons = new ArrayList<>();
    this.disabledNumberButtons = new ArrayList<>();
    this.gameScreen = this.loadGameScreen();

    this.getStyleClass().add("app-background");
    this.getChildren().add(this.gameScreen);
  }


  /**  Private Helper Methods  **/


  /**
   *
   * Loads the Game Screen timer button.
   *
   * @return Button : Game Screen timer button
   *
   */
    private TimerButton loadGameScreenTimerButton()
    {
      return new TimerButton();
    }


    /**
     *
     * Loads the Game Screen timer label.
     *
     * @return Label : Game Screen timer label
     *
     */
    private Label loadGameScreenTimerLabel()
    {
      Label timerLabel = new Label("00:00:00");

      timerLabel.getStyleClass().add("timer-label");

      return timerLabel;
    }


    /**
     *
     * Loads the Game Screen time penalty label.
     *
     * @return Label : Game Screen time penalty label
     *
     */
    private Label loadGameScreenTimePenaltyLabel()
    {
      Label timePenaltyLabel = new Label("+00");

      timePenaltyLabel.getStyleClass().addAll("time-penalty-label", "time-penalty-label--hidden");

      return timePenaltyLabel;
    }


    /**
     *
     * Loads the Game Screen number buttons.
     *
     * @return ArrayList<NumberButton> : list of number buttons
     *
     */
    private ArrayList<NumberButton> loadGameScreenNumberButtons()
    {
      return IntStream.range(0, PUZZLE_SIZE)
              .mapToObj(sudokuVal -> new NumberButton(sudokuVal + 1))
              .collect(Collectors.toCollection(ArrayList::new));
    }


    /**
     *
     * Loads the Game Screen with the following UI element(s):
     *
     *  — Game Screen Top Position     —> {@link #loadGameScreenOuterTopPaneContainer()}
     *  — Game Screen Bottom Position —> {@link #loadGameScreenBottomPaneContainer()}
     *  — Game Screen Left Position   —> {@link #loadGameScreenLeftPaneContainer()}
     *  — Game Screen Right Position  —> {@link #loadGameScreenRightPaneContainer()}
     *  — Game Screen Center Position —> {@link #loadGameScreenCenterPaneContainer()}
     *
     * @return BorderPane : Game Screen
     *
     */
    private BorderPane loadGameScreen() throws Exception
    {
      BorderPane gameScreen = new BorderPane();

      gameScreen.setTop(this.loadGameScreenOuterTopPaneContainer());
      gameScreen.setBottom(this.loadGameScreenBottomPaneContainer());
      gameScreen.setLeft(this.loadGameScreenLeftPaneContainer());
      gameScreen.setRight(this.loadGameScreenRightPaneContainer());
      gameScreen.setCenter(this.loadGameScreenCenterPaneContainer());

      return gameScreen;
    }


    /**
     *
     * Loads the Game Screen outer top pane container with the following UI element(s):
     *
     *  — Open Dialog Button Container        —> {@link #loadOpenDialogButtonContainer()}
     *  — Game Screen Inner Top Pane Container —> {@link #loadGameScreenInnerTopPaneContainer()}
     *
     * @return VBox : Game Screen outer top pane container
     *
     */
    private VBox loadGameScreenOuterTopPaneContainer()
    {
      VBox gameScreenOuterTopPaneContainer = new VBox();

      gameScreenOuterTopPaneContainer.getStyleClass().add("outer-top-pane-container");
      gameScreenOuterTopPaneContainer.getChildren().add(this.loadOpenDialogButtonContainer());
      gameScreenOuterTopPaneContainer.getChildren().add(this.loadGameScreenInnerTopPaneContainer());

      return gameScreenOuterTopPaneContainer;
    }


    /**
     *
     * Loads the open dialog button container with the following UI element(s):
     *
     *  — Return To Main Menu Dialog Button —> {@link #loadReturnToMainMenuDialogButton()}
     *
     * @return HBox : open dialog button container
     *
     */
    private HBox loadOpenDialogButtonContainer()
    {
      HBox openDialogButtonContainer = new HBox();

      openDialogButtonContainer.getStyleClass().add("open-dialog-button-container");
      openDialogButtonContainer.getChildren().add(this.loadReturnToMainMenuDialogButton());

      return openDialogButtonContainer;
    }


    /**
     *
     * Loads the Return to Main Menu dialog button with the {@link ReturnToMainMenuDialogButtonHandler}.
     *
     * @return Button : Return to Main Menu dialog button
     *
     */
    private Button loadReturnToMainMenuDialogButton()
    {
      Button returnToMainMenuDialogButton = new Button();

      returnToMainMenuDialogButton.getStyleClass().addAll("open-dialog-button", "return-to-main-menu-dialog-button");
      returnToMainMenuDialogButton.setOnAction(new ReturnToMainMenuDialogButtonHandler());

      return returnToMainMenuDialogButton;
    }


    /**
     *
     * Loads the Game Screen inner top pane container with the following UI element(s):
     *
     *  — Game Screen Difficulty Container —> {@link #loadGameScreenDifficultyContainer()}
     *  — Game Screen Timer Container     —> {@link #loadGameScreenTimerContainer()}
     *
     * @return HBox : Game Screen inner top pane container
     *
     */
    private HBox loadGameScreenInnerTopPaneContainer()
    {
      HBox gameScreenInnerTopPaneContainer = new HBox();

      gameScreenInnerTopPaneContainer.getStyleClass().add("inner-top-pane-container");
      gameScreenInnerTopPaneContainer.getChildren().add(this.loadGameScreenDifficultyContainer());
      gameScreenInnerTopPaneContainer.getChildren().add(this.loadGameScreenTimerContainer());

      return gameScreenInnerTopPaneContainer;
    }


    /**
     *
   * Loads the Game Screen difficulty container with the following UI element(s):
   *
   *  — Game Screen Difficulty Label     —> {@link #loadGameScreenDifficultyLabel()}
   *  — Game Screen Difficulty Dropdown —> {@link #loadGameScreenDifficultyDropdown()}
   *
   * @return HBox : Game Screen difficulty container
     *
     */
    private HBox loadGameScreenDifficultyContainer()
    {
      HBox gameScreenDifficultyContainer = new HBox();

      gameScreenDifficultyContainer.getStyleClass().add("difficulty-container");
      gameScreenDifficultyContainer.getChildren().add(this.loadGameScreenDifficultyLabel());
      gameScreenDifficultyContainer.getChildren().add(this.difficultyDropdown = this.loadGameScreenDifficultyDropdown());

    return gameScreenDifficultyContainer;
    }


    /**
     *
     * Loads the Game Screen difficulty label.
     *
     * @return Label : Game Screen difficulty label
     *
     */
    private Label loadGameScreenDifficultyLabel()
    {
      Label gameScreenDifficultyLabel = new Label("Puzzle Difficulty: ");

      gameScreenDifficultyLabel.getStyleClass().add("difficulty-label");

    return gameScreenDifficultyLabel;
    }


    /**
     *
     * Loads the Game Screen difficulty dropdown with the {@link GameScreenDifficultyDropdownHandler}.
     *
     * @return ComboBox<String> : Game Screen difficulty dropdown
     *
     */
    private ComboBox<String> loadGameScreenDifficultyDropdown()
    {
      ComboBox<String> gameScreenDifficultyDropdown = new ComboBox<String>(GameController.getPuzzleDifficulties());

    // ComboBox default style classes are .combo-box-base and .combo-box

      gameScreenDifficultyDropdown.getSelectionModel().select(GameController.getCurrentDifficultyName());
      gameScreenDifficultyDropdown.setOnAction(new GameScreenDifficultyDropdownHandler());

      return gameScreenDifficultyDropdown;
    }


    /**
     *
     * Loads the Game Screen timer container with the following UI element(s):
     *
     *  — Timer Button —> {@link #timerButton}
     *  — Timer Label  —> {@link #timerLabel}
     *  — Time Penalty —> {@link #timePenalty}
     *
     * @return HBox : Game Screen timer container
     *
     */
    private HBox loadGameScreenTimerContainer()
    {
      HBox gameScreenTimerContainer = new HBox();

      gameScreenTimerContainer.getStyleClass().add("timer-container");
      gameScreenTimerContainer.getChildren().add(this.timerButton);
      gameScreenTimerContainer.getChildren().add(this.timerLabel);
      gameScreenTimerContainer.getChildren().add(this.timePenaltyLabel);

      return gameScreenTimerContainer;
    }


    /**
     *
     * Loads the Game Screen bottom pane container with the following UI element(s):
     *
     *  — Game Screen Number Buttons —> {@link #numberButtons}
     *
     * @return HBox : Game Screen bottom pane container
     *
     */
    private HBox loadGameScreenBottomPaneContainer()
    {
      HBox gameScreenBottomPaneContainer = new HBox();

      this.disableGameScreenNumberButtons();

      gameScreenBottomPaneContainer.getStyleClass().add("bottom-pane-container");
      gameScreenBottomPaneContainer.getChildren().addAll(this.numberButtons);

      return gameScreenBottomPaneContainer;
    }


    /**
     *
     * Disables the Game Screen number buttons, if necessary.
     *
     */
    private void disableGameScreenNumberButtons()
    {
      this.numberButtons.forEach(numberButton ->
      {
        this.disableGameScreenNumberButton(numberButton);
      });
    }


    /**
     *
     * Disables a Game Screen number button, if necessary.
     *
     * @param numberButton : number button
     *
     */
    private void disableGameScreenNumberButton(NumberButton numberButton)
    {
      HashMap<Integer, Integer> valueOccurrences = this.currentSudoku.getValueOccurrences();

      if (valueOccurrences.containsKey(numberButton.getValue()) && valueOccurrences.get(numberButton.getValue()) == PUZZLE_SIZE)
      {
        numberButton.setDisable(true);

        this.disabledNumberButtons.add(numberButton);
      }
    }


    /**
     *
     * Loads the Game Screen left pane container with the following UI element(s):
     *
     *  — Pen Button   —> {@link #loadPenButton()}
     *  — Pencil Button —> {@link #loadPencilButton()}
     *  — Eraser Button —> {@link #loadEraserButton()}
     *
     * @throws Exception
     *
     * @return VBox : Game Screen left pane container
     *
     */
    private VBox loadGameScreenLeftPaneContainer() throws Exception
    {
      VBox gameScreenLeftPaneContainer = new VBox();

      Button penButton = this.loadPenButton();
      Button pencilButton = this.loadPencilButton();
      Button eraserButton = this.loadEraserButton();

      this.writingToolButtons.add(penButton);
      this.writingToolButtons.add(pencilButton);
      this.writingToolButtons.add(eraserButton);

      gameScreenLeftPaneContainer.getStyleClass().add("left-pane-container");
      gameScreenLeftPaneContainer.getChildren().addAll(penButton, pencilButton, eraserButton);

      return gameScreenLeftPaneContainer;
    }


    /**
     *
     * Loads the pen button with the {@link WritingToolClickHandler}.
     *
     * @throws Exception
     *
     * @return Button : pen button
     *
     */
    private Button loadPenButton() throws Exception
    {
      Button penButton = new Button("Pen");

      penButton.getStyleClass().addAll("game-button", "icon-button");
    penButton.setGraphic(new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("pen-icon.png"), 40, 40, true, true)));
      penButton.setOnAction(new WritingToolClickHandler());

      return penButton;
    }


    /**
     *
     * Loads the pencil button with the {@link WritingToolClickHandler}.
     *
     * @throws Exception
     *
     * @return Button : pencil button
     *
     */
    private Button loadPencilButton() throws Exception
    {
      Button pencilButton = new Button("Pencil");

      pencilButton.getStyleClass().addAll("game-button", "icon-button");
      pencilButton.setGraphic(new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("pencil-icon.png"), 40, 40, true, true)));
      pencilButton.setOnAction(new WritingToolClickHandler());

      return pencilButton;
    }


    /**
     *
     * Loads the eraser button with the {@link WritingToolClickHandler}.
     *
     * @throws Exception
     *
     * @return Button : eraser button
     *
     */
    private Button loadEraserButton() throws Exception
    {
      Button eraserButton = new Button("Eraser");

      eraserButton.getStyleClass().addAll("game-button", "icon-button");
      eraserButton.setGraphic(new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("eraser-icon.png"), 40, 40, true, true)));
      eraserButton.setOnAction(new WritingToolClickHandler());

    return eraserButton;
    }


  /**
     *
     * Loads the Game Screen right pane container with the following UI element(s):
     *
     *  — Hint Button     —> {@link #loadHintButton()}
     *  — Restart Button   —> {@link #loadRestartButton()}
     *  — New Puzzle Button —> {@link #loadNewPuzzleButton()}
     *
     * @throws Exception
     *
     * @return VBox : Game Screen right pane container
     *
     */
    private VBox loadGameScreenRightPaneContainer() throws Exception
    {
      VBox gameScreenRightPaneContainer = new VBox();

      Button hintButton = this.loadHintButton();
      Button restartButton = this.loadRestartButton();
      Button newPuzzleButton = this.loadNewPuzzleButton();

      this.helperButtons.add(hintButton);
      this.helperButtons.add(restartButton);
      this.helperButtons.add(newPuzzleButton);

      gameScreenRightPaneContainer.getStyleClass().add("right-pane-container");
      gameScreenRightPaneContainer.getChildren().addAll(hintButton, restartButton, newPuzzleButton);

      return gameScreenRightPaneContainer;
    }


    /**
     *
     * Loads the hint button with the {@link HintButtonHandler}.
     *
     * @throws Exception
     *
     * @return Button : hint button
     *
     */
    private Button loadHintButton() throws Exception
    {
      Button hintButton = new Button("Hint");

      hintButton.getStyleClass().addAll("game-button", "icon-button");
      hintButton.setGraphic(new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("hint-icon.png"), 40, 40, false, true)));
      hintButton.setOnAction(new HintButtonHandler());

      return hintButton;
    }


    /**
     *
     * Loads the restart button with the {@link RestartButtonHandler}.
     *
     * @throws Exception
     *
     * @return Button : restart button
     *
     */
    private Button loadRestartButton() throws Exception
    {
      Button restartButton = new Button("Restart");

      restartButton.getStyleClass().addAll("game-button", "icon-button");
    restartButton.setGraphic(new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("restart-icon.png"), 40, 40, true, true)));
      restartButton.setOnAction(new RestartButtonHandler());

      return restartButton;
    }


    /**
     *
     * Loads the new puzzle button with the {@link NewPuzzleButtonHandler}.
     *
     * @throws Exception
     *
     * @return Button : new puzzle button
     *
     */
    private Button loadNewPuzzleButton() throws Exception
    {
      Button newPuzzleButton = new Button("New Game");

      newPuzzleButton.getStyleClass().addAll("game-button", "icon-button");
    newPuzzleButton.setGraphic(new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("new-puzzle-icon.png"), 40, 40, true, true)));
      newPuzzleButton.setOnAction(new NewPuzzleButtonHandler());

      return newPuzzleButton;
    }


    /**
     *
     * Loads the Game Screen center pane container with the sudoku puzzle grid.
     *
     * @return GridPane : Game Screen center pane container
     *
     */
  private GridPane loadGameScreenCenterPaneContainer()
  {
    GridPane gameScreenCenterPaneContainer = new GridPane();

    for (SudokuBlock sudokuBlock : this.currentSudoku.getUserPuzzle())
    {
      GridPane sudokuBlockContainer = new GridPane();

      for (SudokuCell sudokuCell : sudokuBlock.getBlockCells())
      {
        GridPane.setConstraints(sudokuCell, sudokuCell.getBlockCol(), sudokuCell.getBlockRow());

        sudokuBlockContainer.getChildren().add(sudokuCell);
      }

      sudokuBlockContainer.getStyleClass().add("sudoku-block-container");

      GridPane.setConstraints(sudokuBlockContainer, sudokuBlock.getBlockCol(), sudokuBlock.getBlockRow());

      gameScreenCenterPaneContainer.getChildren().add(sudokuBlockContainer);
    }

    gameScreenCenterPaneContainer.getStyleClass().add("center-pane-container");

    return gameScreenCenterPaneContainer;
  }


  /**  Public Helper Methods  **/


  /**
   *
   * Loads a new sudoku puzzle into the Game Screen center pane container.
   *
   * @param sudoku : new sudoku puzzle
   *
   */
  public void loadNewSudoku(Sudoku sudoku)
  {
    this.currentSudoku = sudoku;
    this.numberButtons = this.loadGameScreenNumberButtons();
    this.disabledNumberButtons = new ArrayList<>();

    this.gameScreen.setCenter(this.loadGameScreenCenterPaneContainer());
    this.gameScreen.setBottom(this.loadGameScreenBottomPaneContainer());
  }


  /**
   *
   * Loads the Win dialog into the Game Screen.
   *
   */
  public void loadWinDialog()
  {
    this.getChildren().add(new WinDialog());
  }


  /**
   *
   * Loads the Error dialog into the Game Screen.
   *
   */
  public void loadErrorDialog()
  {
    this.getChildren().add(new ErrorDialog(false));
  }


  /**
   *
   * Loads the Return to Main Menu dialog into the Game Screen.
   *
   */
  public void loadReturnToMainMenuDialog()
  {
    this.getChildren().add(new ReturnToMainMenuDialog());
  }


  /**
   *
   * Removes the dialog from the Game Screen.
   *
   */
  public void closeDialog()
  {
    this.getChildren().remove(this.getChildren().size() - 1);
  }


  /**  Getters and Setters  **/


  /**
   *
   * Retrieves {@link #difficultyDropdown}.
   *
   * @return ComboBox<String> : difficulty dropdown
   *
   */
  public ComboBox<String> getDifficultyDropdown()
  {
    return this.difficultyDropdown;
  }


  /**
   *
   * Retrieves {@link #timerButton}.
   *
   * @return Button : timer button
   *
   */
  public Button getTimerButton()
  {
    return this.timerButton;
  }


  /**
   *
   * Retrieves {@link #timerLabel}.
   *
   * @return Label : timer label
   *
   */
  public Label getTimerLabel()
  {
    return this.timerLabel;
  }


  /**
   *
   * Retrieves {@link #timePenaltyLabel}.
   *
   * @return Label : time penalty label
   *
   */
  public Label getTimePenaltyLabel()
  {
    return this.timePenaltyLabel;
  }


  /**
   *
   * Retrieves {@link #writingToolButtons}.
   *
   * @return ArrayList<Button> : list of writing tool buttons
   *
   */
  public ArrayList<Button> getWritingToolButtons()
  {
    return this.writingToolButtons;
  }


  /**
   *
   * Retrieves {@link #helperButtons}.
   *
   * @return ArrayList<Button> : list of helper buttons
   *
   */
  public ArrayList<Button> getHelperButtons()
  {
    return this.helperButtons;
  }


  /**
   *
   * Retrieves {@link #numberButtons}.
   *
   * @return ArrayList<NumberButton> : list of number buttons
   *
   */
  public ArrayList<NumberButton> getNumberButtons()
  {
    return this.numberButtons;
  }


  /**
   *
   * Retrieves {@link #disabledNumberButtons}.
   *
   * @return ArrayList<NumberButton> : list of disabled number buttons
   *
   */
  public ArrayList<NumberButton> getDisabledNumberButtons()
  {
    return this.disabledNumberButtons;
  }
}
