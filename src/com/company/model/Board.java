package com.company.model;

import java.util.Random;

public class Board {
    private Field[][] board;
    private int uncovered;
    private boolean gameON;
    private final int BOARD_SIZE = 10;
    private final int NUMBER_OF_MINES = 10;
    private final int NUMBER_OF_FIELDS = 100;
    private final char DEFAULT_SYMBOL = 'X';
    private final char FLAG = 'F';
    private final char MINE = '#';
    private static final int[][] DIRECTIONS = {{1, 0}, {1, 1}, {1, -1}, {0, 1}, {0, -1}, {-1, 0}, {-1, 1}, {-1, -1}};


    public Board(){
        this.board = new Field[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                this.board[i][j] = new Field();
            }
        }
        uncovered = 0;
        gameON = true;
        setMines();
        countMines();

    }

    public char[][] getCurrentState(){
        char [][] tempBoard = new char[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                tempBoard[i][j] = board[i][j].getSymbol();
            }
        }
        return tempBoard;
    }

    private void setMines(){
        Random random = new Random();
        int placedMines = 0;
        while (placedMines < NUMBER_OF_MINES){
            int i = random.nextInt(BOARD_SIZE);
            int j = random.nextInt(BOARD_SIZE);
            if (!board[i][j].isMine()){
                board[i][j].setMine(true);
                placedMines++;
            }
        }
    }

    public boolean correctCoordinates(int i, int j){
        return 0 <= i && i < BOARD_SIZE && 0 <= j && j < BOARD_SIZE;
    }

    private void countMines(){
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                if (!board[i][j].isMine()){
                    int counter = 0;
                    for(int[] direction : DIRECTIONS){
                        int newI = i + direction[0];
                        int newJ = j + direction[1];
                        if (correctCoordinates(newI, newJ) && board[newI][newJ].isMine()){
                            counter++;
                        }

                    }
                    board[i][j].setMinesCounter(counter);
                }
            }
        }
    }

//    public void printCounter(){
//        for (int i = 0; i < BOARD_SIZE; i++) {
//            for (int j = 0; j < BOARD_SIZE; j++) {
//                System.out.print(board[i][j].getMinesCounter() + " ");
//            }
//            System.out.println();
//        }
//    }
//
//    public void printMines(){
//        for (int i = 0; i < BOARD_SIZE; i++) {
//            for (int j = 0; j < BOARD_SIZE; j++) {
//                System.out.print(board[i][j].isMine() + " ");
//            }
//            System.out.println();
//        }
//    }

    public void uncoverFiled(int i, int j){
        if (board[i][j].isMine()){
            loseGame();
            gameON = false;
        }
        else {
            if (board[i][j].getSymbol() != FLAG){
                board[i][j].setSymbol(String.valueOf(board[i][j].getMinesCounter()).charAt(0));
                uncovered++;
                if (uncovered == (NUMBER_OF_FIELDS - NUMBER_OF_MINES)){
                    gameON = false;
                }
                if (board[i][j].getMinesCounter() == 0){
                    uncoverSurroundingFields(i, j);
                }
            }

        }

    }

    public void putFlag(int i, int j){
        if (board[i][j].getSymbol() == DEFAULT_SYMBOL){
            board[i][j].setSymbol(FLAG);
        }
    }

    public void deleteFlag(int i, int j){
        if (board[i][j].getSymbol() == FLAG){
            board[i][j].setSymbol(DEFAULT_SYMBOL);
        }
    }


    private void uncoverSurroundingFields(int i, int j){
        for (int[] direction : DIRECTIONS){
            int newI = i + direction[0];
            int newJ = j + direction[1];
            if (correctCoordinates(newI, newJ) && board[newI][newJ].getSymbol() == DEFAULT_SYMBOL){
                uncoverFiled(newI, newJ);
            }
        }
    }

    private void loseGame(){
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j].isMine()) {
                    board[i][j].setSymbol(MINE);
                } else {
                    board[i][j].setSymbol(String.valueOf(board[i][j].getMinesCounter()).charAt(0));
                }

            }
        }
    }

    public int getUncovered() {
        return uncovered;
    }

    public boolean isGameON() {
        return gameON;
    }

    public int getTotalToUncover(){
        return NUMBER_OF_FIELDS - NUMBER_OF_MINES;
    }

}
