package TSP;

import DataStructures.Vector;

public class Main {

    public static void main(String[] argv) {
        if(argv.length < 1 || argv.length > 2){
            System.err.println("ERROR: command syntax is \"tsp [-d] <fileName>\"");
            System.exit(1);
        }
        else{
            if(argv.length == 2 && argv[0].equals("-d")){ debugTests(); mainTests(argv[1]); }
            else mainTests(argv[0]);
        }
    }

    // ================= PRIVATE AUXILIARY METHODS ================= //
    private static void mainTests(String fileName){
        TSP tspSteepestTest = new TSP(fileName);
        System.out.println(" ############### TESTING STEEPEST HILL ALGORITHM (with restarts) ################ ");
        System.out.println(">>> INITIAL SEQUENCE OF CITIES: ");
        tspSteepestTest.getVec().print();
        tspSteepestTest.printDist();
        System.out.println();

        System.out.println(">>> BEST SEQUENCE OF CITIES : ");
        int[] bestSeqFound = SteepestHill.runWithRestarts(tspSteepestTest.getCities(), tspSteepestTest.getVec().getPrimitiveVector(), 8, 3);
        tspSteepestTest.setVec(bestSeqFound);
        tspSteepestTest.getVec().print();
        tspSteepestTest.printDist();
        System.out.println(" ################################################################################ \n");


        TSP tspTabuTest = new TSP(fileName);
        System.out.println(" ############################# TESTING TABU ALGORITHM ########################### ");
        System.out.println(">>> INITIAL SEQUENCE OF CITIES: ");
        tspTabuTest.getVec().print();
        tspTabuTest.printDist();
        System.out.println();

        System.out.println(">>> BEST SEQUENCE OF CITIES: ");
        int[] tabuBestSeqFound = Tabu.run(tspTabuTest.getCities(), tspTabuTest.getVec().getPrimitiveVector(), 20, 10);
        tspTabuTest.setVec(tabuBestSeqFound);
        tspTabuTest.getVec().print();
        tspTabuTest.printDist();
        System.out.println(" ################################################################################ \n");

    }

    private static void debugTests(){
        TSP tspRandTest = new TSP("../res/tsp5.txt");
        System.out.println(" ##################### TESTING CUSTOM CLASSES FOR EXERCISE ##################### ");
        System.out.println("The following tests are conducted on file tsp5.txt for simplicity...\n");

        System.out.println(">>> RANDOM CITY SEQUENCE GENERATION (on file tsp5.txt):");
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
