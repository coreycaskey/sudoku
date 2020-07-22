package app.virtual_games.sudoku.models;


/**
 * 
 * Enum for the sudoku cell border stylings.
 * 
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public enum BorderStyling 
{
	TOP_LEFT 	  ("top-left-border"),
	TOP_MIDDLE	  ("top-middle-border"),
	TOP_RIGHT	  ("top-right-border"),
	LEFT 		  ("left-border"),
	MIDDLE 		  ("no-border"),
	RIGHT 		  ("right-border"),
	BOTTOM_LEFT   ("bottom-left-border"),
	BOTTOM_MIDDLE ("bottom-middle-border"),
	BOTTOM_RIGHT  ("bottom-right-border");
	
	private final String className;
	
	
	/**
	 * 
	 * Initializes the following variable(s):
	 * 
	 *  — {@link #className}
	 * 
	 * @param className : CSS class name
	 * 
	 */
	BorderStyling(String className)
	{
		this.className = className;
	}
	
	
	/**  Public Helper Methods  **/
	
	
	/**
	 * 
	 * Retrieves the enum instance that maps to the cell row and column.
	 * 
	 * @param row 			 : cell row
	 * @param col 			 : cell column
	 * 
	 * @return BorderStyling : corresponding enum instane
	 * 
	 */
	public static BorderStyling getEnumInstance(int row, int col) 
	{
		CellPosition cell = new CellPosition(row, col);
		
		if 		(isTopLeftCell(cell)) 	   { return TOP_LEFT; 	   }
		else if (isTopMiddleCell(cell))    { return TOP_MIDDLE;    }
		else if (isTopRightCell(cell)) 	   { return TOP_RIGHT; 	   }
		else if (isMiddleLeftCell(cell))   { return LEFT; 		   }
		else if (isMiddleRightCell(cell))  { return RIGHT; 		   }
		else if (isBottomLeftCell(cell))   { return BOTTOM_LEFT;   }
		else if (isBottomMiddleCell(cell)) { return BOTTOM_MIDDLE; }
		else if (isBottomRightCell(cell))  { return BOTTOM_RIGHT;  }
		else 							   { return MIDDLE; 	   }
	}

	
	/**  Private Helper Methods  **/
	
	
	/**
	 * 
	 * Determines whether the position corresponds to a top—left cell in the sudoku block.
	 * 
	 * @param cell     : cell position
	 * 
	 * @return boolean : true —> top—left cell; false —> not top—left cell
	 * 
	 */
	private static boolean isTopLeftCell(CellPosition cell)
	{
		return cell.getLocation().equals(new CellPosition(0, 0).getLocation())
				|| cell.getLocation().equals(new CellPosition(0, 3).getLocation())
				|| cell.getLocation().equals(new CellPosition(0, 6).getLocation())
				|| cell.getLocation().equals(new CellPosition(3, 0).getLocation())
				|| cell.getLocation().equals(new CellPosition(3, 3).getLocation())
				|| cell.getLocation().equals(new CellPosition(3, 6).getLocation())
				|| cell.getLocation().equals(new CellPosition(6, 0).getLocation())
				|| cell.getLocation().equals(new CellPosition(6, 3).getLocation())
				|| cell.getLocation().equals(new CellPosition(6, 6).getLocation());
	}
	
	
	/**
	 * 
	 * Determines whether the position corresponds to a top—middle cell in the sudoku block.
	 * 
	 * @param cell     : cell position
	 * 
	 * @return boolean : true —> top—middle cell; false —> not top—middle cell
	 * 
	 */
	private static boolean isTopMiddleCell(CellPosition cell)
	{
		return cell.getLocation().equals(new CellPosition(0, 1).getLocation())
				|| cell.getLocation().equals(new CellPosition(0, 4).getLocation())
				|| cell.getLocation().equals(new CellPosition(0, 7).getLocation())
				|| cell.getLocation().equals(new CellPosition(3, 1).getLocation())
				|| cell.getLocation().equals(new CellPosition(3, 4).getLocation())
				|| cell.getLocation().equals(new CellPosition(3, 7).getLocation())
				|| cell.getLocation().equals(new CellPosition(6, 1).getLocation())
				|| cell.getLocation().equals(new CellPosition(6, 4).getLocation())
				|| cell.getLocation().equals(new CellPosition(6, 7).getLocation());
	}
	
	
	/**
	 * 
	 * Determines whether the position corresponds to a top—right cell in the sudoku block.
	 * 
	 * @param cell     : cell position
	 * 
	 * @return boolean : true —> top—right cell; false —> not top—right cell
	 * 
	 */
	private static boolean isTopRightCell(CellPosition cell) 
	{
		return cell.getLocation().equals(new CellPosition(0, 2).getLocation())
				|| cell.getLocation().equals(new CellPosition(0, 5).getLocation())
				|| cell.getLocation().equals(new CellPosition(0, 8).getLocation())
				|| cell.getLocation().equals(new CellPosition(3, 2).getLocation())
				|| cell.getLocation().equals(new CellPosition(3, 5).getLocation())
				|| cell.getLocation().equals(new CellPosition(3, 8).getLocation())
				|| cell.getLocation().equals(new CellPosition(6, 2).getLocation())
				|| cell.getLocation().equals(new CellPosition(6, 5).getLocation())
				|| cell.getLocation().equals(new CellPosition(6, 8).getLocation());
	}
	
	
	/**
	 * 
	 * Determines whether the position corresponds to a middle—left cell in the sudoku block.
	 * 
	 * @param cell     : cell position
	 * 
	 * @return boolean : true —> middle—left cell; false —> not middle—left cell
	 * 
	 */
	private static boolean isMiddleLeftCell(CellPosition cell) 
	{
		return cell.getLocation().equals(new CellPosition(1, 0).getLocation())
				|| cell.getLocation().equals(new CellPosition(1, 3).getLocation())
				|| cell.getLocation().equals(new CellPosition(1, 6).getLocation())
				|| cell.getLocation().equals(new CellPosition(4, 0).getLocation())
				|| cell.getLocation().equals(new CellPosition(4, 3).getLocation())
				|| cell.getLocation().equals(new CellPosition(4, 6).getLocation())
				|| cell.getLocation().equals(new CellPosition(7, 0).getLocation())
				|| cell.getLocation().equals(new CellPosition(7, 3).getLocation())
				|| cell.getLocation().equals(new CellPosition(7, 6).getLocation());
	}
	
	
	/**
	 * 
	 * Determines whether the position corresponds to a middle—right cell in the sudoku block.
	 * 
	 * @param cell     : cell position
	 * 
	 * @return boolean : true —> middle—right cell; false —> not middle—right cell
	 * 
	 */
	private static boolean isMiddleRightCell(CellPosition cell)
	{
		return cell.getLocation().equals(new CellPosition(1, 2).getLocation())
				|| cell.getLocation().equals(new CellPosition(1, 5).getLocation())
				|| cell.getLocation().equals(new CellPosition(1, 8).getLocation())
				|| cell.getLocation().equals(new CellPosition(4, 2).getLocation())
				|| cell.getLocation().equals(new CellPosition(4, 5).getLocation())
				|| cell.getLocation().equals(new CellPosition(4, 8).getLocation())
				|| cell.getLocation().equals(new CellPosition(7, 2).getLocation())
				|| cell.getLocation().equals(new CellPosition(7, 5).getLocation())
				|| cell.getLocation().equals(new CellPosition(7, 8).getLocation());
	}
	
	
	/**
	 * 
	 * Determines whether the position corresponds to a bottom—left cell in the sudoku block.
	 * 
	 * @param cell     : cell position
	 * 
	 * @return boolean : true —> bottom—left cell; false —> not bottom—left cell
	 * 
	 */
	private static boolean isBottomLeftCell(CellPosition cell) 
	{
		return cell.getLocation().equals(new CellPosition(2, 0).getLocation())
				|| cell.getLocation().equals(new CellPosition(2, 3).getLocation())
				|| cell.getLocation().equals(new CellPosition(2, 6).getLocation())
				|| cell.getLocation().equals(new CellPosition(5, 0).getLocation())
				|| cell.getLocation().equals(new CellPosition(5, 3).getLocation())
				|| cell.getLocation().equals(new CellPosition(5, 6).getLocation())
				|| cell.getLocation().equals(new CellPosition(8, 0).getLocation())
				|| cell.getLocation().equals(new CellPosition(8, 3).getLocation())
				|| cell.getLocation().equals(new CellPosition(8, 6).getLocation());
	}
	
	
	/**
	 * 
	 * Determines whether the position corresponds to a bottom—middle cell in the sudoku block.
	 * 
	 * @param cell     : cell position
	 * 
	 * @return boolean : true —> bottom—middle cell; false —> not bottom—middle cell
	 * 
	 */
	private static boolean isBottomMiddleCell(CellPosition cell)
	{
		return cell.getLocation().equals(new CellPosition(2, 1).getLocation())
				|| cell.getLocation().equals(new CellPosition(2, 4).getLocation())
				|| cell.getLocation().equals(new CellPosition(2, 7).getLocation())
				|| cell.getLocation().equals(new CellPosition(5, 1).getLocation())
				|| cell.getLocation().equals(new CellPosition(5, 4).getLocation())
				|| cell.getLocation().equals(new CellPosition(5, 7).getLocation())
				|| cell.getLocation().equals(new CellPosition(8, 1).getLocation())
				|| cell.getLocation().equals(new CellPosition(8, 4).getLocation())
				|| cell.getLocation().equals(new CellPosition(8, 7).getLocation());
	}
	
	
	/**
	 * 
	 * Determines whether the position corresponds to a bottom—right cell in the sudoku block.
	 * 
	 * @param cell     : cell position
	 * 
	 * @return boolean : true —> bottom—right cell; false —> not bottom—right cell
	 * 
	 */
	private static boolean isBottomRightCell(CellPosition cell)
	{
		return cell.getLocation().equals(new CellPosition(2, 2).getLocation())
				|| cell.getLocation().equals(new CellPosition(2, 5).getLocation())
				|| cell.getLocation().equals(new CellPosition(2, 8).getLocation())
				|| cell.getLocation().equals(new CellPosition(5, 2).getLocation())
				|| cell.getLocation().equals(new CellPosition(5, 5).getLocation())
				|| cell.getLocation().equals(new CellPosition(5, 8).getLocation())
				|| cell.getLocation().equals(new CellPosition(8, 2).getLocation())
				|| cell.getLocation().equals(new CellPosition(8, 5).getLocation())
				|| cell.getLocation().equals(new CellPosition(8, 8).getLocation());
	}
	
	
	/**  Getters and Setters  **/
	
	
	/**
	 * 
	 * Retrieves the {@link #className} for the enum instance.
	 * 
	 * @return String : CSS class name
	 * 
	 */
	public String getClassName() 
	{
		return this.className;
	}
}
