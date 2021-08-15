package Logic;

import Tree.Tree;

/**
 *
 * @author omarc
 */
public class Juego {

    private Tree<Tablero> tree;

    public Juego(boolean circleTurn) {
        tree = generateTree(new Tablero(), circleTurn);
    }

    public Tree<Tablero> generateTree(Tablero tab, boolean circleTurn) {

        if (tab.isEmptyTab() || !tab.isFullTab()) {
            Casilla casillas[][] = tab.getTab();
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    if (casillas[i][j].isEmpty()) {
                        casillas[i][j] = new Casilla(circleTurn);
                        tab.setTab(casillas);
                        tree.getRoot().getChildren().add(generateTree(tab, !circleTurn));
                    }
                }
            }
        }

        return tree;
    }

}
