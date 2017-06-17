/**
 * Clase que implementa el problema de busqueda adversaria para el
 * juego Pathagon, obteniendo una IA que permita jugar a este.    
 * @author Gardiola Joaquin y Giachero Ezequiel
 * @version 0.1
 */

import java.util.*; //VER SI HAY QUE ELIMINAR
import java.io.*;

public class PathagonProblem implements AdversarySearchProblem<PathagonState> {
	
	//Conserva el estado inicial para la busqueda adversaria
    private PathagonState initial;    
    
    /**
     * Constructor de la clase
     * @pre. true.
     * @post. Este constructor inicializa initial con un nuevo PathagonState
     */
	public PathagonProblem() {
        initial = new PathagonState();
    } 

    /**
     * Constructor de la clase
     * @param initial indica el PathagonState para iniciar el problema
     * @pre. true.
     * @post. Este constructor inicializa initial con un PathagonState
     */
    public PathagonProblem(PathagonState initial) {
        this.initial = initial;
    } 

    /**
     * Devuelve el estado inicial
     * @return initial
     */	
	public PathagonState initialState() {
		return initial;
    } 

    /**
     * A partir de un estado del juego, obtiene la lista con todos los
     * estados posibles que le sucedan al mismo.
     * @param s Estado a partir del cual calcular los sucesores
     * @pre true
     * @post Lista con todos los sucesores validos del estado s
     */
	public List<PathagonState> getSuccessors(PathagonState s) {
		
		List<PathagonState> successors = new LinkedList<PathagonState>();
		int move;
        if(s.isMax())
            move=0;
        else
            move=1;
        for (int i=0;i<7;i++){
            for (int j=0;j<7;j++) {
                PathagonState newState = (PathagonState) deepClone(s);
                if(newState.addPiece(i,j,move)){
                    successors.add(newState);
                    //System.out.println(newState.toString());
                }                
            }
        }  
		return successors;	
    }

    /**
     * Comprueba si existe un camino de fichas iguales desde un borde del
     * tablero hacia otro, y que este camino este conectado horizontal y
     * verticalmente para saber si hubo un equipo ganador
     * @param s Estado a partir del cual comprobar si hay un ganador
     * @pre true
     * @post true si hubo un ganador en ese estado
     */
    public boolean win(PathagonState state, int color){
        return state.searchPath(color);    

    }

    /**
     * Chequea si se colocaron todas las fichas en el tablero o si
     * hubo un ganador.
     * @param s Estado a comprobar si termino el juego
     * @pre true
     * @post true si termino el juego.
     */
    public boolean end(PathagonState state){
        return ((state.getPieces() == 28) || win(state,0) || win(state,1)) ;

    }

    /**
     * Evalua un estado y devuelve un entero con el valor aproximado
     * del mismo. Mientras mas pequeño es el valor, mayor es la
     * posibilidad de ganar para la IA
     * @param s Estado a comprobar si termino el juego
     * @pre true
     * @post true si termino el juego.
     */    
    public int value(PathagonState state){
        int val;
        int color; 
        if (state.isMax() && win(state,1)){
            System.out.println("La IA ha ganado...");
            return minValue();
        }
        else if (!state.isMax() && win(state,0)){
            return maxValue();
        }
        else
            return 0;
    }

    /**
     * Retorna el valor minimo posible de un estado
     * para el PathagonProblem
     */ 
    public int minValue(){
        return -50;
    }

    /**
     * Retorna el valor minimo posible de un estado
     * para el PathagonProblem
     */ 
    public int maxValue(){
        return 50;
    }

    /**
    * This method makes a "deep clone" of any Java object it is given.
    */
    public static Object deepClone(Object object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
