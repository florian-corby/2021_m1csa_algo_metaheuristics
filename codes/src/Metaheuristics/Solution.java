package Metaheuristics;

import java.util.Random;

public interface Solution {
    Solution[] genNeighbourhood();
    Boolean isBetterNeighb(Solution other);
    Solution betterNeighb(Solution other);
    Solution bestNeighb();
    Solution clone();
    void copy(Solution s);
    void randomize(Random rand);
    void print();
}
