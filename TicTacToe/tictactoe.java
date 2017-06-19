/**
 * Main que permite jugar a ticTacToe contra una IA que funciona
 * a travez del Algoritmo MinMax con Poda AlphaBeta.   
 * @author Gardiola Joaquin y Giachero Ezequiel
 * @version 0.1
 */

import java.util.*;

public class tictactoe {

    public static void main(String[] args) {
        Boolean finish=false;
        while (!finish) {
            clearScreen();
            System.out.println("Menu");
            System.out.println("1- Jugador vs Jugador");
            System.out.println("2- Jugador vs IA");
            System.out.println("3- Salir");
            String opt = leerTeclado();
            if (opt.equals("1"))
                vsPlayer();
            else if (opt.equals("2")){
                System.out.println("Ingrese dificultad");
                vsIA(Integer.parseInt(leerTeclado()));
            }
            else if (opt.equals("3"))
                finish = true;
            System.out.println("Pulse enter para continuar");
            leerTeclado();
            clearScreen();
        }
    }

    private static void vsIA(Integer level){
        clearScreen();
        TictactoeSearchProblem ticTacToe = new TictactoeSearchProblem();
        TictactoeState game = ticTacToe.initialState();
        AdversarySearchEngine machine = new MinMaxAlphaBetaEngine(ticTacToe,level);
        while(!ticTacToe.end(game)){
            if(!ticTacToe.end(game)){
                boolean ok = false;
                while(!ok){
                    clearScreen();
                    System.out.println("Su Turno");
                    System.out.println(game.toString());
                    int x = -1; int y = -1;
                    try{
                        x = move("X");
                        y = move("Y");
                    }catch(IllegalArgumentException e){System.err.println("Entrada erronea/Movimiento invalido");}
                    ok = game.setBoard(x,y,1);
                }
            }
            clearScreen();
            if(!ticTacToe.end(game)){
                System.out.println("Turno de la IA");
                game = (TictactoeState) machine.computeSuccessor(game);
            }
        }
        System.out.println(game.toString());
        System.out.println("Fin del juego");
    }
    
    private static void vsPlayer(){
        clearScreen();
        TictactoeSearchProblem ticTacToe = new TictactoeSearchProblem();
        TictactoeState game = ticTacToe.initialState();
        while(!ticTacToe.end(game)){
            if(!ticTacToe.end(game)){
                boolean ok = false;
                while(!ok){
                    clearScreen();
                    System.out.println("Jugador 1 / 0");
                    System.out.println(game.toString());
                    int i = -1; int j = -1;
                    try{
                        i = move("X");
                        j = move("Y");
                    }catch(IllegalArgumentException e){System.err.println("Entrada erronea/Movimiento invalido");}
                    ok = game.setBoard(i,j,0);
                }
            }
            if(!ticTacToe.end(game)){
                boolean ok = false;
                while(!ok){
                    clearScreen();
                    System.out.println("Jugador 2 / X");
                    System.out.println(game.toString());
                    int x = -1; int y = -1;
                    try{
                        x = move("X");
                        y = move("Y");
                    }catch(IllegalArgumentException e){System.err.println("Entrada erronea/Movimiento invalido");}
                    ok = game.setBoard(x,y,1);
                }
            }
        }
        clearScreen();
        System.out.println(game.toString());
        System.out.println("Fin del juego");
    }
    
    //Funcion para leer movimiento
    public static Integer move(String mv){
        System.out.println("Ingrese movimiento "+mv);
        return Integer.parseInt(leerTeclado());
    }



    //Funcion para leer cadenas por teclado
    public static String leerTeclado(){
        String entradaTeclado = "";
        Scanner entradaEscaner = new Scanner (System.in); 
        entradaTeclado = entradaEscaner.nextLine();
        return entradaTeclado;
    }

    //Funcion para limpiar la pantalla
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  

}
 
