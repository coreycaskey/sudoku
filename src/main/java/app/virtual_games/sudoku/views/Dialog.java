package app.virtual_games.sudoku.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Parent class for dialogs.
 *
 * @author Corey Caskey
 * @version 1.0.0
 */
public class Dialog extends StackPane
{
  private Pane backgroundPane;
  private Pane mainPane;
  private VBox mainContainer;
  private HBox closeButtonContainer;
  private Button closeButton;

  /**
   * Initializes parent dialog.
   */
  public Dialog()
  {
    this.backgroundPane = this.buildBackgroundPane();
    this.mainPane = this.buildMainPane();

    this.getChildren().add(this.backgroundPane);
    this.getChildren().add(this.mainPane);
  }

  /** Private Helper Methods **/

  /**
   * Builds background pane.
   *
   * @return Pane : background pane
   */
  private Pane buildBackgroundPane()
  {
    var bg = new Pane();

    bg.getStyleClass().add("dialog-background");

    return bg;
  }

  /**
   * Builds main pane.
   *
   * @return Pane : main pane
   */
  private Pane buildMainPane()
  {
    var dialog = new Pane();

    this.mainContainer = this.buildMainContainer();

    dialog.getStyleClass().add("dialog");
    dialog.getChildren().add(this.mainContainer);

    return dialog;
  }

  /**
   * Builds main container with {@link #buildCloseButtonContainer}.
   *
   * @return VBox : main container
   */
  private VBox buildMainContainer()
  {
    var container = new VBox();

    this.closeButtonContainer = buildCloseButtonContainer();

    container.getStyleClass().add("dialog-container");
    container.getChildren().add(this.closeButtonContainer);

    return container;
  }

  /**
   * Builds close button container with {@link #buildCloseButton}.
   *
   * @return HBox : close button container
   */
  private HBox buildCloseButtonContainer()
  {
    var buttonContainer = new HBox();

    this.closeButton = buildCloseButton();

    buttonContainer.getStyleClass().add("close-button-container");
    buttonContainer.getChildren().add(this.closeButton);

    return buttonContainer;
  }

  /**
   * Builds close button.
   *
   * @return Button : close button
   */
  private Button buildCloseButton()
  {
    var button = new Button();

    button.getStyleClass().add("close-button");

    return button;
  }

  /** Public Helper Methods **/

  /**
   * Adds class names to dialog.
   *
   * @param styling : class names
   */
  public void addDialogStyling(String... styling)
  {
    this.mainPane.getStyleClass().addAll(styling);
  }

  /**
   * Adds content container to main container.
   *
   * @param contentContainer : content container
   */
  public void addContentContainer(VBox contentContainer)
  {
    this.mainContainer.getChildren().add(contentContainer);
  }

  /**
   * Adds class names to close button container.
   *
   * @param styling : class names
   */
  public void addCloseButtonContainerStyling(String... styling)
  {
    this.closeButtonContainer.getStyleClass().addAll(styling);
  }

  /**
   * Removes close button container.
   */
  public void hideCloseButtonContainer()
  {
    this.mainContainer.getChildren().remove(0);
  }

  /**
   * Adds targeted event handler to close button.
   *
   * @param eventHandler : event handler
   */
  public void addCloseDialogButtonHandler(EventHandler<ActionEvent> eventHandler)
  {
    this.closeButton.setOnAction(eventHandler);
  }
}
