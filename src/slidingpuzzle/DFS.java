/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slidingpuzzle;

import java.util.Vector;

/**
 *
 * @author #k
 */
public class DFS {

    private PlayGame playGame;

    public DFS(PlayGame playGame) {
        this.playGame = playGame;
    }

    public void recursiveSolvePuzzle() {
        Vector<GridState> enableStates = new Vector<>();
        try {
//            for (GridState gridState : this.playGame.getGridStates()) {
            GridState gridState = this.playGame.getGridStates().firstElement();
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
                    this.recursiveSolvePuzzle();
                }
//            }
        } catch (Exception ex) {

        }

    }

    public void solvePuzzle() {
        GridState currentState;
        do {
            currentState = this.playGame.getLastState();
            Vector<GridState> enableStates = new Vector<>();
            enableStates = this.playGame.enabledNotRepeatedStates(currentState);
            if (enableStates.isEmpty()) {
                this.playGame.undoLastStep();
                System.out.println("undo the last step");
            } else {
                currentState = enableStates.firstElement();
                this.playGame.addState(currentState);
                currentState.printState();
            }
        } while (!currentState.checkIdealState());
    }

}
