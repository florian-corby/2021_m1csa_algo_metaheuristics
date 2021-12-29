package UBQP;

import DataStructures.Matrix;

import java.io.File;
import java.util.Random;

public class Main {
    private static Matrix matrix;
    private static VecSol vecSolution;
    private static int solution;
    private static final Random rand = new Random();

    private static final int MAX_DEPL = 8;
    private static final int MAX_TRIALS = 10;


    public static void main(String[] argv) {
        matrix = new Matrix(new File("../res/matrixQ.txt"));
        //matrix.initFromCLI();
        matrix.print();

        //vecSolution = ex11_randomSolution();
        vecSolution = new VecSol(new int[]{1, 1, 0, 1, 0, 0});
        System.out.println("\n");
        vecSolution.print();

        solution = ex12_f(vecSolution.getVector());
        System.out.println("f(X) = " + solution + "\n");

        vecSolution.setVector(ex14_steepestHill(vecSolution.getVector()));
        vecSolution.print();
        System.out.println("f(X) = " + ex12_f(vecSolution.getVector()) + "\n");

        ex15_steepestHillWithRestarts();
        vecSolution.print();
        System.out.println("f(X) = " + ex12_f(vecSolution.getVector()) + "\n");
    }

    public static int[] ex11_randomSolution(){
        vecSolution.setVector(new int[matrix.size()]);
        for(int i = 0; i < matrix.size(); i++){
            vecSolution.getVector()[i] = Math.abs(rand.nextInt() % 2);
        }

        return vecSolution.getVector();
    }

    public static int ex12_f(int[] X){
        solution = 0;

        for(int i = 0; i < matrix.size(); i++){
            for(int j = 0; j < matrix.size(); j++){
                solution += matrix.getMatrix()[i][j]*X[i]*X[j];
            }
        }

        return solution;
    }

    public static int[] ex13_bestNeigh(int[] X){
        int[] res = new int[matrix.size()];
        int min = 0, tmp, bestIdx = 0;

        System.arraycopy(X, 0, res, 0, matrix.size());

        for(int i = 0; i < matrix.size(); i++){
            res[i] = (res[i] + 1) % 2;
            tmp = ex12_f(res);

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

            if(ex12_f(tmpVecSol) > ex12_f(tmpVecSol2))
                tmpVecSol = tmpVecSol2;
            else
                stop = true;

            nb_depl++;
        }

        return tmpVecSol;
    }

    public static void ex15_steepestHillWithRestarts(){
        int[] randInitSol;
        int[] res;
        int nb_trials = 0;

        while(nb_trials < MAX_TRIALS){
            randInitSol = ex11_randomSolution();
            res = ex14_steepestHill(randInitSol);

            if(ex12_f(vecSolution.getVector()) > ex12_f(res))
                vecSolution.setVector(res);

            nb_trials++;
        }
    }
}
