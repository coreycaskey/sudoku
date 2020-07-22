package app.virtual_games.sudoku.views;

import app.virtual_games.sudoku.handlers.CloseGameScreenDialogHandler;
import app.virtual_games.sudoku.handlers.ReturnToMainMenuHandler;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * 
 * Loads the Return to Main Menu dialog for the JavaFX application.
 * 
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class ReturnToMainMenuDialog extends Dialog
{
	/**
	 * 
	 * Loads the parent {@link Dialog} with the Return to Main Menu dialog elements.
	 * 
	 */
	public ReturnToMainMenuDialog()
	{
		super();
		
		this.addDialogStyling("small-dialog");
		this.addContentContainer(this.loadReturnToMainMenuDialogContentContainer());
		this.addCloseDialogButtonContainerStyling("small-close-dialog-button-container");
		this.addCloseDialogButtonHandler(new CloseGameScreenDialogHandler());
	}
	
	
	/**  Private Helper Methods  **/
	
	
	/**
	 * 
	 * Loads the Return to Main Menu dialog content container with the following UI element(s):
	 * 
	 *  — Return to Main Menu Dialog Title 			  —> {@link #loadReturnToMainMenuDialogTitle()}
	 *  — Return to Main Menu Dialog Button Container —> {@link #loadReturnToMainMenuDialogButtonContainer()}
	 * 
	 * @return VBox : Return to Main Menu dialog content container
	 * 
	 */
	private VBox loadReturnToMainMenuDialogContentContainer()
	{
		VBox returnToMainMenuContentContainer = new VBox();
		
		returnToMainMenuContentContainer.getStyleClass().addAll("content-container", "small-content-container");
		returnToMainMenuContentContainer.getChildren().add(this.loadReturnToMainMenuDialogTitle());
		returnToMainMenuContentContainer.getChildren().add(this.loadReturnToMainMenuDialogButtonContainer());
		
		return returnToMainMenuContentContainer;
	}

	
	/**
	 * 
	 * Loads the Return to Main Menu dialog title.
	 * 
	 * @return Label : Return to Main Menu dialog title
	 * 
	 */
	private Label loadReturnToMainMenuDialogTitle()
	{
		Label returnToMainMenuDialogTitle = new Label("Are you sure you want to exit to the main menu?");
		
		returnToMainMenuDialogTitle.getStyleClass().addAll("dialog-title", "small-dialog-title");
		
		return returnToMainMenuDialogTitle;		
	}
	
	
	/**
	 * 
	 * Loads the Return to Main Menu dialog button container with the following UI element(s):
	 * 
	 *  — Confirm Button —> {@link #loadConfirmButton()}
	 *  — Cancel Button  —> {@link #loadCancelButton()}
	 * 
	 * @return HBox : Return to Main Menu dialog button container
	 * 
	 */
	private HBox loadReturnToMainMenuDialogButtonContainer()
	{
		HBox returnToMainMenuButtonContainer = new HBox();
		
		returnToMainMenuButtonContainer.getStyleClass().addAll("dialog-button-container", "multiple-option-dialog-button-container");
		returnToMainMenuButtonContainer.getChildren().add(this.loadConfirmButton());
		returnToMainMenuButtonContainer.getChildren().add(this.loadCancelButton());
		
		return returnToMainMenuButtonContainer;
	}
	
	
	/**
	 * 
	 * Loads the confirm button with the {@link ReturnToMainMenuHandler}.
	 * 
	 * @return Button : confirm button
	 * 
	 */
	private Button loadConfirmButton()
	{
		Button confirmButton = new Button("Yes");
		
		confirmButton.getStyleClass().add("dialog-button");
		confirmButton.setOnAction(new ReturnToMainMenuHandler());
		
		return confirmButton;
	}
	
	
	/**
	 * 
	 * Loads the cancel button with the {@link CloseGameScreenDialogHandler}.
	 * 
	 * @return Button : cancel button
	 * 
	 */
	private Button loadCancelButton()
	{
		Button cancelButton = new Button("No");
		
		cancelButton.getStyleClass().add("dialog-button");
		cancelButton.setOnAction(new CloseGameScreenDialogHandler());
		
		return cancelButton;
	}
}