package TSP;

import UBQP.UBQP;

public class Main {

    public static void main(String[] argv) {

        System.out.println(" ############## TESTING CUSTOM CLASSES FOR EXERCISE ############## ");
        //Testing random generation of vectors:
        TSP tspRandTest = new TSP("../res/tsp5.txt");
        tspRandTest.getVec().print();
        System.out.println();

        //Testing distance function (expected result is 263.88km):
        TSP tspDistTest = new TSP("../res/tsp5.txt");
        tspDistTest.setVec(new int[]{5, 3, 4, 1, 2});
        tspDistTest.getVec().print();
        tspDistTest.printDist();
        System.out.println(" ################################################################# \n");
    }

}
