package Logic;

/**
 *
 * @author omarc
 */
public class Casilla {
    
    private boolean empty;
    private boolean circleTurn;
    
    public Casilla(){
        empty = true;
    }
    public Casilla(boolean circleTurn){
        this.circleTurn = circleTurn;
        empty = false;
    }
    
    public boolean isEmpty(){
        return this.empty == true;
    }
    
    public boolean getSymbol(){
        return this.circleTurn;        
    }
    
    public void setCircleTurn(boolean circleTurn){
        this.circleTurn = circleTurn;
    }
}
