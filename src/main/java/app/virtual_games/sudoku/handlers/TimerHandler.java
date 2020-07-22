package app.virtual_games.sudoku.handlers;

import app.virtual_games.sudoku.controllers.GameController;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;


/**
 * 
 * Custom event handler for the game timer.
 * 
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class TimerHandler implements EventHandler<ActionEvent> 
{
	/**
	 * 
	 * Updates the playing time and timer label.
	 * 
	 * @param event : base event
	 * 
	 */
	@Override
	public void handle(ActionEvent event) 
	{	
		GameController.incrementPlayingTime(1000);
		GameController.updateTimerLabel();
	}
}