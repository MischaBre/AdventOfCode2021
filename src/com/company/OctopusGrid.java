package com.company;

public class OctopusGrid {

    private int[] grid = new int[100];
    private int sum = 0;
    private int sumDiff = 0;

    public OctopusGrid(int[] grid) {
        this.grid = grid;
    }

    public void AdvanceStepUntil(int x) {
        for (int i = 0; i < x; i++) {
            sumDiff = 0;
            for (int g = 0; g < grid.length; g++) {
                if (grid[g] < 10) {
                    grid[g]++;
                }
            }
            for (int g = 0; g < grid.length; g++) {
                if (grid[g] > 9) {
                    Flash(g);
                }
            }
            sum += sumDiff;
            if (sumDiff > 99) {
                System.out.println("In Step " + (i+1) + " all Octopuses flashed simultaneously.");
                break;
            }
        }
    }

    private void Flash(int g) {
        grid[g] = 0;
        sumDiff++;
        for (int i : FindNeighbors(g)) {
            if (i != -1) {
                if (grid[i] != 0) {
                    grid[i]++;
                }
                if (grid[i] > 9) {
                    Flash(i);
                }
            }
        }
    }

    private int[] FindNeighbors(int g) {
        return new int[] {
                ((g > 9) && (g % 10 != 0) ? g-11 : -1), (g > 9 ? g-10 : -1) , ((g > 9) && (g % 10 != 9) ? g-9 : -1),
                (g % 10 != 0 ? g-1 : -1)                                    , (g % 10 != 9 ? g+1 : -1),
                ((g < 90) && (g % 10 != 0) ? g+9 : -1), (g < 90 ? g+10 : -1), ((g % 10 != 9) && (g < 90) ? g+11 : -1)
        };
    }

    public void PrintGrid() {
        for (int i = 0; i < grid.length; i++) {
            System.out.printf("%01d ",grid[i]);
            if ((i+1) % 10 == 0) {
                System.out.printf("%n");
            }
        }
        System.out.println("-------------------- " + sum + " Flashes");
    }
}
