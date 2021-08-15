/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author omarc
 */
public class Casilla {
    
    private boolean empty;
    private String symbol;
    
    public Casilla(){
        empty = true;
    }
    public Casilla(String symbol){
        this.symbol = symbol;
        empty = false;
    }
    
    public boolean isEmpty(){
        return this.empty == true;
    }
    
    public String getSymbol(){
        return this.symbol;        
    }
}
