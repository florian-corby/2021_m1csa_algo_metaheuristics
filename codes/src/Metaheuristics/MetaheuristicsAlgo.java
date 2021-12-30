package Metaheuristics;

public abstract class MetaheuristicsAlgo {
    private Integer nbMoves;
    private Solution solution;

    public MetaheuristicsAlgo(Solution s, Integer nbMoves){
        setSolution(s);
        setNbMoves(nbMoves);
    }
    public abstract void run();
    public Integer getNbMoves() { return nbMoves; }
    public void setNbMoves(Integer nbMoves) { this.nbMoves = nbMoves; }
    public Solution getSolution() { return solution; }
    public void setSolution(Solution solution) { this.solution = solution; }
}
