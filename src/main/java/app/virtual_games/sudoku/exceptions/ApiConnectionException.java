package app.virtual_games.sudoku.exceptions;

public class ApiConnectionException extends Exception
{
  private static final long serialVersionUID = 1L;


  public ApiConnectionException(String errorMessage) {
    super(errorMessage);
  }
}
