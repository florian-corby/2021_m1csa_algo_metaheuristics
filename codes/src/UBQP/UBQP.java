package UBQP;

import DataStructures.Matrix;

import java.util.Random;

public class UBQP {
    private int size;
    private Matrix matrix;
    private VecSol vecsol;
    private int solution;

    // ================= CONSTRUCTORS ================= //
    public UBQP(Matrix m){ initFromMatrix(m); }

    // ================= METHODS ================= //
    public void initFromMatrix(Matrix m){
        size = m.size();
        matrix = m;
        vecsol = new VecSol(size);
    }

    public static int f(Matrix matrix, int[] X){
        int solution = 0;

        for(int i = 0; i < matrix.size(); i++){
            for(int j = 0; j < matrix.size(); j++){
                solution += matrix.getMatrix()[i][j]*X[i]*X[j];
            }
        }

        return solution;
    }

    public Matrix getMatrix() { return matrix; }
    public VecSol getVecsol(){ return vecsol; }
    public void setVecsol(int[] vecsol){
        this.vecsol = new VecSol(vecsol);
        setSolution();
    }
    public void setRandVecsol(Random rand) {
        vecsol = new VecSol(size, rand);
        setSolution();
    }
    public void setSolution(){ solution = f(matrix, vecsol.getVector()); }
    public int getSolution(){ return solution; }
    public void printSolution(){ System.out.println("f(vecsol) = " + solution); }
    public int size() { return size; }
}
