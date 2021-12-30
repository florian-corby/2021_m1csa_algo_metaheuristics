package Metaheuristics;


import java.util.Random;

public class SteepestHill extends MetaheuristicsAlgo {

    public SteepestHill(Solution s, Integer nbMoves){
        super(s, nbMoves);
    }

    @Override
    public void run() {
        Solution res = getSolution().clone();
        Solution tmpSolution;
        int nbMoves = 0;
        boolean stop = false;

        while(!stop && nbMoves < getNbMoves()){
            tmpSolution = res.bestNeighb();

            if(tmpSolution.isBetterNeighb(res))
                res = tmpSolution;
            else
                stop = true;

            nbMoves++;
        }

        getSolution().copy(res);
    }

    public void runWithRestarts(int maxTrials, Random rand){
        Solution res = getSolution().clone();
        int nbTrials = 0;

        while(nbTrials < maxTrials){
            run();

            if(getSolution().isBetterNeighb(res)) {
                res = getSolution().clone();
            }

            nbTrials++;
            getSolution().randomize(rand);
        }

        getSolution().copy(res);
    }
}
