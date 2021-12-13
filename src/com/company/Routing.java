package com.company;

import java.util.ArrayList;
import java.util.List;

public class Routing {

    private List<Cave> caveList;
    private List<Line> lineList;
    private Cave startCave;
    private Cave endCave;
    private int maxVisits;
    private Path currentPath;
    private List<Path> pathList = new ArrayList<>();

    public Routing(List<Cave> caveList, List<Line> lineList, int maxVisits) {
        this.caveList = caveList;
        this.lineList = lineList;
        this.maxVisits = maxVisits;
        for (Cave c : this.caveList) {
            if (c.isStart) { startCave = c; }
            if (c.isEnd) { endCave = c; }
        }
    }

    public void StartSearch() {
        System.out.println("Starting Pathfinding...");
        Path firstPath = new Path();
        firstPath.path.add(startCave);
        NextMove(firstPath, startCave);
        System.out.println(pathList.size() + " Routen gefunden.");
    }

    public void NextMove(Path previousPath, Cave cave) {
        for (Cave c : FindNeighborCaves(cave)) {
            if (IsValidMove(previousPath, c)) {
                Path thisPath = new Path();
                thisPath.path = new ArrayList<>(previousPath.path);
                thisPath.path.add(c);
                if (c == endCave && !pathList.contains(thisPath)) {
                    thisPath.PrintPath();
                    pathList.add(thisPath);
                } else {
                    NextMove(thisPath, c);
                }
            }
        }
    }

    private boolean IsValidMove(Path previousPath, Cave c) {
        return ((!EnoughSmallVisits(previousPath) || c.isBig) && !c.isStart);
    }

    private boolean EnoughSmallVisits(Path previousPath) {
        int isFirst = 0;
        for (int i = 1; i < previousPath.path.size(); i++) {
            Cave c = previousPath.path.get(i);
            int visits = 0;
            if (!c.isBig && !c.isStart) {
                for (Cave d : previousPath.path.subList(i, previousPath.path.size())) {
                    if (d.equals(c)) {
                        visits++;
                        if (visits+isFirst > maxVisits) {
                            return true;
                        }
                        if (visits == maxVisits && isFirst == 0) {
                            isFirst++;
                        }
                    }
                }
            }
        }
        return false;
    }

    private List<Cave> FindNeighborCaves(Cave c) {
        List<Cave> neighbors = new ArrayList<>();
        List<Line> connections = lineList.stream().filter(l -> (l.fromCave == c) || (l.toCave == c)).toList();
        for (Line l : connections) {
            neighbors.add(l.fromCave == c ? l.toCave : l.fromCave);
        }
        return neighbors;
    }

    public List<Cave> ParseLineListToCaveList(List<Line> lineList) {
        List<Cave> caveList = new ArrayList<>();
        for (Line l : lineList) {
            if (!caveList.contains(l.fromCave)) { caveList.add(l.fromCave); }
            if (!caveList.contains(l.toCave)) { caveList.add(l.toCave); }
        }
        return caveList;
    }

}
