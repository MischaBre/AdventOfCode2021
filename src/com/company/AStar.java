package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AStar {

    public List<AStarNode> nodes;
    private final List<AStarNode> openNodes;
    private final List<AStarNode> closedNodes;
    private final List<AStarNode> path = new ArrayList<>();
    private final int size;

    public AStar(int size) {
        nodes = new ArrayList<>();
        openNodes = new ArrayList<>();
        closedNodes = new ArrayList<>();
        this.size = size;
    }

    public void SetPenalty(List<Integer> penalty) {
        for (int pen : penalty) {
            nodes.add(new AStarNode(pen));
        }
    }

    public void PrintNodes() {
        AStarNode node;
        for (int i = 0; i < size*size; i++) {
            node = nodes.get(i);
            System.out.printf((path.contains(node) ? "_" : nodes.get(i).penalty) + (i % size == size-1 ? "%n" : ""));
        }
    }

    public void FindRoute() {
        openNodes.add(nodes.get(0));
        do {
            AStarNode currentNode = FindMinNode(openNodes);
            openNodes.remove(currentNode);
            closedNodes.add(currentNode);

            if (currentNode.equals(nodes.get(size*size-1))) {
                RetracePath();
                break;
            }

            for (AStarNode node : FindNeighbors(nodes.indexOf(currentNode))) {
                if (closedNodes.contains(node)) {
                    continue;
                }
                int newMoveCost = currentNode.gCost + node.penalty;
                if (newMoveCost < node.gCost || !openNodes.contains(node)) {
                    node.gCost = newMoveCost;
                    node.hCost = 2*size - nodes.indexOf(node) / size - nodes.indexOf(node) % size;
                    node.fCost = node.gCost + node.hCost;
                    node.parent = currentNode;

                    if (!openNodes.contains(node)) {
                        openNodes.add(node);
                    }
                }
            }
        } while (openNodes.size() < size*size);
    }

    private void RetracePath() {

        int sum = 0;
        AStarNode currentNode = nodes.get(size*size-1);

        while (!currentNode.equals(nodes.get(0))) {
            path.add(currentNode);
            currentNode = currentNode.parent;
        }

        for (AStarNode node : path) {
            sum += node.penalty;
            //System.out.println(node.penalty);
        }
        System.out.println("Summe: " + sum);
    }

    private AStarNode FindMinNode(List<AStarNode> nodes) {
        return nodes.stream().min(Comparator.comparing(AStarNode::GetFCost).thenComparing(AStarNode::GetHCost)).get();
    }

    public List<AStarNode> FindNeighbors(int index) {
        List<AStarNode> nodes = new ArrayList<>();
        if (index > size-1) { nodes.add(this.nodes.get(index-size)); }
        if (index % size != 0) { nodes.add(this.nodes.get(index-1)); }
        if (index % size != size-1) { nodes.add(this.nodes.get(index +1)); }
        if (index < size*size-size) { nodes.add(this.nodes.get(index + size)); }
        return nodes;
    }

}
