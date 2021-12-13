package com.company;

import java.util.Comparator;

public class Cave {

    public String name;
    public boolean isBig;
    public boolean isStart;
    public boolean isEnd;

    public Cave(String name, boolean isBig, boolean isStart, boolean isEnd) {
        this.name = name;
        this.isBig = isBig;
        this.isStart = isStart;
        this.isEnd = isEnd;
    }
}
