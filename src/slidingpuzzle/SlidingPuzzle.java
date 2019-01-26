/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slidingpuzzle;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author #k
 */
public class SlidingPuzzle {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

//        PlayingCmd playingCmd = new PlayingCmd();
//        playingCmd.play();
        Scanner in = new Scanner(System.in);
        System.out.println("please enter the puzzle grid width:");
        int width = in.nextInt();
        System.out.println("please enter the puzzle grid height:");
        int height = in.nextInt();
        GridState gridState = new GridState(width, height);
        gridState.printState();
        PlayGame playGame = new PlayGame(gridState);
//        DFS dfs = new DFS(playGame);
//        dfs.solvePuzzle();
//        dfs.recursiveSolvePuzzle();
        //        playGame.printGridStates();
//        BFS bfs = new BFS(playGame);
//        bfs.solvePuzzle();
//        bfs.recursiveSolvePuzzle();
//        UCS ucs = new UCS(playGame);
//        ucs.solvePuzzle();
        AStar aStar = new AStar(playGame);
        aStar.solvePuzzle();

    }
}
