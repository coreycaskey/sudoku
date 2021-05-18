package app.virtual_games.sudoku.listeners;

import app.virtual_games.sudoku.models.SudokuCell;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


/**
 *
 * Custom change listener for the sudoku cell (text field) length property.
 *
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class LengthPropertyListener implements ChangeListener<Number>
{
  /**
   *
   * Limits the length of the text field to one digit.
   *
   * @param observable : {@link ObservableValue} that maps to the sudoku cell input
   * @param oldValue   : previous length of the sudoku cell input
   * @param newValue   : current length of the sudoku cell input
   *
   */
  @Override
  public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
  {
    SudokuCell inputCell = (SudokuCell) ((ReadOnlyIntegerProperty) observable).getBean();

    if (newValue.intValue() > 1)
    {
      inputCell.setText(inputCell.getText().substring(0, 1));
    }
  }
}
