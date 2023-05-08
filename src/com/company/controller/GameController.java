package com.company.controller;

import com.company.model.Board;
import com.company.view.GameView;

import java.util.Scanner;

public class GameController {
    private Board model;
    private GameView view;

    public GameController(){
        this.model = new Board();
        this.view = new GameView();
    }

    private void printResult(){
        view.printGameState(model.getCurrentState(), model.getUncovered());
    }

    private void uncoverField(int i, int j){
        model.uncoverFiled(i, j);
    }

    private boolean checkCoordinates(int i, int j){
        return model.correctCoordinates(i, j);
    }

    private void putFlag(int i, int j){
        model.putFlag(i, j);
    }

    private void deleteFlag(int i, int j){
        model.deleteFlag(i, j);
    }

    private boolean getGameState(){
        return  model.isGameON();
    }

    private void printMenu(){
        view.printMenu();
    }

    private int[] getMove(){
        return view.getMove();
    }

    private void wonGame(){
        view.wonGame();
    }

    private void lostGame(){
        view.lostGame();
    }
    private int getUncovered(){
        return model.getUncovered();
    }

    private int getTaskNumber(){
        return view.getTaskNumber();
    }

    public void playGame(){
        printResult();
        while (getGameState()){
            printMenu();
            int task = getTaskNumber();
            int [] move;
            switch (task){
                case 1:
                    move = getMove();
                    if (checkCoordinates(move[0], move[1])){
                        uncoverField(move[0], move[1]);
                    }
                    break;
                case 2:
                    move = getMove();
                    if (checkCoordinates(move[0], move[1])){
                        putFlag(move[0], move[1]);
                    }
                    break;
                case 3:
                    move = getMove();
                    if (checkCoordinates(move[0], move[1])){
                        deleteFlag(move[0], move[1]);
                    }
                    break;
                case 4:
                    System.exit(0);
                    break;
            }
            printResult();
        }
        if (!getGameState()){
            if (getUncovered() == (model.NUMBER_OF_FIELDS - model.NUMBER_OF_MINES)){
                wonGame();
            }
            else {
                lostGame();
            }
        }

    }

}
