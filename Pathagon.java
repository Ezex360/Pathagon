/**
 * Main que permite jugar a Pathagon contra una IA que funciona
 * a travez del Algoritmo MinMax con Poda AlphaBeta.   
 * @param args[0] nivel de profundidad el algoritmo MinMax
 * @author Gardiola Joaquin y Giachero Ezequiel
 * @version 0.1
 */

import java.util.*;

public class Pathagon {

    public static void main(String[] args) {
        clearScreen();
        PathagonProblem pathagon = new PathagonProblem();
        PathagonState game = pathagon.initialState();
        AdversarySearchEngine machine = new MinMaxAlphaBetaEngine(pathagon,Integer.parseInt(args[0]));
        while(!pathagon.end(game)){
            if(!pathagon.end(game)){
                System.out.println(game.toString());
                int x = move("X");
                int y = move("Y");
                game.addPiece(x,y,0);
            }
            clearScreen();
            if(!pathagon.end(game)){
                game = (PathagonState) machine.computeSuccessor(game);
            }
        }
        System.out.println(game.toString());

	}
    
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
 
