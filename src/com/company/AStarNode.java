package com.company;

public class AStarNode {

    public int gCost;
    public int hCost;
    public int fCost;
    public int penalty;
    public AStarNode parent;

    public AStarNode(int penalty) {
        fCost = 0;
        gCost = 0;
        hCost = 0;
        this.penalty = penalty;
    }

    public int GetFCost() {
        return fCost;
    }

    public int GetHCost() {
        return hCost;
    }
}
