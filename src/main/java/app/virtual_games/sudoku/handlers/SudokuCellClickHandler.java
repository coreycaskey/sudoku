package app.virtual_games.sudoku.handlers;

import app.virtual_games.sudoku.controllers.GameController;
import app.virtual_games.sudoku.models.SudokuCell;
import app.virtual_games.sudoku.models.WritingTool;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

/**
 * Custom event handler to process the sudoku cell click event.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class SudokuCellClickHandler implements EventHandler<MouseEvent>
{
  /**
   * Initiates updating the contents and styling of the impacted cells.
   *
   * @param clickEvent : sudoku cell click event
   */
  @Override
  public void handle(MouseEvent clickEvent)
  {
    var clickedCell = (SudokuCell) clickEvent.getSource();

    GameController.unhighlightSudokuPuzzle();
    GameController.unclickNumberButton();
    GameController.setCurrentClickedNumberButton(null);
    GameController.hideTimePenalty();
    GameController.stopTimePenaltyTimer();
    GameController.stopHintCellTimer();
    GameController.setCurrentHintCell(null);

    if (GameController.getCurrentWritingTool() == WritingTool.ERASER)
    {
      this.handleErase(clickedCell);
    } else
    {
      this.handleClick(clickedCell);
    }
  }

  /** Private Helper Methods **/

  /**
   * Clears the cell contents and makes the cell uneditable to prevent input.
   *
   * @param clickedCell : clicked sudoku cell
   */
  private void handleErase(SudokuCell clickedCell)
  {
    if (clickedCell.isEditable())
    {
      clickedCell.setText(""); // calls TextPropertyListener
      clickedCell.setEditable(false);
    } else if (this.isCompletedCell(clickedCell))
    {
      GameController.setCurrentClickedCell(clickedCell);
      GameController.highlightRowColumnBlock();
    }
  }

  /**
   * Determines whether the clicked cell is completed.
   *
   * @param clickedCell : clicked cell
   * @return boolean : true —> is completed cell; false —> is incompleted cell
   */
  private boolean isCompletedCell(SudokuCell clickedCell)
  {
    return !clickedCell.isEditable() && !clickedCell.isEmpty();
  }

  /**
   * Handles both editable and non—editable cell click events.
   *
   * @param clickedCell : clicked sudoku cell
   */
  private void handleClick(SudokuCell clickedCell)
  {
    GameController.setCurrentClickedCell(clickedCell);
    GameController.highlightRowColumnBlock();

    if (clickedCell.isEditable())
    {
      this.handleEditableCell(clickedCell);
    }
  }

  /**
   * Handles the editable cell click event.
   *
   * @param clickedCell : clicked sudoku cell
   */
  private void handleEditableCell(SudokuCell clickedCell)
  {
    clickedCell.setCursor(Cursor.TEXT);
    clickedCell.positionCaret(clickedCell.getText().length());

    if (clickedCell.isEmpty())
    {
      this.handleEmptyCell(clickedCell);
    } else
    {
      this.handleFilledCell(clickedCell);
    }

    this.updateClickedWritingTool();
  }

  /**
   * Updates the currently clicked writing tool, if necessary.
   */
  private void updateClickedWritingTool()
  {
    if (GameController.getCurrentClickedWritingTool() == null)
    {
      GameController.setCurrentClickedWritingTool(GameController.getWritingToolButton());
      GameController.getCurrentClickedWritingTool().getStyleClass().add("clicked-writing-tool");
    }
  }

  /**
   * Adds styling to the empty cell based on the current writing tool.
   *
   * @param clickedCell : clicked sudoku cell
   */
  private void handleEmptyCell(SudokuCell clickedCell)
  {
    if (GameController.getCurrentWritingTool() == WritingTool.PEN)
    {
      this.addPenStyling(clickedCell);
    } else
    {
      this.addPencilStyling(clickedCell);
    }
  }

  /**
   * Adds pen—related CSS styling.
   *
   * @param clickedCell : clicked sudoku cell
   */
  private void addPenStyling(SudokuCell clickedCell)
  {
    clickedCell.addStyling("pen");
    clickedCell.removeStyling("pencil");
    clickedCell.setWritingTool(WritingTool.PEN);
  }

  /**
   * Adds pencil—related CSS styling.
   *
   * @param clickedCell : clicked sudoku cell
   */
  private void addPencilStyling(SudokuCell clickedCell)
  {
    clickedCell.addStyling("pencil");
    clickedCell.removeStyling("pen");
    clickedCell.setWritingTool(WritingTool.PENCIL);
  }

  /**
   * Clears the contents of the cell and handles the empty cell.
   *
   * @param clickedCell : clicked sudoku cell
   */
  private void handleFilledCell(SudokuCell clickedCell)
  {
    if (!GameController.getCurrentWritingTool().equals(clickedCell.getWritingTool()))
    {
      clickedCell.setText(""); // calls TextPropertyListener

      this.handleEmptyCell(clickedCell);
    }
  }
}
