package app.virtual_games.sudoku.views;

import app.virtual_games.sudoku.controllers.GameController;
import app.virtual_games.sudoku.handlers.MainMenuDifficultyDropdownHandler;
import app.virtual_games.sudoku.handlers.ReturnToMainMenuHandler;
import app.virtual_games.sudoku.handlers.WinDialogHandler;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;


/**
 * 
 * Loads the Win dialog for the JavaFX application.
 * 
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class WinDialog extends Dialog
{
	/**
	 * 
	 * Loads the parent {@link Dialog} with the Win dialog elements.
	 * 
	 */
	public WinDialog() 
	{
		super();
		
		this.addDialogStyling("large-dialog");
		this.removeCloseDialogButtonContainer();
		this.addContentContainer(this.loadWinDialogContentContainer());
	}
	
	
	/**  Private Helper Methods  **/
	
	
	/**
	 * 
	 * Loads the Win dialog content container with the following UI element(s):
	 * 
	 *  — Win Dialog Title 				  —> {@link #loadWinDialogTitle()}
	 *  — Win Dialog Timer Container	  —> {@link #loadWinDialogTimerContainer()}
	 *  — Win Dialog Difficulty Container —> {@link #loadWinDialogDifficultyContainer()}
	 *  — Win Dialog Button Container	  —> {@link #loadWinDialogButtonContainer()}
	 * 
	 * @return VBox : Win dialog content container
	 * 
	 */
	private VBox loadWinDialogContentContainer() 
	{
		VBox winDialogContainer = new VBox();
		
		winDialogContainer.getStyleClass().addAll("content-container", "large-content-container");
		winDialogContainer.getChildren().add(this.loadWinDialogTitle());
		winDialogContainer.getChildren().add(this.loadWinDialogTimerContainer());
		winDialogContainer.getChildren().add(this.loadWinDialogDifficultyContainer());
		winDialogContainer.getChildren().add(this.loadWinDialogButtonContainer());
		
		return winDialogContainer;
	}
	
	
	/**
	 * 
	 * Loads the Win dialog title.
	 * 
	 * @return Label : Win dialog title
	 * 
	 */
	private Label loadWinDialogTitle()
	{
		Label winDialogTitle = new Label("Congratulations!");
		
		winDialogTitle.getStyleClass().addAll("dialog-title", "win-dialog-title");
		
		return winDialogTitle;
	}
	
	
	/**
	 * 
	 * Loads the Win dialog timer container.
	 * 
	 * @return VBox : Win dialog timer container
	 * 
	 */
	private VBox loadWinDialogTimerContainer()
	{
		VBox winDialogTimerContainer = new VBox();
		
		winDialogTimerContainer.getStyleClass().add("win-dialog-timer-container");
		winDialogTimerContainer.getChildren().add(this.loadWinDialogTimerDescriptionContainer());
		winDialogTimerContainer.getChildren().add(this.loadWinDialogTimerLabel());
		
		return winDialogTimerContainer;
	}
	
	
	/**
	 * 
	 * Loads the Win dialog timer description container.
	 * 
	 * @return HBox : Win dialog timer description container
	 * 
	 */
	private HBox loadWinDialogTimerDescriptionContainer()
	{
		HBox winDialogTimerDescriptionContainer = new HBox();
		
		Label timerDescriptionBeginning = new Label("You solved this ");
		Label timerDescriptionMiddle = new Label(GameController.getCurrentDifficultyName().toUpperCase());
		Label timerDescriptionEnd = new Label(" puzzle in:");
		
		timerDescriptionBeginning.getStyleClass().add("timer-description-label");
		timerDescriptionMiddle.getStyleClass().add("puzzle-difficulty-label");
		timerDescriptionEnd.getStyleClass().add("timer-description-label");
		
		winDialogTimerDescriptionContainer.getStyleClass().add("timer-description-container");
		winDialogTimerDescriptionContainer.getChildren().addAll(timerDescriptionBeginning, timerDescriptionMiddle, timerDescriptionEnd);
		
		return winDialogTimerDescriptionContainer;
	}
	
	
	/**
	 * 
	 * Loads the Win dialog timer label.
	 * 
	 * @return Label : Win dialog timer label
	 * 
	 */
	private Label loadWinDialogTimerLabel()
	{
		Label timerLabel = new Label(GameController.formatWinTime());
		
		timerLabel.getStyleClass().add("win-dialog-timer-label");
		
		return timerLabel;
	}
	
	
	/**
	 * 
	 * Loads the Win dialog difficulty container.
	 * 
	 * @return HBox : Win dialog difficulty container
	 * 
	 */
	private HBox loadWinDialogDifficultyContainer()
	{
		HBox winDialogDifficultyContainer = new HBox();
		
		winDialogDifficultyContainer.getStyleClass().add("difficulty-container");
		winDialogDifficultyContainer.getChildren().add(this.loadWinDialogDifficultyLabel());
		winDialogDifficultyContainer.getChildren().add(loadWinDialogDifficultyDropdown());
		
		return winDialogDifficultyContainer;
	}
	
	
	/**
	 * 
	 * Loads the Win dialog difficulty label.
	 * 
	 * @return Label : Win dialog difficulty label
	 * 
	 */
	private Label loadWinDialogDifficultyLabel()
	{
		Label winDialogDifficultyLabel = new Label("Puzzle Difficulty: ");
		
		winDialogDifficultyLabel.getStyleClass().add("difficulty-label");

		return winDialogDifficultyLabel;
	}
	
	
	/**
	 * 
	 * Loads the Win dialog difficulty dropdown.
	 * 
	 * @return ComboBox<String> : Win dialog difficulty dropdown
	 * 
	 */
	private ComboBox<String> loadWinDialogDifficultyDropdown()
	{
		ComboBox<String> winDialogDifficultyDropdown = new ComboBox<String>(GameController.getPuzzleDifficulties());

		// ComboBox default style classes are .combo-box-base and .combo-box

		winDialogDifficultyDropdown.getSelectionModel().select(GameController.getCurrentDifficultyName());
		winDialogDifficultyDropdown.setOnAction(new MainMenuDifficultyDropdownHandler());
		
		return winDialogDifficultyDropdown;
	}
	
	
	/**
	 * 
	 * Loads the Win dialog button container.
	 * 
	 * @return HBox : Win dialog button container
	 * 
	 */
	private HBox loadWinDialogButtonContainer()
	{
		HBox winDialogButtonContainer = new HBox();
		
		winDialogButtonContainer.getStyleClass().addAll("dialog-button-container", "multiple-option-dialog-button-container");
		winDialogButtonContainer.getChildren().add(this.loadStartNewPuzzleButton());
		winDialogButtonContainer.getChildren().add(this.loadReturnToMainMenuButton());
		
		return winDialogButtonContainer;
	}
	
	
	/**
	 * 
	 * Loads the start new puzzle button with the {@link WinDialogHandler}.
	 * 
	 * @return Button : start new puzzle button
	 * 
	 */
	private Button loadStartNewPuzzleButton()
	{
		Button startNewPuzzleButton = new Button("Start New Puzzle");
		
		startNewPuzzleButton.getStyleClass().addAll("dialog-button", "win-dialog-button");
		startNewPuzzleButton.setOnAction(new WinDialogHandler());
		
		return startNewPuzzleButton;
	}
	
	
	/**
	 * 
	 * Loads the return to main menu button with the {@link ReturnToMainMenuHandler}.
	 * 
	 * @return Button : return to main menu button
	 * 
	 */
	private Button loadReturnToMainMenuButton()
	{
		Button returnToMainMenuButton = new Button("Main Menu");
		
		returnToMainMenuButton.getStyleClass().addAll("dialog-button", "win-dialog-button");
		returnToMainMenuButton.setOnAction(new ReturnToMainMenuHandler());
		
		return returnToMainMenuButton;
	}

}