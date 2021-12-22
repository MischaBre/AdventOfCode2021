package com.company;

public class TrenchMap {

    private String algorithm;
    private char[][] image;
    private int size;
    private int overhead;

    public TrenchMap(int size) {
        this.algorithm = "";
        this.size = size*4;
        this.image = new char[this.size][this.size];
        image = InitImage();
    }

    public char[][] InitImage() {
        char[][] init = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                init[i][j] = '.';
            }
        }
        return init;
    }

    public void PrintImage() {
        System.out.println(size + "x" + size);
        for (char[] c : image) {
            for (char _c : c) {
                System.out.printf(String.valueOf(_c));
            }
            System.out.printf("%n");
        }
    }

    public void LoadImage(char[][] input) {
        for (int i = 3*size/8; i < 5*size/8; i++) {
            for (int j = 3*size/8; j < 5*size/8; j++) {
                image[i][j] = input[i-3*size/8][j-3*size/8];
            }
        }
    }

    public void LoadAlgorithm(String algo) {
        algorithm = algo;
    }

    public void EnhanceStep() {
        char[][] newImage;
        newImage = InitImage();
        for (int i = 1; i < size-1; i++) {
            for (int j = 1; j < size-1; j++) {
                newImage[i][j] = EnhancePixel(j,i);
            }
        }
        image = newImage;
    }

    public char EnhancePixel(int x, int y) {
        StringBuilder result = new StringBuilder("00000000000000000000000");
        for (int i = y-1; i < y+2; i++) {
            for (int j = x-1; j < x+2; j++) {
                result.append(image[i][j]);
            }
        }
        int res = Integer.parseInt(result.toString().replace('#','1').replace('.','0'),2);
        return algorithm.charAt(res);
    }

    public int CountPixels() {
        int count = 0;
        for (int i = 1*size/8; i < 7*size/8; i++) {
            for (int j = 1*size/8; j < 7*size/8; j++) {
                count += (image[i][j] == '#' ? 1 : 0);
            }
        }
        System.out.println(count + " Pixel an");
        return count;
    }

}
