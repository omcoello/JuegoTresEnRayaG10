package Logic;

import LogicaTablero.Tablero;
import Tree.Tree;

/**
 *
 * @author omarc
 */
public class Juego {

    private Tree<Tablero> generalTree;

    public Juego(String turn) {
        generalTree = generateTree(new Tablero(), turn, new Tree());
    }

    public Tree<Tablero> generateTree(Tablero tab, String turn, Tree<Tablero> tree) {

        if (tab.isEmptyTab() || !tab.isFullTab()) {
            String casillas[][] = tab.getTablero();
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    if (casillas[i][j].isEmpty()) {
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
    
    public String alternateTurn(String turn){
        if(turn.equals("X")){
            return "O";
        }else{
            return "X";
        }        
    }

}
