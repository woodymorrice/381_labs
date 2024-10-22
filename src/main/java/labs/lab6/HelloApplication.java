package labs.lab6;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * MVC is Cyclical
 * Model -> updates view
 * View -> sends event to controller
 * Controller -> makes changes to the model
 */


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        MainUI root = new MainUI();
        Scene scene = new Scene(root);
        stage.setTitle("MVC Example!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}