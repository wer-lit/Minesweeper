package com.company.view;

import java.util.Scanner;

public class GameView {
    public void printGameState(char[][] result, int uncovered){
        System.out.printf("%4s |", "i|j");
        for (int i = 0; i < result.length; i++) {
            System.out.printf("%4s |", i);
        }

        System.out.println("\n------------------------------------------------------------------");

        for (int i = 0; i < result.length; i++) {
            System.out.printf("%4s |", i);
            for (int j = 0; j < result[i].length; j++) {
                System.out.printf("%4s |", result[i][j]);
            }
            System.out.println("\n------------------------------------------------------------------");
        }
        System.out.println("Uncover fields: " + uncovered);
    }

    public int [] getMove(){
        Scanner scanner = new Scanner(System.in);
        int [] move = new int[2];
        System.out.print("Enter i: ");
        move[0] = scanner.nextInt();
        System.out.print("Enter j: ");
        move[1] = scanner.nextInt();
        return move;
    }

    public void printMenu(){
        System.out.println("1 - uncover field");
        System.out.println("2 - put flag");
        System.out.println("3 - delete flag");
        System.out.println("4 - end game");
    }

    public void lostGame(){
        System.out.println("You have lost this game :(");
    }

    public void wonGame(){
        System.out.println("You have won this game :D");

    }

    public int getTaskNumber(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter task number: ");
        return scanner.nextInt();
    }

}
