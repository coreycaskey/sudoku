package app.virtual_games.sudoku.exceptions;

public class ApiResponseException extends Exception
{
  private static final long serialVersionUID = 1L;

  public ApiResponseException(String errorMessage) {
    super(errorMessage);
  }
}
