package Pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author omarc
 */
public class MenuPane {

    public VBox menuRoot;

    public VBox getMenuRoot() {

        menuRoot = new VBox(20);
        String style = "-fx-font-weight: bold; -fx-font-size: 16px;-fx-background-color: #44BEC6;";

        //Titulo
        Text title = new Text("Tres en Raya");
        title.setStyle(style);
        VBox titleVb = new VBox(25);
        titleVb.getChildren().add(title);
        titleVb.setAlignment(Pos.CENTER);
        titleVb.setPadding(new Insets(5,10,150,10));

        //Simbolo
        HBox symbolHb = new HBox(15);
        Label symbolLabel = new Label("Simbolo: ");
        symbolLabel.setStyle(style);
        ToggleGroup tg = new ToggleGroup();

        RadioButton xRb = new RadioButton("X");
        xRb.setUserData("X");
        xRb.setToggleGroup(tg);
        xRb.setStyle(style);

        RadioButton oRb = new RadioButton("O");
        oRb.setUserData("O");
        oRb.setToggleGroup(tg);
        oRb.setStyle(style);

        symbolHb.getChildren().addAll(symbolLabel, xRb, oRb);

        //Modo de juego
        HBox modeHb = new HBox(15);
        Label modeLabel = new Label("Modo de juego: ");
        modeLabel.setStyle(style);
        ToggleGroup tgMode = new ToggleGroup();

        RadioButton oneVcpuRb = new RadioButton("P1 vs CPU");
        oneVcpuRb.setUserData("X");
        oneVcpuRb.setToggleGroup(tgMode);
        oneVcpuRb.setStyle(style);

        RadioButton oneVoneRb = new RadioButton("P1 vs P2");
        oneVoneRb.setUserData("O");
        oneVoneRb.setToggleGroup(tgMode);
        oneVoneRb.setStyle(style);

        RadioButton cpuVcpuRb = new RadioButton("CPU vs CPU");
        cpuVcpuRb.setUserData("O");
        cpuVcpuRb.setToggleGroup(tgMode);
        cpuVcpuRb.setStyle(style);

        modeHb.getChildren().addAll(modeLabel, oneVcpuRb, oneVoneRb, cpuVcpuRb);
        
        //Boton de jugar
        VBox playHb = new VBox(20);
        Button playButton = new Button("Jugar");
        playButton.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");
        playHb.getChildren().add(playButton);
        
        playButton.setOnAction(e -> {
        
            
        
        
        });

        //Anadiendo nodos al root
        menuRoot.getChildren().add(titleVb);
        menuRoot.getChildren().add(symbolHb);
        menuRoot.getChildren().add(modeHb);
        menuRoot.getChildren().add(playHb);

        //Diseno del root
        menuRoot.setPadding(new Insets(40, 10, 15, 10));

        return menuRoot;
    }
    
    public void generateGameWindow(){
        Stage gameStage = new Stage();
        Scene gameScene = new Scene(new GamePane().getGameRoot(),650,650);
        gameStage.setScene(gameScene);
        
    }
}
