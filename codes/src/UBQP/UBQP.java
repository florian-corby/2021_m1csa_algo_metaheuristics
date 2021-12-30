package UBQP;

import DataStructures.Matrix;


public class UBQP {
    private int size;
    private Matrix matrix;
    private VecSol vecsol;

    // ================= CONSTRUCTORS ================= //
    public UBQP(Matrix m){
        initFromMatrix(m);
    }

    public UBQP(UBQP toCopy){
        size = toCopy.size();
        getMatrix().setPrimitiveMatrix(toCopy.getMatrix().getPrimitiveMatrix());
        this.vecsol = (VecSol) toCopy.getVecsol().clone();
    }

    // ================= METHODS ================= //
    public void initFromMatrix(Matrix m){
        size = m.size();
        matrix = m;
        vecsol = new VecSol(size);
        vecsol.setUbqp(this);
    }

    public static int f(Matrix matrix, int[] vector){
        int solution = 0;

        for(int i = 0; i < matrix.size(); i++){
            for(int j = 0; j < matrix.size(); j++){
                solution += matrix.getPrimitiveMatrix()[i][j]*vector[i]*vector[j];
            }
        }

        return solution;
    }

    public Matrix getMatrix() { return matrix; }
    public VecSol getVecsol(){ return vecsol; }
    public void setVecsol(int[] vector){
        vecsol = new VecSol(vector);
        vecsol.setUbqp(this);
    }
    public void setVecsol(VecSol vecsol){ this.vecsol = vecsol; }
    public void printSolution(){
        System.out.println("f(vecsol) = " + f(matrix, vecsol.getVector()));
    }
    public int size() { return size; }
}
