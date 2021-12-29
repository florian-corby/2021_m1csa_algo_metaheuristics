package UBQP;

import DataStructures.Matrix;

import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static Scanner scanner;
    private static Matrix matrix;
    private static int[] vecSolution;
    private static int solution;
    private  static int MAX_depl = 8;
    private  static int MAX_trials = 10;
    private static Random rand = new Random();

    public static void main(String[] argv) {
        matrix = new Matrix(new File("../res/matrixQ.txt"));
        //matrix.initFromCLI();
        matrix.print();

        //vecSolution = ex11_randomSolution();
        vecSolution = new int[] {1, 1, 0, 1, 0, 0};
        System.out.println("\n");
        print_solution(vecSolution);

        solution = ex12_f(vecSolution);
        System.out.println("f(X) = " + solution);

        vecSolution = ex14_steepestHill(vecSolution);
        print_solution(vecSolution);
        System.out.println("f(X) = " + ex12_f(vecSolution));

        ex15_steepestHillWithRestarts();
        print_solution(vecSolution);
        System.out.println("f(X) = " + ex12_f(vecSolution));
    }

    public static int[] ex11_randomSolution(){
        vecSolution = new int[matrix.size()];
        for(int i = 0; i < matrix.size(); i++){
            vecSolution[i] = Math.abs(rand.nextInt() % 2);
        }

        return vecSolution;
    }

    public static int ex12_f(int[] X){
        solution = 0;

        for(int i = 0; i < matrix.size(); i++){
            for(int j = 0; j < matrix.size(); j++){
                solution += matrix.getMatrix()[i][j]*X[i]*X[j];
            }
        }

        return solution;
    }

    public static int[] ex13_bestNeigh(int[] X){
        int[] res = new int[matrix.size()];
        int min = 0, tmp, bestIdx = 0;

        System.arraycopy(X, 0, res, 0, matrix.size());

        for(int i = 0; i < matrix.size(); i++){
            res[i] = (res[i] + 1) % 2;
            tmp = ex12_f(res);

            if(i == 0 || tmp < min) {
                min = tmp;
                bestIdx = i;
            }

            res[i] = (res[i] + 1) % 2;
        }

        res[bestIdx] = (res[bestIdx] + 1) % 2;
        return res;
    }

    public static int[] ex14_steepestHill(int[] initSol){
        int[] tmpVecSol = initSol;
        int[] tmpVecSol2;
        int nb_depl = 0;
        boolean stop = false;
        
        while(!stop && nb_depl < MAX_depl){
            tmpVecSol2 = ex13_bestNeigh(tmpVecSol);

            if(ex12_f(tmpVecSol) > ex12_f(tmpVecSol2))
                tmpVecSol = tmpVecSol2;
            else
                stop = true;

            nb_depl++;
        }

        return tmpVecSol;
    }

    public static void ex15_steepestHillWithRestarts(){
        int[] randInitSol;
        int[] res;
        int nb_trials = 0;

        while(nb_trials < MAX_trials){
            randInitSol = ex11_randomSolution();
            res = ex14_steepestHill(randInitSol);

            if(ex12_f(vecSolution) > ex12_f(res))
                vecSolution = res;

            nb_trials++;
        }
    }

    public static void print_solution(int[] vecSolution){
        System.out.print("[");
        for(int i = 0; i < matrix.size(); i++){
            System.out.print(vecSolution[i] + " ");
        }
        System.out.print("]");
    }

    public static Scanner getScanner(){
        return scanner;
    }
}
