/**
 * Clase que implementa los estados del juego Pathagon    
 * @author Gardiola Joaquin y Giachero Ezequiel
 * @version 0.1
 */
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.*;

public class PathagonState implements AdversarySearchState, Serializable {
    
    //Matriz para representar el tablero
    //-1 si esta vacio, 0 si la ficha es blanca y 1 si es negra
	private int[][] board; 
    private List<Pair> blocked; // Lista con los lugares bloqueados
    private int turn; //Numero de turno
    private int pieces; //Cantidad de piezas en el tablero
    private List<Pair> visited; //Se utiliza para hacer el recorrido de caminos

    /**
     * Constructor de la clase
     * @pre. true.
     * @post. Este constructor setea los turnos y cantidad de piezas en 0,
     * el tablero vacio y la lista de movimientos no validos vacia. 
     */
	public PathagonState() {
        turn = 0;
        pieces = 0;
        blocked = new ArrayList<Pair>();
        board = new int[7][7];
        for (int i=0 ;i<7 ;i++ ) {
            for (int j=0 ;j<7 ;j++ ) {
                board[i][j]=-1;                
            }
        }
    }

    /**
     * Comprueba si el turno pertenece es Min o Max
     * @pre. true.
     * @post. Si el turno es par, entonces el estado es Max.
     */
    public boolean isMax(){
        return ((turn != 0) && (turn % 2 == 0));
    }

    /**
     * Devuelve el numero de turno
     * @return turn
     */
    public int getTurn(){
        return turn;
    }

    /**
     * Devuelve el numero de piezas
     * @return pieces
     */
    public int getPieces(){
        return pieces;
    }

    /**
     * Incrementa en 1 el turno
     */
    public void addTurn(){
        turn++;
    }

    /**
     * Limpia la lista con los movimientos bloqueados.
     */
    public void cleanBlocked(){
        blocked = new ArrayList<Pair>();
    }

    /**
     * Devuelve la ficha que se encuentra en una determinada posicion
     * @param i,j representan la posicion de la ficha a devolver
     * @pre 0 <= i,j <= 6
     * @post devuelve la ficha en esa posicion.
     */
    public Integer getBoard(int i,int j){
        return board[i][j];
    }

    /**
     * Se fija si un valor esta dentro del rango de filas o columnas.
     * @param place determina una posicion de filas o columnas en el tablero
     * @pre true
     * @post true si 0 <= place <= 6
     */
    public boolean insideBoard(int place){
        return (place >= 0 && place < 7);
    }

    /**
     * Si hay una ficha colocada en el tablero, devuelve el color contrario.
     * @param i,j representan la posicion de la ficha
     * @pre 0 <= i,j < 7
     * @post int con el color contrario a la pieza.
     */
    public int counter(int i, int j){
        int res = -1;
        try{
            if(board[i][j] == 0)
                res = 1;
            else if(board[i][j] == 1)
                res = 0;
            else 
                res = -1;
        }catch(IllegalArgumentException e){System.err.println("Movimiento no valido");}
        return res;
    }

    /**
     * Elimina una ficha del tablero, la inserta en la lista de movimientos
     * no validos y resta en 1 la cantidad de piezas
     * @param i,j representan la posicion de la ficha a remover
     * @pre 0 <= i,j <= 6
     * @post true si se pudo eliminar la ficha.
     */
    public boolean removePiece(int i,int j){
        boolean removed = false;
        try{
            if(board[i][j] != -1){            
                board[i][j] = -1;
                pieces--;
                Pair rm = new Pair(i,j);
                blocked.add(rm);
                removed = true;
            }
            else
                throw new IllegalArgumentException();
        }catch(IllegalArgumentException e){System.err.println("Movimiento no valido");}
        return removed;

    }

    /**
     * Coloca una ficha en una determinada posicion y aumenta pieces en 1.
     * @param i,j representan la posicion de la ficha a insertar
     * @pre 0 <= i,j < 7 and move in {0,1}
     * @post true si se pudo insertar la ficha.
     */
    public boolean addPiece(int i,int j,int move){
        boolean inserted = false;
        if(insideBoard(i) && insideBoard(j)){
            try{
                //Insertar Ficha.
                int up = i-2;
                int down = i+2;
                int left = j-2;
                int right = j+2;
                if(board[i][j] == -1){
                    for(int n=0;n<blocked.size();n++){
                        Pair rm = blocked.get(n);
                        if (i == rm.fst() && j == rm.snd())
                            throw new IllegalArgumentException();
                    }
                    pieces++;
                    board[i][j]=move;
                    inserted = true;
                }else throw new IllegalArgumentException();
                cleanBlocked();
                //Remover piezas encerradas.
                if(insideBoard(up) && move==counter(i-1,j) && board[i][j]==board[up][j])                
                    removePiece(i-1,j);
                if(insideBoard(down) && move==counter(i+1,j) && board[i][j]==board[down][j])                
                    removePiece(i+1,j);
                if(insideBoard(left) && move==counter(i,j-1) && board[i][j]==board[i][left])                
                    removePiece(i,j-1);
                if(insideBoard(right) && move==counter(i,j+1) && board[i][j]==board[i][right])                
                    removePiece(i,j+1);
            }catch(IllegalArgumentException e){/*System.err.println("Movimiento no valido");*/}
            if (inserted)
                addTurn();
        }
        return inserted;

    }

