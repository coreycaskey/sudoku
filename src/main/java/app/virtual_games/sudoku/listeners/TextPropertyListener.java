package app.virtual_games.sudoku.listeners;

import java.util.HashMap;

import app.virtual_games.sudoku.controllers.GameController;
import app.virtual_games.sudoku.models.Sudoku;
import app.virtual_games.sudoku.models.SudokuCell;
import app.virtual_games.sudoku.models.WritingTool;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


/**
 *
 * Custom change listener for the sudoku cell (text field) text property.
 *
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class TextPropertyListener implements ChangeListener<String>
{
  private static final int PUZZLE_SIZE = 9;


  /**
   *
   * Limits the inputs in the text field to numbers between 1 and 9.
   *
   * @param observable : {@link ObservableValue} that maps to the sudoku cell input
   * @param oldValue : previous sudoku cell input
   * @param newValue : current sudoku cell input
   *
   */
  @Override
  public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
  {
    SudokuCell inputCell = (SudokuCell) ((StringProperty) observable).getBean();

    if (newValue.equals("") && !inputCell.isDisabled()) // character was deleted by keystroke (not eraser)
    {
      // Inputting invalid characters will call TextPropertyListener again
      // and proceed to this point

      if (oldValue.matches("^([1-9])$"))
      {
        this.handleValidOldValue(inputCell);
      }
    }
    else if (!newValue.matches("^([1-9])$"))
    {
      inputCell.setText("");
    }
    else
    {
      this.handleValidNewValue(newValue, inputCell);
    }
  }


  /**  Private Helper Methods  **/


  /**
   *
   * Handles the deleted input event when the previous cell text was valid.
   *
   * @param inputCell : sudoku cell input
   *
   */
  private void handleValidOldValue(SudokuCell inputCell)
  {
    if (inputCell.getWritingTool() == WritingTool.PEN)
    {
      inputCell.setCurrentValue(0);
      inputCell.removeStyling("incorrect-cell-value"); // only incorrect pen values can be deleted
    }
    else if (inputCell.getWritingTool() == WritingTool.PENCIL)
    {
      inputCell.setNotes(0);
    }
  }


  /**
   *
   * Handles the input event when the current cell text is valid.
   *
   * @param newValue : current cell text
   * @param inputCell : sudoku cell input
   *
   */
  private void handleValidNewValue(String newValue, SudokuCell inputCell)
  {
    if (inputCell.getWritingTool() == WritingTool.PEN)
    {
      inputCell.setCurrentValue(Integer.parseInt(newValue));

      this.checkCellCorrectness(inputCell);
    }
    else if (inputCell.getWritingTool() == WritingTool.PENCIL)
    {
      inputCell.setNotes(Integer.parseInt(newValue));
    }
  }


  /**
   *
   * Updates the sudoku puzzle and cell based on the correctness of the input.
   *
   * @param inputCell : sudoku cell input
   *
   */
  private void checkCellCorrectness(SudokuCell inputCell)
  {
    if (inputCell.isCorrect())
    {
      inputCell.setEditable(false);

      var sudoku = inputCell.getParentSudoku();

      sudoku.addCorrectCell(inputCell);
      sudoku.updateValueOccurrences(inputCell.getCurrentValue());
      sudoku.decrementCellsRemaining();

      this.disableNumberButton(sudoku, inputCell);
      this.handleWinCondition(sudoku);
    }
    else
    {
      inputCell.addStyling("incorrect-cell-value");

      int timePenalty = GameController.getTimePenalty();

      GameController.hideTimePenalty();
      GameController.stopTimePenaltyTimer();
      GameController.showTimePenalty(timePenalty);
      GameController.startTimePenaltyTimer();
      GameController.incrementPlayingTime(timePenalty * ((long) 1000));
      GameController.updateTimerLabel();
    }
  }


  /**
   *
   * Disables the number button corresponding to the sudoku cell input, if necessary.
   *
   * @param sudoku : outer sudoku class
   * @param inputCell : sudoku cell input
   *
   */
  private void disableNumberButton(Sudoku sudoku, SudokuCell inputCell)
  {
    var valueOccurrences = (HashMap<Integer, Integer>) sudoku.getValueOccurrences();

    if (valueOccurrences.get(inputCell.getCurrentValue()) == PUZZLE_SIZE)
    {
      var numberButton = GameController.getNumberButton(inputCell.getCurrentValue());

      GameController.disableNumberButton(numberButton);
      GameController.addDisabledNumberButton(numberButton);
    }
  }


  /**
   *
   * Handles the win condition, if necessary.
   *
   * @param sudoku : outer sudoku class
   *
   */
  private void handleWinCondition(Sudoku sudoku)
  {
    if (sudoku.getCellsRemaining() == 0)
    {
      GameController.checkPuzzleCompleted();
    }
  }
}
