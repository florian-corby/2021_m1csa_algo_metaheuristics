package DataStructures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Vector {
    private int[] vector;
    private Integer vecSize;

    // ================= CONSTRUCTORS ================= //
    //From file
    public Vector(File f){ this.initFromFile(f); }

    //With nothing (cf. later initialization)
    public Vector(){

    }

    //With primitive matrix array
    public Vector(int[] input_vector){ this.setVector(input_vector); }


    // ================= METHODS ================= //
    public void initFromCLI(){
        Scanner scanner = new Scanner(System.in);
        this.vecSize = scanner.nextInt();
        this.vector = new int[vecSize];

        //On remplit:
        for (int i = 0; i < vecSize; i++) {
            this.vector[i] = scanner.nextInt();
        }
    }

    public void initFromFile(File f){
        try {
            Scanner scanner = new Scanner(f);
            this.vecSize = scanner.nextInt();
            this.vector = new int[vecSize];

            //On remplit:
            for (int i = 0; i < vecSize; i++) {
                    this.vector[i] = scanner.nextInt();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void print(){
        System.out.println("================================================ ");
        System.out.println("Vector #" + this.toString() + " contains: ");
        for(int col = 0; col < vecSize; col++){
                System.out.printf("% 5d ", vector[col]);
        }
        System.out.println("================================================ ");
    }

    public Integer size(){ return vecSize; }
    public void setVector(int[] input_vector){ this.vector = input_vector; }
    public int[] getVector(){
        return vector;
    }
}
