package UBQP;

import java.util.LinkedList;

public abstract class Tabu {

    public static int[] run(int[][] mat, int[] initSol, int maxMoves, int tabuSize){
        LinkedList<int []> tabu = new LinkedList<>();
        int nbMoves = 0; int[] tmpBestSol = new int[initSol.length];
        boolean stop = false; int[] currentSol = initSol;

        while(nbMoves < maxMoves && !stop){
            if(!hasNonTabuNeighb(tabu, currentSol))
                stop = true;
            else{
                addTabu(currentSol, tabu, tabuSize);
                currentSol = bestNonTabuNeighb(mat, currentSol);

                if(isBetterNeighb(mat, currentSol, tmpBestSol))
                    tmpBestSol = currentSol;

                nbMoves++;
            }
        }

        return tmpBestSol;
    }

    // ================= PRIVATE AUXILIARY METHODS ================= //
    private static void addTabu(int[] vec, LinkedList<int[]> tabu, int tabuSize){
        if(tabu.size() > tabuSize)
            tabu.removeFirst();
        tabu.add(vec);
    }

    private static int[] bestNonTabuNeighb(int[][] mat, int[] vec){
        LinkedList<int[]> neighbourhood = genNeighbourhood(vec);
        int[] res = new int[vec.length];

        for(int[] neighbour : neighbourhood){
            if(res == null || isBetterNeighb(mat, neighbour, res))
                res = neighbour;
        }

        return res;
    }

    private static LinkedList<int[]> genNeighbourhood(int[] vec){
        LinkedList<int[]> res = new LinkedList<>();

        for(int i = 0; i < vec.length; i++){
            int[] tmp = new int[vec.length];
            System.arraycopy(vec, 0, tmp, 0, vec.length);
            tmp[i] = (tmp[i]+1)%2;
            res.add(tmp);
        }

        return res;
    }

    private static boolean hasNonTabuNeighb(LinkedList<int[]> tabu, int[] vec){
        boolean found = false;

        for (int[] tabuVec : tabu) {
            if(vecEquals(tabuVec, vec)) {
                found = true;
                break;
            }
        }

        return found;
    }

    private static boolean isBetterNeighb(int[][] mat, int[] vec1, int[] vec2){
        return UBQP.f(mat, vec1) <= UBQP.f(mat, vec2);
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
}
