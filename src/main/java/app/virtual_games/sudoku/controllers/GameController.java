package app.virtual_games.sudoku.controllers;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import app.virtual_games.sudoku.exceptions.SudokuPuzzleException;
import app.virtual_games.sudoku.handlers.HintCellHandler;
import app.virtual_games.sudoku.handlers.TimePenaltyHandler;
import app.virtual_games.sudoku.handlers.TimerHandler;
import app.virtual_games.sudoku.models.NumberButton;
import app.virtual_games.sudoku.models.PuzzleDifficulty;
import app.virtual_games.sudoku.models.Sudoku;
import app.virtual_games.sudoku.models.SudokuCell;
import app.virtual_games.sudoku.models.WritingTool;
import app.virtual_games.sudoku.views.GameScreen;
import app.virtual_games.sudoku.views.MainMenu;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Main controller for game state.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class GameController extends Application
{
  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  private static final String GAME_TIME_FORMAT = "%02d:%02d:%02d";
  private static final String WIN_TIME_FORMAT = "%s hour(s), %s minute(s), %s second(s)";

  private static Stage appStage;
  private static Pane currentScreen;
  private static Sudoku currentSudoku;

  // ObservableList is easily convertable to a ComboBox dropdown list
  private static ObservableList<String> puzzleDifficultyNames;
  private static PuzzleDifficulty currentDifficulty;

  private static WritingTool currentWritingTool;
  private static Button currentClickedWritingTool;
  private static NumberButton currentClickedNumberButton;

  private static Timeline gameTimer;
  private static long startTime;
  private static long playingTime;
  private static Label timerLabel;

  private static Timeline timePenaltyTimer;
  private static Label timePenaltyLabel;
  private static boolean isTimePenaltyShown;

  private static Timeline hintCellTimer;

  private static ArrayList<NumberButton> numberButtons;
  private static ArrayList<Button> writingToolButtons;
  private static ArrayList<Button> helperButtons;
  private static ArrayList<NumberButton> disabledNumberButtons;

  /** Public Helper Methods **/

  /**
   * Launches application and implicitly invokes {@link #start}.
   *
   * @param args : command line args
   */
  public static void main(String[] args)
  {
    launch(args);
  }

  /**
   * Starts game.
   *
   * @param primaryStage : application window
   */
  @Override
  public void start(Stage primaryStage)
  {
    appStage = primaryStage;
    appStage.setTitle("Do You Sudoku?");
    appStage.setResizable(false);

    puzzleDifficultyNames = PuzzleDifficulty.getPuzzleDifficultyNames();
    currentDifficulty = PuzzleDifficulty.EASY;

    openMainMenu();

    appStage.show();
  }

  /**
   * Opens {@link MainMenu} and related styles.
   */
  public static void openMainMenu()
  {
    currentScreen = new MainMenu();

    appStage.setScene(new Scene(currentScreen, 800, 800));
    appStage.getScene().getStylesheets().addAll(
        GameController.class.getClassLoader().getResource("Shared.css").toExternalForm(),
        // TODO: do I remove MainMenu.css when transitioning to GameScreen ??
        GameController.class.getClassLoader().getResource("MainMenu.css").toExternalForm());
  }

  /**
   * Transitions to game screen.
   */
  public static void transitionToGameScreen()
  {
    try
    {
      initializeGameScreenVariables();

      if (currentSudoku.getIsSolved())
      {
        loadGameScreen();
        loadGameScreenElements();
        startGameTimer();
      } else
      {
        LOGGER.log(Level.SEVERE, "No sudoku solution found");
        openErrorDialog(true); // true —> Main Menu
      }
    } catch (Exception e)
    {
      for (StackTraceElement element : e.getStackTrace())
      {
        LOGGER.log(Level.SEVERE, element.toString());
      }

      openErrorDialog(true); // true —> Main Menu
    }
  }

  /**
   * Starts a new sudoku puzzle.
   */
  public static void startNewSudoku()
  {
    try
    {
      initializeGameScreenVariables();

      if (currentSudoku.getIsSolved())
      {
        loadNewSudoku();
        loadGameScreenElements();
        startGameTimer();
      } else
      {
        LOGGER.log(Level.SEVERE, "No sudoku solution found");
        stopGameTimer();
        openErrorDialog(false); // false —> Game Screen
      }
    } catch (Exception e)
    {
      LOGGER.log(Level.SEVERE, e.getMessage());
      stopGameTimer();
      openErrorDialog(false); // false —> Game Screen
    }
  }

  /**
   * Resets the sudoku puzzle and corresponding game elements.
   */
  public static void restartSudoku()
  {
    currentSudoku.restartPuzzle();

    currentWritingTool = WritingTool.PEN;
    currentClickedNumberButton = null;

    if (isWritingToolClicked())
    {
      unclickWritingTool();
    }

    loadNewSudoku();
    loadGameScreenElements();
  }

  /**
   * Starts {@link #gameTimer}.
   */
  public static void startGameTimer()
  {
    gameTimer.play();
  }

  /**
   * Pauses {@link #gameTimer}.
   */
  public static void pauseGameTimer()
  {
    gameTimer.pause();
  }

  /**
   * Stops {@link #gameTimer}.
   */
  public static void stopGameTimer()
  {
    gameTimer.stop();
  }

  /**
   * Restarts {@link #timePenaltyTimer}.
   */
  public static void startTimePenaltyTimer()
  {
    timePenaltyTimer.playFromStart();
  }

  /**
   * Stops {@link #timePenaltyTimer}.
   */
  public static void stopTimePenaltyTimer()
  {
    timePenaltyTimer.stop();
  }

  /**
   * Restarts {@link #hintCellTimer}.
   */
  public static void startHintCellTimer()
  {
    hintCellTimer.playFromStart();
  }

  /**
   * Stops {@link #hintCellTimer}.
   */
  public static void stopHintCellTimer()
  {
    hintCellTimer.stop();
  }

  /**
   * Resets the text of {@link #timerLabel}.
   */
  public static void resetTimerLabel()
  {
    timerLabel.setText("00:00:00");
  }

  /**
   * Opens exit application dialog.
   */
  public static void openExitApplicationDialog()
  {
    ((MainMenu) currentScreen).openExitApplicationDialog();
  }

  /**
   * Opens info dialog.
   */
  public static void openInfoDialog()
  {
    ((MainMenu) currentScreen).openInfoDialog();
  }

  /**
   * Loads the Return to Main Menu dialog into the JavaFX application.
   */
  public static void loadReturnToMainMenuDialog()
  {
    ((GameScreen) currentScreen).loadReturnToMainMenuDialog();
  }

  /**
   * Loads the Error dialog into the JavaFX application.
   *
   * @param isMainMenu : true —> Main Menu; false —> Game Screen
   */
  public static void openErrorDialog(boolean isMainMenu)
  {
    if (isMainMenu)
    {
      ((MainMenu) currentScreen).openErrorDialog();
    } else
    {
      ((GameScreen) currentScreen).openErrorDialog();
    }
  }

  /**
   * Loads the Win dialog into the JavaFX application.
   */
  public static void loadWinDialog()
  {
    ((GameScreen) currentScreen).loadWinDialog();
  }

  /**
   * Closes application.
   */
  public static void closeApplication()
  {
    System.exit(0);
  }

  /**
   * Closes dialog on main menu.
   */
  public static void closeMainMenuDialog()
  {
    ((MainMenu) currentScreen).closeDialog();
  }

  /**
   * Closes dialog on game screen.
   */
  public static void closeGameScreenDialog()
  {
    ((GameScreen) currentScreen).closeDialog();
  }

  /**
   * Updates the previously clicked writing tool.
   */
  public static void unclickWritingTool()
  {
    currentClickedWritingTool.getStyleClass().remove("clicked-writing-tool");
    currentClickedWritingTool = null;
  }

  /**
   * Updates the currently clicked writing tool.
   *
   * @param writingToolButton : clicked writing tool button
   */
  public static void clickWritingTool(Button writingToolButton)
  {
    currentWritingTool = WritingTool.getEnumInstance(writingToolButton.getText());
    currentClickedWritingTool = writingToolButton;
    currentClickedWritingTool.getStyleClass().add("clicked-writing-tool");
  }

  /**
   * Hides the contents of the sudoku cells.
   */
  public static void hideSudokuCells()
  {
    currentSudoku.hideSudokuCells();
  }

  /**
   * Shows the contents of the sudoku cells.
   */
  public static void showSudokuCells()
  {
    currentSudoku.showSudokuCells();
  }

  /**
   * Disable all Game Screen elements when progress is paused.
   */
  public static void disableGameScreenElements()
  {
    ((GameScreen) currentScreen).getDifficultyDropdown().setDisable(true);

    numberButtons.forEach(button -> button.setDisable(true));
    writingToolButtons.forEach(button -> button.setDisable(true));
    helperButtons.forEach(button -> button.setDisable(true));
  }

  /**
   * Enable all Game Screen elements when progress is resumed.
   */
  public static void enableGameScreenElements()
  {
    ((GameScreen) currentScreen).getDifficultyDropdown().setDisable(false);

    numberButtons.forEach(GameController::enableNumberButton);
    writingToolButtons.forEach(button -> button.setDisable(false));
    helperButtons.forEach(button -> button.setDisable(false));
  }

  /**
   * Highlights all occurrences of the sudoku value represented by
   * {@link #currentClickedNumberButton}.
   */
  public static void highlightOccurrences()
  {
    currentSudoku.highlightOccurrences(currentClickedNumberButton.getValue());
  }

  /**
   * Highlights the row, column, and block of the clicked cell.
   */
  public static void highlightRowColumnBlock()
  {
    currentSudoku.highlightRowColumnBlock();
  }

  /**
   * Unhighlights all sudoku cells.
   */
  public static void unhighlightSudokuPuzzle()
  {
    currentSudoku.unhighlightSudokuPuzzle();
  }

  /**
   * Retrieves the hint and updates the sudoku puzzle.
   */
  public static void getHint()
  {
    stopHintCellTimer();
    currentSudoku.getHint();
    startHintCellTimer();
  }

  /**
   * Increments {@link #playingTime} by the specified time increase.
   *
   * @param timeIncrease : time increase
   */
  public static void incrementPlayingTime(long timeIncrease)
  {
    playingTime += timeIncrease;
  }

  /**
   * Determines whether a writing tool is currently clicked.
   *
   * @return boolean : true —> writing tool is clicked; false —> writing tool is not clicked
   */
  public static boolean isWritingToolClicked()
  {
    return currentClickedWritingTool != null;
  }

  /**
   * Determines whether a number button is currently clicked.
   *
   * @return boolean : true —> number button is clicked; false —> number button is not clicked
   */
  public static boolean isNumberButtonClicked()
  {
    return currentClickedNumberButton != null;
  }

  /**
   * Updates the styling for the clicked number button to "click" it.
   *
   * @param numberButton : clicked number button
   */
  public static void clickNumberButton(NumberButton numberButton)
  {
    numberButton.addStyling("clicked-number-button");
  }

  /**
   * Updates the styling for the currently clicked number button to "unclick" it.
   */
  public static void unclickNumberButton()
  {
    if (isNumberButtonClicked())
    {
      currentClickedNumberButton.removeStyling("clicked-number-button");
    }
  }

  /**
   * Disables a number button.
   *
   * @param numberButton : number button
   */
  public static void disableNumberButton(NumberButton numberButton)
  {
    numberButton.setDisable(true);
  }

  /**
   * Resets the currently clicked cell.
   */
  public static void resetCurrentClickedCell()
  {
    currentSudoku.setCurrentClickedCell(null);
  }

  /**
   * Determines if the completed puzzle is complete and load the Win dialog, if so.
   */
  public static void checkPuzzleCompleted()
  {
    if (currentSudoku.isCorrectSolution())
    {
      gameTimer.stop();
      loadWinDialog();
    }
  }

  /**
   * Displays the time penalty.
   *
   * @param timePenalty : time penalty
   */
  public static void showTimePenalty(int timePenalty)
  {
    isTimePenaltyShown = true;

    timePenaltyLabel.setText("+" + Integer.toString(timePenalty));
    timePenaltyLabel.getStyleClass().remove("time-penalty-label--hidden");
    timePenaltyLabel.getStyleClass().add("time-penalty-label--shown");
  }

  /**
   * Hides the time penalty.
   */
  public static void hideTimePenalty()
  {
    isTimePenaltyShown = false;

    timePenaltyLabel.getStyleClass().add("time-penalty-label--hidden");
    timePenaltyLabel.getStyleClass().remove("time-penalty-label--shown");
  }

  /**
   * Retrieves the name of the {@link #currentDifficulty}.
   *
   * @return String : name of current puzzle difficulty
   */
  public static String getCurrentDifficultyName()
  {
    return currentDifficulty.getName();
  }

  /**
   * Retrieves the Game Screen difficulty dropdown.
   *
   * @return Combobox : Game Screen difficulty dropdown
   */
  public static ComboBox<String> getGameScreenDifficultyDropdown()
  {
    return ((GameScreen) currentScreen).getDifficultyDropdown();
  }

  /**
   * Retrieves the currently clicked cell.
   *
   * @return SudokuCell : currently clicked cell
   */
  public static SudokuCell getCurrentClickedCell()
  {
    return currentSudoku.getCurrentClickedCell();
  }

  /**
   * Updates the currently clicked cell.
   *
   * @param currClickedCell : currently clicked cell
   */
  public static void setCurrentClickedCell(SudokuCell currClickedCell)
  {
    currentSudoku.setCurrentClickedCell(currClickedCell);
  }

  /**
   * Retrieves {@link #currentHintCell}.
   *
   * @return SudokuCell : current hint cell
   */
  public static SudokuCell getCurrentHintCell()
  {
    return currentSudoku.getCurrentHintCell();
  }

  /**
   * Updates {@link #currentHintCell}.
   *
   * @param hintCell : current hint cell
   */
  public static void setCurrentHintCell(SudokuCell hintCell)
  {
    currentSudoku.setCurrentHintCell(hintCell);
  }

  /**
   * Retrieves the corresponding writing tool button for the {@link #currentWritingTool}.
   *
   * @return Button : writing tool button
   */
  public static Button getWritingToolButton()
  {
    for (Button writingToolButton : writingToolButtons)
    {
      if (writingToolButton.getText().equals(currentWritingTool.getToolName()))
      {
        return writingToolButton;
      }
    }

    return null;
  }

  /**
   * Retrieves the corresponding number button for the provided sudoku value.
   *
   * @param number : sudoku value
   * @return NumberButton : corresponding number button
   */
  public static NumberButton getNumberButton(int number)
  {
    for (NumberButton numberButton : numberButtons)
    {
      if (numberButton.getText().equals(Integer.toString(number)))
      {
        return numberButton;
      }
    }

    return null;
  }

  /**
   * Adds the disabled number button to {@link #disabledNumberButtons}.
   *
   * @param numberButton : number button
   */
  public static void addDisabledNumberButton(NumberButton numberButton)
  {
    disabledNumberButtons.add(numberButton);
  }

  /**
   * Retrieves the corresponding time penalty value.
   *
   * @return int : time penalty
   */
  public static int getTimePenalty()
  {
    int cellsRemaining = currentSudoku.getCellsRemaining();

    if (cellsRemaining < 27)
    {
      return 45;
    } else if (cellsRemaining < 54)
    {
      return 30;
    } else
    {
      return 15;
    }
  }

  /**
   * Updates {@link #timerLabel} with the formatted game time.
   */
  public static void updateTimerLabel()
  {
    timerLabel.setText(formatGameTime());
  }

  /**
   * Formats the playing time for the Win dialog.
   *
   * @return String : formatted playing time
   */
  public static String formatWinTime()
  {
    String[] timerText = timerLabel.getText().split(":");

    String hours = timerText[0];
    String minutes = timerText[1];
    String seconds = timerText[2];

    return String.format(WIN_TIME_FORMAT, hours, minutes, seconds);
  }

  /** Private Helper Methods **/

  /**
   * Initializes game screen state.
   *
   * @throws SudokuPuzzleException
   */
  private static void initializeGameScreenVariables() throws SudokuPuzzleException
  {
    currentSudoku = new Sudoku(currentDifficulty.getId());
    currentWritingTool = WritingTool.PEN;
    currentClickedNumberButton = null; // TODO: is this the best way to do this ?

    if (isWritingToolClicked())
    {
      unclickWritingTool();
    }

    gameTimer = loadGameTimer();
    startTime = System.currentTimeMillis();
    playingTime = System.currentTimeMillis();
    timePenaltyTimer = loadTimePenaltyTimer();
    isTimePenaltyShown = false;
    hintCellTimer = loadHintCellTimer();
  }

  /**
   * Loads the game timer to track the playing time.
   *
   * @return Timeline : game timer
   */
  private static Timeline loadGameTimer()
  {
    var timer = new Timeline(new KeyFrame(Duration.seconds(1), new TimerHandler()));

    timer.setCycleCount(Animation.INDEFINITE);

    return timer;
  }

  /**
   * Loads the time penalty timer to track the duration of the penalty label visibility.
   *
   * @return Timeline : time penalty timer
   */
  private static Timeline loadTimePenaltyTimer()
  {
    var timer = new Timeline(new KeyFrame(Duration.seconds(3), new TimePenaltyHandler()));

    timer.setCycleCount(Animation.INDEFINITE);

    return timer;
  }

  /**
   * Loads the hint cell timer to track the duration of the hint cell's background highlight.
   *
   * @return Timeline : hint cell timer
   */
  private static Timeline loadHintCellTimer()
  {
    var timer = new Timeline(new KeyFrame(Duration.seconds(3), new HintCellHandler()));

    timer.setCycleCount(Animation.INDEFINITE);

    return timer;
  }

  /**
   * Loads the {@link GameScreen} into the JavaFX stage.
   */
  private static void loadGameScreen() throws IllegalStateException
  {
    currentScreen = new GameScreen(currentSudoku);

    appStage.setScene(new Scene(currentScreen, 800, 800));
    appStage.getScene().getStylesheets().addAll(
        GameController.class.getClassLoader().getResource("Shared.css").toExternalForm(),
        GameController.class.getClassLoader().getResource("GameScreen.css").toExternalForm());
  }

  /**
   * Loads a new sudoku puzzle into the {@link GameScreen}.
   */
  private static void loadNewSudoku()
  {
    ((GameScreen) currentScreen).loadNewSudoku(currentSudoku);
  }

  /**
   * Loads the tracked {@link GameScreen} elements.
   */
  private static void loadGameScreenElements()
  {
    timerLabel = ((GameScreen) currentScreen).getTimerLabel();
    timePenaltyLabel = ((GameScreen) currentScreen).getTimePenaltyLabel();
    writingToolButtons = (ArrayList<Button>) ((GameScreen) currentScreen).getWritingToolButtons();
    helperButtons = (ArrayList<Button>) ((GameScreen) currentScreen).getHelperButtons();
    numberButtons = (ArrayList<NumberButton>) ((GameScreen) currentScreen).getNumberButtons();
    disabledNumberButtons = (ArrayList<NumberButton>) ((GameScreen) currentScreen).getDisabledNumberButtons();
  }

  /**
   * Formats the playing time.
   *
   * @return String : formatted playing time
   */
  private static String formatGameTime()
  {
    return String.format(GAME_TIME_FORMAT, TimeUnit.MILLISECONDS.toHours(playingTime - startTime),
        TimeUnit.MILLISECONDS.toMinutes(playingTime - startTime) % TimeUnit.HOURS.toMinutes(1),
        TimeUnit.MILLISECONDS.toSeconds(playingTime - startTime) % TimeUnit.MINUTES.toSeconds(1));
  }

  /**
   * Enables a number button if it's not supposed to be disabled.
   *
   * @param numberButton : number button
   */
  private static void enableNumberButton(NumberButton numberButton)
  {
    if (!disabledNumberButtons.contains(numberButton))
    {
      numberButton.setDisable(false);
    }
  }

  /** Getters and Setters **/

  /**
   * Retrieves {@link #currentScreen}.
   *
   * @return Pane : current screen
   */
  public static Pane getCurrentScreen()
  {
    return currentScreen;
  }

  /**
   * Retrieves {@link #currentSudoku}.
   *
   * @return Sudoku : current sudoku
   */
  public static Sudoku getCurrentSudoku()
  {
    return currentSudoku;
  }

  /**
   * Retrieves {@link #puzzleDifficultyNames}.
   *
   * @return ObservableList : list of puzzle difficulty names
   */
  public static ObservableList<String> getPuzzleDifficultyNames()
  {
    return puzzleDifficultyNames;
  }

  /**
   * Retrieves {@link #currentDifficulty}.
   *
   * @return PuzzleDifficulty : current puzzle difficulty
   */
  public static PuzzleDifficulty getCurrentDifficulty()
  {
    return currentDifficulty;
  }

  /**
   * Updates {@link #currentDifficulty}.
   *
   * @param difficultyName : name of selected puzzle difficulty
   */
  public static void setCurrentDifficulty(String difficultyName)
  {
    currentDifficulty = PuzzleDifficulty.getEnumInstance(difficultyName);
  }

  /**
   * Retrieves {@link #currentWritingTool}.
   *
   * @return WritingTool : selected writing tool
   */
  public static WritingTool getCurrentWritingTool()
  {
    return currentWritingTool;
  }

  /**
   * Retrieves {@link #currentClickedWritingTool}.
   *
   * @return Button : currently clicked writing tool button
   */
  public static Button getCurrentClickedWritingTool()
  {
    return currentClickedWritingTool;
  }

  /**
   * Updates {@link #currentClickedWritingTool}.
   *
   * @param clickedWritingTool : currently clicked writing tool button
   */
  public static void setCurrentClickedWritingTool(Button clickedWritingTool)
  {
    currentClickedWritingTool = clickedWritingTool;
  }

  /**
   * Retrieves {@link #currentClickedNumberButton}.
   *
   * @return NumberButton : currently clicked number button
   */
  public static NumberButton getCurrentClickedNumberButton()
  {
    return currentClickedNumberButton;
  }

  /**
   * Updates {@link #currentClickedNumberButton}.
   *
   * @param clickedNumberButton : currently clicked number button
   */
  public static void setCurrentClickedNumberButton(NumberButton clickedNumberButton)
  {
    currentClickedNumberButton = clickedNumberButton;
  }

  /**
   * Retrieves {@link #gameTimer}.
   *
   * @return Timeline : game timer
   */
  public static Timeline getGameTimer()
  {
    return gameTimer;
  }

  /**
   * Retrieves {@link #timerLabel}.
   *
   * @return Label : game timer label
   */
  public static Label getTimerLabel()
  {
    return timerLabel;
  }

  /**
   * Retrieves {@link #isTimePenaltyShown}.
   *
   * @return boolean : true —> time penalty is shown; false —> time penalty isn't shown
   */
  public static boolean getIsTimePenaltyShown()
  {
    return isTimePenaltyShown;
  }
}
