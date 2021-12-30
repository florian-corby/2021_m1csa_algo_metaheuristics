package UBQP;

import DataStructures.Matrix;
import DataStructures.Vector;

import java.util.Random;

public class UBQP {
    private Random rand = new Random();
    private final int PB_SIZE;
    private final Matrix MATRIX;
    private Vector vec;

    // ================= CONSTRUCTORS ================= //
    public UBQP(Matrix mat){
        MATRIX = mat;
        PB_SIZE = mat.size();
        vec = new Vector(randomSolution(rand));
    }

    public UBQP(Matrix mat, Vector vec){
        MATRIX = mat;
        PB_SIZE = mat.size();
        this.vec = vec;
    }

    // ================= METHODS ================= //
    public int[] randomSolution(Random rand){
        int[] res = new int[PB_SIZE];
        for(int i = 0; i < PB_SIZE; i++){
            res[i] = Math.abs(rand.nextInt() % 2);
        }

        return res;
    }

    public static int f(int[][] mat, int[] vec){
        int solution = 0;

        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat.length; j++){
                solution += mat[i][j]*vec[i]*vec[j];
            }
        }

        return solution;
    }

    public void printSolution(int[] X){ System.out.println("f(Vector) = " + f(MATRIX.getPrimitiveMatrix(), X)); }

    public void solve(int maxMoves, int maxTrials, String mode){
        switch(mode){
            case "noRestarts":
                System.out.println("\n>>>> BEFORE " + mode + " :");
                vec.print();
                printSolution(vec.getPrimitiveVector());

                vec = new Vector(steepestHill(vec.getPrimitiveVector(), maxMoves));

                System.out.println("\n>>>> AFTER " + mode + " :");
                vec.print();
                printSolution(vec.getPrimitiveVector());
                break;
            case "withRestarts":
                System.out.println("\n>>>> BEFORE " + mode + " :");
                vec.print();
                printSolution(vec.getPrimitiveVector());

                vec = new Vector(steepestHillWithRestarts(maxMoves, maxTrials));

                System.out.println("\n>>>> AFTER " + mode + " :");
                vec.print();
                printSolution(vec.getPrimitiveVector());
                break;
            default:
                System.err.println("Wrong Mode in solve() from UBQP class.");
        }
    }

    // ================= METAHEURISTICS ================= //
    public int[] bestNeighb(int[] X){
        int min = 0, tmp, bestIdx = 0;
        int[] res = new int[PB_SIZE];
        System.arraycopy(X, 0, res, 0, PB_SIZE);

        for(int i = 0; i < PB_SIZE; i++){
            res[i] = (res[i] + 1) % 2;
            tmp = f(MATRIX.getPrimitiveMatrix(), res);

            if(i == 0 || tmp < min) {
                min = tmp;
                bestIdx = i;
            }

            res[i] = (res[i] + 1) % 2;
        }

        res[bestIdx] = (res[bestIdx] + 1) % 2;
        return res;
    }

    public int[] steepestHill(int[] initSol, int maxMoves){
        int[] res = initSol;
        int[] tmp;
        int nb_depl = 0;
        boolean stop = false;

        while(!stop && nb_depl < maxMoves){
            tmp = bestNeighb(res);

            if(f(MATRIX.getPrimitiveMatrix(), res) > f(MATRIX.getPrimitiveMatrix(), tmp))
                res = tmp;
            else
                stop = true;

            nb_depl++;
        }

        return res;
    }

    public int[] steepestHillWithRestarts(int maxMoves, int maxTrials){
        int[] randInitSol;
        int[] res = new int[PB_SIZE];
        int nbTrials = 0;

        while(nbTrials < maxTrials){
            randInitSol = randomSolution(rand);
            res = steepestHill(randInitSol, maxMoves);

            if(f(MATRIX.getPrimitiveMatrix(), randInitSol) < f(MATRIX.getPrimitiveMatrix(), res))
                res = randInitSol;

            nbTrials++;
        }

        return res;
    }
}
