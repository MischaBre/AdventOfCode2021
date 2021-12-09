package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileReader {

    private final List<String> data;
    private int dataLength;


    public FileReader() {
        //init
        data = new ArrayList<String>();
    }

    public void OpenFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner myScanner = new Scanner(file);

            while (myScanner.hasNextLine()) {
                data.add(myScanner.nextLine());
            }

            myScanner.close();

        } catch(Exception e)  {
            System.out.println("Fehler!");
        }

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) == "") {
                data.remove(i);
            }
        }

        dataLength = data.size();
    }

    public int GetDataLength() { return dataLength; }

    public void PrintData(int maxInt) {
        for (int i = 0; i < (maxInt < this.data.size() ? maxInt : this.data.size()); i++) {
            System.out.println(this.data.get(i));
        }
    }

    public void StripFirstLine() {
        data.remove(0);
    }

    public List<Integer> ParseSingleLineToInt(int i, String reg) {
        return Arrays.stream(data.get(i).split(reg)).map(num -> Integer.parseInt(num.trim())).collect(Collectors.toList());
    }

    public List<Integer> ParseLinesToInt(int i, int j) {
        List<Integer> list = new ArrayList<Integer>();
        for (int x = i; x < j; x++) {
            for (String s : data.get(x).split(" ")) {
                if (s != "") { list.add(Integer.parseInt(s)); }
            }
        }
        return list;
    }

    public List<Integer> ParseDataToInt() {
        return data.stream().map(num -> Integer.parseInt(num.trim())).collect(Collectors.toList());
    }

    public List<Integer> ParseBinaryDataToInt() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < this.data.size(); i++) {
            int val = 0;
            for (char c : data.get(i).toCharArray()) {
                val <<= 1;
                val += c-'0';
            }
            list.add(val);
        }
        return list;
    }

    public List<String> ParseDataToString() {
        return this.data;
    }

    public List<Instruction> ParseDataToInstruction() {
        List<Instruction> list = new ArrayList<Instruction>();
        String[] line = new String[2];
        for (int i = 0; i < this.data.size(); i++) {
            line = this.data.get(i).split(" ");
            //Instruction instruction = new Instruction("",0);
            //instruction.SetInstruction(line[0], Integer.parseInt(line[1]));
            list.add(new Instruction(line[0], Integer.parseInt(line[1])));
        }
        return list;
    }
}
