package TSP;

import DataStructures.Vector;

public class Main {

    public static void main(String[] argv) {
        System.out.println(" ##################### TESTING CUSTOM CLASSES FOR EXERCISE ##################### ");
        System.out.println("The following tests are conducted on file tsp5.txt for simplicity...\n");

        System.out.println(">>> RANDOM CITY SEQUENCE GENERATION (on file tsp5.txt):");
        TSP tspRandTest = new TSP("../res/tsp5.txt");
        tspRandTest.getVec().print();
        System.out.println();

        System.out.println(">>> DISTANCE FUNCTION TEST (on file tsp5.txt):");
        TSP tspDistTest = new TSP("../res/tsp5.txt");
        tspDistTest.setVec(new int[]{5, 3, 4, 1, 2});
        tspDistTest.getVec().print();
        System.out.println("Expected distance: 263.88km");
        tspDistTest.printDist();
        System.out.println(" ################################################################################ \n");


        System.out.println(" ############## TESTING BEST NEIGHBOUR FUNCTION FROM STEEPEST HILL ############## ");
        System.out.println("The following tests are conducted on file tsp5.txt for simplicity...\n");

        System.out.println(">>> INITIAL SEQUENCE OF CITIES: ");
        tspDistTest.getVec().print();
        tspDistTest.printDist();
        System.out.println();

        System.out.println(">>> BEST NEIGHBOUR IS: ");
        Vector bestNeighb = new Vector(SteepestHill.bestNeighb(tspDistTest.getCities(), tspDistTest.getVec().getPrimitiveVector()));
        bestNeighb.print();
        System.out.println("Distance: " + TSP.getDistance(tspDistTest.getCities(), bestNeighb.getPrimitiveVector()) + " km");
        System.out.println(" ################################################################################ \n");


        System.out.println(" ######################## TESTING STEEPEST HILL ALGORITHM ####################### ");
        System.out.println("The following tests are conducted on whichever file is specified in CLI...\n");

        System.out.println(">>> INITIAL SEQUENCE OF CITIES: ");
        tspDistTest.getVec().print();
        tspDistTest.printDist();
        System.out.println();

        System.out.println(">>> BEST SEQUENCE OF CITIES (without restarts): ");
        Vector bestSol = new Vector(SteepestHill.run(tspDistTest.getCities(), tspDistTest.getVec().getPrimitiveVector(), 8));
        bestSol.print();
        System.out.println("Distance: " + TSP.getDistance(tspDistTest.getCities(), bestSol.getPrimitiveVector()) + " km");
        System.out.println();

        System.out.println(">>> BEST SEQUENCE OF CITIES (with restarts): ");
        Vector bestSolWithRestarts = new Vector(SteepestHill.runWithRestarts(tspDistTest.getCities(), tspDistTest.getVec().getPrimitiveVector(), 8, 3));
        bestSolWithRestarts.print();
        System.out.println("Distance: " + TSP.getDistance(tspDistTest.getCities(), bestSolWithRestarts.getPrimitiveVector()) + " km");
        System.out.println(" ################################################################################ \n");


        System.out.println(" ############################# TESTING TABU ALGORITHM ########################### ");
        System.out.println("The following tests are conducted on whichever file is specified in CLI...\n");

        TSP tspTabuTest = new TSP("../res/tsp101.txt");
        tspTabuTest.getVec().print();

        System.out.println(">>> INITIAL SEQUENCE OF CITIES: ");
        tspTabuTest.getVec().print();
        tspTabuTest.printDist();
        System.out.println();

        System.out.println(">>> BEST SEQUENCE OF CITIES: ");
        Vector bestSolTabu = new Vector(Tabu.run(tspTabuTest.getCities(), tspTabuTest.getVec().getPrimitiveVector(), 150, 80));
        bestSolTabu.print();
        System.out.println("Distance: " + TSP.getDistance(tspTabuTest.getCities(), bestSolTabu.getPrimitiveVector()) + " km");
        System.out.println(" ################################################################################ \n");
    }

}
