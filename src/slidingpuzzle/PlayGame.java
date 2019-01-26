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
public class PlayGame {

    private Vector<GridState> gridStates = new Vector<>();
    private Vector<GridState> blackList = new Vector<>();

    public PlayGame(GridState gridState) {
        this.gridStates.add(gridState);
    }

    public void addState(GridState gridState) {
        this.gridStates.add(gridState);
    }
    public void removeState(GridState gridState) {
        if(this.gridStates.contains(gridState)) {
            this.gridStates.remove(gridState);
        }
    }
    
    public void addToTheBlackList(GridState gridState) {
        this.blackList.add(gridState);
    }

    public void undoLastStep() {
        GridState badChoice = this.gridStates.remove(this.gridStates.size() - 1);
        this.blackList.add(badChoice);
    }

    public Vector<GridState> getGridStates() {
        return this.gridStates;
    }

    public void printGridStates() {
        for (int i = 0; i < gridStates.size(); i++) {
            gridStates.elementAt(i).printState();
        }
    }

    public boolean repeatedStatus(GridState gridState) {
        for (int i = 0; i < this.gridStates.size(); i++) {
            GridState elementAt = this.gridStates.elementAt(i);
            if (elementAt.areEqual(gridState)) {
                return true;
            }
        }
        for (int i = 0; i < this.blackList.size(); i++) {
            GridState elementAt = this.blackList.elementAt(i);
            if (elementAt.areEqual(gridState)) {
                return true;
            }
        }
        return false;
    }

    void moveLeft(int x, int y) {

        GridState lastState = this.gridStates.lastElement();
        int width = lastState.getWidth();
        int height = lastState.getHeight();
        if (lastState.canMoveLeft(x, y)) {
            int tempArray[][];
            tempArray = lastState.cloneState();
            tempArray[y][x - 1] = tempArray[y][x];
            tempArray[y][x] = 0;
            lastState = new GridState(width, height);
            lastState.setState(tempArray);
            this.gridStates.add(lastState);
            lastState.printState();
        } else {
            System.out.println("the block can not be moved left!");
            System.out.println("--------------------");
        }
    }

    void moveRight(int x, int y) {

        GridState lastState = this.gridStates.lastElement();
        int width = lastState.getWidth();
        int height = lastState.getHeight();
        if (lastState.canMoveRight(x, y)) {
            int tempArray[][];
            tempArray = lastState.cloneState();
            tempArray[y][x + 1] = tempArray[y][x];
            tempArray[y][x] = 0;
            lastState = new GridState(width, height);
            lastState.setState(tempArray);
            this.gridStates.add(lastState);
            lastState.printState();
        } else {
            System.out.println("the block can not be moved right!");
            System.out.println("--------------------");
        }
    }

    void moveUp(int x, int y) {

        GridState lastState = this.gridStates.lastElement();
        int width = lastState.getWidth();
        int height = lastState.getHeight();
        if (lastState.canMoveUp(x, y)) {
            int tempArray[][];
            tempArray = lastState.cloneState();
            tempArray[y - 1][x] = tempArray[y][x];
            tempArray[y][x] = 0;
            lastState = new GridState(width, height);
            lastState.setState(tempArray);
            this.gridStates.add(lastState);
            lastState.printState();
        } else {
            System.out.println("the block can not be moved up!");
            System.out.println("--------------------");
        }
    }

    void moveDown(int x, int y) {

        GridState lastState = this.gridStates.lastElement();
        int width = lastState.getWidth();
        int height = lastState.getHeight();
        if (lastState.canMoveDown(x, y)) {
            int tempArray[][];
            tempArray = lastState.getState().clone();
            tempArray[y + 1][x] = tempArray[y][x];
            tempArray[y][x] = 0;
            lastState = new GridState(width, height);
            lastState.setState(tempArray);
            this.gridStates.add(lastState);
            lastState.printState();
        } else {
            System.out.println("the block can not be moved down!");
            System.out.println("--------------------");
        }
    }

    public GridState getLastState() {
        return this.gridStates.lastElement();
    }

    public void printLastState() {
        this.gridStates.lastElement().printState();
    }

