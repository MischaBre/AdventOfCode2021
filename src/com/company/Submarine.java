package com.company;

import java.util.ArrayList;
import java.util.List;

public class Submarine {

    private int horizontal;
    private int depth;
    private int aim;
    private int gammaRate;
    private int epsilonRate;
    private int oxygenRating;
    private int co2Rating;
    private int powerConsumption;
    private int LifeSupportRating;

    private List<Integer> intList = new ArrayList<>();
    private int intListLength;
    private List<String> stringList = new ArrayList<>();
    private int stringListLength;
    private List<Instruction> instructionList = new ArrayList<>();
    private int instructionListLength;
    private List<Bingo> bingo = new ArrayList<>();
    private List<Integer> bingoIntList = new ArrayList<>();
    private List<Integer> bingoWonSum = new ArrayList<>();


    public Submarine(int h, int d, int aim) {
        this.horizontal = h;
        this.depth = d;
        this.aim = aim;
    }

    public void ReadGammaEpsilonRate() {
        gammaRate = 0;
        epsilonRate = 0;
        int[] bitArray = new int[] {0,0,0,0,0,0,0,0,0,0,0,0};
        char[] charArray = new char[12];
        for (String l : stringList) {
            for (int i = 0; i < l.length(); i++) {
                bitArray[i] += (l.charAt(i) == '1' ? 1 : 0);
            }
        }
        for (int i = 0; i < bitArray.length; i++) {
            charArray[i] = (bitArray[i] > stringListLength/2 ? '1' : '0');
             //Math.pow(2, bitArray.length-i-1) * (bitArray[i]-1);
        }
        gammaRate = Integer.parseInt(String.valueOf(charArray),2);
        epsilonRate = Integer.parseInt(Integer.toBinaryString(~gammaRate).substring(20,32), 2);
        powerConsumption = gammaRate * epsilonRate;
    }

    public void ReadOxygenCo2Rating() {
        List <String> listOne = new ArrayList<String>(stringList);
        List <String> listTwo = new ArrayList<String>(stringList);
        oxygenRating = StripBits(listOne,0, false);
        co2Rating = StripBits(listTwo,0, true);
        LifeSupportRating = oxygenRating * co2Rating;
    }

    private int StripBits(List<String> list, int step, boolean isCo2) {
        if (list.size() > 1) {
            int val = 0;
            for (int i = 0; i < list.size(); i++) {
                val += Integer.parseInt(list.get(i).substring(step,step+1));
            }
            char removeChar;
            if (!isCo2) {
                removeChar = (val*2 < list.size() ? '1' : '0');
            } else {
                removeChar = (val*2 < list.size() ? '0' : '1');
            }
            int finalStep = step;
            list.removeIf(n -> (n.charAt(finalStep) == removeChar));
            step += 1;
            return StripBits(list, step, isCo2);
        } else {
            return Integer.parseInt(list.get(0),2);
        }
    }

    public int GetLifeSupportRating() {
        return LifeSupportRating;
    }

    public int GetPowerConsumption() {
        return powerConsumption;
    }

    public void ReadStringList(List<String> stringList) {
        this.stringList = stringList;
        this.stringListLength = stringList.size();
    }

    public void ReadIntList(List<Integer> intList) {
        this.intList = intList;
        this.intListLength = intList.size();
    }

    public void ReadInstructionList(List<Instruction> instructionList) {
        this.instructionList = instructionList;
        this.instructionListLength = instructionList.size();
    }

    public void PrintIntList() {
        for (int s : intList) {
            System.out.println(s);
        }
    }

    public void ReadBingoFromIntList() {
        for (int i = 0; i < intListLength/25; i++) {
            int [][] data = new int[25][2];
            for (int j = 0; j < 25; j++) {
                data[j][0] = intList.get(i*25+j);
                data[j][1] = 0;
            }
            bingo.add(new Bingo(data));
        }
    }

    public void ReadBingoListFromIntList(List<Integer> list) {
        bingoIntList = list;
    }

    public void PlayBingo() {
        for (int i : bingoIntList) {
            System.out.println(i);
            int winSum = 0;
            for (Bingo j : bingo) {
                if (!j.won) {
                    j.CheckField(i);
                    winSum = j.CheckWin(i);
                    if (winSum > 0) {
                        bingoWonSum.add(winSum);
                        j.PrintField();
                    }
                }
            }
            if (bingoWonSum.size() == bingo.size()) {
                break;
            }
        }
        System.out.println(bingoWonSum.get(bingoWonSum.size()-1));
    }

    public int GetInstructionListLength() {
        return this.instructionListLength;
    }

    public Instruction GetInstructionById(int id) {
        return this.instructionList.get(id);
    }

    public int GetHorizontal() {
        return this.horizontal;
    }

    public int GetDepth() {
        return this.depth;
    }

    public int GetAim() {
        return this.aim;
    }

    public void changePos(int[] newPos) {
        this.horizontal += newPos[0];
        this.depth += newPos[0]*this.aim;
        this.aim += newPos[1];
    }

    public int getHorDep() {
        return (this.horizontal*this.depth);
    }

}
