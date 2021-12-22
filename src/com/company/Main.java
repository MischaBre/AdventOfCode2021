package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Part20(false);

    }

    private static void Part20(boolean isTest) {
        FileReader file = new FileReader();
        String filename = isTest ? "data20test.txt" : "data20.txt";
        file.OpenFile(filename);
        int size = 100;

        TrenchMap image = new TrenchMap(size);
        image.LoadAlgorithm(file.ParseSingleDataToString(0));
        image.LoadImage(file.ParseDataToChar(1, size+1 ,size));
        image.CountPixels();
        image.PrintImage();

        for (int i = 0; i < 2; i++) {
            image.EnhanceStep();
            image.PrintImage();
        }
        image.CountPixels();
    }

    private static char[][] GenerateTestImage(int size) {
        char[][] image = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                image[i][j] = (int)(Math.random()*2.0) < 1 ? '#' : '.';
            }
        }
        return image;
    }


    private void OldParts() {
        Submarine sub = new Submarine(0,0,0);

        FileReader file = new FileReader();

        int part = 15;

        switch (part) {
            case 1:
                file.OpenFile("data.txt");
                break;
            case 2:
                file.OpenFile("data2.txt");
//Part2
                sub.ReadInstructionList(file.ParseDataToInstruction());
                for (int i = 0; i < sub.GetInstructionListLength(); i++) {
                    sub.changePos(sub.GetInstructionById(i).TranslateInstruction());
                }
                System.out.println("Position: " + sub.GetHorizontal() * sub.GetDepth());
                break;
            case 3:
                file.OpenFile("data3.txt");
//Part3.1
                sub.ReadStringList(file.ParseDataToString());
                sub.ReadGammaEpsilonRate();
                System.out.println("Power consumption: " + sub.GetPowerConsumption());
//Part3.2
                sub.ReadOxygenCo2Rating();
                System.out.println("Life support rating: " + sub.GetLifeSupportRating());
                break;
            case 4:
                file.OpenFile("data4.txt");

                sub.ReadIntList(file.ParseLinesToInt(1,file.GetDataLength()));
                sub.ReadBingoListFromIntList(file.ParseSingleLineToInt(0, ","));
                sub.ReadBingoFromIntList();
                sub.PlayBingo();
                break;
            case 10:
                file.OpenFile("data10.txt");
                sub.ReadStringList(file.ParseDataToString());
                System.out.println(sub.CheckNavigationStringList());    //part1
                System.out.println(sub.CorrectNavigationStringList());  //part2
                break;
            case 11:
                file.OpenFile("data11.txt");
                OctopusGrid octogrid = new OctopusGrid(file.ParseSingleDigitsIntToArray());
                octogrid.PrintGrid();
                octogrid.AdvanceStepUntil(1000);
                octogrid.PrintGrid();
                break;
            case 12:
                file.OpenFile("data12.txt");
                List<Cave> caveList = new ArrayList<>(file.ParseDataToCave("-"));
                List<Line> lineList = new ArrayList<>(file.ParseDataToLine("-",caveList));
                Routing routing = new Routing(caveList, lineList, 1, false);
                routing.StartSearch();
                break;
            case 15:
                file.OpenFile("data15.txt");
                int size;
                List<Integer> intList = new ArrayList<>(file.ParseLinesToSingleInt(0,file.GetDataLength()));
                size = (int) Math.sqrt(intList.size());
                AStar caveNodes = new AStar(size);
                caveNodes.SetPenalty(intList);
                caveNodes.FindRoute();
                caveNodes.PrintNodes();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + part);
        }
    }
}
