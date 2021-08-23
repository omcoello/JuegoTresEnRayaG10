package Jugadores;

public class Persona extends Player {

    public Persona() {
    }

    public Persona(String name, String symbol, boolean turn) {
        super(name, symbol, turn);
    }
     /* 
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Persona persona = (Persona) o;
        return getSymbol().equals(persona.getSymbol());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSymbol());
    }
*/
}
