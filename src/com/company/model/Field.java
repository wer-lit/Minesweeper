package com.company.model;

public class Field {
    private int minesCounter;
    private char symbol;
    private boolean isMine;
    private final char DEFAULT_SYMBOL = 'X';

    public Field(){
        this.isMine = false;
        this.symbol = DEFAULT_SYMBOL;
        this.minesCounter = 0;
    }

    public int getMinesCounter() {
        return minesCounter;
    }

    public void setMinesCounter(int minesCounter) {
        this.minesCounter = minesCounter;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }
}
