package Pane;

import Jugadores.ComputadoraIA;
import Jugadores.Persona;
import Jugadores.Player;
import Logic.Juego;
import Logic.Tablero;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 *
 * @author omarc
 */
public class GamePane {

    private GridPane gameRoot;
    public Player p1 = MenuPane.p1;
    public Player p2 = MenuPane.p2;

    Juego game = new Juego(p1, p2, String.valueOf(MenuPane.tgTurn.getSelectedToggle().getUserData()));

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

                button.setOnAction(e -> {
                    
                });

                gameRoot.add(button, j, i);
            }
        }
    }
    
    public void refreshTabWindow(int fila, int columna){
        game.refreshTab(fila,columna);
        
    }

    public void refreshRoot() {
        gameRoot.getChildren().clear();
    }
}
