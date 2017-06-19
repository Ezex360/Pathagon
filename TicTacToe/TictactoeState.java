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

    public boolean setBoard(int i,int j,int move){
        boolean setted = false;
        if(i<=2 && i>=0 && j<=2 && j>=0){
            try{
                if (board[i][j] == -1){
                    turn++;
                    board[i][j] = move;
                    setted = true;
                }
            }catch(IllegalArgumentException e){}
        }
        return setted;
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
