package Logic;

import Jugadores.ComputadoraIA;
import Jugadores.Persona;
import Tree.Tree;
import java.util.Scanner;

/**
 *
 * @author omarc
 */
public class Juego {

    private Tree<Tablero> generalTree;
    private String iniTurn;

    Persona humanPlayer = new Persona();
    ComputadoraIA computadoraIA = new ComputadoraIA();
    Tablero tablero = new Tablero();

    public Juego(Persona humanPlayer, ComputadoraIA computadoraIA, Tablero tablero) {
        this.humanPlayer = humanPlayer;
        this.computadoraIA = computadoraIA;
        this.tablero = tablero;
        generalTree = generateTree(new Tablero(), iniTurn, new Tree());
    }

    public void startGame() {
        asignarSimbolos();
        escogerTurno();
        tablero.verTablero();
        ingresarSimbolos();
    }

    private void asignarSimbolos() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escoge tu simbolo (X || O): ");
        String simbolo = sc.nextLine();
        if (simbolo.toUpperCase().equals("X")) {
            humanPlayer.setSymbol("X");
            computadoraIA.setSymbol("O");
        } else {
            humanPlayer.setSymbol("O");
            computadoraIA.setSymbol("X");
        }
    }

    private void ingresarSimbolos() {
        while (!tablero.isFullTab() && tablero.isWinner(humanPlayer) && tablero.isWinner(computadoraIA)) {

            if (humanPlayer.isTurn()) {
                ingresoHumano();
                humanPlayer.setTurn(false);
                computadoraIA.setTurn(true);
            } else if (computadoraIA.isTurn()) {
                ingresoPC();
                computadoraIA.setTurn(false);
                humanPlayer.setTurn(true);
            }

            if (tablero.isWinner(humanPlayer)) {
                System.out.println("GANADOR: HUMANO");
            } else if (tablero.isWinner(computadoraIA)) {
                System.out.println("GANADOR: CPU");
            }
        }
    }

    private void ingresoHumano() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Turno humano: " + humanPlayer.getSymbol());
        System.out.println("Posiciones: ");
        int pos_i = sc.nextInt();
        int pos_j = sc.nextInt();
        tablero.getTablero()[pos_i][pos_j] = humanPlayer.getSymbol();
        tablero.verTablero();
    }

    //SOLO DE PRUEBA, DEBERIA SER EL ALGORITMO QUE ELIGA EL INGRESO
    private void ingresoPC() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Turno cpu: " + computadoraIA.getSymbol());
        System.out.println("Posiciones: ");
        int pos_i = sc.nextInt();
        int pos_j = sc.nextInt();
        tablero.getTablero()[pos_i][pos_j] = computadoraIA.getSymbol();
        tablero.verTablero();
    }

    private void escogerTurno() {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Quien empieza? 1 = humano, 2 = computadora: ");
        String lector = sc.nextLine();
        if (lector.equals("1")) {
            humanPlayer.setTurn(true);
        } else {
            humanPlayer.setTurn(false);
            computadoraIA.setTurn(true);
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
