package UBQP;

import DataStructures.Matrix;

import java.io.File;
import java.util.Random;

public class Main {
    private static UBQP ubqp;
    private static final Random rand = new Random();

    private static final int MAX_DEPL = 8;
    private static final int MAX_TRIALS = 10;


    public static void main(String[] argv) {
        Matrix matrix = new Matrix(new File("../res/matrixQ.txt"));
        //Matrix matrix = matrix.initFromCLI();
        matrix.print();

        System.out.println("\n ##### TESTING UBQP CLASS ##### ");
        ubqp = new UBQP(matrix);
        ubqp.setVecsol(new int[]{1, 1, 0, 1, 0, 0});
        ubqp.getVecsol().print();
        ubqp.printSolution();

        System.out.println("\n ##### TESTING STEEPEST HILL ALGORITHM ##### ");
        ubqp.setVecsol(ex14_steepestHill(ubqp.getVecsol().getVector()));
        ubqp.printSolution();

        System.out.println("\n ##### TESTING STEEPEST HILL WITH RESTARTS ##### ");
        ex15_steepestHillWithRestarts();
        ubqp.getVecsol().print();
        ubqp.printSolution();
    }

    public static int[] ex13_bestNeigh(int[] X){
        int[] res = new int[ubqp.size()];
        int min = 0, tmp, bestIdx = 0;

        System.arraycopy(X, 0, res, 0, ubqp.size());

        for(int i = 0; i < ubqp.size(); i++){
            res[i] = (res[i] + 1) % 2;
            tmp = UBQP.f(ubqp.getMatrix(), res);

            if(i == 0 || tmp < min) {
                min = tmp;
                bestIdx = i;
            }

            res[i] = (res[i] + 1) % 2;
        }

        res[bestIdx] = (res[bestIdx] + 1) % 2;
        return res;
    }

    public static int[] ex14_steepestHill(int[] initSol){
        int[] tmpVecSol = initSol;
        int[] tmpVecSol2;
        int nb_depl = 0;
        boolean stop = false;
        
        while(!stop && nb_depl < MAX_DEPL){
            tmpVecSol2 = ex13_bestNeigh(tmpVecSol);

            if(UBQP.f(ubqp.getMatrix(), tmpVecSol) > UBQP.f(ubqp.getMatrix(), tmpVecSol2))
                tmpVecSol = tmpVecSol2;
            else
                stop = true;

            nb_depl++;
        }

        return tmpVecSol;
    }

    public static void ex15_steepestHillWithRestarts(){
        int[] res;
        int nb_trials = 0;

        while(nb_trials < MAX_TRIALS){
            ubqp.setRandVecsol(rand);
            res = ex14_steepestHill(ubqp.getVecsol().getVector());

            if(UBQP.f(ubqp.getMatrix(), ubqp.getVecsol().getVector()) > UBQP.f(ubqp.getMatrix(), res))
                ubqp.setVecsol(res);

            nb_trials++;
        }
    }
}
