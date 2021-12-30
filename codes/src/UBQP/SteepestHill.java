package UBQP;

import java.util.Random;

public abstract class SteepestHill {

    public static int[] run(int[][] mat, int[] initSol, int maxMoves){
        int[] res = initSol; int[] tmp;
        int nbMoves = 0; boolean stop = false;

        while(!stop && nbMoves < maxMoves){
            tmp = bestNeighb(mat, res);

            if(UBQP.f(mat, res) > UBQP.f(mat, tmp))
                res = tmp;
            else
                stop = true;

            nbMoves++;
        }

        return res;
    }

    public static int[] runWithRestarts(int[][] mat, int[] initSol, int maxMoves, int maxTrials){
        int size = initSol.length, nbTrials = 0;
        int[] randInitSol = initSol, res = new int[size], tmp;

        while(nbTrials < maxTrials){
            tmp = run(mat, randInitSol, maxMoves);

            if(UBQP.f(mat, tmp) < UBQP.f(mat, res))
                res = tmp;

            nbTrials++;

            randInitSol = UBQP.randomSolution(size, new Random());
        }

        return res;
    }

    // ================= PRIVATE AUXILIARY METHODS ================= //
    private static int[] bestNeighb(int[][] mat, int[] X){
        int min = 0, tmp, bestIdx = 0, pbSize = X.length;
        int[] res = new int[pbSize];
        System.arraycopy(X, 0, res, 0, pbSize);

        for(int i = 0; i < pbSize; i++){
            res[i] = (res[i] + 1) % 2;
            tmp = UBQP.f(mat, res);

            if(i == 0 || tmp < min) {
                min = tmp;
                bestIdx = i;
            }

            res[i] = (res[i] + 1) % 2;
        }

        res[bestIdx] = (res[bestIdx] + 1) % 2;
        return res;
    }
}
