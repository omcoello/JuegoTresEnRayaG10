package Logic;

import Tree.Tree;

/**
 *
 * @author omarc
 */
public class Juego {

    private Tree<Tablero> generalTree;

    public Juego(boolean circleTurn) {
        generalTree = generateTree(new Tablero(), circleTurn, new Tree());
    }

    public Tree<Tablero> generateTree(Tablero tab, boolean circleTurn, Tree<Tablero> tree) {

        if (tab.isEmptyTab() || !tab.isFullTab()) {
            Casilla casillas[][] = tab.getTab();
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    if (casillas[i][j].isEmpty()) {
                        casillas[i][j] = new Casilla(circleTurn);
                        tab.setTab(casillas);                        
                        tree.getRoot().getChildren().add(generateTree(tab, !circleTurn, tree.getRoot().getChildren().get(tree.getRoot().getChildren().size()-1)));
                    }
                }
            }
        }

        return tree;
    }

}
