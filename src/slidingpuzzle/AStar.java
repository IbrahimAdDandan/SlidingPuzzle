/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slidingpuzzle;

import java.util.PriorityQueue;

/**
 *
 * @author #k
 */
public class AStar {

    private final PlayGame playGame;

    public AStar(PlayGame playGame) {
        this.playGame = playGame;
    }

    public void solvePuzzle() {
        boolean found = false;
        int iterator = 0;
        GridState current = this.playGame.getLastState();
        PriorityQueue<GridState> toVisit = new PriorityQueue<>((o1, o2) -> {
            if (o1.getCost() > o2.getCost()) {
                return 1;
            }
            if (o2.getCost() > o1.getCost()) {
                return -1;
            }
            return 0;
        });

        toVisit.add(current);
        while (!toVisit.isEmpty()) {
            iterator++;
            current = toVisit.poll();
            if (current.checkIdealState()) {
                current.printState();
                System.out.println("the total cost:" + current.getCost());
                return;
            }
            this.playGame.addToTheBlackList(current);
//            toVisit.addAll(this.playGame.enabledNotRepeatedStates(current));
            for (GridState child : this.playGame.enabledNotRepeatedStates(current)) {
//                int w = this.playGame.getLastState().getHeight();
//                int height = this.playGame.getLastState().getHeight();
//                int h = (w * height) - 2 * (w + height) - iterator;
                child.setCost(current.getCost() + child.getCost() + (iterator*10));
//                child.setCost(current.getCost() + child.getCost() + h);
                for (GridState gridState : toVisit) {
                    if (gridState.areEqual(child)) {
                        if (gridState.getCost() > child.getCost()) {
                            gridState.setCost(child.getCost());
                        }
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    toVisit.add(child);
                }
                found = false;
            }
        }
        System.out.println("it's impossible");
    }

}
