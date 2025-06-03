package app.virtual_games.sudoku.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.simple.JSONObject;

import app.virtual_games.sudoku.controllers.ApiController;
import app.virtual_games.sudoku.controllers.SolutionController;
import app.virtual_games.sudoku.exceptions.SudokuPuzzleException;
import app.virtual_games.sudoku.models.ApiResponse.Square;

/**
 * Sudoku puzzle.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class Sudoku
{
  private static final int PUZZLE_SIZE = 9;
  private static final int BLOCK_SIZE = 3;
  private static final int TOTAL_CELLS = 81;

  private ArrayList<Square> correctCells;
  private HashMap<Integer, Integer> valueOccurrences;
  private ArrayList<CellPosition> hintCells;
  private SudokuCell currentClickedCell;
  private SudokuCell currentHintCell;
  private int cellsRemaining;

  private int[] initialPuzzle;
  private int[] solvedPuzzle;
  private SudokuBlock[] userPuzzle;

  private boolean isSolved;

  /**
   * Initializes a sudoku puzzle with the corresponding difficulty. Initializes the following
   * variable(s): {@link #correctCells} {@link #valueOccurrences} {@link #cellsRemaining}
   * {@link #initialPuzzle} {@link #solvedPuzzle} {@link #isSolved} {@link #userPuzzle}
   * {@link #hintCells}
   *
   * @throws SudokuPuzzleException
   * @param difficultyId : unique identifier for the puzzle difficulty (e.g. Easy —> 1)
   */
  public Sudoku(int difficultyId) throws SudokuPuzzleException
  {
    this.correctCells = (ArrayList<Square>) ApiController.getSudokuPuzzle(difficultyId);
    this.valueOccurrences = (HashMap<Integer, Integer>) this.initializeValueOccurrences();
    this.cellsRemaining = TOTAL_CELLS - this.correctCells.size();

    this.initialPuzzle = this.buildInitialPuzzle();

    this.solvedPuzzle = SolutionController.solvePuzzle(this);
    this.isSolved = this.solvedPuzzle != null;
    this.userPuzzle = this.loadUserPuzzle();
    this.hintCells = this.initializeHintCells();
  }

  /** Private Helper Methods **/

  /**
   * Initializes occurrence counts for each sudoku value.
   *
   * @return Map<Integer, Integer> : map of value occurrences
   */
  private Map<Integer, Integer> initializeValueOccurrences()
  {
    var occurrencesMap = new HashMap<Integer, Integer>();

    this.correctCells.forEach(cell ->
    {
      int value = cell.value;
      int occurrences = occurrencesMap.containsKey(value) ? occurrencesMap.get(value) : 0;
      occurrencesMap.put(value, occurrences + 1);
    });

    return occurrencesMap;
  }

  /**
   * Builds flat array of initial sudoku values.
   *
   * @return int[] : array of initial sudoku values
   */
  private int[] buildInitialPuzzle()
  {
    return IntStream.range(0, TOTAL_CELLS).map(this::getCellValue).toArray();
  }

  /**
   * Retrieves the cell's current value (0 —> empty cell; 1 — 9 —> non—empty cell).
   *
   * @param cellIndex : index of cell in the puzzle array
   * @return int : cell value
   */
  private int getCellValue(int cellIndex)
  {
    Optional<Square> initialCell = this.correctCells.stream().filter(cell -> this.isInitialCell(cell, cellIndex))
        .findAny();

    return initialCell.isPresent() ? (int) initialCell.get().value : 0;
  }

  /**
   * Determines whether cell index corresponds to initial cell.
   *
   * @param initialCell : current initial cell
   * @param cellIndex   : index of the cell in the puzzle array
   * @return boolean : true —> initial cell; false —> not initial cell
   */
  private boolean isInitialCell(Square initialCell, int cellIndex)
  {
    boolean isSameRow = initialCell.y == (cellIndex / PUZZLE_SIZE);
    boolean isSameCol = initialCell.x == (cellIndex % PUZZLE_SIZE);

    return isSameRow && isSameCol;
  }

  /**
   * Loads array with {@link SudokuBlock} objects.
   *
   * @return SudokuBlock[] : array of SudokuBlock instances
   */
  private SudokuBlock[] loadUserPuzzle()
  {
    return IntStream.range(0, PUZZLE_SIZE).mapToObj(this::getSudokuBlock).toArray(SudokuBlock[]::new);
  }

  /**
   * Initializes a {@link SudokuBlock} instance.
   *
   * @param blockId : unique identifier for the sudoku block
   * @return SudokuBlock : SudokuBlock instance
   */
  private SudokuBlock getSudokuBlock(int blockId)
  {
    return new SudokuBlock(blockId, this);
  }

  /**
   * Retrieves the positions of all the hints cells (i.e. all the empty cells).
   *
   * @return ArrayList<CellPosition> : list of hint cell positions
   */
  private ArrayList<CellPosition> initializeHintCells()
  {
    return IntStream.range(0, TOTAL_CELLS).filter(cellIndex -> this.initialPuzzle[cellIndex] == 0)
        .mapToObj(this::getCellPosition).collect(Collectors.toCollection(ArrayList::new));
  }

  /**
   * Retrieves the {@link CellPosition} for the cell index.
   *
   * @param cellIndex : index of the cell in the puzzle array
   * @return CellPosition : position of the cell
   */
  private CellPosition getCellPosition(int cellIndex)
  {
    return new CellPosition(cellIndex / PUZZLE_SIZE, cellIndex % PUZZLE_SIZE);
  }

  /**
   * Retrieves a cell {@link JSONObject}.
   *
   * @param cellIndex : index of the cell in the puzzle array
   * @param cellValue : cell value
   * @return JSONObject : cell JSON Object
   */
  private Square toSquare(int cellIndex, int cellValue)
  {
    return new Square(cellIndex % PUZZLE_SIZE, cellIndex / PUZZLE_SIZE, cellValue);
  }

  /**
   * Resets the contents of each sudoku cell in the {@link #userPuzzle}.
   */
  private void resetUserPuzzle()
  {
    for (SudokuBlock sudokuBlock : this.userPuzzle)
    {
      for (SudokuCell sudokuCell : sudokuBlock.getBlockCells())
      {
        if (!this.isInitialCell(sudokuCell.getCellIndex()))
        {
          sudokuCell.textProperty().removeListener(sudokuCell.getTextListener());

          sudokuCell.setText("");
          sudokuCell.setCurrentValue(0);
          sudokuCell.setNotes(0);
          sudokuCell.setEditable(true);
          sudokuCell.setWritingTool(WritingTool.PEN);
          sudokuCell.removeStyling("incorrect-cell-value", "pencil", "pen");
          sudokuCell.addStyling("pen");

          sudokuCell.textProperty().addListener(sudokuCell.getTextListener());
        }
      }
    }
  }

  /**
   * Determines whether the cell index corresponds to an initial cell.
   *
   * @param cellIndex : index of the cell in the puzzle array
   * @return boolean : true —> initial cell; false —> not initial cell
   */
  private boolean isInitialCell(int cellIndex)
  {
    return this.initialPuzzle[cellIndex] != 0;
  }

  /**
   * Highlights the cell occurrence.
   *
   * @param sudokuCell : corresponding sudoku cell
   * @param cellValue  : cell value
   */
  private void highlightCellOccurrence(SudokuCell sudokuCell)
  {
    if (this.isCorrectValue(sudokuCell))
    {
      sudokuCell.highlightCorrectOccurrence();
    } else
    {
      sudokuCell.highlightIncorrectOccurrence();
    }
  }

  /**
   * Highlights the row, column, block, or clicked cell.
   *
   * @param sudokuCell : corresponding sudoku cell
   */
  private void highlightCell(SudokuCell sudokuCell)
  {
    if (sudokuCell.equals(currentClickedCell))
    {
      sudokuCell.highlightClickedCell();
    } else
    {
      sudokuCell.highlightRowColumnBlockCell();
    }
  }

  /**
   * Retrieves a {@link SudokuCell} object with the given row and column.
   *
   * @param row : cell row
   * @param col : cell column
   * @return SudokuCell : SudokuCell instance
   */
  private SudokuCell getSudokuCell(int row, int col)
  {
    for (SudokuBlock sudokuBlock : this.userPuzzle)
    {
      for (SudokuCell sudokuCell : sudokuBlock.getBlockCells())
      {
        if (sudokuCell.getPuzzleRow() == row && sudokuCell.getPuzzleCol() == col)
        {
          return sudokuCell;
        }
      }
    }

    return null;
  }

  /**
   * Retrieves a random index within {@link #hintCells}.
   *
   * @return Integer : random index
   */
  private Integer generateRandomIndex()
  {
    return !this.hintCells.isEmpty() ? new Random().nextInt(this.hintCells.size()) : null;
  }

  /**
   * Retrieves the hint cell.
   *
   * @param cellIndex : cell index
   * @return SudokuCell : hint cell
   */
  private SudokuCell getHintCell(int cellIndex)
  {
    int cellRow = this.hintCells.get(cellIndex).getRow();
    int cellCol = this.hintCells.get(cellIndex).getCol();

    return this.getSudokuCell(cellRow, cellCol);
  }

  /**
   * Updates the hint cell styling and contents.
   *
   * @param hintCell : hint cell
   */
  private void updateHintCellStyling(SudokuCell hintCell)
  {
    if (hintCell.getWritingTool() == WritingTool.PENCIL)
    {
      hintCell.addStyling("pen");
      hintCell.removeStyling("pencil");
      hintCell.setWritingTool(WritingTool.PEN);
      hintCell.setNotes(0);
    } else if (!hintCell.isCorrect())
    {
      hintCell.removeStyling("incorrect-cell-value");
    }

    hintCell.setText(Integer.toString(this.solvedPuzzle[hintCell.getCellIndex()]));
    hintCell.highlightHintCell();
  }

  /**
   * Updates {@link #currHintCell}.
   *
   * @param hintCell : hint cell
   */
  private void updateCurrHintCell(SudokuCell hintCell)
  {
    if (this.currentHintCell != null)
    {
      this.currentHintCell.unhighlightHintCell();
    }

    this.currentHintCell = hintCell;
  }

  /** Public Helper Methods **/

  /**
   * Increments the value occurrence in {@link #valueOccurrences}.
   *
   * @param cellValue : cell value
   */
  public void updateValueOccurrences(int cellValue)
  {
    int occurrences = this.valueOccurrences.containsKey(cellValue) ? this.valueOccurrences.get(cellValue) : 0;

    this.valueOccurrences.put(cellValue, occurrences + 1);
  }

  /**
   * Resets the following variable(s): {@link #correctCells} {@link #valueOccurrences}
   * {@link #cellsRemaining} {@link #hintCells} {@link #currentClickedCell} {@link #currentHintCell}
   * {@link #userPuzzle}
   */
  public void restartPuzzle()
  {
    this.correctCells = IntStream.range(0, TOTAL_CELLS).filter(cellIndex -> this.initialPuzzle[cellIndex] > 0)
        .mapToObj(cellIndex -> this.toSquare(cellIndex, this.initialPuzzle[cellIndex]))
        .collect(Collectors.toCollection(ArrayList::new));

    this.valueOccurrences = (HashMap<Integer, Integer>) this.initializeValueOccurrences();
    this.cellsRemaining = TOTAL_CELLS - this.correctCells.size();
    this.hintCells = this.initializeHintCells();
    this.currentClickedCell = null;
    this.currentHintCell = null;

    this.resetUserPuzzle();
  }

  /**
   * Disables each sudoku cell and hides its contents.
   */
  public void hideSudokuCells()
  {
    for (SudokuBlock sudokuBlock : this.userPuzzle)
    {
      for (SudokuCell sudokuCell : sudokuBlock.getBlockCells())
      {
        sudokuCell.textProperty().removeListener(sudokuCell.getTextListener());

        sudokuCell.setText("");
        sudokuCell.setDisable(true);

        sudokuCell.textProperty().addListener(sudokuCell.getTextListener());
      }
    }
  }

  /**
   * Enables each sudoku cell and shows its contents.
   */
  public void showSudokuCells()
  {
    for (SudokuBlock sudokuBlock : this.userPuzzle)
    {
      for (SudokuCell sudokuCell : sudokuBlock.getBlockCells())
      {
        sudokuCell.textProperty().removeListener(sudokuCell.getTextListener());

        if (sudokuCell.getCurrentValue() > 0)
        {
          sudokuCell.setText(Integer.toString(sudokuCell.getCurrentValue()));
        } else if (sudokuCell.getNotes() > 0)
        {
          sudokuCell.setText(Integer.toString(sudokuCell.getNotes()));
        }

        sudokuCell.setDisable(false);
        sudokuCell.textProperty().addListener(sudokuCell.getTextListener());
      }
    }
  }

  /**
   * Increments {@link #cellsRemaining}.
   */
  public void incrementCellsRemaining()
  {
    this.cellsRemaining++;
  }

  /**
   * Decrements {@link #cellsRemaining}.
   */
  public void decrementCellsRemaining()
  {
    this.cellsRemaining--;
  }

  /**
   * Highlights all occurrences of the clicked number button value.
   *
   * @param clickedValue : number button value
   */
  public void highlightOccurrences(int clickedValue)
  {
    for (SudokuBlock sudokuBlock : this.userPuzzle)
    {
      for (SudokuCell sudokuCell : sudokuBlock.getBlockCells())
      {
        if (sudokuCell.getCurrentValue() == clickedValue)
        {
          this.highlightCellOccurrence(sudokuCell);
        }
      }
    }
  }

  /**
   * Highlights the row, column, and block of the clicked cell.
   */
  public void highlightRowColumnBlock()
  {
    for (SudokuBlock sudokuBlock : this.userPuzzle)
    {
      for (SudokuCell sudokuCell : sudokuBlock.getBlockCells())
      {
        if (sudokuCell.getParentBlockId() == currentClickedCell.getParentBlockId()
            || sudokuCell.getPuzzleRow() == currentClickedCell.getPuzzleRow()
            || sudokuCell.getPuzzleCol() == currentClickedCell.getPuzzleCol())
        {
          this.highlightCell(sudokuCell);
        }
      }
    }
  }

  /**
   * Unhighlights all sudoku cells.
   */
  public void unhighlightSudokuPuzzle()
  {
    for (SudokuBlock sudokuBlock : this.userPuzzle)
    {
      for (SudokuCell sudokuCell : sudokuBlock.getBlockCells())
      {
        sudokuCell.unhighlightCell();
      }
    }
  }

  /**
   * Loads the sudoku puzzle with the provided hint.
   */
  public void getHint()
  {
    Integer hintCellIndex = this.generateRandomIndex();

    if (hintCellIndex != null)
    {
      SudokuCell hintCell = this.getHintCell(hintCellIndex);

      if (hintCell != null)
      {
        this.updateHintCellStyling(hintCell);
        this.hintCells.remove(hintCellIndex.intValue());
        this.updateCurrHintCell(hintCell);
      }
    }
  }

  /**
   * Determines whether the input of the sudoku cell is correct.
   *
   * @param sudokuCell : updated sudoku cell
   * @return boolean : true —> correct input; false —> incorrect input
   */
  public boolean isCorrectValue(SudokuCell sudokuCell)
  {
    return this.solvedPuzzle[(sudokuCell.getPuzzleRow() * PUZZLE_SIZE) + sudokuCell.getPuzzleCol()] == sudokuCell
        .getCurrentValue();
  }

  /**
   * Determines whether the completed {@link #userPuzzle} is correct.
   *
   * @return boolean : true —> correct solution; false —> incorrect solution
   */
  public boolean isCorrectSolution()
  {
    for (SudokuBlock sudokuBlock : this.userPuzzle)
    {
      for (SudokuCell sudokuCell : sudokuBlock.getBlockCells())
      {
        if (sudokuCell.getCurrentValue() != this.solvedPuzzle[sudokuCell.getCellIndex()])
        {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * Adds a sudoku cell to the list of correct cells.
   *
   * @param sudokuCell : sudoku cell
   */
  public void addCorrectCell(SudokuCell sudokuCell)
  {
    Square correctCell = new Square(sudokuCell.getPuzzleCol(), sudokuCell.getPuzzleRow(), sudokuCell.getCurrentValue());
    this.correctCells.add(correctCell);
  }

  /** Getters and Setters **/

  /**
   * Retrieves {@link #PUZZLE_SIZE}.
   *
   * @return int : puzzle size
   */
  public int getPuzzleSize()
  {
    return PUZZLE_SIZE;
  }

  /**
   * Retrieves {@link #BLOCK_SIZE}.
   *
   * @return int : block size
   */
  public int getBlockSize()
  {
    return BLOCK_SIZE;
  }

  /**
   * Retrieves {@link #TOTAL_CELLS}.
   *
   * @return int : total cells
   */
  public int getTotalCells()
  {
    return TOTAL_CELLS;
  }

  /**
   * Retrieves {@link #valueOccurrences}.
   *
   * @return Map<Integer, Integer> : map of sudoku value occurrences
   */
  public Map<Integer, Integer> getValueOccurrences()
  {
    return this.valueOccurrences;
  }

  /**
   * Retrieves {@link #currentClickedCell}.
   *
   * @return SudokuCell : currently clicked cell
   */
  public SudokuCell getCurrentClickedCell()
  {
    return this.currentClickedCell;
  }

  /**
   * Updates {@link #currentClickedCell}.
   *
   * @param currClickedCell : newly clicked cell
   */
  public void setCurrentClickedCell(SudokuCell newClickedCell)
  {
    this.currentClickedCell = newClickedCell;
  }

  /**
   * Retrieves {@link #currentHintCell}.
   *
   * @return SudokuCell : current hint cell
   */
  public SudokuCell getCurrentHintCell()
  {
    return this.currentHintCell;
  }

  /**
   * Updates {@link #currentHintCell}.
   *
   * @param hintCell : new hint cell
   */
  public void setCurrentHintCell(SudokuCell hintCell)
  {
    this.currentHintCell = hintCell;
  }

  /**
   * Retrieves {@link #cellsRemaining}.
   *
   * @return int : number of empty, incorrect, and note cells
   */
  public int getCellsRemaining()
  {
    return this.cellsRemaining;
  }

  /**
   * Retrieves {@link #initialPuzzle}.
   *
   * @return int[] : array of initial sudoku values
   */
  public int[] getInitialPuzzle()
  {
    return this.initialPuzzle;
  }

  /**
   * Retrieves {@link #solvedPuzzle}.
   *
   * @return int[] : array of correct sudoku values
   */
  public int[] getSolvedPuzzle()
  {
    return this.solvedPuzzle;
  }

  /**
   * Retrieves {@link #userPuzzle}.
   *
   * @return SudokuBlock[] : array of SudokuBlock instances
   */
  public SudokuBlock[] getUserPuzzle()
  {
    return this.userPuzzle;
  }

  /**
   * Retrieves {@link #isSolved}.
   *
   * @return boolean : true —> puzzle is solved; false —> puzzle is unsolved
   */
  public boolean getIsSolved()
  {
    return this.isSolved;
  }
}
