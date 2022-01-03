package TSP;

import DataStructures.Vector;

import java.util.Scanner;

public class Main {

    public static void main(String[] argv) {
        if(argv.length < 2 || argv.length > 3){
            System.err.println("ERROR: command syntax is \"tsp [-d] <fileName> <steepest|tabu>\"");
            System.exit(1);
        }
        else{
            if(argv.length == 3 && argv[0].equals("-d")){ debugTests(); mainTests(argv[1], argv[2]); }
            else mainTests(argv[0], argv[1]);
        }
    }

    // ================= PRIVATE AUXILIARY METHODS ================= //
    private static void mainTests(String fileName, String algoName){
        switch(algoName) {
            case "steepest":
                //Inputting last parameters for full tests control with .jar:
                Scanner steepestSc = new Scanner(System.in);
                System.out.print("Input maxMoves: ");
                int steepestMaxMoves = steepestSc.nextInt();
                System.out.print("Input maxTrials: ");
                int steepestMaxTrials = steepestSc.nextInt();
                System.out.println();

                TSP tspSteepestTest = new TSP(fileName);
                System.out.println(" ############### TESTING STEEPEST HILL ALGORITHM (with restarts) ################ ");
                System.out.println(">>> INITIAL SEQUENCE OF CITIES: ");
                tspSteepestTest.getVec().print();
                tspSteepestTest.printDist();
                System.out.println();

                System.out.println(">>> BEST SEQUENCE OF CITIES : ");
                int[] bestSeqFound = SteepestHill.runWithRestarts(tspSteepestTest.getCities(), tspSteepestTest.getVec().getPrimitiveVector(), steepestMaxMoves, steepestMaxTrials);
                tspSteepestTest.setVec(bestSeqFound);
                tspSteepestTest.getVec().print();
                tspSteepestTest.printDist();
                System.out.println(" ################################################################################ \n");
                break;

            case "tabu":
                //Inputting last parameters for full tests control with .jar:
                Scanner tabuSc = new Scanner(System.in);
                System.out.print("Input maxMoves: ");
                int tabuMaxMoves = tabuSc.nextInt();
                System.out.print("Input tabuSize: ");
                int tabuMaxTrials = tabuSc.nextInt();
                System.out.println();

                TSP tspTabuTest = new TSP(fileName);
                System.out.println(" ############################# TESTING TABU ALGORITHM ########################### ");
                System.out.println(">>> INITIAL SEQUENCE OF CITIES: ");
                tspTabuTest.getVec().print();
                tspTabuTest.printDist();
                System.out.println();

                System.out.println(">>> TABU ALGORITHM EXECUTION: ");
                int[] tabuBestSeqFound = Tabu.run(tspTabuTest.getCities(), tspTabuTest.getVec().getPrimitiveVector(), tabuMaxMoves, tabuMaxTrials);

                System.out.println(">>> BEST SEQUENCE OF CITIES: ");
                tspTabuTest.setVec(tabuBestSeqFound);
                tspTabuTest.getVec().print();
                tspTabuTest.printDist();
                System.out.println(" ################################################################################ \n");
                break;

            default:
                System.err.println("ERROR: invalid algorithm name, possible names are \"steepest\" or \"tabu\"");
                System.exit(1);
                break;
        }
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
