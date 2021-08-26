package Main;

import Pane.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MenuPane mp = new MenuPane();
        Scene scene = new Scene(mp.getMenuRoot(), 500, 500);
        primaryStage.setScene(scene);        
        primaryStage.show();        
    }

}
