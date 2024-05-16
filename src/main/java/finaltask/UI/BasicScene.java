package finaltask.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class BasicScene {
    private final Button FirstButton;
    private final Button SecondButton;
    private final Label label;
    private final Scene scene;

    public BasicScene(String labelText, String firstButtonText, String secondButtonText){
        this.FirstButton = new Button();
        this.SecondButton = new Button();

        this.FirstButton.setText(firstButtonText);
        this.FirstButton.setFont(new Font(20));

        this.SecondButton.setText(secondButtonText);
        this.SecondButton.prefWidthProperty().bind(this.FirstButton.widthProperty());
        this.SecondButton.setFont(new Font(20));
        this.SecondButton.setTextAlignment(TextAlignment.CENTER);

        this.label = new Label(labelText);
        this.label.setFont(new Font(50));

        HBox hBox = new HBox(this.FirstButton, this.SecondButton);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(35);

        VBox vBox = new VBox(this.label, hBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(30);
        vBox.setPadding(new Insets(20));

        BorderPane pane = new BorderPane();
        pane.setCenter(vBox);

        this.scene = new Scene(pane, 600, 450);
    }

    public void setLabelText(String text) {
        label.setText(text);
    }

    public Scene getScene() {
        return scene;
    }

    public Button getFirstButton() {
        return FirstButton;
    }

    public Button getSecondButton() {
        return SecondButton;
    }
}
