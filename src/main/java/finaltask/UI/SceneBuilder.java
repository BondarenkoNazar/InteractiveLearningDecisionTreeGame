package finaltask.UI;

import finaltask.tree.Node;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;

public class SceneBuilder {

    private Node tree;
    private Node node;
    private final Stage stage;

    public SceneBuilder(Stage stage){
        this.tree = new Node();

        this.stage = stage;
    }


    public Scene mainScene() throws IOException, ClassNotFoundException {

        this.readTree();

        BasicScene basicScene = new BasicScene(this.tree.getData(), "Yes", "No");

        basicScene.getFirstButton().setOnAction(actionEvent -> {
            try{
                this.node = node.getChildren().get(0);
            } catch (Exception e){
                this.stage.setScene(finishScene());
            }

            if(this.node.getChildren().isEmpty()){
                this.stage.setScene(this.checkScene());
            }else {
                basicScene.setLabelText(this.node.getData());
            }

        });

        basicScene.getSecondButton().setOnAction(actionEvent -> {
            this.node = node.getChildren().get(1);

            if(this.node.getChildren().isEmpty()){
                this.stage.setScene(this.checkScene());
            }else {
                basicScene.setLabelText(this.node.getData());
            }
        });

        return basicScene.getScene();
    }

    private Scene checkScene(){
        BasicScene basicScene = new BasicScene("Your object is: " + this.node.getData(), "Yes", "No");

        basicScene.getFirstButton().setOnAction(actionEvent -> this.stage.setScene(this.finishScene()));

        basicScene.getSecondButton().setOnAction(actionEvent -> this.stage.setScene(this.addNewOption()));

        return basicScene.getScene();
    }

    private Scene finishScene(){
        BasicScene basicScene = new BasicScene("Do u want play again?", "Yes", "No");

        basicScene.getFirstButton().setOnAction(actionEvent -> {
            try {
                saveTree();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                this.stage.setScene(this.mainScene());
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        basicScene.getSecondButton().setOnAction(actionEvent -> System.exit(0));

        return basicScene.getScene();
    }

    private Scene addNewOption(){
        Font font = new Font(20);

        TextArea question = new TextArea();
        question.setFont(font);
        question.setWrapText(true);
        TextArea answer = new TextArea();
        answer.setWrapText(true);
        answer.setFont(font);

        Label label1 = new Label("Enter enigmatic object:");
        label1.setFont(font);
        Label label2 = new Label("Enter the question which will help to find this object:");
        label2.setFont(font);

        Button saveButton = getSaveButton(answer, question);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(20));

        HBox hBox = new HBox(saveButton);
        hBox.setAlignment(Pos.CENTER_RIGHT);

        vBox.getChildren().addAll(label1, answer, label2, question, hBox);
        vBox.setSpacing(20);
        return new Scene(vBox, 600, 450);
    }


    private Button getSaveButton(TextArea answer, TextArea question) {
        Button saveButton = new Button("Ok");
        saveButton.setFont(new Font(15));
        saveButton.setAlignment(Pos.BOTTOM_RIGHT);

        saveButton.setOnAction(actionEvent -> {

            if (!answer.getText().isEmpty() && !question.getText().isEmpty()) {
                String data = this.node.getData();

                this.node.setData(question.getText());
                Node firstChild = new Node(answer.getText());
                firstChild.setParent(this.node);

                Node secondChild = new Node(data);
                secondChild.setParent(this.node);

                this.node.getChildren().add(firstChild);
                this.node.getChildren().add(secondChild);

                this.stage.setScene(this.finishScene());
            }
        });
        return saveButton;
    }

    private void saveTree() throws IOException {
        clearFile();

        FileOutputStream fileOutputStream = new FileOutputStream("Data.txt");

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(this.tree);
        objectOutputStream.close();
    }

    private void readTree(){
        try {
            FileInputStream fileInputStream = new FileInputStream("Data.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            this.tree = (Node) objectInputStream.readObject();
            this.node = tree;

            objectInputStream.close();
        } catch (Exception exception){
            this.tree.setData("Is it alive?");
            this.tree.getChildren().add(new Node("Person"));
            this.tree.getChildren().add(new Node("House"));

            try {
                this.saveTree();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static void clearFile()
    {
        try{
            FileWriter fw = new FileWriter("Data.txt", false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.flush();
            pw.close();
            fw.close();
        }catch(Exception exception){
            System.out.println("Exception have been caught");
        }
    }
}
