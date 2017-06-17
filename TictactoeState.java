public class TictactoeState implements AdversarySearchState {
	
	private int[][] board; 
    private int turn;

	public TictactoeState() {
        turn = 1;
        board = new int[3][3];
        for (int i=0 ;i<3 ;i++ ) {
            for (int j=0 ;j<3 ;j++ ) {
                board[i][j]=-1;                
            }
            
        }
    }

    public TictactoeState(int t,int[][] b) {
        turn = t;
        board = b;
    }

    public TictactoeState(TictactoeState original) {
        this(original.turn+1,original.board);
    }


    public boolean isMax(){
        return (turn % 2 == 0);
    }

    public int getTurn(){
        return turn;
    }

    public int getBoard(int i,int j){
        return board[i][j];
    }

    public void setBoard(int i,int j,int move){
        board[i][j] = move;
    }

	
	public boolean equals(AdversarySearchState other) {
		
		TictactoeState e = (TictactoeState) other;
        boolean same = true;
        for (int i=0 ;i<3 ;i++ ) {
            for (int j=0 ;j<3 ;j++ ) {
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
	
	public String toString() {
		String print = "";
		for (int i=0 ;i<3 ;i++ ) {
            for (int j=0 ;j<3 ;j++ ) {
                if(board[i][j]==0)
                    print +=" 0 ";
                if(board[i][j]==-1)
                    print +=" X ";
                else
                    print +="   ";
            }
            print += "\n";
        }
        return print;
		
	} 

    public Integer ruleApplied(){
        return 1;
    };

	
	
} 
