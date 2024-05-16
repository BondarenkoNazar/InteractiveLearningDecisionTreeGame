package finaltask;

import finaltask.UI.SceneBuilder;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        SceneBuilder sceneBuilder = new SceneBuilder(stage);
        Scene scene = sceneBuilder.mainScene();
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }


}
