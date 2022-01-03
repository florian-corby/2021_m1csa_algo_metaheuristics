package TSP;

import java.util.LinkedList;

public abstract class Tabu {
    // ================= MAIN METHODS ================= //
    // Methods to be used in any application involving a traveling salesman problem.

    // This method modifies the parameter citySequence in place hence discarding the previous worse solution.
    public static int[] run(City[] cities, int[] citySequence, int maxMoves, int tabuSize){
        LinkedList<int []> tabu = new LinkedList<>();
        int[] tmpBestCitySeq = null, bestNonTabuNeighb, currentCitySeq = citySequence;
        int nbMoves = 0; boolean stop = false;

        while(nbMoves < maxMoves && !stop){
            bestNonTabuNeighb = bestNonTabuNeighb(tabu, cities, currentCitySeq);
            if(bestNonTabuNeighb == null)
                stop = true;
            else{
                addTabu(currentCitySeq, tabu, tabuSize);
                currentCitySeq = bestNonTabuNeighb;

                if(isBetterNeighb(cities, currentCitySeq, tmpBestCitySeq))
                    tmpBestCitySeq = currentCitySeq;

                nbMoves++;
            }
        }

        return tmpBestCitySeq;
    }

    // ================= PUBLIC AUXILIARY METHODS ================= //
    // These methods are public for testing purposes but should be private in a production context...
    public static int[] bestNonTabuNeighb(LinkedList<int[]> tabu, City[] cities, int[] citySequence){
        LinkedList<int[]> neighbourhood = genNeighbourhood(citySequence);
        neighbourhood.removeIf(neighbour -> isTabu(tabu, neighbour));
        int[] res = null;

        for(int[] neighbour : neighbourhood){
            if(res == null || isBetterNeighb(cities, neighbour, res))
                res = neighbour;
        }

        return res;
    }

    public static LinkedList<int[]> genNeighbourhood(int[] citySeq){
        int nbCities = citySeq.length;
        LinkedList<int[]> res = new LinkedList<>();

        for(int i = 0; i < nbCities; i++) {
            for (int j = i + 1; j < nbCities; j++) {
                int[] tmp = new int[nbCities];
                swap(citySeq, i, j);
                System.arraycopy(citySeq, 0, tmp, 0, nbCities);
                res.add(tmp);
                swap(citySeq, i, j);
            }
        }
        return res;
    }

    public static boolean isTabu(LinkedList<int[]> tabu, int[] citySeq){
        boolean found = false;

        for (int[] tabuCitySeq : tabu) {
            if(vecEquals(tabuCitySeq, citySeq)) {
                found = true;
                break;
            }
        }

        return found;
    }

    public static boolean isBetterNeighb(City[] cities, int[] citySeq1, int[] citySeq2){
        return citySeq2 == null || TSP.getDistance(cities, citySeq1) <= TSP.getDistance(cities, citySeq2);
    }


    // ================= PRIVATE AUXILIARY METHODS ================= //
    // These methods are private as they should be simple enough to not be tested regularly...
    private static void addTabu(int[] citySequence, LinkedList<int[]> tabu, int tabuSize){
        if(tabu.size() > tabuSize)
            tabu.removeFirst();
        tabu.add(citySequence);
    }

    private static boolean vecEquals(int[] vec1, int[] vec2){
        boolean areEquals = true;

        for(int i = 0; i < vec1.length; i++){
            if(vec1[i] != vec2[i]){
                areEquals = false;
                break;
            }
        }

        return areEquals;
    }

    private static void swap(int[] vec, int idx1, int idx2){
        int tmp = vec[idx1];
        vec[idx1] = vec[idx2];
        vec[idx2] = tmp;
    }
}
