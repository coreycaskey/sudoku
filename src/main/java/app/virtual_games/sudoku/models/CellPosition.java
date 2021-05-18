package app.virtual_games.sudoku.models;

import java.awt.Point;


/**
 *
 * Custom child class of {@link Point} that directly provides the cell row and column.
 *
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class CellPosition extends Point
{
  private static final long serialVersionUID = 1L;

  private int row;
  private int col;


  /**
   *
   * Initializes the following variable(s):
   *
   *  — {@link row}
   *  — {@link col}
   *
   * @param row : cell row
   * @param col : cell column
   *
   */
  public CellPosition(int row, int col)
  {
    super(col, row);  // Point(x, y) -> Point(col, row)

    this.row = row;
    this.col = col;
  }


  /**  Getters and Setters  **/


  /**
   *
   * Retrieves {@link #row}.
   *
   * @return int : cell row
   *
   */
  public int getRow()
  {
    return this.row;
  }


  /**
   *
   * Retrieves {@link #col}.
   *
   * @return int : cell column
   *
   */
  public int getCol()
  {
    return this.col;
  }
}
