package Logic;

import Jugadores.Player;
import Pane.MenuPane;
import Tree.Tree;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author omarc
 */
public class Juego {

    public Tree<Tablero> generalTree;
    public Tree<Tablero> gameTree;

    public String iniTurn;

    public static Player p1 = new Player();
    public static Player p2 = new Player();
    public static Tablero tablero = new Tablero();

    public Juego(Player p1, Player p2, String iniTurn) {
        this.p1 = p1;
        this.p2 = p2;
        this.tablero = new Tablero();
        this.iniTurn = iniTurn;
        generalTree = genericTree(iniTurn);
    }

    public Tree<Tablero> getGameTree() {
        return gameTree;
    }

    public void setGameTree(Tree<Tablero> gameTree) {
        this.gameTree = gameTree;
    }

    public void refreshTab(int fila, int columna) {
        if (p1.isTurn()) {
            tablero.getTablero()[fila][columna] = p1.getSymbol();
            p1.setTurn(false);
            p2.setTurn(true);
            System.out.println(p1.getSymbol() + " <- Simbolo");
        } else if (p2.isTurn()) {
            tablero.getTablero()[fila][columna] = p2.getSymbol();
            System.out.println(p2.getSymbol() + " <- Simbolo");
            p2.setTurn(false);
            p1.setTurn(true);
        }
    }

    public boolean winCondition() {
        return tablero.isFullTab() || tablero.isWinner(p1) || tablero.isWinner(p2);
    }

    public Tree<Tablero> generateTree(Tablero tab, String turn, Tree<Tablero> tree) {
        if (tab.isEmptyTab()) {
            turn = initializeTurn(turn);
        }
        if (tab.isEmptyTab() || !tab.isFullTab()) {
            tab.verTablero();
            String casillas[][] = tab.getTablero();
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    if (casillas[i][j].equals("-1")) {
                        casillas[i][j] = turn;
                        tree.getRoot().getChildren().add(new Tree(tab));
                        turn = alternateTurn(turn);
                        ArrayList<Tree<Tablero>> children = tree.getRoot().getChildren();
                        Tree<Tablero> subtree = generateTree(tab, turn, children.get(children.size() - 1));
                        children.add(subtree);
                    }
                }
            }
        }

        return tree;
    }
    static int count = 0;

    public Tree<Tablero> genericTree(String turn) {
        Tree<Tablero> tree = new Tree(new Tablero());
        Stack<Tree<Tablero>> op = new Stack<>();
        Stack<Tree<Tablero>> xp = new Stack<>();

        turn = initializeTurn(turn);
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (tree.getRoot().getContent().getTablero()[i][j].equals("-1")) {
                    Tablero cp = new Tablero();
                    tree.getRoot().getContent().getTablero()[i][j] = turn;
                    cp.copyTab(tree.getRoot().getContent());
                    tree.getRoot().getChildren().add(new Tree(cp));
                    op.push(tree.getRoot().getChildren().get(tree.getRoot().getChildren().size() - 1));
                }
            }
        }
        turn = alternateTurn(turn);
        while (!op.isEmpty() || !xp.isEmpty()) {
            while (!op.isEmpty()) {
                Tree<Tablero> t = op.pop();
                for (int i = 0; i < 3; ++i) {
                    for (int j = 0; j < 3; ++j) {
                        if (t.getRoot().getContent().getTablero()[i][j].equals("-1")) {
                            Tablero cp = new Tablero();
                            t.getRoot().getContent().getTablero()[i][j] = turn;
                            cp.copyTab(t.getRoot().getContent());
                            cp.verTablero();
                            if (!t.getRoot().getContent().isFullTab()) {
                                t.getRoot().getChildren().add(new Tree(cp));
                                op.push(t.getRoot().getChildren().get(t.getRoot().getChildren().size() - 1));
                            }
                        }
                        System.out.println("count: " + count++);
                    }
                }
            }
            turn = alternateTurn(turn);
            while (!xp.isEmpty()) {
                Tree<Tablero> t = xp.pop();
                for (int i = 0; i < 3; ++i) {
                    for (int j = 0; j < 3; ++j) {
                        if (t.getRoot().getContent().getTablero()[i][j].equals("-1")) {
                            Tablero cp = new Tablero();
                            t.getRoot().getContent().getTablero()[i][j] = turn;
                            cp.copyTab(t.getRoot().getContent());
                            if (!t.getRoot().getContent().isFullTab()) {
                                t.getRoot().getChildren().add(new Tree(cp));
                                xp.push(t.getRoot().getChildren().get(t.getRoot().getChildren().size() - 1));
                            }
                        }
                    }
                }
            }
            turn = alternateTurn(turn);
        }

        return tree;
    }

    public String alternateTurn(String turn) {

        if (turn.equals("X")) {
            return "O";
        } else {
            return "X";
        }
    }

    public String initializeTurn(String turn) {
        if (turn.equals("SI")) {
            p1.setTurn(true);
            p2.setTurn(false);
            return String.valueOf(MenuPane.tgSymbol.getSelectedToggle().getUserData());
        } else {
            p1.setTurn(false);
            p2.setTurn(true);
            if (String.valueOf(MenuPane.tgSymbol.getSelectedToggle().getUserData()).equals("X")) {
                return "O";
            } else {
                return "X";
            }
        }
    }

    public Tree<Tablero> getChild(Tablero tab, Tree<Tablero> tree) {
        /*
        if(tree.getRoot().getContent().equals(tab)){
            return tree;
        }
         */
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
