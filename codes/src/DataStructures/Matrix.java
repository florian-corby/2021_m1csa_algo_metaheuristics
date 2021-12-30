package DataStructures;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class Matrix {
    private int[][] matrix;
    private Integer matSize;

    // ================= CONSTRUCTORS ================= //
    //From file
    public Matrix(File f, int nbSkips){ this.initFromFile(f, nbSkips); }

    //From CLI
    public Matrix(InputStream in, int nbSkips){ this.initFromCLI(in, nbSkips); }

    //From size
    public Matrix(int size){
        matSize = size;
        matrix = new int[matSize][matSize];
    }

    //From primitive matrix array
    public Matrix(int[][] input_matrix){
        setPrimitiveMatrix(input_matrix);
    }


    // ================= METHODS ================= //
    public void initFromCLI(InputStream in, int nbSkips){
        Scanner scanner = new Scanner(in);
        matSize = scanner.nextInt();
        matrix = new int[matSize][matSize];

        for(int i = 0; i < nbSkips; i++)
            scanner.nextInt();

        //On remplit:
        for (int i = 0; i < matSize; i++) {
            for (int j = 0; j < matSize; j++)
                matrix[i][j] = scanner.nextInt();
        }
    }

    public void initFromFile(File f, int nbSkips){
        try {
            Scanner scanner = new Scanner(f);
            matSize = scanner.nextInt();
            matrix = new int[matSize][matSize];

            for(int i = 0; i < nbSkips; i++)
                scanner.nextInt();

            //On remplit:
            for (int i = 0; i < matSize; i++) {
                for (int j = 0; j < matSize; j++)
                    matrix[i][j] = scanner.nextInt();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void print(){
        System.out.println("===================================================== ");
        System.out.println("Matrix #" + this.toString() + " has length: " + matSize);
        System.out.println("Matrix #" + this.toString() + " contains: ");
        for(int line = 0; line < matSize; line++){
            for(int col = 0; col < matSize; col++){
                System.out.printf("% 5d ", matrix[line][col]);
            }
            System.out.println();
        }
        System.out.println("===================================================== ");
    }

    public Integer size(){ return matSize; }
    public static int[][] clonePrimitiveMatrix(int[][] input_matrix){
        int size = input_matrix.length;
        int[][] res = new int[size][size];
        for(int i = 0; i < size; i++)
            System.arraycopy(input_matrix[i], 0, res[i], 0, size);
        return res;
    }
    public void setPrimitiveMatrix(int[][] input_matrix){
        matSize = input_matrix.length;
        matrix = clonePrimitiveMatrix(input_matrix);
    }
    public int[][] getPrimitiveMatrix(){
        return clonePrimitiveMatrix(matrix);
    }
}
