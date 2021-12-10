package com.company;

public class Instruction {

    private String operation;
    private int value;

    public Instruction(String op, int val) {
        this.operation = op;
        this.value = val;
    }

    public Instruction GetInstruction() {
        return this;
    }

    public String GetOperation() {
        return this.operation;
    }

    public int GetValue() {
        return this.value;
    }

    public void SetInstruction(String op, int val) {
        this.operation = op;
        this.value = val;
    }

    public void SetOperation(String op) {
        this.operation = op;
    }

    public void SetValue(int val) {
        this.value = val;
    }

    public int[] TranslateInstruction() {
        return switch (this.operation) {
            case "up" -> new int[]{0, -value};
            case "down" -> new int[]{0, value};
            case "forward" -> new int[]{value, 0};
            default -> throw new IllegalStateException("Unexpected value: " + this.operation);
        };
    }

}
