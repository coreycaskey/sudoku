package app.virtual_games.sudoku.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


/**
 * 
 * Parent class for all JavaFX application dialog.
 * 
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class Dialog extends StackPane
{
	private Pane dialog;
	private VBox dialogContainer;
	private HBox closeDialogButtonContainer;
	private Button closeDialogButton;
	
	
	/**
	 * 
	 * Loads the parent Dialog with the shared elements.
	 * 
	 */
	public Dialog() 
	{
		this.getChildren().add(this.loadDialogBackground());
		this.getChildren().add(this.dialog = this.loadDialog());
	}	
	
	
	/**
	 * 
	 * Loads the Dialog background.
	 * 
	 * @return Pane : Dialog background
	 * 
	 */
	private Pane loadDialogBackground()
	{
		Pane dialogBackground = new Pane();
		
		dialogBackground.getStyleClass().add("dialog-background");
		
		return dialogBackground;
	}
	
	
	/**
	 * 
	 * Loads the Dialog.
	 * 
	 * @return Pane : Dialog
	 * 
	 */
	private Pane loadDialog()
	{
		Pane dialog = new Pane();
		
		dialog.getStyleClass().add("dialog");
		dialog.getChildren().add(this.dialogContainer = this.loadDialogContainer());
		
		return dialog;
	}
	
	
	/**
	 * 
	 * Loads the Dialog container with the following UI element(s):
	 * 
	 *  — Close Dialog Button Container —> {@link #loadCloseDialogButtonContainer()}
	 * 
	 * @return VBox : Dialog container
	 * 
	 */
	private VBox loadDialogContainer()
	{
		VBox dialogContainer = new VBox();
		
		dialogContainer.getStyleClass().add("dialog-container");
		dialogContainer.getChildren().add(this.closeDialogButtonContainer = this.loadCloseDialogButtonContainer());
		
		return dialogContainer;
	}
	
	
	/**
	 * 
	 * Loads the close dialog button container with the following UI element(s):
	 * 
	 *  — Close Dialog Button —> {@link #loadCloseDialogButton()}
	 * 
	 * @return HBox : close dialog button container
	 * 
	 */
	private HBox loadCloseDialogButtonContainer()
	{
		HBox closeDialogContainer = new HBox();
		
		closeDialogContainer.getStyleClass().add("close-dialog-button-container");
		closeDialogContainer.getChildren().add(this.closeDialogButton = this.loadCloseDialogButton());
		
		return closeDialogContainer;
	}
	
	
	/**
	 * 
	 * Loads the close dialog button.
	 * 
	 * @return Button : close dialog button
	 * 
	 */
	private Button loadCloseDialogButton()
	{
		Button closeDialogButton = new Button();
		
		closeDialogButton.getStyleClass().add("close-dialog-button");
		
		return closeDialogButton;
	}
	
	
	/**  Public Helper Methods  **/
	
	
	/**
	 * 
	 * Adds a collection of CSS class names to the Dialog.
	 * 
	 * @param styling : collection of CSS class names
	 * 
	 */
	public void addDialogStyling(String... styling)
	{
		this.dialog.getStyleClass().addAll(styling);
	}
	
	
	/**
	 * 
	 * Adds a child class content container to the Dialog container.
	 * 
	 * @param contentContainer : child class content container
	 * 
	 */
	public void addContentContainer(VBox contentContainer)
	{
		this.dialogContainer.getChildren().add(contentContainer);
	}
	
	
	/**
	 * 
	 * Adds a collection of CSS class names to the close dialog button container.
	 * 
	 * @param styling : collection of CSS class names
	 * 
	 */
	public void addCloseDialogButtonContainerStyling(String... styling)
	{
		this.closeDialogButtonContainer.getStyleClass().addAll(styling);
	}
	
	
	/**
	 * 
	 * Removes the close dialog button container.
	 * 
	 */
	public void removeCloseDialogButtonContainer()
	{
		this.dialogContainer.getChildren().remove(0);
	}
	
	
	/**
	 * 
	 * Adds an event handler to the close dialog button.
	 * 
	 * @param eventHandler : event handler
	 * 
	 */
	public void addCloseDialogButtonHandler(EventHandler<ActionEvent> eventHandler)
	{
		this.closeDialogButton.setOnAction(eventHandler);
	}
}