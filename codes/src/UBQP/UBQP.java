package UBQP;

import DataStructures.Matrix;
import DataStructures.Vector;

import java.util.Random;

public class UBQP {
    private final Matrix MAT;
    private Vector vec;

    // ================= CONSTRUCTORS ================= //
    public UBQP(Matrix mat){
        MAT = mat;
        vec = new Vector(randomSolution(mat.size(), new Random()));
    }

    public UBQP(Matrix mat, Vector vec){
        MAT = mat;
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
        System.out.println("f(Vector) = " + f(MAT.getPrimitiveMatrix(), vec.getPrimitiveVector()));
    }

    public void solve(int maxMoves, int maxTrials, int tabuSize, String mode){
        switch(mode){
            case "noRestarts":
                System.out.println("\n>>>> BEFORE " + mode + " :");
                printSolution();
                vec = new Vector(SteepestHill.run(MAT.getPrimitiveMatrix(), vec.getPrimitiveVector(), maxMoves));
                System.out.println("\n>>>> AFTER " + mode + " :");
                printSolution();
                break;
            case "withRestarts":
                System.out.println("\n>>>> BEFORE " + mode + " :");
                printSolution();
                vec = new Vector(SteepestHill.runWithRestarts(MAT.getPrimitiveMatrix(), vec.getPrimitiveVector(), maxMoves, maxTrials));
                System.out.println("\n>>>> AFTER " + mode + " :");
                printSolution();
                break;
            case "tabu":
                System.out.println("\n>>>> BEFORE " + mode + " :");
                printSolution();
                vec = new Vector(Tabu.run(MAT.getPrimitiveMatrix(), vec.getPrimitiveVector(), maxMoves, tabuSize));
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
