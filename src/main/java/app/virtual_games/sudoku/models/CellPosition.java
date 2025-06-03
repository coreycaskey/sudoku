package app.virtual_games.sudoku.models;

import java.awt.Point;

/**
 * Custom child class of {@link Point} that provides the cell row and column.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class CellPosition extends Point
{
  private static final long serialVersionUID = 1L;

  private int row;
  private int col;

  /**
   * Initializes {@link row} and {@link col}
   *
   * @param row : cell row
   * @param col : cell column
   */
  public CellPosition(int row, int col)
  {
    super(col, row); // Point(col, row) -> Point(x, y)
    this.row = row;
    this.col = col;
  }

  /** Getters and Setters **/

  /**
   * Retrieves {@link #row}.
   *
   * @return int : cell row
   */
  public int getRow()
  {
    return this.row;
  }

  /**
   * Retrieves {@link #col}.
   *
   * @return int : cell column
   */
  public int getCol()
  {
    return this.col;
  }
}
