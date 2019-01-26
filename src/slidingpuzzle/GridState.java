/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slidingpuzzle;

import java.util.Arrays;

/**
 *
 * @author #k
 */
public class GridState {

    private int width;
    private int height;
    private int[][] state;
    private int cost;

    public GridState(int width, int height) {
        this.width = width;
        this.height = height;
        this.state = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.state[i][j] = i + 1;
            }

        }
        this.state[0][0] = 0;
        this.cost = this.state[0][0];
    }
    
    public int compare(GridState o1, GridState o2) {
        if(o1.getCost() > o2.getCost()) {
            return 1;
        }
        if(o2.getCost() > o1.getCost()) {
            return -1;
        }
        return 0;
    }
    
    /**
     * 
     * @return the cost
     */
    public int getCost() {
        return this.cost;
    }
    
    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean canMoveLeft(int x, int y) {
        if (x > 0 && this.state[y][x - 1] == 0) {
            return true;
        }
        return false;
    }

    public boolean canMoveRight(int x, int y) {
        if (x < this.width && this.state[y][x + 1] == 0) {
            return true;
        }
        return false;
    }

    public boolean canMoveUp(int x, int y) {
        if (y > 0 && this.state[y - 1][x] == 0) {
            return true;
        }
        return false;
    }

    public boolean canMoveDown(int x, int y) {
        if (y < this.height && this.state[y + 1][x] == 0) {
            return true;
        }
        return false;
    }

    public boolean checkIdealState() {
        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                if ((i > 0) && (j > 0)) {
                    if (this.state[i][j] == this.state[i - 1][j - 1]) {
                        return false;
                    }
                }
                if (i > 0) {
                    if ((this.state[i][j] == this.state[i - 1][j])) {
                        return false;
                    }
                }
                if (i > 0 && (j + 1) < this.getWidth()) {
                    if (this.state[i][j] == this.state[i - 1][j + 1]) {
                        return false;
                    }
                }
                if (j > 0) {
                    if (this.state[i][j] == this.state[i][j - 1]) {
                        return false;
                    }
                }
                if (j > 0 && (i + 1) < this.getHeight()) {
                    if (this.state[i][j] == this.state[i + 1][j - 1]) {
                        return false;
                    }
                }
                if ((i + 1) < this.getHeight()) {
                    if ((this.state[i][j] == this.state[i + 1][j])) {
                        return false;
                    }
                }
                if ((j + 1) < this.getWidth()) {
                    if ((this.state[i][j] == this.state[i][j + 1])) {
                        return false;
                    }
                }
                if (((i + 1) < this.getHeight()) && (j + 1) < this.getWidth()) {
                    if ((this.state[i][j] == this.state[i + 1][j + 1])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public int[][] cloneState() {
        int[][] tempArray = new int[this.state.length][];
        for (int i = 0; i < this.state.length; i++) {
            tempArray[i] = this.state[i].clone();
        }
        return tempArray;
    }

    public boolean areEqual(GridState gridState) {
        if (this.height != gridState.getHeight()) {
            return false;
        }
        if (this.width != gridState.getWidth()) {
            return false;
        }
        for (int i = 0; i < gridState.getState().length; i++) {
            if (!Arrays.equals(gridState.getState()[i], this.state[i])) {
                return false;
            }
        }

        return true;
    }

    public void printState() {
        System.out.println("***********************");
        System.out.print(" * ");
        for (int j = 0; j < this.getWidth(); j++) {
            System.out.print(j+"  ");
        }
        System.out.println("\n***********************");
        for (int i = 0; i < this.getHeight(); i++) {
            System.out.print(i+"* ");
            for (int j = 0; j < this.getWidth(); j++) {
                System.out.print(this.state[i][j]+"  ");
            }
            System.out.println();
        }
        System.out.println("**********************");
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the state
     */
    public int[][] getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(int[][] state) {
        this.state = state;
        this.cost = this.state[0][0];
    }

   
}
