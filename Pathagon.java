/**
 * Main que permite jugar a Pathagon contra una IA que funciona
 * a travez del Algoritmo MinMax con Poda AlphaBeta.   
 * @author Gardiola Joaquin y Giachero Ezequiel
 * @version 0.1
 */

import java.util.*;

public class Pathagon {

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
        PathagonProblem pathagon = new PathagonProblem();
        PathagonState game = pathagon.initialState();
        AdversarySearchEngine machine = new MinMaxAlphaBetaEngine(pathagon,level);
        while(!pathagon.end(game)){
            if(!pathagon.end(game)){
                clearScreen();
                System.out.println("Su Turno");
                System.out.println(game.toString());
                int x = move("X");
                int y = move("Y");
                game.addPiece(x,y,0);
            }
            clearScreen();
            if(!pathagon.end(game)){
                System.out.println("Turno de la IA");
                game = (PathagonState) machine.computeSuccessor(game);
            }
        }
        System.out.println(game.toString());
    }
    
    private static void vsPlayer(){
        clearScreen();
        PathagonProblem pathagon = new PathagonProblem();
        PathagonState game = pathagon.initialState();
        while(!pathagon.end(game)){
            
                System.out.println("Jugador 1 / 0");
                System.out.println(game.toString());
                int x = move("X");
                int y = move("Y");
                game.addPiece(x,y,0);
            
            clearScreen();
            
                System.out.println("Jugador 2 / X");
                System.out.println(game.toString());
                int i = move("X");
                int j = move("Y");
                game.addPiece(i,j,1);
            
            clearScreen();
        }
        System.out.println(game.toString());
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
 
