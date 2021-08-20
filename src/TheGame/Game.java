package TheGame;

import Jugadores.ComputadoraIA;
import Jugadores.Persona;
import LogicaTablero.Tablero;

import java.util.Scanner;

public class Game {

    Persona humanPlayer = new Persona();
    ComputadoraIA computadoraIA = new ComputadoraIA();
    Tablero tablero = new Tablero();

    public Game(Persona humanPlayer, ComputadoraIA computadoraIA, Tablero tablero) {
        this.humanPlayer = humanPlayer;
        this.computadoraIA = computadoraIA;
        this.tablero = tablero;
    }

    public void startGame(){
        asginarSimbolos();
        escogerTurno();
        tablero.verTablero();
        ingresarSimbolos();
    }

    private void asginarSimbolos(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escoge tu simbolo (X || O): ");
        String simbolo = sc.nextLine();
        if(simbolo.toUpperCase().equals("X")){
            humanPlayer.setSimbolo("X");
            computadoraIA.setSimbolo("O");
        } else {
            humanPlayer.setSimbolo("O");
            computadoraIA.setSimbolo("X");
        }
    }

    private void ingresarSimbolos(){
        while(!tablero.isFullTab()){

            if(humanPlayer.isTurnoHumano()){
                ingresoHumano();
                humanPlayer.setTurnoHumano(false);
                computadoraIA.setPcTurno(true);
            } else if(computadoraIA.isPcTurno()){
                ingresoPC();
                computadoraIA.setPcTurno(false);
                humanPlayer.setTurnoHumano(true);
            }

            if(humanPlayer.PersonWins(this.tablero)){
                System.out.println("GANADOR: HUMANO");
                break;
            }
            if(computadoraIA.computerWins(this.tablero)){
                System.out.println("GANADOR: CPU");
                break;
            }
        }
    }

    private void ingresoHumano(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Turno humano: "+humanPlayer.getSimbolo());
        System.out.println("Posiciones: ");
        int pos_i = sc.nextInt();
        int pos_j = sc.nextInt();
        tablero.getTablero()[pos_i][pos_j] = humanPlayer.getSimbolo();
        tablero.verTablero();
    }

    //SOLO DE PRUEBA, DEBERIA SER EL ALGORITMO QUE ELIGA EL INGRESO
    private void ingresoPC(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Turno cpu: "+computadoraIA.getSimbolo());
        System.out.println("Posiciones: ");
        int pos_i = sc.nextInt();
        int pos_j = sc.nextInt();
        tablero.getTablero()[pos_i][pos_j] = computadoraIA.getSimbolo();
        tablero.verTablero();
    }

    private void escogerTurno(){
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Quien empieza? 1 = humano, 2 = computadora: ");
        String lector = sc.nextLine();
        if(lector.equals("1")){
            humanPlayer.setTurnoHumano(true);
        } else{
            humanPlayer.setTurnoHumano(false);
            computadoraIA.setPcTurno(true);
        }
    }


}
