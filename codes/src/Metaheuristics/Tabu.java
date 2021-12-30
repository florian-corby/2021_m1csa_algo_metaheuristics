package Metaheuristics;

import java.util.LinkedList;

public class Tabu extends MetaheuristicsAlgo {
    private final LinkedList<Solution> tabu;
    private final int TABUSIZE;
    private Solution tmpBestSol;

    public Tabu(Solution s, Integer nbMoves, int tabuSize){
        super(s, nbMoves);
        this.TABUSIZE = tabuSize;
        tabu = new LinkedList<>();
    }

    @Override
    public void run() {
//        int nbMoves = 0;
//        boolean stop = false;
//
//        while(nbMoves < getNbMoves() && !stop){
//            if(notTabuNeighbours(getSolution()) == null)
//                stop = true;
//            else{
//                tabu.add(getSolution());
//                if(tabu.size() > TABUSIZE)
//                    tabu.removeFirst();
//
//                setSolution(bestNotTabu(getSolution().genNeighbourhood()));
//                if(getSolution().isBetterNeighb(tmpBestSol)){
//                    tmpBestSol = getSolution().clone();
//                    nbMoves++;
//                }
//            }
//        }
//
//        return tmpBestSol;
    }

    public boolean isTabu(Solution neighbour){
        for (Solution s : tabu) {
            if(s == neighbour)
                return true;
        }

        return false;
    }

    public Solution[] notTabuNeighbours(Solution s){
        Solution[] neighbourhood = s.genNeighbourhood();
        Solution[] res = new Solution[neighbourhood.length];
        int counter = 0;

        for(Solution solution : neighbourhood){
            if(!isTabu(solution)) {
                res[counter] = solution;
                counter++;
            }
        }

        return res;
    }

    // Neighbourhood must be ordered for this to work!
    public Solution bestNotTabu(Solution[] neighbourhood){
        for (Solution solution : neighbourhood) {
            if (!isTabu(solution))
                return solution;
        }
        return null;
    }
}
