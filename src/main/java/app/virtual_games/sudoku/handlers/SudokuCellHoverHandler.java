package app.virtual_games.sudoku.handlers;

import app.virtual_games.sudoku.controllers.GameController;
import app.virtual_games.sudoku.models.SudokuCell;
import app.virtual_games.sudoku.models.WritingTool;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;


/**
 *
 * Custom event handler for the sudoku cell hover event.
 *
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class SudokuCellHoverHandler implements EventHandler<MouseEvent>
{
  private static final ImageCursor PEN_CURSOR = new ImageCursor(new Image(SudokuCellHoverHandler.class.getClassLoader().getResourceAsStream("./img/pen-icon-cursor.png"), 30, 30, true, true), 15, 15);
  private static final ImageCursor PENCIL_CURSOR = new ImageCursor(new Image(SudokuCellHoverHandler.class.getClassLoader().getResourceAsStream("./img/pencil-icon-cursor.png"), 30, 30, true, true), 15, 15);
  private static final ImageCursor ERASER_CURSOR = new ImageCursor(new Image(SudokuCellHoverHandler.class.getClassLoader().getResourceAsStream("./img/eraser-icon-cursor.png"), 30, 30, true, true), 15, 15);


  /**
   *
   * Updates the cursor icon.
   *
   * @param hoverEvent : sudoku cell hover event
   *
   */
  @Override
  public void handle(MouseEvent hoverEvent)
  {
    SudokuCell hoveredCell = (SudokuCell) hoverEvent.getSource();

    if (GameController.isWritingToolClicked())
    {
      this.handleWritingToolClicked(hoveredCell);
    }
    else
    {
      hoveredCell.setCursor(Cursor.HAND);
    }
  }


  /**  Private Helper Methods  **/


  /**
   *
   * Handles the cell hover event when a writing tool is already clicked.
   *
   * @param hoveredCell : hovered cell
   *
   */
  private void handleWritingToolClicked(SudokuCell hoveredCell)
  {
    ImageCursor cursorImage = this.getCursorImage();

    if (this.isPenOrPencil())
    {
      this.resetErasedCell(hoveredCell);
    }

    if (hoveredCell.isEditable() || this.isErased(hoveredCell))
    {
      this.handleEditableCell(hoveredCell, cursorImage);
    }
    else
    {
      hoveredCell.setCursor(Cursor.HAND);
    }
  }


  /**
   *
   * Retrieves the custom cursor image.
   *
   * @return ImageCursor : custom cursor image
   *
   */
  private ImageCursor getCursorImage()
  {
    if (GameController.getCurrentWritingTool() == WritingTool.PEN)
    {
      return PEN_CURSOR;
    }
    else if (GameController.getCurrentWritingTool() == WritingTool.PENCIL)
    {
      return PENCIL_CURSOR;
    }
    else
    {
      return ERASER_CURSOR;
    }
  }


  /**
   *
   * Determines whether the current writing tool is a pen or pencil.
   *
   * @return boolean : true —> pen or pencil; false —> eraser
   *
   */
  private boolean isPenOrPencil()
  {
    return GameController.getCurrentWritingTool() == WritingTool.PEN || GameController.getCurrentWritingTool() == WritingTool.PENCIL;
   }


  /**
   *
   * Resets the editability of an erased cell.
   *
   * @param hoveredCell : hovered cell
   *
   */
  private void resetErasedCell(SudokuCell hoveredCell)
  {
    if (this.isErased(hoveredCell))
    {
      hoveredCell.setEditable(true);
    }
  }


  /**
   *
   * Determines whether the hovered cell has been erased.
   *
   * @param hoveredCell : hovered cell
   *
   * @return boolean : true —> is erased; false —> is not erased
   *
   */
  private boolean isErased(SudokuCell hoveredCell)
  {
    return !hoveredCell.isEditable() && hoveredCell.isEmpty();
  }


  /**
   *
   * Handles the cell hover event when the cell is editable.
   *
   * @param hoveredCell : hovered cell
   * @param cursorImg    : custom cursor image
   *
   */
  private void handleEditableCell(SudokuCell hoveredCell, ImageCursor cursorImg)
  {
    if (hoveredCell.equals(GameController.getCurrentClickedCell()))
    {
      hoveredCell.setCursor(Cursor.TEXT);
    }
    else
    {
      hoveredCell.setCursor(cursorImg);
    }
  }
}
