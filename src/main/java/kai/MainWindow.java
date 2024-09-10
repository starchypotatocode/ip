package kai;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import kai.ui.DialogBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Kai kai;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserProfile.png"));
    private Image kaiImage = new Image(this.getClass().getResourceAsStream("/images/KaiProfile.png"));

    /**
     * Initializes the window with part of its required bindings
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Kai instance in this class, and displays the startup message
     *
     * @param k the Kai instance in questions
     */
    public void initializeWithKai(Kai k) {
        kai = k;
        String message = kai.getStartupMessage();
        dialogContainer.getChildren().add(DialogBox.getKaiDialog(message, kaiImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Kai's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = kai.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKaiDialog(response, kaiImage)
        );
        userInput.clear();

        if (!kai.continueRunning()) {
            PauseTransition termination = new PauseTransition(Duration.seconds(1d));
            termination.setOnFinished(event -> Platform.exit());
            termination.play();
        }
    }
}
