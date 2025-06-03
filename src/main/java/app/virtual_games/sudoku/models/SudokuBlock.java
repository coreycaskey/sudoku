package app.virtual_games.sudoku.models;

import java.util.stream.IntStream;


/**
 *
 * Class that represents the nine sudoku blocks.
 *
 * @author Corey Caskey
 * @version 1.0.0
 *
 */
public class SudokuBlock
{
  private int blockId;
  private Sudoku parentSudoku;
  private SudokuCell[] blockCells;


  /**
   *
   * Initializes the following variable(s):
   *
   * {@link #blockId}
   * {@link #parentSudoku}
   * {@link #blockCells}
   *
   * @param blockId : unique identifier (e.g. 0 — 8)
   * @param parentSudoku : outer sudoku class
   *
   */
  public SudokuBlock(int blockId, Sudoku parentSudoku)
  {
    this.blockId = blockId;
    this.parentSudoku = parentSudoku;
    this.blockCells = this.loadBlockCells();
  }


  /**  Private Helper Methods  **/


  /**
   *
   * Retrieves the nine {@link SudokuCell} objects that reside in the sudoku block.
   *
   * @return SudokuCell[] : array of SudokuCell instances
   *
   */
  private SudokuCell[] loadBlockCells()
  {
    return IntStream.range(0, this.parentSudoku.getTotalCells())
                    .filter(cellIndex -> this.calculateParentBlockIdForCell(cellIndex) == this.blockId)
                    .mapToObj(this::getSudokuCell)
                    .toArray(SudokuCell[]::new);
  }


  /**
   *
   * Calculates the block id for the cell's parent block.
   *
   * @param cellIndex : index of the cell in the puzzle array
   *
   * @return int : cell's parent block id
   *
   */
  private int calculateParentBlockIdForCell(int cellIndex)
  {
    int puzzleRow = cellIndex / this.parentSudoku.getPuzzleSize();
    int puzzleCol = cellIndex % this.parentSudoku.getPuzzleSize();

    return (puzzleCol / this.parentSudoku.getBlockSize()) + ((puzzleRow / this.parentSudoku.getBlockSize()) * this.parentSudoku.getBlockSize());
  }


  /**
   *
   * Initializes a {@link SudokuCell} instance.
   *
   * @param cellIndex : index of the cell in the puzzle array
   *
   * @return SudokuCell : SudokuCell instance
   *
   */
  private SudokuCell getSudokuCell(int cellIndex)
  {
    return new SudokuCell(cellIndex, this.blockId, this.parentSudoku);
  }


  /**  Public Helper Methods  **/


  /**
   *
   * Calculates the block's row in the puzzle.
   *
   * @return int : block's row in the puzzle
   *
   */
  public int getBlockRow()
  {
    return this.blockId / this.parentSudoku.getBlockSize();
  }


  /**
   *
   * Calculates the block's column in the puzzle.
   *
   * @return int : block's column in the puzzle
   *
   */
  public int getBlockCol()
  {
    return this.blockId % this.parentSudoku.getBlockSize();
  }


  /**  Getters and Setters  **/


  /**
   *
   * Retrieves {@link #blockId}.
   *
   * @return int : unique identifier
   *
   */
  public int getBlockId()
  {
    return this.blockId;
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
   * Retrieves {@link #blockCells}.
   *
   * @return SudokuCell[] : array of SudokuCell instances
   *
   */
  public SudokuCell[] getBlockCells()
  {
    return this.blockCells;
  }
}
