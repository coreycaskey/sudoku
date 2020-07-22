package app.virtual_games.sudoku.models;

import app.virtual_games.sudoku.handlers.SudokuCellHoverHandler;
import app.virtual_games.sudoku.handlers.SudokuCellClickHandler;
import app.virtual_games.sudoku.listeners.LengthPropertyListener;
import app.virtual_games.sudoku.listeners.TextPropertyListener;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.TextField;


/**
 * 
 * Custom child class of {@link TextField} that represents the 81 sudoku cells.
 * 
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class SudokuCell extends TextField 
{
	private int cellIndex;
	private int parentBlockId;
	private int currentValue;
	private int correctValue;
	private int notes;

	private Sudoku parentSudoku;
	private BorderStyling borderClass;
	private WritingTool writingTool;
	private ChangeListener<String> textListener;
	

	/**
	 * 
	 * Initializes the following variables:
	 * 
	 *  — {@link #cellIndex}
	 *  — {@link #parentBlockId}
	 *  — {@link #currentValue}
	 *  — {@link #correctValue}
	 *  — {@link #notes}
	 *  — {@link #parentSudoku}
	 *  — {@link #borderClass}
	 *  — {@link #writingTool}
	 *  — {@link #textListener}
	 *  
	 * Loads the sudoku cell's base styling and event handlers.
	 *  
	 * @param cellIndex	    : index of the cell in the puzzle array
	 * @param parentBlockId : unique identifier for the parent sudoku block
	 * @param parentSudoku  : outer sudoku class
	 * 
	 */
	public SudokuCell(int cellIndex, int parentBlockId, Sudoku parentSudoku)
	{
		this.cellIndex = cellIndex;
		this.parentBlockId = parentBlockId;
		this.currentValue = parentSudoku.getInitialPuzzle()[cellIndex];
		this.correctValue = parentSudoku.getSolvedPuzzle()[cellIndex];
		this.notes = 0;

		this.parentSudoku = parentSudoku;
		this.borderClass = BorderStyling.getEnumInstance(this.getPuzzleRow(), this.getPuzzleCol());
		this.writingTool = WritingTool.PEN;
		this.textListener = new TextPropertyListener();
				
		this.loadStaticStyling();
		this.loadHandlersAndListeners();
	}
	
	
	/**  Private Helper Methods  **/
	
	
	/**
	 * 
	 * Loads the base styling and display information for the cell.
	 * 
	 */
	private void loadStaticStyling() 
	{
		this.addStyling(this.borderClass.getClassName(), "pen");
		this.setText(this.currentValue == 0 ? "" : Integer.toString(this.currentValue));
		this.setEditable(this.currentValue == 0 ? true : false);
		this.positionCaret(this.getText().length()); 				// places the cursor at the end of the text
	}
	
	
	/**
	 * 
	 * Loads the different cell handlers and listeners.
	 * 
	 */
	private void loadHandlersAndListeners() 
	{
		this.setOnMouseEntered(new SudokuCellHoverHandler());
		this.setOnMouseClicked(new SudokuCellClickHandler());
		
		this.lengthProperty().addListener(new LengthPropertyListener());
		this.textProperty().addListener(this.textListener);
	}
	
	
	/**
	 * 
	 * Calculates the new cell index after shifting the value to the left.
	 * 
	 * @return int : left—shifted cell index
	 * 
	 */
	private int shiftCellIndexLeft()
	{
		return this.cellIndex - (this.parentSudoku.getBlockSize() * (this.parentBlockId % this.parentSudoku.getBlockSize()));
	}
	
	
	/**
	 * 
	 * Calculates the new cell index after shifting the value upward.
	 * 
	 * @param tempIndex : left—shifted cell index
	 * 
	 * @return int 		: upward—shifted cell index
	 * 
	 */
	private int shiftCellIndexUp(int tempIndex)
	{
		return tempIndex - (this.parentSudoku.getBlockSize() * this.parentSudoku.getPuzzleSize() * (this.parentBlockId / this.parentSudoku.getBlockSize()));
	}
	
	
	/**
	 * 
	 * Calculates the new cell index after scaling it down.
	 * 
	 * @param tempIndex : upward—shifted cell index
	 * 
	 * @return int 		: scaled—down cell index
	 * 
	 */
	private int scaleDownIndex(int tempIndex)
	{
		return (tempIndex / this.parentSudoku.getBlockSize()) + (tempIndex % this.parentSudoku.getBlockSize());
	}

	
	/**  Public Helper Methods  **/

	
	/**
	 * 
	 * Adds a collection of CSS class names to the sudoku cell.
	 * 
	 * @param styling : collection of CSS class names
	 * 
	 */
	public void addStyling(String... styling)
	{
		this.getStyleClass().addAll(styling);
	}
	
	
	/**
	 * 
	 * Removes a collection of CSS classes from the sudoku cell.
	 * 
	 * @param styling : collection of CSS class names
	 * 
	 */
	public void removeStyling(String... styling) 
	{
		this.getStyleClass().removeAll(styling);
	}


	/**
	 * 
	 * Highlights the occurrence of a correct sudoku cell.
	 * 
	 */
	public void highlightCorrectOccurrence()
	{
		this.addStyling("correct-cell-occurrence");
	}
	
	
	/**
	 * 
	 * Highlights the occurrence of an incorrect sudoku cell.
	 * 
	 */
	public void highlightIncorrectOccurrence() 
	{
		this.addStyling("incorrect-cell-occurrence");
	}
	
	
	/**
	 * 
	 * Highlights the clicked sudoku cell.
	 * 
	 */
	public void highlightClickedCell() 
	{
		this.addStyling("clicked-sudoku-cell");	
	}
	
	
	/**
	 * 
	 * Highlights the sudoku cell that's in the same row, column, or block as the clicked cell.
	 * 
	 */
	public void highlightRowColumnBlockCell() 
	{
		this.addStyling("row-col-block-cell");
	}
	
	
	/**
	 * 
	 * Highlights the sudoku cell hint.
	 * 
	 */
	public void highlightHintCell()
	{
		this.addStyling("hint-cell");
	}
	
	
	/**
	 * 
	 * Unhighlights the sudoku cell hint.
	 * 
	 */
	public void unhighlightHintCell()
	{
		this.removeStyling("hint-cell");
	}

	
	/**
	 * 
	 * Removes all highlight styling from the sudoku cell.
	 * 
	 */
	public void unhighlightCell()
	{
		this.removeStyling("correct-cell-occurrence", "incorrect-cell-occurrence", "clicked-sudoku-cell", "row-col-block-cell", "hint-cell");
	}
	
	
	/**
	 * 
	 * Calculates the cell's row in the puzzle.
	 * 
	 * @return int : puzzle row
	 * 
	 */
	public int getPuzzleRow() 
	{
		return this.cellIndex / this.parentSudoku.getPuzzleSize();
	}
	

	/**
	 * 
	 * Calculates the cell's column in the puzzle.
	 * 
	 * @return int : puzzle column
	 * 
	 */
	public int getPuzzleCol()
	{
		return this.cellIndex % this.parentSudoku.getPuzzleSize();
	}
	
	
	/**
	 * 
	 * Calculates the cell's row in the parent block.
	 * 
	 * @return int : parent block row
	 * 
	 */
	public int getBlockRow() 
	{
		return this.getBlockCellIndex() / this.parentSudoku.getBlockSize();
	}
	
	
	/**
	 * 
	 * Calculates the cell's column in the parent block.
	 * 
	 * @return int : parent block column
	 * 
	 */
	public int getBlockCol()
	{
		return this.getBlockCellIndex() % this.parentSudoku.getBlockSize();
	}
	
	
	/**
	 * 
	 * Calculates the index of the cell in the parent block (i.e. 0 — 8).
	 *
	 * @return int : parent block cell index
	 * 
	 */
	public int getBlockCellIndex()
	{
		return this.scaleDownIndex(this.shiftCellIndexUp(this.shiftCellIndexLeft()));
	}
	
	
	/**
	 * 
	 * Determines if the cell is empty.
	 *  
	 * @return boolean : true —> empty cell; false —> non—empty cell
	 * 
	 */
	public boolean isEmpty()
	{
		return this.currentValue == 0 && this.notes == 0;	// no permanent or non-permanent guesses
	}
	
	
	/**
	 * 
	 * Determines if the cell has the correct value.
	 * 
	 * @return boolean : true —> has the correct value; false —> has an incorrect value
	 * 
	 */
	public boolean isCorrect()
	{
		return this.correctValue == this.currentValue;
	}
	
	
	/**  Getters and Setters  **/
	
	
	/**
	 * 
	 * Retrieves {@link #cellIndex}.
	 * 
	 * @return int : index of the cell in the puzzle array
	 * 
	 */
	public int getCellIndex() 
	{
		return this.cellIndex;
	}
	
	
	/**
	 * 
	 * Retrieves {@link #parentBlockId}.
	 * 
	 * @return int : unique identifier for the parent sudoku block
	 * 
	 */
	public int getParentBlockId()
	{
		return this.parentBlockId;
	}
	
	
	/**
	 * 
	 * Retrieves {@link #currentValue}.
	 * 
	 * @return int : current cell value
	 * 
	 */
	public int getCurrentValue() 
	{
		return this.currentValue;
	} 
	
	
	/**
	 * 
	 * Updates {@link #currentValue}.
	 * 
	 * @param newValue : new cell value
	 * 
	 */
	public void setCurrentValue(int newValue) 
	{
		this.currentValue = newValue;
	}
	
	
	/**
	 * 
	 * Retrieves {@link #correctValue}.
	 * 
	 * @return int : correct cell value
	 * 
	 */
	public int getCorrectValue() 
	{
		return this.correctValue;
	}
	
	
	/**
	 * 
	 * Retrieves {@link #notes}.
	 * 
	 * @return int : cell notes
	 * 
	 */
	public int getNotes()
	{
		return this.notes;
	}
	
	
	/**
	 * 
	 * Updates {@link #notes}.
	 * 
	 * @param notes : cell notes
	 * 
	 */
	public void setNotes(int notes)
	{
		this.notes = notes;
	}
	
	
	/**
	 * 
	 * Retrieves {@link #parentSudoku}.
	 * 
	 * @return Sudoku : outer sudoku class
	 * 
	 */
	public Sudoku getParentSudoku()
	{
		return this.parentSudoku;
	}
	
	
	/**
	 * 
	 * Retrieves {@link #writingTool}.
	 * 
	 * @return WritingTool : current writing tool
	 * 
	 */
	public WritingTool getWritingTool()
	{
		return this.writingTool;
	}
	
	
	/**
	 * 
	 * Updates {@link #writingTool}.
	 * 
	 * @param writingTool : new writing tool
	 * 
	 */
	public void setWritingTool(WritingTool writingTool)
	{
		this.writingTool = writingTool;
	}
	

	/**
	 * 
	 * Retrieves {@link #textListener}.
	 * 
	 * @return ChangeListener<String> : cell text property listener
	 * 
	 */
	public ChangeListener<String> getTextListener()
	{
		return this.textListener;
	}
}