    public Vector<GridState> enabledStates(GridState theState) {

        int tempArray[][];
        int xEmpty = 0;
        int yEmpty = 0;
        Vector<GridState> enabledStates = new Vector<>(0);
        for (int i = 0; i < theState.getHeight(); i++) {
            for (int j = 0; j < theState.getWidth(); j++) {
                if (theState.getState()[i][j] == 0) {
                    xEmpty = i;
                    yEmpty = j;
                }
            }
        }

        if (yEmpty > 0) {
            tempArray = theState.cloneState();
            tempArray[xEmpty][yEmpty] = tempArray[xEmpty][yEmpty - 1];
            tempArray[xEmpty][yEmpty - 1] = 0;
            GridState enableState1 = new GridState(theState.getWidth(), theState.getHeight());
            enableState1.setState(tempArray);
            enabledStates.add(enableState1);
            System.out.println("the first enable state:");
            enableState1.printState();
        }
        if (xEmpty > 0) {
            tempArray = theState.cloneState();
            tempArray[xEmpty][yEmpty] = tempArray[xEmpty - 1][yEmpty];
            tempArray[xEmpty - 1][yEmpty] = 0;
            GridState enableState2 = new GridState(theState.getWidth(), theState.getHeight());
            enableState2.setState(tempArray);
            enabledStates.add(enableState2);
            System.out.println("the second enable state:");
            enableState2.printState();
        }
        if (xEmpty < (theState.getHeight() - 1)) {
            tempArray = theState.cloneState();
            tempArray[xEmpty][yEmpty] = tempArray[xEmpty + 1][yEmpty];
            tempArray[xEmpty + 1][yEmpty] = 0;
            GridState enableState3 = new GridState(theState.getWidth(), theState.getHeight());
            enableState3.setState(tempArray);
            enabledStates.add(enableState3);
            System.out.println("the third enable state:");
            enableState3.printState();
        }
        if (yEmpty < (theState.getWidth() - 1)) {
            tempArray = theState.cloneState();
            tempArray[xEmpty][yEmpty] = tempArray[xEmpty][yEmpty + 1];
            tempArray[xEmpty][yEmpty + 1] = 0;
            GridState enableState4 = new GridState(theState.getWidth(), theState.getHeight());
            enableState4.setState(tempArray);
            enabledStates.add(enableState4);
            System.out.println("the forth enable state:");
            enableState4.printState();
        }
        return enabledStates;
    }

    public Vector<GridState> enabledNotRepeatedStates(GridState theState) {

        int tempArray[][];
        int xEmpty = 0;
        int yEmpty = 0;
        Vector<GridState> enabledStates = new Vector<>(0);
        for (int i = 0; i < theState.getHeight(); i++) {
            for (int j = 0; j < theState.getWidth(); j++) {
                if (theState.getState()[i][j] == 0) {
                    xEmpty = i;
                    yEmpty = j;
                }
            }
        }

        if (yEmpty > 0) {
            tempArray = theState.cloneState();
            tempArray[xEmpty][yEmpty] = tempArray[xEmpty][yEmpty - 1];
            tempArray[xEmpty][yEmpty - 1] = 0;
            GridState enableState1 = new GridState(theState.getWidth(), theState.getHeight());
            enableState1.setState(tempArray);
            if (!this.repeatedStatus(enableState1)) {
                enabledStates.add(enableState1);
//                System.out.println("the first enable state:");
//                enableState1.printState();
            }
        }
        if (xEmpty > 0) {
            tempArray = theState.cloneState();
            tempArray[xEmpty][yEmpty] = tempArray[xEmpty - 1][yEmpty];
            tempArray[xEmpty - 1][yEmpty] = 0;
            GridState enableState2 = new GridState(theState.getWidth(), theState.getHeight());
            enableState2.setState(tempArray);
            if (!this.repeatedStatus(enableState2)) {
                enabledStates.add(enableState2);
//                System.out.println("the second enable state:");
//                enableState2.printState();
            }
        }
        if (xEmpty < (theState.getHeight() - 1)) {
            tempArray = theState.cloneState();
            tempArray[xEmpty][yEmpty] = tempArray[xEmpty + 1][yEmpty];
            tempArray[xEmpty + 1][yEmpty] = 0;
            GridState enableState3 = new GridState(theState.getWidth(), theState.getHeight());
            enableState3.setState(tempArray);
            if (!this.repeatedStatus(enableState3)) {
                enabledStates.add(enableState3);
//                System.out.println("the third enable state:");
//                enableState3.printState();
            }
        }
        if (yEmpty < (theState.getWidth() - 1)) {
            tempArray = theState.cloneState();
            tempArray[xEmpty][yEmpty] = tempArray[xEmpty][yEmpty + 1];
            tempArray[xEmpty][yEmpty + 1] = 0;
            GridState enableState4 = new GridState(theState.getWidth(), theState.getHeight());
            enableState4.setState(tempArray);
            if (!this.repeatedStatus(enableState4)) {
                enabledStates.add(enableState4);
//                System.out.println("the forth enable state:");
//                enableState4.printState();
            }
        }
        return enabledStates;
    }

}
