package UBQP;

import DataStructures.Matrix;
import DataStructures.Vector;

import java.util.Random;

public class UBQP {
    private final int PB_SIZE;
    private final Matrix MATRIX;
    private Vector vec;

    // ================= CONSTRUCTORS ================= //
    public UBQP(Matrix mat){
        MATRIX = mat;
        PB_SIZE = mat.size();
        vec = new Vector(randomSolution(PB_SIZE, new Random()));
    }

    public UBQP(Matrix mat, Vector vec){
        MATRIX = mat;
        PB_SIZE = mat.size();
        this.vec = vec;
    }

    // ================= STATIC METHODS ================= //
    public static int f(int[][] mat, int[] vec){
        int solution = 0;

        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat.length; j++){
                solution += mat[i][j]*vec[i]*vec[j];
            }
        }

        return solution;
    }

    public static int[] randomSolution(int size, Random rand){
        int[] res = new int[size];
        for(int i = 0; i < size; i++){
            res[i] = Math.abs(rand.nextInt() % 2);
        }

        return res;
    }

    // ================= METHODS ================= //
    public void printSolution(){
        vec.print();
        System.out.println("f(Vector) = " + f(MATRIX.getPrimitiveMatrix(), vec.getPrimitiveVector()));
    }

    public void solve(int maxMoves, int maxTrials, String mode){
        switch(mode){
            case "noRestarts":
                System.out.println("\n>>>> BEFORE " + mode + " :");
                printSolution();
                vec = new Vector(SteepestHill.run(MATRIX.getPrimitiveMatrix(), vec.getPrimitiveVector(), maxMoves));
                System.out.println("\n>>>> AFTER " + mode + " :");
                printSolution();
                break;
            case "withRestarts":
                System.out.println("\n>>>> BEFORE " + mode + " :");
                printSolution();
                vec = new Vector(SteepestHill.runWithRestarts(MATRIX.getPrimitiveMatrix(), vec.getPrimitiveVector(), maxMoves, maxTrials));
                System.out.println("\n>>>> AFTER " + mode + " :");
                printSolution();
                break;
            default:
                System.err.println("Wrong Mode in solve() from UBQP class.");
        }
    }

    public void setVec(int[] toCopy){
        vec.setPrimitiveVector(toCopy);
    }
}
