package UBQP;

import DataStructures.Vector;
import Metaheuristics.Solution;

import java.util.Random;

public class VecSol extends Vector implements Solution {
    private VecSol[] neighbourhood;

    // ================= METHODS ================= //
    public VecSol(int size){
        super(size);
    }

    public VecSol(int[] vector){
        super(vector);
    }

    public VecSol(int size, Random rand){
        super(size);
        for(int i = 0; i < this.size(); i++){
            this.getVector()[i] = Math.abs(rand.nextInt() % 2);
        }
    }

    // ================= NEIGHBOURS MGMT ================= //
    @Override
    public Solution genNeighb(Solution s) {
        return null;
    }

    @Override
    public Solution betterNeighb(Solution s1, Solution s2) {
        return null;
    }

    @Override
    public Solution bestNeighb(Solution s) {
        return null;
    }

    public VecSol[] getNeighbourhood(){ return neighbourhood; }
}
