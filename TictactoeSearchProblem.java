import java.util.*; 

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
        s.isMax()==true?move=1:move=0;
        for (int i=0;i<3;i++){
            for (j=0;j<3;j++) {
                TictactoeState newState = new TictactoeState(s);
                                
            }
        }

           


		return successors;	
    }

    public boolean success(TictactoeState s)  {
        TictactoeState ms = (TictactoeState) s;
		int x = ms.getA();
		int y = ms.getB();
		return ((x == 2) || (y == 2));
    }


	


	
	
	
}
