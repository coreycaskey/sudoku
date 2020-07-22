package app.virtual_games.sudoku.handlers;

import app.virtual_games.sudoku.controllers.GameController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


/**
 * 
 * Custom event handler for the New Puzzle button click event.
 * 
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class NewPuzzleButtonHandler implements EventHandler<ActionEvent> 
{
	/**
	 * 
	 * Loads a new sudoku puzzle.
	 * 
	 * @param clickEvent : New Puzzle button click event
	 * 
	 */
	@Override
	public void handle(ActionEvent clickEvent) 
	{
		GameController.stopGameTimer();
		GameController.hideTimePenalty();
		GameController.stopTimePenaltyTimer();
		GameController.stopHintCellTimer();
		GameController.resetTimerLabel();
		GameController.startNewSudoku();
	}
}
