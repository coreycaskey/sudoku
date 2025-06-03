package app.virtual_games.sudoku.views;

import app.virtual_games.sudoku.handlers.CloseApplicationButtonHandler;
import app.virtual_games.sudoku.handlers.CloseMainMenuDialogHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Exit application dialog.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class ExitApplicationDialog extends Dialog
{
  /**
   * Initializes exit application dialog.
   */
  public ExitApplicationDialog()
  {
    super();
    this.addDialogStyling("small-dialog");
    this.addContentContainer(this.buildContentContainer());
    this.addCloseButtonContainerStyling("small-close-button-container");
    this.addCloseDialogButtonHandler(new CloseMainMenuDialogHandler());
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

    contentContainer.getStyleClass().addAll("content-container", "small-content-container");
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
    var title = new Label("Are you sure you want to exit the application?");

    title.getStyleClass().addAll("dialog-title", "small-dialog-title");

    return title;
  }

  /**
   * Builds button container with {@link #buildConfirmButton} and {@link #buildCancelButton}.
   *
   * @return HBox : button container
   */
  private HBox buildButtonContainer()
  {
    var buttonContainer = new HBox();

    buttonContainer.getStyleClass().addAll("button-container", "multiple-option-button-container");
    buttonContainer.getChildren().add(this.buildConfirmButton());
    buttonContainer.getChildren().add(this.buildCancelButton());

    return buttonContainer;
  }

  /**
   * Builds confirm button.
   *
   * @return Button : confirm button
   */
  private Button buildConfirmButton()
  {
    var confirmButton = new Button("Yes");

    confirmButton.getStyleClass().add("dialog-button");
    confirmButton.setOnAction(new CloseApplicationButtonHandler());

    return confirmButton;
  }

  /**
   * Builds cancel button.
   *
   * @return Button : cancel button
   */
  private Button buildCancelButton()
  {
    var cancelButton = new Button("No");

    cancelButton.getStyleClass().add("dialog-button");
    cancelButton.setOnAction(new CloseMainMenuDialogHandler());

    return cancelButton;
  }
}
