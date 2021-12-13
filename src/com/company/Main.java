package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here

        int part = 12;
        Submarine sub = new Submarine(0,0,0);

        FileReader file = new FileReader();

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
            default:
                throw new IllegalStateException("Unexpected value: " + part);
        }




    }

}
