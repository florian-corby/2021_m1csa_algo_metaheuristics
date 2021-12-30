package UBQP;

import DataStructures.Matrix;
import DataStructures.Vector;
import Metaheuristics.Solution;

import java.util.Random;

public class VecSol extends Vector implements Solution {
    private VecSol[] neighbourhood;
    private UBQP ubqp;

    // ================= CONSTRUCTORS ================= //
    public VecSol(int size){
        super(size);
    }

    public VecSol(VecSol toCopy){
        super(toCopy.getVector());
        ubqp = new UBQP(toCopy.ubqp);
        ubqp.setVecsol(this);
        setNeighbourhood();
    }

    public VecSol(int[] vector){
        super(vector);
    }


    // ================= METHODS ================= //
    public void setUbqp(UBQP ubqp) { this.ubqp = ubqp; }
    public UBQP getUbqp(){ return ubqp; }

    // ================= NEIGHBOURS MGMT ================= //
    @Override
    public Solution[] genNeighbourhood() {
        VecSol[] res = new VecSol[size()];

        for(int i = 0; i < size(); i++){
            int[] neighbour = new int[size()];
            System.arraycopy(this.getVector(), 0, neighbour, 0, size());
            neighbour[i] = (neighbour[i] + 1) % 2;
            res[i] = new VecSol(neighbour);

            UBQP ubqp = new UBQP(this.ubqp);
            ubqp.setVecsol(res[i]);
            res[i].setUbqp(ubqp);
        }

        return res;
    }

    @Override
    public Boolean isBetterNeighb(Solution other) {
        VecSol vecsol2 = (VecSol) other;
        Matrix ubqpMatrix = ubqp.getMatrix();

        return other == null
                || UBQP.f(ubqpMatrix, this.getVector()) <= UBQP.f(ubqpMatrix, vecsol2.getVector());
    }

    @Override
    public Solution betterNeighb(Solution other) {
        VecSol vecsol2 = (VecSol) other;

        if(isBetterNeighb(other))
            return this;
        else
            return vecsol2;
    }

    @Override
    public Solution bestNeighb() {
        VecSol[] neighbourh = (VecSol[]) genNeighbourhood();
        VecSol min = neighbourh[0];

        for(int i = 1; i < size(); i++) {
            min = (VecSol) min.betterNeighb(neighbourh[i]);
        }

        return min;
    }

    @Override
    public Solution clone(){
        return new VecSol(this);
    }

    @Override
    public void copy(Solution s){
        VecSol toCopy = (VecSol) s;
        System.arraycopy(toCopy.getVector(), 0, getVector(), 0, size());
        genNeighbourhood();

        ubqp = new UBQP(toCopy.getUbqp());
        ubqp.setVecsol(this);
    }

    @Override
    public void randomize(Random rand){
        for(int i = 0; i < this.size(); i++){
            this.getVector()[i] = Math.abs(rand.nextInt() % 2);
        }
    }

    public void setNeighbourhood(){ neighbourhood = (VecSol[]) genNeighbourhood(); }
    public VecSol[] getNeighbourhood(){ return neighbourhood; }
}
