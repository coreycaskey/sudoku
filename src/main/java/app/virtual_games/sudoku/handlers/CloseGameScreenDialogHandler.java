package app.virtual_games.sudoku.handlers;

import app.virtual_games.sudoku.controllers.GameController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


/**
 * 
 * Custom event handler for the Close Game Screen Dialog click event.
 * 
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class CloseGameScreenDialogHandler implements EventHandler<ActionEvent>
{
	/**
	 * 
	 * Closes a Game Screen dialog.
	 * 
	 * @param clickEvent : Close Game Screen Dialog click event
	 * 
	 */
	@Override
	public void handle(ActionEvent clickEvent)
	{
		GameController.startGameTimer();
		GameController.closeGameScreenDialog();
	}
}
