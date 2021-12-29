package Metaheuristics;

public interface Solution {
    Solution genNeighb(Solution s);
    Solution betterNeighb(Solution s1, Solution s2);
    Solution bestNeighb(Solution s);
}
