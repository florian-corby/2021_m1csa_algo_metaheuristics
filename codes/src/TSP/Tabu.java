package TSP;

import java.util.LinkedList;

public abstract class Tabu {

    public static int[] run(City[] cities, int[] citySequence, int maxMoves, int tabuSize){
        LinkedList<int []> tabu = new LinkedList<>();
        int nbMoves = 0; int[] tmpBestSol = null;
        boolean stop = false; int[] currentSol = citySequence;

        while(nbMoves < maxMoves && !stop){
            if(bestNonTabuNeighb(tabu, cities, citySequence) == null)
                stop = true;
            else{
                addTabu(currentSol, tabu, tabuSize);
                currentSol = bestNonTabuNeighb(tabu, cities, currentSol);

                if(isBetterNeighb(cities, currentSol, tmpBestSol))
                    tmpBestSol = currentSol;

                nbMoves++;
            }
        }

        return tmpBestSol;
    }

    // ================= PRIVATE AUXILIARY METHODS ================= //
    private static void addTabu(int[] citySequence, LinkedList<int[]> tabu, int tabuSize){
        if(tabu.size() > tabuSize)
            tabu.removeFirst();
        tabu.add(citySequence);
    }

    private static int[] bestNonTabuNeighb(LinkedList<int[]> tabu, City[] cities, int[] citySequence){
        LinkedList<int[]> neighbourhood = genNeighbourhood(citySequence);
        neighbourhood.removeIf(neighbour -> isTabu(tabu, neighbour));
        int[] res = null;

        for(int[] neighbour : neighbourhood){
            if(res == null || isBetterNeighb(cities, neighbour, res))
                res = neighbour;
        }

        return res;
    }

    private static LinkedList<int[]> genNeighbourhood(int[] citySeq){
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

    private static boolean isTabu(LinkedList<int[]> tabu, int[] citySeq){
        boolean found = false;

        for (int[] tabuCitySeq : tabu) {
            if(vecEquals(tabuCitySeq, citySeq)) {
                found = true;
                break;
            }
        }

        return found;
    }

    private static boolean isBetterNeighb(City[] cities, int[] citySeq1, int[] citySeq2){
        return citySeq2 == null || TSP.getDistance(cities, citySeq1) <= TSP.getDistance(cities, citySeq2);
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
