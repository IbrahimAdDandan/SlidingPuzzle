/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slidingpuzzle;

import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author #k
 */
public class PlayingCmd {
    public void play() {
        
//        GridState gridState = new GridState(4, 5);
//        gridState.printState();
//        PlayGame playGame = new PlayGame(gridState);
//        playGame.moveLeft(0, 0);
//        playGame.moveLeft(1, 0);
//        playGame.moveLeft(3, 3);
//        playGame.moveUp(1, 1);
//        playGame.printLastState();
//        Vector<GridState> enableStates = playGame.enabledStates(playGame.getLastState());
//        for (int i = 0; i < enableStates.size(); i++) {
//            GridState elementAt = enableStates.elementAt(i);
//            elementAt.printState();
//        }
//        playGame.printLastState();
        int x;
        int y;
        int choice = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("please enter the puzzle grid width:");
        int width = in.nextInt();
        System.out.println("please enter the puzzle grid height:");
        int height = in.nextInt();
        GridState gridState = new GridState(width, height);
        gridState.printState();
        PlayGame playGame = new PlayGame(gridState);

        while (!playGame.getLastState().checkIdealState()) {
            System.out.println("please enter the width index for element you want to move:");
            x = in.nextInt();
            System.out.println("please enter the height index for element you want to move:");
            y = in.nextInt();
            System.out.println("please enter a number:");
            System.out.println("to move left enter: 1");
            System.out.println("to move right enter: 2");
            System.out.println("to move up enter: 3");
            System.out.println("to move down enter: 4");
            choice = in.nextInt();
            switch (choice) {
                case 1:
                    playGame.moveLeft(x, y);
                    break;
                case 2:
                    playGame.moveRight(x, y);
                    break;
                case 3:
                    playGame.moveUp(x, y);
                    break;
                case 4:
                    playGame.moveDown(x, y);
                    break;
                default:
                    System.out.println("invalid choice :(");
            }
        }
    }
}
