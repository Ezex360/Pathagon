/**
 * Clase para el manejo de pares.
 */
import java.io.*;
public class Pair implements Serializable {
    private Integer x;
    private Integer y;

    /* Pair(): Constructor of the class */	
    public Pair() {
	x = null;
	y = null;
    }

    /* Pair(Object, Object): Constructor of the class */	
    public Pair(Integer v1, Integer v2) {
	x = v1;
	y = v2;
    }

    /* ChangeFst: Updates the value of var. x */	
    public void changeFst(Integer v1) {
	x = v1;
    }

    /* ChangeSnd: Updates the value of var. y */		
    public void changeSnd(Integer v2) {
	y = v2;
    }

    /* fst: Returns the value of var. x */	
    public Integer fst() {
	return x;
    }

    /* snd: Returns the value of var. y */		
    public Integer snd() {
	return y;
    }

    //Sobre-escribe la clase equals para la comparacion de pares.
     @Override
    public boolean equals(Object other) {
        if (other == this) 
            return true;
        if(other == null || other.getClass() != this.getClass())
            return false;

        Pair p = (Pair) other;
        return this.x==p.x && this.y==p.y;
    }

} // end of class Pair
