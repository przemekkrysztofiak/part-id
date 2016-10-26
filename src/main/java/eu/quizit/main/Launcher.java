package eu.quizit.main;

import eu.quizit.controller.Controller;
import eu.quizit.model.Model;
import eu.quizit.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller(new View(), new Model());
        controller.launch();
    }

}
