/**
 * Main que permite jugar a Pathagon contra una IA que funciona
 * a travez del Algoritmo MinMax con Poda AlphaBeta.   
 * @author Gardiola Joaquin y Giachero Ezequiel
 * @version 0.2
 */

import java.util.*;

public class Pathagon {

    /**
     * Menu principal donde se permitira elegir si jugar contra la IA
     * o tener un juego 1vs1.
     */
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
                int deep = -1;
                while(deep<1){
                    clearScreen();
                    System.out.println("Ingrese dificultad (Profundidad del algoritmo MinMax con poda AlphaBeta)");
                    try{
                        deep = Integer.parseInt(leerTeclado());
                    }catch(IllegalArgumentException e){System.err.println("Ingrese un numero valido porfavor.");}
                }
                vsIA(deep);
            }else if (opt.equals("3"))
                finish = true;
            System.out.println("Pulse enter para continuar");
            leerTeclado();
            clearScreen();
        }
	}

    /**
     * Funcion que permite tener un enfrentamiento contra la IA, utilizando el algoritmo
     * Min Max con poda Alpha Beta con una profundidad pasada por parametro.
     * @param level indica la profundidad a tener en cuenta por el algoritmo
     * de busqueda adversaria.
     */
    private static void vsIA(Integer level){
        clearScreen();
        PathagonProblem pathagon = new PathagonProblem();
        PathagonState game = pathagon.initialState();
        AdversarySearchEngine machine = new MinMaxAlphaBetaEngine(pathagon,level);
        while(!pathagon.end(game)){
            if(!pathagon.end(game)){
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
                    ok = game.addPiece(x,y,0);
                }
            }
            clearScreen();
            if(!pathagon.end(game)){
                System.out.println("Turno de la IA");
                game = (PathagonState) machine.computeSuccessor(game);
            }
        }
        System.out.println(game.toString());
        System.out.println("Fin del juego");
    }

    /**
     * Metodo que permite jugar a Pathagon de manera Jugador contra Jugador
     */
    private static void vsPlayer(){
        clearScreen();
        PathagonProblem pathagon = new PathagonProblem();
        PathagonState game = pathagon.initialState();
        while(!pathagon.end(game)){
            if(!pathagon.end(game)){
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
                    ok = game.addPiece(i,j,0);
                }
            }
            if(!pathagon.end(game)){
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
                    ok = game.addPiece(x,y,1);
                }
            }
        }
        clearScreen();
        System.out.println(game.toString());
        System.out.println("Fin del juego");
    }
    
    //Funcion para leer movimiento pasado por pantalla.
    public static Integer move(String mv){
        System.out.println("Ingrese movimiento "+mv);
        return Integer.parseInt(leerTeclado());
    }



    //Funcion para leer cadenas por teclado.
    public static String leerTeclado(){
        String entradaTeclado = "";
        Scanner entradaEscaner = new Scanner (System.in); 
        entradaTeclado = entradaEscaner.nextLine();
        return entradaTeclado;
    }

    //Funcion para limpiar la pantalla.
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  

}
 
