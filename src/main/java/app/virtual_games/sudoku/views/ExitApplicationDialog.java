package app.virtual_games.sudoku.views;

import app.virtual_games.sudoku.handlers.CloseMainMenuDialogHandler;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 *
 * Loads the Exit Application dialog for the JavaFX application.
 *
 * @author Corey Caskey
 * @version 0.0.1
 *
 */
public class ExitApplicationDialog extends Dialog
{
  /**
   *
   * Loads the parent {@link Dialog} with the Exit Application dialog elements.
   *
   */
  public ExitApplicationDialog()
  {
    super();

    this.addDialogStyling("small-dialog");
    this.addContentContainer(this.loadExitApplicationDialogContentContainer());
    this.addCloseDialogButtonContainerStyling("small-close-dialog-button-container");
    this.addCloseDialogButtonHandler(new CloseMainMenuDialogHandler());
  }


  /**  Private Helper Methods  **/


  /**
   *
   * Loads the Exit Application dialog content container with the following UI element(s):
   *
   * Exit Application Dialog Title —> {@link #loadExitApplicationDialogTitle()}
   * Exit Application Dialog Button Container —> {@link #loadExitApplicationDialogButtonContainer()}
   *
   * @return VBox : Exit Application dialog content container
   *
   */
  private VBox loadExitApplicationDialogContentContainer()
  {
    var exitApplicationDialogContentContainer = new VBox();

    exitApplicationDialogContentContainer.getStyleClass().addAll("content-container", "small-content-container");
    exitApplicationDialogContentContainer.getChildren().add(this.loadExitApplicationDialogTitle());
    exitApplicationDialogContentContainer.getChildren().add(this.loadExitApplicationDialogButtonContainer());

    return exitApplicationDialogContentContainer;
  }


  /**
   *
   * Loads the Exit Application dialog title.
   *
   * @return Label : Exit Application dialog title
   *
   */
  private Label loadExitApplicationDialogTitle()
  {
    var exitApplicationDialogTitle = new Label("Are you sure you want to exit the application?");

    exitApplicationDialogTitle.getStyleClass().addAll("dialog-title", "small-dialog-title");

    return exitApplicationDialogTitle;
  }


  /**
   *
   * Loads the Exit Application dialog button container with the following UI element(s):
   *
   * Confirm Button —> {@link #loadConfirmButton()}
   * Cancel Button —> {@link #loadCancelButton()}
   *
   * @return HBox : Exit Application dialog button container
   *
   */
  private HBox loadExitApplicationDialogButtonContainer()
  {
    var exitApplicationDialogButtonContainer = new HBox();

    exitApplicationDialogButtonContainer.getStyleClass().addAll("dialog-button-container", "multiple-option-dialog-button-container");
    exitApplicationDialogButtonContainer.getChildren().add(this.loadConfirmButton());
    exitApplicationDialogButtonContainer.getChildren().add(this.loadCancelButton());

    return exitApplicationDialogButtonContainer;
  }


  /**
   *
   * Loads the confirm button with an event handler to close the JavaFX application.
   *
   * @return Button : confirm button
   *
   */
  private Button loadConfirmButton()
  {
    var confirmButton = new Button("Yes");

    confirmButton.getStyleClass().add("dialog-button");
    confirmButton.setOnAction(event -> System.exit(0)); // Exit the application gracefully

    return confirmButton;
  }


  /**
   *
   * Loads the cancel button with the {@link CloseMainMenuDialogHandler}.
   *
   * @return Button : cancel button
   *
   */
  private Button loadCancelButton()
  {
    var cancelButton = new Button("No");

    cancelButton.getStyleClass().add("dialog-button");
    cancelButton.setOnAction(new CloseMainMenuDialogHandler());

    return cancelButton;
  }
}
