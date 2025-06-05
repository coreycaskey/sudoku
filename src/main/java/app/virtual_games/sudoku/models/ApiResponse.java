package app.virtual_games.sudoku.models;

import java.util.List;

public class ApiResponse
{
  private boolean response;
  private int size;
  private List<Square> squares;

  // No-arg constructor for Jackson
  public ApiResponse()
  {
  }

  // All-arg constructor (optional, for manual creation)
  public ApiResponse(boolean response, int size, List<Square> squares)
  {
    this.response = response;
    this.size = size;
    this.squares = squares;
  }

  // Getters and setters
  public boolean isResponse()
  {
    return response;
  }

  public void setResponse(boolean response)
  {
    this.response = response;
  }

  public int getSize()
  {
    return size;
  }

  public void setSize(int size)
  {
    this.size = size;
  }

  public List<Square> getSquares()
  {
    return squares;
  }

  public void setSquares(List<Square> squares)
  {
    this.squares = squares;
  }

  // Static nested class
  public static class Square
  {
    private int x;
    private int y;
    private int value;

    // No-arg constructor for Jackson
    public Square()
    {
    }

    // All-arg constructor (optional)
    public Square(int x, int y, int value)
    {
      this.x = x;
      this.y = y;
      this.value = value;
    }

    // Getters and setters
    public int getX()
    {
      return x;
    }

    public void setX(int x)
    {
      this.x = x;
    }

    public int getY()
    {
      return y;
    }

    public void setY(int y)
    {
      this.y = y;
    }

    public int getValue()
    {
      return value;
    }

    public void setValue(int value)
    {
      this.value = value;
    }
  }
}
