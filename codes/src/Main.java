import DataStructures.Matrix;
import DataStructures.Vector;
import UBQP.UBQP;

import java.io.File;
import java.util.Random;

public class Main {

    public static void main(String[] argv) {
        File f = new File("../res/matrixQ.txt");
        Matrix matrix = new Matrix(f);
        Vector X = new Vector(new int[] {1, 1, 0, 1, 0, 0});
        UBQP ubqp = new UBQP(matrix, X);

        System.out.println(" ############## TESTING CUSTOM CLASSES FOR EXERCISE ############## ");
        matrix.print();
        X.print();
        System.out.println("f(X) = " + UBQP.f(matrix.getPrimitiveMatrix(), X.getPrimitiveVector()));
        System.out.println(" ################################################################# \n");


        System.out.println(" ##################### TESTING STEEPEST HILL ##################### ");
        ubqp.solve(8, 0, "noRestarts");
        System.out.println(" ################################################################# \n");


        System.out.println(" ############## TESTING STEEPEST HILL WITH RESTARTS ############## ");
        ubqp.setVec(X.getPrimitiveVector());
        ubqp.solve(8, 3, "withRestarts");
        System.out.println(" ################################################################# \n");
    }

}
