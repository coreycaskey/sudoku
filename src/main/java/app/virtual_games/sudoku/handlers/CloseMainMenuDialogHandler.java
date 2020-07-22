package app.virtual_games.sudoku.handlers;

import app.virtual_games.sudoku.controllers.GameController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


/**
 * 
 * Custom event handler for the Close Main Menu Dialog click event.
 * 
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class CloseMainMenuDialogHandler implements EventHandler<ActionEvent>
{
	/**
	 * 
	 * Closes a Main Menu dialog.
	 * 
	 * @param clickEvent : Close Main Menu Dialog click event
	 * 
	 */
	@Override
	public void handle(ActionEvent clickEvent)
	{
		GameController.closeMainMenuDialog();
	}
}
