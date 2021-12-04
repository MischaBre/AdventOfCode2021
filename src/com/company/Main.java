package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here

        int part = 3;
        Submarine sub = new Submarine(0,0,0);
        FileReader file = new FileReader();

        switch (part) {
            case 1:
                file.OpenFile("data.txt");

            case 2:
                file.OpenFile("data2.txt");
//Part2
                sub.ReadInstructionList(file.ParseDataToInstruction());
                for (int i = 0; i < sub.GetInstructionListLength(); i++) {
                    sub.changePos(sub.GetInstructionById(i).TranslateInstruction());
                }
                System.out.println("Position: " + sub.GetHorizontal() * sub.GetDepth());

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
            default:
                throw new IllegalStateException("Unexpected value: " + part);
        }




    }

}
