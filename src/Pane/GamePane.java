package Pane;

import Jugadores.ComputadoraIA;
import Jugadores.Persona;
import Jugadores.Player;
import Logic.Juego;
import Logic.Tablero;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author omarc
 */
public class GamePane {

    static Stage gameStage;
    static Scene gameScene;
    public static GridPane gameRoot;

    Juego game = new Juego(MenuPane.p1, MenuPane.p2, String.valueOf(MenuPane.tgTurn.getSelectedToggle().getUserData()));    

    public GridPane getGameRoot() {

        gameRoot = new GridPane();

        colocateButtons();

        return gameRoot;
    }

    public void colocateButtons() {
        String[][] tab = game.tablero.getTablero();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                Button button;
                String info = tab[i][j];
                if (info.equals("-1")) {
                    button = new Button("");
                } else {
                    button = new Button(info);
                }

                button.setUserData(i + "," + j);

                //Accionando botones
                button.setOnAction(e -> {
                    String fila = String.valueOf(button.getUserData()).split(",")[0];
                    String columna = String.valueOf(button.getUserData()).split(",")[1];                    
                    showGameOver(fila, columna);
                });

                gameRoot.add(button, j, i);
            }
        }
    }        

    public void refreshTabWindow(int fila, int columna) {
        game.refreshTab(fila, columna);
        game.setGameTree(game.getChild(game.tablero, game.generalTree));
        game.tablero.suggestMovement(game.getGameTree(), MenuPane.p1, MenuPane.p2);
        game.tablero.verTablero();

    }

    public void refreshScene() {

        gameRoot.getChildren().clear();
        gameScene.setRoot(getGameRoot());
        

    }

    public void showGameOver(String fila, String columna) {
        if (!game.winCondition()) {

            refreshTabWindow(Integer.valueOf(fila), Integer.valueOf(columna));
            refreshScene();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Juego terminado");
            alert.setHeaderText("Game Over");            

            if (game.tablero.isWinner(MenuPane.p1)) {
                alert.setContentText("Ganador: " + MenuPane.p1.getName());
            } else if (game.tablero.isWinner(MenuPane.p2)) {
                alert.setContentText("Ganador: " + MenuPane.p2.getName());
            }
            alert.show();
        }
    }
        
}
