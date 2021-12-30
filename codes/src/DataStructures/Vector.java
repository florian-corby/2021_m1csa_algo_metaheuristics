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

    //From CLI
    public Vector(){ this.initFromCLI(); }

    //From size
    public Vector(int size){
        vecSize = size;
        vector = new int[vecSize];
    }

    //From primitive vector array
    public Vector(int[] input_vector){
        setPrimitiveVector(input_vector);
    }


    // ================= METHODS ================= //
    public void initFromCLI(){
        Scanner scanner = new Scanner(System.in);
        vecSize = scanner.nextInt();
        vector = new int[vecSize];

        //On remplit:
        for (int i = 0; i < vecSize; i++) {
            vector[i] = scanner.nextInt();
        }
    }

    public void initFromFile(File f){
        try {
            Scanner scanner = new Scanner(f);
            vecSize = scanner.nextInt();
            vector = new int[vecSize];

            //On remplit:
            for (int i = 0; i < vecSize; i++) {
                    vector[i] = scanner.nextInt();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void print(){
        System.out.println("===================================================== ");
        System.out.println("Vector #" + this.toString() + " has length: " + vecSize);
        System.out.println("Vector #" + this.toString() + " contains: ");
        for(int col = 0; col < vecSize; col++){
                System.out.printf("% 5d ", vector[col]);
        }
        System.out.println("\n===================================================== ");
    }

    public Integer size(){ return vecSize; }
    public static int[] clonePrimitiveVector(int[] inputVector){
        int[] res = new int[inputVector.length];
        System.arraycopy(inputVector, 0, res, 0, inputVector.length);
        return res;
    }
    public void setPrimitiveVector(int[] inputVector){
        vecSize = inputVector.length;
        vector = clonePrimitiveVector(inputVector);
    }
    public int[] getPrimitiveVector(){
        return clonePrimitiveVector(vector);
    }
}
