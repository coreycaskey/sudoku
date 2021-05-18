package app.virtual_games.sudoku.controllers;

import app.virtual_games.sudoku.models.CellPosition;
import app.virtual_games.sudoku.models.Sudoku;

import java.util.OptionalInt;
import java.util.stream.IntStream;


/**
 *
 * Implements a back—tracking, DFS algorithm to solve the sudoku puzzle.
 *
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class SolutionController
{
  private static int[] solvedPuzzle;
  private static int puzzleSize;
  private static int blockSize;
  private static int totalCells;


  private SolutionController() { }


  /**
   *
   * Initializes the following variable(s):
   *
   * {@link #solvedPuzzle}
   * {@link #puzzleSize}
   * {@link #blockSize}
   * {@link #totalCells}
   *
   * Passes control to {@link #solutionHelper()}.
   *
   * @param sudoku : base sudoku class
   *
   * @return int[] : array of correct sudoku values
   *
   */
  public static int[] solvePuzzle(Sudoku sudoku)
  {
    solvedPuzzle = sudoku.getInitialPuzzle().clone();
    puzzleSize = sudoku.getPuzzleSize();
    blockSize = sudoku.getBlockSize();
    totalCells = sudoku.getTotalCells();

    return solutionHelper() ? solvedPuzzle : null;
  }


  /**  Private Helper Methods  **/


  /**
   *
   * Primary recursive helper method that facilitates the DFS algorithm.
   *
   * @return boolean : true —> valid solution found; false —> no solution found
   *
   */
  private static boolean solutionHelper()
  {
    CellPosition emptyCell = getEmptyCell();

    if (emptyCell != null) {
      return testSudokuValues(emptyCell);
    }

    return true; // no more empty cells
  }


  /**
   *
   * Retrieves the {@link CellPosition} of the next empty cell.
   *
   * @return CellPosition : position of the empty cell
   *
   */
  private static CellPosition getEmptyCell()
  {
    OptionalInt cellIndex = IntStream.range(0, totalCells)
                                      .filter(i -> solvedPuzzle[i] == 0)
                                      .findFirst();

    return cellIndex.isPresent() ? getCellPosition(cellIndex.getAsInt()) : null;
  }


  /**
   *
   * Initializes the {@link CellPosition} for the empty cell.
   *
   * @param cellIndex : index of the cell in the puzzle array
   *
   * @return CellPosition : position of the empty cell
   *
   */
  private static CellPosition getCellPosition(int cellIndex)
  {
    return new CellPosition(cellIndex / puzzleSize, cellIndex % puzzleSize);
  }


  /**
   *
   * Secondary recursive helper method that facilites the back—tracking process.
   *
   * @param emptyCell : position of the empty cell
   *
   * @return boolean : true —> valid solution found; false —> invalid solution found
   *
   */
  private static boolean testSudokuValues(CellPosition emptyCell)
  {
    for (var testValue = 1; testValue <= puzzleSize; testValue++)
    {
      if (isPotentialValue(emptyCell, testValue))
      {
        solvedPuzzle[getCellIndex(emptyCell)] = testValue; // test the proposed value

        if (solutionHelper()) { return true; } // proposed value gave a valid solution

        solvedPuzzle[getCellIndex(emptyCell)] = 0; // proposed value gave an invalid solution
      }
    }

    return false;
  }


  /**
   *
   * Determines whether the proposed sudoku value and position cause conflicts.
   *
   * @param emptyCell : position of the empty cell
   * @param testValue : proposed sudoku value
   *
   * @return boolean : true —> no conflicts; false —> conflicts
   *
   */
  private static boolean isPotentialValue(CellPosition emptyCell, int testValue)
  {
    return isUniqueInRow(emptyCell, testValue) && isUniqueInColumn(emptyCell, testValue) && isUniqueInBlock(emptyCell, testValue);
  }


  /**
   *
   * Determines whether the proposed sudoku value already exists in the sudoku row.
   *
   * @param emptyCell : position of the empty cell
   * @param testValue : proposed sudoku value
   *
   * @return boolean : true —> no conflicts; false —> conflicts
   *
   */
  private static boolean isUniqueInRow(CellPosition emptyCell, int testValue)
  {
    return IntStream.range(0, puzzleSize)
                    .filter(col -> isRepeat(new CellPosition(emptyCell.getRow(), col), emptyCell, testValue))
                    .count() == 0;
  }


  /**
   *
   * Determines whether the proposed sudoku value already exists in the sudoku column.
   *
   * @param emptyCell : position of the empty cell
   * @param testValue : proposed sudoku value
   *
   * @return boolean : true —> no conflicts; false —> conflicts
   *
   */
  private static boolean isUniqueInColumn(CellPosition emptyCell, int testValue)
  {
    return IntStream.range(0, puzzleSize)
                    .filter(row -> isRepeat(new CellPosition(row, emptyCell.getCol()), emptyCell, testValue))
                    .count() == 0;
  }


  /**
   *
   * Determines whether the proposed sudoku value already exists in the sudoku block.
   *
   * @param emptyCell : position of the empty cell
   * @param testValue : proposed sudoku value
   *
   * @return boolean : true —> no conflicts; false —> conflicts
   *
   */
  private static boolean isUniqueInBlock(CellPosition emptyCell, int testValue)
  {
    return isUniqueInBlockRows(emptyCell, testValue);
  }


  /**
   *
   * Helper method that checks for conflicts in the block's rows.
   *
   * @param emptyCell : position of the empty cell
   * @param testValue : proposed sudoku value
   *
   * @return boolean : true —> no conflicts; false —> conflicts
   *
   */
  private static boolean isUniqueInBlockRows(CellPosition emptyCell, int testValue)
  {
    int rowOffset = emptyCell.getRow() - (emptyCell.getRow() % blockSize);

    // If the proposed value is unique in each block column of the current block row,
    // the number of true values filtered below will be equal to blockSize (i.e. 3)

    return IntStream.range(0, blockSize)
                    .filter(row -> isUniqueInBlockCols(row + rowOffset, emptyCell, testValue))
                    .count() == blockSize;
  }


  /**
   *
   * Helper method that checks for conflicts in the block's columns.
   *
   * @param row : current block row
   * @param emptyCell : position of the empty cell
   * @param testValue : proposed sudoku value
   *
   * @return boolean : true —> no conflicts; false —> conflicts
   *
   */
  private static boolean isUniqueInBlockCols(int row, CellPosition emptyCell, int testValue)
  {
    int colOffset = emptyCell.getCol() - (emptyCell.getCol() % blockSize);

    // If the proposed value is unique (i.e not a repeat) in each of the block row's cells,
    // the number of true values filtered below will be equal to blockSize (i.e. 3)

    return IntStream.range(0, blockSize)
                    .filter(col -> !isRepeat(new CellPosition(row, col + colOffset), emptyCell, testValue))
                    .count() == blockSize;
  }


  /**
   *
   * Determines whether another cell has the same value as the proposed sudoku value.
   *
   * @param compareCell : position of the compared cell
   * @param emptyCell : position of the empty cell
   * @param testValue : proposed sudoku value
   *
   * @return boolean : true —> conflicts; false —> no conflicts
   *
   */
  private static boolean isRepeat(CellPosition comparedCell, CellPosition emptyCell, int testValue)
  {
    boolean isDifferentCell = !emptyCell.equals(comparedCell);
    boolean isSameValue = solvedPuzzle[getCellIndex(comparedCell)] == testValue;

    return isDifferentCell && isSameValue;
  }


  /**
   *
   * Retrieves the cell index.
   *
   * @param cell : position of the cell
   *
   * @return int : index of the cell in the puzzle array
   *
   */
  private static int getCellIndex(CellPosition cell)
  {
    return (cell.getRow() * puzzleSize) + cell.getCol();
  }
}
