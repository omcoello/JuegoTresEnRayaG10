package Pane;

import Jugadores.Player;
import java.io.File;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author omarc
 */
public class MenuPane {

    public VBox menuRoot;
    public static ToggleGroup tgSymbol;
    public static ToggleGroup tgMode;
    public static ToggleGroup tgTurn;
    public static Player p1;
    public static Player p2;

    public VBox getMenuRoot() {

        menuRoot = new VBox(20);
        String style = "-fx-font-weight: bold; -fx-font-size: 16px;-fx-background-color: #44BEC6;";

        //Titulo
        Text title = new Text("Tres en Raya");
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 24px;-fx-background-color: #44BEC6;");
        VBox titleVb = new VBox(25);
        titleVb.getChildren().add(title);
        titleVb.setAlignment(Pos.CENTER);
        titleVb.setPadding(new Insets(5, 10, 150, 10));

        //Simbolo
        HBox symbolHb = new HBox(15);
        Label symbolLabel = new Label("Simbolo P1: ");
        symbolLabel.setStyle(style);
        tgSymbol = new ToggleGroup();

        RadioButton xRb = new RadioButton("X");
        xRb.setUserData("X");
        xRb.setToggleGroup(tgSymbol);
        xRb.setStyle(style);

        RadioButton oRb = new RadioButton("O");
        oRb.setUserData("O");
        oRb.setToggleGroup(tgSymbol);
        oRb.setStyle(style);

        symbolHb.getChildren().addAll(symbolLabel, xRb, oRb);

        //Modo de juego
        HBox modeHb = new HBox(15);
        Label modeLabel = new Label("Modo de juego: ");
        modeLabel.setStyle(style);
        tgMode = new ToggleGroup();

        RadioButton oneVcpuRb = new RadioButton("P1 vs CPU");
        oneVcpuRb.setUserData("p1Vcpu");
        oneVcpuRb.setToggleGroup(tgMode);
        oneVcpuRb.setStyle(style);

        RadioButton oneVoneRb = new RadioButton("P1 vs P2");
        oneVoneRb.setUserData("p1Vp2");
        oneVoneRb.setToggleGroup(tgMode);
        oneVoneRb.setStyle(style);

        RadioButton cpuVcpuRb = new RadioButton("CPU vs CPU");
        cpuVcpuRb.setUserData("cpuVcpu");
        cpuVcpuRb.setToggleGroup(tgMode);
        cpuVcpuRb.setStyle(style);

        modeHb.getChildren().addAll(modeLabel, oneVcpuRb, oneVoneRb, cpuVcpuRb);

        //Turno
        tgTurn = new ToggleGroup();
        HBox turnHb = new HBox(15);
        Label turnLabel = new Label("Empezar turno P1: ");
        turnLabel.setStyle(style);

        RadioButton yesRb = new RadioButton("SI");
        yesRb.setUserData("SI");
        yesRb.setToggleGroup(tgTurn);
        yesRb.setStyle(style);

        RadioButton noRb = new RadioButton("NO");
        noRb.setUserData("NO");
        noRb.setToggleGroup(tgTurn);
        noRb.setStyle(style);

        turnHb.getChildren().addAll(turnLabel, yesRb, noRb);

        //Boton de jugar
        VBox playHb = new VBox(20);
        Button playButton = new Button("Jugar");
        playButton.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");
        playHb.getChildren().add(playButton);
        playHb.setAlignment(Pos.CENTER);

        playButton.setOnAction(e -> {
            if (tgMode.getSelectedToggle() != null && tgSymbol.getSelectedToggle() != null && tgTurn.getSelectedToggle() != null) {
                generateGameWindow();
            }
        });

        //Anadiendo nodos al root
        menuRoot.getChildren().add(titleVb);
        menuRoot.getChildren().add(symbolHb);
        menuRoot.getChildren().add(modeHb);
        menuRoot.getChildren().add(turnHb);
        menuRoot.getChildren().add(playHb);

        //Diseno del root
        File path = new File("src/Resources/table.jpg");
        Image img = new Image(path.toURI().toString());
        menuRoot.setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(500, 500, false, false, false, false))));
        
        menuRoot.setPadding(new Insets(40, 10, 15, 10));

        return menuRoot;
    }

    public void generateGameWindow() {
        p1 = new Player();
        p2 = new Player();
        configurateSymbols();
        configurateMode();
        GamePane.gameStage = new Stage();
        GamePane.gameScene = new Scene(new GamePane().getGameRoot(), 350, 350);
        GamePane.gameStage.setScene(GamePane.gameScene);
        GamePane.gameStage.show();

    }

    public void configurateSymbols() {

        String mainSymbol = String.valueOf(MenuPane.tgSymbol.getSelectedToggle().getUserData());
        p1.setSymbol(mainSymbol);

        if (mainSymbol.equals("X")) {
            p2.setSymbol("O");
        } else {
            p2.setSymbol("X");
        }
    }

    public void configurateMode() {
        String mode = String.valueOf(tgMode.getSelectedToggle().getUserData());
        if(mode.equals("p1Vcpu")){
            
        }
    }
}
