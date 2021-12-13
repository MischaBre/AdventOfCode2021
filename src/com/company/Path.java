package com.company;

import java.util.ArrayList;
import java.util.List;

public class Path {

    public List<Cave> path = new ArrayList<>();

    public Path() {
    }

    public void PrintPath() {
        for (Cave p : path) {
            System.out.print(!p.isEnd ? p.name + " -> " : "End%n");
        }
    }
}
