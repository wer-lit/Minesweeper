package com.company;

import com.company.controller.GameController;
import com.company.model.Board;
import com.company.view.GameView;

import javax.swing.text.View;

public class Main {

    public static void main(String[] args) {

        GameController controller = new GameController();
        controller.playGame();

    }
}
