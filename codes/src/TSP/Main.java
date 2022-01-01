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
    }

}
