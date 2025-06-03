package app.virtual_games.sudoku.views;

import app.virtual_games.sudoku.controllers.GameController;
import app.virtual_games.sudoku.handlers.CloseMainMenuDialogHandler;
import app.virtual_games.sudoku.handlers.ReturnToMainMenuHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Error dialog.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class ErrorDialog extends Dialog
{
  /**
   * Initializes error dialog.
   */
  public ErrorDialog()
  {
    super();
    this.addDialogStyling("small-dialog");
    this.addContentContainer(this.buildContentContainer());
    this.hideCloseButtonContainer();
  }

  /** Private Helper Methods **/

  /**
   * Builds content container with {@link #buildTitle} and {@link #buildButtonContainer}.
   *
   * @return VBox : content container
   */
  private VBox buildContentContainer()
  {
    var contentContainer = new VBox();

    contentContainer.getStyleClass().addAll("content-container", "small-content-container",
        "error-dialog-content-container");
    contentContainer.getChildren().add(this.buildTitle());
    contentContainer.getChildren().add(this.buildButtonContainer());

    return contentContainer;
  }

  /**
   * Builds title.
   *
   * @return Label : title
   */
  private Label buildTitle()
  {
    var title = new Label("Uh oh... Something went wrong. Try again later.");

    title.getStyleClass().addAll("dialog-title", "error-dialog-title");

    return title;
  }

  /**
   * Builds button container with {@link #buildConfirmButton}.
   *
   * @return HBox : button container
   */
  private HBox buildButtonContainer()
  {
    var buttonContainer = new HBox();

    buttonContainer.getStyleClass().add("button-container");
    buttonContainer.getChildren().add(this.buildConfirmButton());

    return buttonContainer;
  }

  /**
   * Builds confirm button.
   *
   * @return Button : confirm button
   */
  private Button buildConfirmButton()
  {
    var confirmButton = new Button("OK");

    confirmButton.getStyleClass().add("dialog-button");
    confirmButton
        .setOnAction(GameController.getCurrentScreen().getId() == "main-menu" ? new CloseMainMenuDialogHandler()
            : new ReturnToMainMenuHandler());

    return confirmButton;
  }
}
