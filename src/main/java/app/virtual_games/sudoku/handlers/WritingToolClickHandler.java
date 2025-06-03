package app.virtual_games.sudoku.handlers;

import app.virtual_games.sudoku.controllers.GameController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Custom event handler for the writing tool button click event.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class WritingToolClickHandler implements EventHandler<ActionEvent>
{
  /**
   * Initiates updating the current writing tool.
   *
   * @param clickEvent : writing tool button click event
   */
  @Override
  public void handle(ActionEvent clickEvent)
  {
    var writingToolButton = (Button) clickEvent.getSource();

    if (!this.isSameWritingTool(writingToolButton))
    {
      if (GameController.isWritingToolClicked())
      {
        GameController.unclickWritingTool();
      }

      GameController.clickWritingTool(writingToolButton);
      GameController.resetCurrentClickedCell();
      GameController.unhighlightSudokuPuzzle();
      GameController.hideTimePenalty();
      GameController.stopTimePenaltyTimer();
      GameController.stopHintCellTimer();
      GameController.setCurrentHintCell(null);

      if (GameController.isNumberButtonClicked())
      {
        GameController.highlightOccurrences();
      }
    }
  }

  /** Private Helper Methods **/

  /**
   * Determines whether the newly clicked writing tool is the same as the currently clicked writing
   * tool.
   *
   * @param writingToolButton : writing tool button
   * @return boolean : true —> same button; false —> different button
   */
  private boolean isSameWritingTool(Button writingToolButton)
  {
    if (!GameController.isWritingToolClicked())
    {
      return false;
    } else
    {
      return writingToolButton.getText().equals(GameController.getCurrentClickedWritingTool().getText());
    }
  }
}
