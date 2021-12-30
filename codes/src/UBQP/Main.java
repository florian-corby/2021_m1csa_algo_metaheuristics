package UBQP;

import DataStructures.Matrix;
import Metaheuristics.Solution;
import Metaheuristics.SteepestHill;
import Metaheuristics.Tabu;

import java.io.File;
import java.util.Random;

public class Main {
    private static final Random rand = new Random();

    public static void main(String[] argv) {
        Matrix matrix = new Matrix(new File("../res/matrixQ.txt"));
        //Matrix matrix = matrix.initFromCLI();
        matrix.print();
        UBQP ubqp = new UBQP(matrix);

        System.out.println("\n ##### TESTING UBQP CLASS ##### ");
        ubqp.setVecsol(new int[]{1, 1, 0, 1, 0, 0});
        ubqp.getVecsol().print();
        ubqp.printSolution();

        System.out.println("\n ##### TESTING STEEPEST HILL ALGORITHM ##### ");
        SteepestHill steepestHill = new SteepestHill(ubqp.getVecsol(), 6);
        steepestHill.run();
        ubqp.getVecsol().print();
        ubqp.printSolution();

        System.out.println("\n ##### TESTING STEEPEST HILL WITH RESTARTS ##### ");
        ubqp.setVecsol(new int[]{1, 1, 0, 1, 0, 0});
        steepestHill.setSolution(ubqp.getVecsol());
        steepestHill.runWithRestarts(6, rand);
        ubqp.getVecsol().print();
        ubqp.printSolution();

//        System.out.println("\n ##### TESTING TABU ##### ");
//        ubqp.setVecsol(new int[]{1, 1, 0, 1, 0, 0});
//        Tabu tabu = new Tabu(ubqp.getVecsol(), 6, 6);
//        ubqp.setVecsol(((VecSol) tabu.run()).getVector());
//        ubqp.getVecsol().print();
//        ubqp.printSolution();
    }
}
