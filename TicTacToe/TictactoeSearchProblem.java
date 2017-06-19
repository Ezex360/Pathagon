import java.util.*;
import java.io.*;

public class TictactoeSearchProblem implements AdversarySearchProblem<TictactoeState> {
	
	
    private TictactoeState initial;    
    
	public TictactoeSearchProblem() {
        initial = new TictactoeState();
    } 

    public TictactoeSearchProblem(TictactoeState initial) {
        this.initial = initial;
    } 
	
	public TictactoeState initialState() {
		return initial;
    } 

	public List<TictactoeState> getSuccessors(TictactoeState s) {
		
		List<TictactoeState> successors = new LinkedList<TictactoeState>();
		int move;
        if(s.isMax())
            move=1;
        else
            move=0;
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++) {
                TictactoeState newState = (TictactoeState) deepClone(s);
                if(newState.setBoard(i,j,move)){
                    successors.add(newState);
                    //System.out.println(newState.toString());
                }                
            }
        }  
		return successors;	
    }

    public boolean win(TictactoeState state){
        boolean winner = false;
        for(int i=0;i<3;i++){
            if(winner)
                break;
            if(state.getBoard(i,1) != -1 &&
               state.getBoard(i,0).equals(state.getBoard(i,1)) && 
               state.getBoard(i,1).equals(state.getBoard(i,2)))
                winner = true;
        }
        for(int j=0;j<3;j++){
            if(winner)
                break;
            if(state.getBoard(1,j) != -1 &&
               state.getBoard(0,j).equals(state.getBoard(1,j)) && 
               state.getBoard(1,j).equals(state.getBoard(2,j)))
                winner = true;
        }
        if(!winner){
            if(state.getBoard(1,1) != -1 &&
               state.getBoard(0,0).equals(state.getBoard(1,1)) && 
               state.getBoard(1,1).equals(state.getBoard(2,2)))
                winner=true;
            else if(state.getBoard(1,1) != -1 &&
                    state.getBoard(0,2).equals(state.getBoard(1,1)) && 
                    state.getBoard(1,1).equals(state.getBoard(2,0)))
                winner=true; 
        }
        return winner;    

    }

    public boolean end(TictactoeState state){
        return ((state.getTurn() == 9) || win(state));

    }

    public int value(TictactoeState state){
        int val;
        boolean anyWinner = win(state);
        if(anyWinner){
            if(state.isMax())
                val = -10;
            else
                val = 10;
        }else{
            val = 0;
        }
        return val;
    }

    public int minValue(){
        return -10;
    }

    public int maxValue(){
        return 10;
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
