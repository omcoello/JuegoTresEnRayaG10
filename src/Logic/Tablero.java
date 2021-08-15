package Logic;

import Tree.Tree;

/**
 *
 * @author omarc
 */
public class Tablero {

    private Casilla tab[][];
    private Tree<Tablero> tree;

    public Tablero() {
        this.tab = new Casilla[3][3];
        this.tree = new Tree<>();
    }

    public Casilla[][] getTab() {
        return this.tab;
    }
    public void setTab(Casilla[][] casillas){
        this.tab = casillas;
    }
    
    public Tree<Tablero> getTree(){
        return tree;
    }
    
    //Metodo para verificar si TODAS las casillas del tablero estan VACIAS
    public boolean isEmptyTab() {

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if(!tab[i][j].isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }
    
    //Metodo para verificar si TODAS las casillas del tablero estan LLENAS
    public boolean isFullTab(){
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if(tab[i][j].isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }

}
