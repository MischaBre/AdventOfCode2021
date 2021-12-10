package com.company;

import java.util.List;

public class Bingo {

    private final int[][] field;
    private final int[] sumFieldCol = {0,0,0,0,0};
    private final int[] sumFieldLin = {0,0,0,0,0};
    private int fieldSum = 0;
    public Boolean won;

    public Bingo(int[][] field) {
        this.field = field;
        won = false;
        for (int i = 0; i<25; i++) {
            fieldSum += this.field[i][0];
        }
    }

    public void ReadBingoList(List<Integer> list) {
        //private List<Integer> bingoCheckList = new ArrayList<>();
    }

    public void PrintField() {
        for (int i=0; i<5; i++) {
            String line = "";
            for (int j=0; j<5; j++) {
                line += (String.format("%02d",field[i*5+j][0]) + (field[i*5+j][1] == 1 ? "_" : " ") + " ");
            }
            System.out.println(line);
        }
        System.out.println("------------------ " + fieldSum);
    }

    public void CheckField(int num) {
        for (int i=0; i<25; i++) {
            if (field[i][0] == num) {
                field[i][1] = 1;
                fieldSum -= num;
                sumFieldCol[i%5] += 1;
                sumFieldLin[i/5] += 1;
            }
        }
    }

    public int CheckWin(int num) {
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            if (sumFieldCol[i] == 5) {
                for (int j = 0; j < 5; j++) {
                    sum += field[i+j*5][0];
                }
                won = true;
                return fieldSum * num;
            }
            if (sumFieldLin[i] == 5) {
                for (int j = 0; j < 5; j++) {
                    sum += field[i*5+j][0];
                }
                won = true;
                return fieldSum * num;
            }
        }
        return 0;
    }

}
