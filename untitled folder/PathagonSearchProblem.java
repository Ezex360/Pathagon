package examples.jugs;
/**
 * Title:        JugsSearchProblem<p>
 * Description:  class describing the basics of the two jugs problem as a search
 problem. It extends the abstract class AbstractSearchProblem. <p>
 * Copyright:    Copyright (c) Nazareno Aguirre 2003,2010<p>
 * Company:      None<p>
 * @author Nazareno Aguirre
 * @version 0.3
 */

import java.util.*; 

import framework.AbstractSearchProblem;

public class JugsSearchProblem implements AbstractSearchProblem<JugsState> {
	
	
    private JugsState initial; // stores the initial state for the problem
	                           // which can be set when via a constructor.
    
    /** Constructor of class JugsSearchProblem. It initialises the contents of
	 * the two jugs with the provided parameters.
	 * @param x is the value to set the contents of jug A.
	 * @param y is the value to set the contents of jug B.
	 * @pre.  0<=x<=4 and 0<=y<=3.
	 * @post. It sets the initial state for the two jugs problem to be (x,y).
	 */
	public JugsSearchProblem(int x, int y) {
		
        initial = new JugsState(x,y);
		
    } 
	
	
    /** Returns the initial state for this instance of the Jugs Problem.
	 * @return the initial state for the instance of the problem.
	 * @pre. true.
	 * @post. The initial state for the instance of the Jugs Problem is returned.
	 */	
    public JugsState initialState() {
		
		return initial;
		
    } 
    

    /** Returns the list of successors of a given state, for the Jugs Problem.
	 * @param s is the state for which the successors are being computed.
	 * @return the list of successors of a state, for the Jugs Problem.
	 * @pre. true.
	 * @post. The list of successors of s is returned. 
	 */	    
	public List<JugsState> getSuccessors(JugsState s) {
		
		List<JugsState> successors = new LinkedList<JugsState>(); // list for storing the successors of s
		JugsState js = (JugsState) s; 
		int x = js.getA();
		int y = js.getB();
		// we empty the contents of jug A
		successors.add(0, new JugsState(0,y));
		// we empty the contents of jug B
		successors.add(0, new JugsState(x,0));
		// we fill jug A
		successors.add(0, new JugsState(4,y));
		// we fill jug B
		successors.add(0, new JugsState(x,3));
		// we pour contents of A in B
		if ((x>0)&&(y<3)) {
			int curr_x = x;
			int curr_y = y;
			while ((curr_x>0)&&(curr_y<3)) {
				curr_y++;
				curr_x--;
			}   
			successors.add(0, new JugsState(curr_x,curr_y));
		}
		// we pour contents of B in A
		if ((y>0)&&(x<4)) {
			int curr_x = x;
			int curr_y = y;
			while ((curr_y>0)&&(curr_x<4)) {
				curr_x++;
				curr_y--;
			}   
			successors.add(0, new JugsState(curr_x,curr_y));
		}
		
		return successors;	
    } // end of getSuccessors
    
	
    /** Checks whether a given state is a success state, for the Jugs Problem.
	 * @param s is the state to be analysed.
	 * @return true iff s is a successful state
	 * @pre. true.
	 * @post. True is returned iff s.A equals 2. 
	 */	   
    public boolean success(JugsState s)  {
        JugsState ms = (JugsState) s;
		int x = ms.getA();
		int y = ms.getB();
		return ((x == 2) || (y == 2));
    } // end of success


	


	
	
	
} // end of class JugsSearchProblem
