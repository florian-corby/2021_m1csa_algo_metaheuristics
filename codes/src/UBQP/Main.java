package UBQP;

import DataStructures.Matrix;
import DataStructures.Vector;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] argv) {
        File f = new File("../res/partition6.txt");
        Matrix matrix = new Matrix(f, 1);

        //If partition6.txt (specific vector for testing purposes):
        Vector X = new Vector(new int[] {1, 1, 0, 1, 0, 0});
        UBQP ubqp = new UBQP(matrix, X);

        //If other files (random vector given for first iterations):
        //UBQP ubqp = new UBQP(matrix);
        //Vector X = ubqp.getVec();

        System.out.println(" ############## TESTING CUSTOM CLASSES FOR EXERCISE ############## ");
        matrix.print();
        X.print();
        System.out.println("f(X) = " + UBQP.f(matrix.getPrimitiveMatrix(), X.getPrimitiveVector()));
        System.out.println(" ################################################################# \n");


        System.out.println(" ##################### TESTING STEEPEST HILL ##################### ");
        ubqp.solve(8, 0, 0, 0,"noRestarts");
        System.out.println(" ################################################################# \n");


        System.out.println(" ############## TESTING STEEPEST HILL WITH RESTARTS ############## ");
        ubqp.setVec(X.getPrimitiveVector());
        ubqp.solve(8, 3, 0, 0,"withRestarts");
        System.out.println(" ################################################################# \n");


        System.out.println(" ############## TESTING TABU ALGORITHM ############## ");
        ubqp.setVec(X.getPrimitiveVector());
        ubqp.solve(8, 3, 5, 0,"withRestarts");
        System.out.println(" #################################################### \n");


        System.out.println(" ############## TESTING STEEPEST HILL WITH RESTARTS AND CONSTRAINT ############## ");
        ubqp.setVec(X.getPrimitiveVector());
        int constraint = 0;
        try {
            Scanner sc = new Scanner(f);
            sc.nextInt(); constraint = sc.nextInt();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Constraint is " + constraint);
        ubqp.solve(8, 3, 0, constraint,"withConstraint");
        System.out.println(" ################################################################################ \n");
    }

}
