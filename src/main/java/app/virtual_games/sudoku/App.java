package app.virtual_games.sudoku;

import app.virtual_games.sudoku.controllers.GameController;


/**
 *
 * Main entry point for the JavaFX application.
 *
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class App
{
  /**
   *
   * Passes control of the JavaFX application to {@link GameController}.
   *
   * @param args : command line arguments
   *
   */
  public static void main(String[] args)
  {
    GameController.main(args);
  }
}
