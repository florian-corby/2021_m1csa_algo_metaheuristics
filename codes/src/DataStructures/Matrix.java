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

    //With nothing (cf. later initialization)
    public Matrix(){

    }

    //With primitive matrix array
    public Matrix(int[][] input_matrix){ this.setMatrix(input_matrix); }


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
            this.matSize = scanner.nextInt();
            this.matrix = new int[matSize][matSize];

            //On remplit:
            for (int i = 0; i < matSize; i++) {
                for (int j = 0; j < matSize; j++)
                    this.matrix[i][j] = scanner.nextInt();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void print(){
        System.out.println("================================================ ");
        System.out.println("Matrix #" + this.toString() + " contains: ");
        for(int line = 0; line < matSize; line++){
            for(int col = 0; col < matSize; col++){
                System.out.printf("% 5d ", matrix[line][col]);
            }
            System.out.println();
        }
        System.out.println("================================================ ");
    }

    public Integer size(){ return matSize; }
    public void setMatrix(int[][] input_matrix){ this.matrix = input_matrix; }
    public int[][] getMatrix(){
        return matrix;
    }
}
