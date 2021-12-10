package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileReader {

    private final List<String> data;
    private int dataLength;


    public FileReader() {
        //init
        data = new ArrayList<>();
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
            if (data.get(i).equals("")) {
                data.remove(i);
                i--;
            }
        }

        dataLength = data.size();
    }

    public int GetDataLength() { return dataLength; }

    public void PrintData(int maxInt) {
        for (int i = 0; i < (Math.min(maxInt, this.data.size())); i++) {
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
        List<Integer> list = new ArrayList<>();
        for (String s : data.subList(i,j)) {
            if (!s.equals("")) {
                Matcher m = Pattern.compile("[0-9]+").matcher(s);
                while (m.find()) {
                    list.add(Integer.parseInt(m.group(0)));
                }
            }
        }
        return list;
    }

    public List<Integer> ParseDataToInt() {
        return data.stream().map(num -> Integer.parseInt(num.trim())).collect(Collectors.toList());
    }

    public List<Integer> ParseBinaryDataToInt() {
        List<Integer> list = new ArrayList<>();
        for (String datum : this.data) {
            int val = 0;
            for (char c : datum.toCharArray()) {
                val <<= 1;
                val += c - '0';
            }
            list.add(val);
        }
        return list;
    }

    public List<String> ParseDataToString() {
        return this.data;
    }

    public List<Instruction> ParseDataToInstruction() {
        List<Instruction> list = new ArrayList<>();
        String[] line;
        for (String datum : this.data) {
            line = datum.split(" ");
            list.add(new Instruction(line[0], Integer.parseInt(line[1])));
        }
        return list;
    }
}
