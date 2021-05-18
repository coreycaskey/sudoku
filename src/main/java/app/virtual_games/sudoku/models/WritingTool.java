package app.virtual_games.sudoku.models;


/**
 *
 * Enum for the sudoku writing tools.
 *
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public enum WritingTool
{
  PEN    ("Pen", "pen"),
  PENCIL ("Pencil", "pencil"),
  ERASER ("Eraser", "");

  private final String toolName;
  private final String className;


  /**
   *
   * Initializes the following variable(s):
   *
   *  — {@link #toolName}
   *  — {@link #className}
   *
   * @param toolName  : name of the writing tool
   * @param className : CSS class name
   *
   */
  WritingTool(String toolName, String className)
  {
    this.toolName = toolName;
    this.className = className;
  }


  /**  Public Helper Methods  **/


  /**
   *
   * Retrieves the enum instance that maps to the writing tool name.
   *
   * @param toolName     : name of the writing tool
   *
   * @return WritingTool : corresponding enum instance
   *
   */
  public static WritingTool getEnumInstance(String toolName)
  {
    return WritingTool.valueOf(toolName.toUpperCase());
  }


  /**  Getters and Setters **/


  /**
   *
   * Retrieves the {@link #toolName} for the enum instance.
   *
   * @return String : name of the writing tool
   *
   */
  public String getToolName()
  {
    return this.toolName;
  }


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
