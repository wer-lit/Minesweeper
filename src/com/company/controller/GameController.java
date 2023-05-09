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

    private int getNumberToUncover(){
        return model.getTotalToUncover();
    }

    public void playGame(){
        printResult();
        while (getGameState()){
            printMenu();
            int task = getTaskNumber() - 1;
            String[] tasks = {"UNCOVER", "PUT", "DELETE", "EXIT"};
            String taskStr = tasks[task];
            int [] move;
            switch (taskStr){
                case "UNCOVER":
                    move = getMove();
                    if (checkCoordinates(move[0], move[1])){
                        uncoverField(move[0], move[1]);
                    }
                    break;
                case "PUT":
                    move = getMove();
                    if (checkCoordinates(move[0], move[1])){
                        putFlag(move[0], move[1]);
                    }
                    break;
                case "DELETE":
                    move = getMove();
                    if (checkCoordinates(move[0], move[1])){
                        deleteFlag(move[0], move[1]);
                    }
                    break;
                case "EXIT":
                    System.exit(0);
                    break;
            }
            printResult();
        }
        if (!getGameState()){
            if (getUncovered() == getNumberToUncover()){
                wonGame();
            }
            else {
                lostGame();
            }
        }

    }

}