    /** 
     * Comprueba si este estado es igual a otro pasado por parametro. 
     * @param other es el estado a comparar con 'this'.
     * @pre. other!=null.
     * @post. true si 'this' es igual al estado 'other.
     */	
	public boolean equals(AdversarySearchState other) {
		
		PathagonState e = (PathagonState) other;
        boolean same = true;
        for (int i=0 ;i<7 ;i++ ) {
            for (int j=0 ;j<7 ;j++ ) {
                if(board[i][j]!=e.getBoard(i,j))
                    same = false;
                if (!same)
                    break;          
            }
            if (!same)
                break;
            
        }
		return same;
		
	} 
	
    /** 
     * Devuelve la cadena con el tablero para mostrar por pantalla 
     * @pre. true
     * @post. String con el tablero en ese estado.
     */ 
	public String toString() {
        String print = "    0   1   2   3   4   5   6   ";
		print += "\n   ---------------------------\n";
		for (int i=0 ;i<7 ;i++ ) {
            print += i + " |";
            for (int j=0 ;j<7 ;j++ ) {
                if(board[i][j]==0)
                    print +=" 0 |";
                if(board[i][j]==1)
                    print +=" X |";
                if(board[i][j]==-1)
                    print +="   |";
            }
            print += "\n   ---------------------------\n";
        }
        if (!blocked.isEmpty()){
            print+="No se puede colocar en ";
            for(int n=0;n<blocked.size();n++)
                print+="("+blocked.get(n).fst()+","+blocked.get(n).snd()+") ";
        }
        return print;
		
	} 

    /** 
     * A partir de un par, indicando el lugar en el tablero, obtiene todas
     * las fichas adyacentes del mismo color
     * @param place indica la posicion de la pieza en el tablero
     * @param color indica el color de la pieza
     * @pre. 0 <= place.fst(),place.snd() < 7, color in {0,1}
     * @post. Lista de pares que contienen las fichas del mismo color
     * que son adyacentes a la indicada en parametro
     */  
    private List<Pair> next(Pair place, int color){
        List<Pair> ret = new ArrayList<Pair>();
        int i = place.fst();
        int j = place.snd();
        if(insideBoard(i+1) && board[i][j]==color && board[i][j]==board[i+1][j]){
            Pair down = new Pair(i+1,j);
            ret.add(down);
        }
        if(insideBoard(i-1) && board[i][j]==color && board[i][j]==board[i-1][j]){
            Pair up = new Pair(i-1,j);
            ret.add(up);
        }
        if(insideBoard(j+1) && board[i][j]==color && board[i][j]==board[i][j+1]){
            Pair right = new Pair(i,j+1);
            ret.add(right);
        }
        if(insideBoard(j-1) && board[i][j]==color && board[i][j]==board[i][j-1]){
            Pair left = new Pair(i,j-1);
            ret.add(left);
        }
        return ret;
    }

    /** 
     * A partir de un tipo de ficha recorre todos los posibles caminos
     * para saber si ese color salio victorioso.
     * @param move indica el color de la ficha a buscar el camino
     * @pre. true
     * @post. true si se encontro un camino victorioso para las fichas de un
     * determinado color
     */     
    public int searchPath(int move) {
        
        //Inicializo la lista de fichas iniciales a buscar caminos
        List<Pair> initialMoves = new ArrayList<Pair>();
        //Relleno la lista de lugares iniciales.
        for(int n=0;n<7;n++){
            if(move == 0 && board[0][n]==0){
                Pair initial = new Pair(0,n);
                initialMoves.add(initial);
            }
            else if(move == 1 && board[n][0]==1){
                Pair initial = new Pair(n,0);
                initialMoves.add(initial);
            }else if(move!=0 && move!=1)
                throw new IllegalArgumentException("Color invalido");

        }

        int result = 0;
        for (int n=0;n<initialMoves.size();n++){
            //Vacio la lista de visitados
            visited = new LinkedList<Pair>();
            if (result==7){
                break;
            }
            int temp = breadthFirst(initialMoves.get(n), move);
            if (temp>result)
                result=temp;
        }
        if(move==1)
            result = result * -1;
        return result;
        
    } 

    /** 
     * Realiza el recorrido Breadth First para encontrar un camino ganador
     * para un determinado color a partir de un lugar inicial en el tablero.
     * @param place indica la posicion inicial para realizar el recorrido
     * @param color indica el color de las fichas a recorrer
     * @pre. 0 <= place.fst(),place.snd() < 7, color in {0,1}
     * @post. true si se encontro un camino victorioso para las fichas de un
     * determinado color partiendo de places
     */  
    private int breadthFirst(Pair place, int color){
    List<Pair> queqe = new LinkedList<Pair>();
    queqe.add(place);
    int result = 0;
    while(!queqe.isEmpty()){
        Pair aux = queqe.remove(0);
        visited.add(aux);
        if (color==0 && aux.fst()==6)
            return 7;
        if (color==0 && result < aux.fst()+1)
            result = aux.fst();
        if (color==1 && aux.snd()==6)
            return 7;
        if (color==1 && result < aux.snd()+1)
             result = aux.snd();
        List<Pair> succ = next(aux,color);
        while( !succ.isEmpty() ){
            Pair child = succ.remove(0);
            if (!visited.contains(child)) 
                queqe.add(child);
        }

    }
    return result;

    }       


    /**
     * Retorna el elemento con la regla aplicada
     */
    public Integer ruleApplied(){
        return 1;
    };

	
	
} 
