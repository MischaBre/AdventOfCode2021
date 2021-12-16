package com.company;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Comparator.naturalOrder;

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

    public List<Integer> ParseLinesToSingleInt(int i, int j) {
        List<Integer> list = new ArrayList<>();
        for (String s : data.subList(i,j)) {
            if (!s.equals("")) {
                Matcher m = Pattern.compile("[0-9]").matcher(s);
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

    public int[] ParseSingleDigitsIntToArray() {
        int index = 0;
        int[] array = new int[100];
        for (String s : data) {
            for (char c : s.toCharArray()) {
                array[index] = Integer.parseInt(String.valueOf(c));
                index++;
            }
        }
        return array;
    }

    public List<String> ParseDataToString() {
        return this.data;
    }

    public List<Cave> ParseDataToCave(String reg) {
        List<Cave> list = new ArrayList<>();
        for (String l : data) {
            String[] result = l.split(reg);
            for (String r : result) {
                boolean exists = false;
                for (Cave c : list) {
                    if (!exists) {
                        exists = (c.name.equals(r));
                    }
                }
                if (!exists) {
                    list.add(new Cave(r, r.charAt(0) < 97, r.equals("start"), r.equals("end")));
                }
            }
        }
        return list;
    }

    public List<Line> ParseDataToLine(String reg, List<Cave> caveList) {
        List<Line> list = new ArrayList<>();
        for (String l : data) {
            String[] result = l.split(reg);
            Cave fromCave = caveList.get(0);
            Cave toCave = caveList.get(0);
            for (Cave c : caveList) {
                if (c.name.equals(result[0])) {
                    fromCave = c;
                }
                if (c.name.equals(result[1])) {
                    toCave = c;
                }
            }
            list.add(new Line(fromCave, toCave));
        }
        return list;
    }

    public List<Cave> ParseLineListToCaveList(List<Line> lineList) {
        List<Cave> caveList = new ArrayList<>();
        for (Line l : lineList) {
            caveList.add(l.fromCave);
            caveList.add(l.toCave);
        }
        return new ArrayList<>(new HashSet<>(caveList));
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
