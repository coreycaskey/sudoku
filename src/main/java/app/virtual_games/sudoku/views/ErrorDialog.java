package app.virtual_games.sudoku.views;

import app.virtual_games.sudoku.handlers.CloseMainMenuDialogHandler;
import app.virtual_games.sudoku.handlers.ReturnToMainMenuHandler;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * 
 * Loads the Error dialog for the JavaFX application.
 * 
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class ErrorDialog extends Dialog
{
	private boolean isMainMenu;
	
	
	/**
	 * 
	 * Initializes the following variable(s):
	 * 
	 *  — {@link #isMainMenu}
	 * 
	 * Loads the parent {@link Dialog} with the Error dialog elements.
	 * 
	 * @param isMainMenu : true —> Main Menu; false —> Game Screen
	 * 
	 */
	public ErrorDialog(boolean isMainMenu)
	{
		super();
		
		this.isMainMenu = isMainMenu;
		this.addDialogStyling("small-dialog");
		this.addContentContainer(this.loadErrorDialogContentContainer());
		this.removeCloseDialogButtonContainer();
	}
	

	/**  Private Helper Methods  **/
	
	
	/**
	 * 
	 * Loads the Error dialog content container with the following UI element(s):
	 * 
	 *  — Error Dialog Title 			—> {@link #loadErrorDialogTitle()}
	 *  — Error Dialog Button Container —> {@link #loadErrorDialogButtonContainer()}
	 * 
	 * @return VBox : Error dialog content container
	 * 
	 */
	private VBox loadErrorDialogContentContainer()
	{
		VBox errorDialogContentContainer = new VBox();
		
		errorDialogContentContainer.getStyleClass().addAll("content-container", "small-content-container", "error-dialog-content-container");
		errorDialogContentContainer.getChildren().add(this.loadErrorDialogTitle());
		errorDialogContentContainer.getChildren().add(this.loadErrorDialogButtonContainer());
		
		return errorDialogContentContainer;
	}
	
	
	/**
	 * 
	 * Loads the Error dialog title.
	 * 
	 * @return Label : Error dialog title
	 * 
	 */
	private Label loadErrorDialogTitle()
	{
		Label errorDialogTitle = new Label("Uh oh... Something went wrong. Try again later.");
		
		errorDialogTitle.getStyleClass().addAll("dialog-title", "error-dialog-title");
		
		return errorDialogTitle;
	}
	
	
	/**
	 * 
	 * Loads the dialog button container with the following UI element(s):
	 * 
	 *  — Confirm Button —> {@link #loadConfirmButton()}
	 * 
	 * @return HBox : Error dialog button container
	 * 
	 */
	private HBox loadErrorDialogButtonContainer()
	{
		HBox errorDialogButtonContainer = new HBox();
		
		errorDialogButtonContainer.getStyleClass().add("dialog-button-container");
		errorDialogButtonContainer.getChildren().add(this.loadConfirmButton());
		
		return errorDialogButtonContainer;
	}
	
	
	/**
	 * 
	 * Loads the confirm button with either the {@link CloseMainMenuDialogHandler} or {@link ReturnToMainMenuHandler}.
	 * 
	 * @return Button : confirm button
	 * 
	 */
	private Button loadConfirmButton()
	{
		Button confirmButton = new Button("OK");
		
		confirmButton.getStyleClass().add("dialog-button");
		
		if (this.isMainMenu) 	// if Main Menu, simply close the dialog
		{
			confirmButton.setOnAction(new CloseMainMenuDialogHandler());
		}
		else 				 	// if Game Screen, return to the Main Menu
		{
			confirmButton.setOnAction(new ReturnToMainMenuHandler());
		}

		return confirmButton;
	}
}