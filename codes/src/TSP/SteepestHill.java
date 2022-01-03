package TSP;

import DataStructures.Vector;

public abstract class SteepestHill {
    // ================= MAIN METHODS ================= //
    // Methods to be used in any application involving a traveling salesman problem.
    public static int[] runWithRestarts(City[] cities,  int[] citySequence, int maxMoves, int maxTrials){
        int nbCities = cities.length, nbTrials = 0;
        int[] randCitySeq = TSP.getRandVec(nbCities).getPrimitiveVector(), res = citySequence, tmpCitySeq;

        while(nbTrials < maxTrials){
            tmpCitySeq = run(cities, randCitySeq, maxMoves);

            if(TSP.getDistance(cities, tmpCitySeq) < TSP.getDistance(cities, res))
                res = tmpCitySeq;

            nbTrials++;
            randCitySeq = TSP.getRandVec(nbCities).getPrimitiveVector();
        }

        return res;
    }

    // ================= PUBLIC AUXILIARY METHODS ================= //
    // These methods are public for testing purposes but should be private in a production context...
    public static int[] bestNeighb(City[] cities, int[] citySequence){
        int nbCities = cities.length;
        int[] res = new int[nbCities];
        double min = 0, tmp;

        for(int i = 0; i < nbCities; i++){
            for(int j = i+1; j < nbCities; j++){
                swap(citySequence, i, j);
                tmp = TSP.getDistance(cities, citySequence);
                if((i == 0 && j == 1) || tmp < min) {
                    min = tmp;
                    System.arraycopy(citySequence, 0, res, 0, nbCities);
                }
                swap(citySequence, i, j);
            }
        }

        return res;
    }

    public static int[] run(City[] cities, int[] citySequence, int maxMoves){
        int[] tmpCitySeq, res = citySequence;
        int nbMoves = 0; boolean stop = false;

        while(!stop && nbMoves < maxMoves){
            tmpCitySeq = bestNeighb(cities, res);

            if(TSP.getDistance(cities, tmpCitySeq) < TSP.getDistance(cities, res))
                res = tmpCitySeq;
            else
                stop = true;

            nbMoves++;
        }

        return res;
    }

    // ================= PRIVATE AUXILIARY METHODS ================= //
    // These methods are private as they should be simple enough to not be tested regularly...
    private static void swap(int[] vec, int idx1, int idx2){
        int tmp = vec[idx1];
        vec[idx1] = vec[idx2];
        vec[idx2] = tmp;
    }
}
