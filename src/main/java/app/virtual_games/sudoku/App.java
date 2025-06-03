package app.virtual_games.sudoku;

import app.virtual_games.sudoku.controllers.GameController;

/**
 * Main entry point.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class App
{
  /**
   * Passes control to {@link GameController}.
   *
   * @param args : command line args
   */
  public static void main(String[] args)
  {
    GameController.main(args);
  }
}
