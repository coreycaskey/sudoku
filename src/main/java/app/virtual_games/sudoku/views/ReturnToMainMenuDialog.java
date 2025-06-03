package app.virtual_games.sudoku.views;

import app.virtual_games.sudoku.handlers.CloseGameScreenDialogHandler;
import app.virtual_games.sudoku.handlers.ReturnToMainMenuHandler;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Loads ReturnToMainMenuDialog for the JavaFX application.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class ReturnToMainMenuDialog extends Dialog
{
  /**
   * Initializes {@link Dialog} with additional elements.
   */
  public ReturnToMainMenuDialog()
  {
    super();
    this.addDialogStyling("small-dialog");
    this.addContentContainer(this.loadContentContainer());
    this.addCloseButtonContainerStyling("small-close-button-container");
    this.addCloseDialogButtonHandler(new CloseGameScreenDialogHandler());
  }

  /** Private Helper Methods **/

  /**
   * Loads the content container and populates {@link #loadDialogTitle} and
   * {@link #loadDialogButtonContainer}.
   *
   * @return VBox : content container
   */
  private VBox loadContentContainer()
  {
    var contentContainer = new VBox();

    contentContainer.getStyleClass().addAll("content-container", "small-content-container");
    contentContainer.getChildren().add(this.loadDialogTitle());
    contentContainer.getChildren().add(this.loadDialogButtonContainer());

    return contentContainer;
  }

  /**
   * Loads the dialog title.
   *
   * @return Label : dialog title
   */
  private Label loadDialogTitle()
  {
    var dialogTitle = new Label("Are you sure you want to exit to the main menu?");

    dialogTitle.getStyleClass().addAll("dialog-title", "small-dialog-title");

    return dialogTitle;
  }

  /**
   * Loads the dialog button container with {@link #loadConfirmButton()} and
   * {@link #loadCancelButton()}.
   *
   * @return HBox : dialog button container
   */
  private HBox loadDialogButtonContainer()
  {
    var dialogButtonContainer = new HBox();

    dialogButtonContainer.getStyleClass().addAll("button-container", "multiple-option-button-container");
    dialogButtonContainer.getChildren().add(this.loadConfirmButton());
    dialogButtonContainer.getChildren().add(this.loadCancelButton());

    return dialogButtonContainer;
  }

  /**
   * Loads the confirm button with {@link ReturnToMainMenuHandler}.
   *
   * @return Button : confirm button
   */
  private Button loadConfirmButton()
  {
    var confirmButton = new Button("Yes");

    confirmButton.getStyleClass().add("dialog-button");
    confirmButton.setOnAction(new ReturnToMainMenuHandler());

    return confirmButton;
  }

  /**
   * Loads the cancel button with {@link CloseGameScreenDialogHandler}.
   *
   * @return Button : cancel button
   */
  private Button loadCancelButton()
  {
    var cancelButton = new Button("No");

    cancelButton.getStyleClass().add("dialog-button");
    cancelButton.setOnAction(new CloseGameScreenDialogHandler());

    return cancelButton;
  }
}
