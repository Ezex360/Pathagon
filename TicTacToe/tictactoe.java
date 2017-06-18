import java.util.*;

public class tictactoe {

    public static void main(String[] args) {
        clearScreen();
        TictactoeState game = new TictactoeState();
        game.addTurn();
        int prim = move("X");
        int secn = move("Y");
        game.setBoard(prim,secn,0);
        game.addTurn();
        clearScreen();
        TictactoeSearchProblem tictactoeProblem = new TictactoeSearchProblem();
        while(!tictactoeProblem.end(game)){
            AdversarySearchEngine machine = new MinMaxAlphaBetaEngine(tictactoeProblem,Integer.parseInt(args[0]));
            game = (TictactoeState) machine.computeSuccessor(game);
            if(!tictactoeProblem.end(game)){
                System.out.println(game.toString());
                int x = move("X");
                int y = move("Y");
                game.setBoard(x,y,0);
                game.addTurn();
            }
            clearScreen();
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
 
