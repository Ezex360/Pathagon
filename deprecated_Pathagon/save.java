
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
