package TSP;

import DataStructures.Vector;

public abstract class SteepestHill {
    public static int[] run(City[] cities, int[] citySequence, int maxMoves){
        int[] tmpVecSol;
        int[] res = Vector.clonePrimitiveVector(citySequence);
        int nbMoves = 0; boolean stop = false;

        while(!stop && nbMoves < maxMoves){
            tmpVecSol = bestNeighb(cities, res);

            if(TSP.getDistance(cities, res) > TSP.getDistance(cities, tmpVecSol))
                res = tmpVecSol;
            else
                stop = true;

            nbMoves++;
        }

        return res;
    }

    public static int[] runWithRestarts(City[] cities,  int[] citySequence, int maxMoves, int maxTrials){
        int nbCities = cities.length, nbTrials = 0;
        int[] randInitSol = TSP.getRandVec(nbCities).getPrimitiveVector(), res = citySequence, tmp;

        while(nbTrials < maxTrials){
            tmp = run(cities, randInitSol, maxMoves);

            if(TSP.getDistance(cities, tmp) < TSP.getDistance(cities, res))
                res = tmp;

            nbTrials++;

            randInitSol = TSP.getRandVec(nbCities).getPrimitiveVector();
        }

        return res;
    }

    public static int[] bestNeighb(City[] cities, int[] citySequence){
        int nbCities = cities.length;
        int[] tmpVecSol = Vector.clonePrimitiveVector(citySequence);
        int[] res = new int[nbCities];
        double min = 0, tmp;

        for(int i = 0; i < nbCities; i++){
            for(int j = i+1; j < nbCities; j++){
                swap(tmpVecSol, i, j);
                tmp = TSP.getDistance(cities, tmpVecSol);
                if((i == 0 && j == 1) || tmp < min) {
                    min = tmp;
                    System.arraycopy(tmpVecSol, 0, res, 0, nbCities);
                }
                swap(tmpVecSol, i, j);
            }
        }

        return res;
    }

    // ================= PRIVATE AUXILIARY METHODS ================= //
    private static void swap(int[] vec, int idx1, int idx2){
        int tmp = vec[idx1];
        vec[idx1] = vec[idx2];
        vec[idx2] = tmp;
    }
}
