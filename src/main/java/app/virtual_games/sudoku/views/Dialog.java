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
 * Parent class for all JavaFX application dialog boxes.
 *
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class Dialog extends StackPane
{
  private Pane dialogBackground;
  private Pane dialogPane;
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
    // sub-elements need to be initialized first to be used by upper elements
    // (e.g. dialogPane requires dialogContainer to be initialized)

    this.closeDialogButton = this.loadCloseDialogButton();
    this.closeDialogButtonContainer = this.loadCloseDialogButtonContainer();
    this.dialogContainer = this.loadDialogContainer();
    this.dialogPane = this.loadDialogPane();
    this.dialogBackground = this.loadDialogBackground();

    this.getChildren().add(this.dialogBackground);
    this.getChildren().add(this.dialogPane);
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
    var bg = new Pane();

    bg.getStyleClass().add("dialog-background");

    return bg;
  }


  /**
   *
   * Loads the Dialog.
   *
   * @return Pane : Dialog
   *
   */
  private Pane loadDialogPane()
  {
    var dialog = new Pane();

    dialog.getStyleClass().add("dialog");
    dialog.getChildren().add(this.dialogContainer);

    return dialog;
  }


  /**
   *
   * Loads the Dialog container with the following UI element(s):
   *
   * Close Dialog Button Container —> {@link #loadCloseDialogButtonContainer()}
   *
   * @return VBox : Dialog container
   *
   */
  private VBox loadDialogContainer()
  {
    var container = new VBox();

    container.getStyleClass().add("dialog-container");
    container.getChildren().add(this.closeDialogButtonContainer);

    return container;
  }


  /**
   *
   * Loads the close dialog button container with the following UI element(s):
   *
   * Close Dialog Button —> {@link #loadCloseDialogButton()}
   *
   * @return HBox : close dialog button container
   *
   */
  private HBox loadCloseDialogButtonContainer()
  {
    var buttonContainer = new HBox();

    buttonContainer.getStyleClass().add("close-dialog-button-container");
    buttonContainer.getChildren().add(this.closeDialogButton);

    return buttonContainer;
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
    var button = new Button();

    button.getStyleClass().add("close-dialog-button");

    return button;
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
    this.dialogPane.getStyleClass().addAll(styling);
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
   * Adds a specified event handler to the generic close dialog button.
   *
   * @param eventHandler : event handler
   *
   */
  public void addCloseDialogButtonHandler(EventHandler<ActionEvent> eventHandler)
  {
    this.closeDialogButton.setOnAction(eventHandler);
  }
}
