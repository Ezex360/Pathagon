import java.io.*;
public class TictactoeState implements AdversarySearchState, Serializable {
	
	private int[][] board; 
    private int turn;

	public TictactoeState() {
        turn = 0;
        board = new int[3][3];
        for (int i=0 ;i<3 ;i++ ) {
            for (int j=0 ;j<3 ;j++ ) {
                board[i][j]=-1;                
            }
            
        }
    }

    public TictactoeState(int t,int[][] b) {
        this.turn = t;
        this.board = b;
    }

    public TictactoeState(TictactoeState original) {
        this(original.turn+1,original.board);
    }


    public boolean isMax(){
        return ((turn != 0) && (turn % 2 == 0));
    }

    public int getTurn(){
        return turn;
    }

    public void addTurn(){
        turn++;
    }

    public Integer getBoard(int i,int j){
        return board[i][j];
    }

    public void setBoard(int i,int j,int move){
        if (board[i][j] == -1)
            board[i][j] = move;
        else
            throw new IllegalArgumentException("Movimiento invalido");
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
		String print = "\n--------------\n|";
		for (int i=0 ;i<3 ;i++ ) {
            for (int j=0 ;j<3 ;j++ ) {
                if(board[i][j]==0)
                    print +=" 0 |";
                if(board[i][j]==1)
                    print +=" X |";
                if(board[i][j]==-1)
                    print +="   |";
            }
            print += "\n--------------\n";
            if(i!=2)
                print += "|";
        }
        return print;
		
	} 

    public Integer ruleApplied(){
        return 1;
    };

	
	
} 
