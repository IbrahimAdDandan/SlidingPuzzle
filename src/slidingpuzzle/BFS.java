/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slidingpuzzle;

import java.util.Vector;
import sun.misc.Queue;

/**
 *
 * @author #k
 * 
 */
public class BFS {

    private PlayGame playGame;

    public BFS(PlayGame playGame) {
        this.playGame = playGame;
    }

    public void recursiveSolvePuzzle() {
        Vector<GridState> enableStates = new Vector<>();
        try {
            for (GridState gridState : this.playGame.getGridStates()) {
                if (gridState.checkIdealState()) {
                    gridState.printState();
                    return;
                }
                this.playGame.addToTheBlackList(gridState);
                this.playGame.removeState(gridState);
                enableStates = this.playGame.enabledNotRepeatedStates(gridState);
                for (GridState enableState : enableStates) {
                    this.playGame.addState(enableState);
//                    enableState.printState();
                }
            }
        } catch (Exception ex) {

        }
        this.recursiveSolvePuzzle();
    }

    public void solvePuzzle() {
        Vector<GridState> enableStates = new Vector<>();
        GridState gridState;
        while (!this.playGame.getGridStates().isEmpty()) {
            gridState = this.playGame.getGridStates().remove(0);
            gridState.printState();
            if (gridState.checkIdealState()) {
//                gridState.printState();
                return;
            }
            this.playGame.addToTheBlackList(gridState);
            enableStates = this.playGame.enabledNotRepeatedStates(gridState);
            this.playGame.getGridStates().addAll(enableStates);
            
        }
        System.out.println("it's impossible to solve the puzzle! :(");
    }

}
