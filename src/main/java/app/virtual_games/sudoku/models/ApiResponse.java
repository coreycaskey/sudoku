package app.virtual_games.sudoku.models;

import java.util.List;

public class ApiResponse
{
  public boolean response;
  public int size;
  public List<Square> squares;

  public static class Square
  {
    public int x;
    public int y;
    public int value;

    public Square(int x, int y, int value)
    {
      this.x = x;
      this.y = y;
      this.value = value;
    }
  }
}
