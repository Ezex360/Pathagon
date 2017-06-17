package examples.jugs;


import java.util.*;

import framework.DepthFirstEngine;

public class appSearch {

    public static void main(String[] args) {
    
        
        if ((args.length > 2)||(args.length == 0)) {
	    System.out.println("*** Usage: java appSearch <int> <int>");
	}
	else {
	    int width = Integer.parseInt(args[0]);
	    int height = Integer.parseInt(args[1]);
	    JugsSearchProblem p = new JugsSearchProblem(width,height); 
	    
	    DepthFirstEngine<JugsState> engine2 = new DepthFirstEngine<JugsState>(p);
		boolean success = engine2.performSearch();
		System.out.println();	    	    
		System.out.println("*** Result using depth-first search ***");
		System.out.println("Solution found? " + success);
		if (success) {
			System.out.print("Path to goal: ");
			List<JugsState> path = engine2.getPath();
			for (int i=0; i<path.size(); i++) {
				JugsState current = path.get(i);
				System.out.print(current.toString());
			}
			System.out.println();
		}
		engine2.report();
	    

	}
    
    } // end of main
    
} // end of appSearch
