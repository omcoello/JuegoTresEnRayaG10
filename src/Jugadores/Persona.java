package Jugadores;

public class Persona extends Player {

    private boolean automaticTurn;

    public Persona() {
    }

    public Persona(String name, String symbol, boolean turn) {
        super(name, symbol, turn);
    }

    public boolean isAutomaticTurn() {
        return automaticTurn;
    }

    public void setAutomaticTurn(boolean automaticTurn) {
        this.automaticTurn = automaticTurn;
    }

    public void personalMove() {

    }
}
