package Jugadores;

/**
 *
 * @author omarc
 */
public class Player {

    private String name;
    private String symbol;
    private boolean turn;
    

    public Player() {
    }

    public Player(String name, String symbol, boolean turn) {
        this.name = name;
        this.symbol = symbol;
        this.turn = turn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
    
    public void automaticMove(){
        
        
    }
}
