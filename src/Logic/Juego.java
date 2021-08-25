package Logic;

import Jugadores.Player;
import Pane.MenuPane;
import Tree.Tree;

/**
 *
 * @author omarc
 */
public class Juego {

    public Tree<Tablero> generalTree;
    public String iniTurn;

    Player p1 = new Player();
    Player p2 = new Player();
    public Tablero tablero = new Tablero();

    public Juego(Player p1, Player p2, String iniTurn) {
        this.p1 = p1;
        this.p2 = p2;
        this.tablero = new Tablero();
        this.iniTurn = iniTurn;
        generalTree = generateTree(new Tablero(), iniTurn, new Tree());
    }

    public void refreshTab(int fila, int columna) {
        while (!tablero.isFullTab() && tablero.isWinner(p1) && tablero.isWinner(p2)) {

            if (p1.isTurn()) {
                tablero.getTablero()[fila][columna] = p1.getSymbol();
                p1.setTurn(false);
                p2.setTurn(true);
            } else if (p2.isTurn()) {
                tablero.getTablero()[fila][columna] = p2.getSymbol();
                p2.setTurn(false);
                p1.setTurn(true);
            }

            if (tablero.isWinner(p1)) {
                System.out.println("GANADOR: " + p1.getName());
            } else if (tablero.isWinner(p2)) {
                System.out.println("GANADOR: " + p2.getName());
            }
        }
    }
    

    public Tree<Tablero> generateTree(Tablero tab, String turn, Tree<Tablero> tree) {

        if (tab.isEmptyTab() || !tab.isFullTab()) {
            String casillas[][] = tab.getTablero();
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    if (casillas[i][j].equals("-1")) {
                        casillas[i][j] = turn;
                        tree.getRoot().getChildren().add(new Tree(tab));
                        turn = alternateTurn(turn);
                        tree.getRoot().getChildren().add(generateTree(tab, turn, tree.getRoot().getChildren().get(tree.getRoot().getChildren().size() - 1)));
                    }
                }
            }
        }

        return tree;
    }

    public String alternateTurn(String turn) {
        if (turn.equals("SI")) {
            return String.valueOf(MenuPane.tgTurn.getSelectedToggle().getUserData());
        } else if (turn.equals("NO")) {
            if (String.valueOf(MenuPane.tgTurn.getSelectedToggle().getUserData()).equals("X")) {
                return "O";
            } else {
                return "X";
            }
        }
        if (turn.equals("X")) {
            return "O";
        } else {
            return "X";
        }
    }

    public Tree<Tablero> getChild(Tablero tab, Tree<Tablero> tree) {

        Tree<Tablero> child = null;
        if (!tree.getRoot().getChildren().isEmpty()) {
            for (Tree<Tablero> t : tree.getRoot().getChildren()) {
                if (t.getRoot().getContent().compareTab(tab) == 0) {
                    return t;
                }
                child = getChild(tab, t);

                if (child != null) {
                    return child;
                }
            }
        }
        return child;
    }

}
