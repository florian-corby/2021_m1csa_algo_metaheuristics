package DataStructures;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Matrix {
    private int[][] matrix;
    private Integer matSize;

    // ================= CONSTRUCTORS ================= //
    //From file
    public Matrix(File f){ this.initFromFile(f); }

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
    public void initFromCLI(){
        Scanner scanner = new Scanner(System.in);
        this.matSize = scanner.nextInt();
        this.matrix = new int[matSize][matSize];

        //On remplit:
        for (int i = 0; i < matSize; i++) {
            for (int j = 0; j < matSize; j++)
                this.matrix[i][j] = scanner.nextInt();
        }
    }

    public void initFromFile(File f){
        try {
            Scanner scanner = new Scanner(f);
            matSize = scanner.nextInt();
            matrix = new int[matSize][matSize];

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
    public void setPrimitiveMatrix(int[][] input_matrix){
        matSize = input_matrix.length;
        matrix = new int[matSize][matSize];
        for(int i = 0; i < matSize; i++)
            System.arraycopy(input_matrix[i], 0, matrix[i], 0, matSize);
    }
    public int[][] getPrimitiveMatrix(){
        return matrix;
    }
}
