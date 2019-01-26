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
public class UCS {

    private final PlayGame playGame;

    public UCS(PlayGame playGame) {
        this.playGame = playGame;
    }

    public void solvePuzzle() {
        boolean found = false;
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
            current = toVisit.poll();
            if (current.checkIdealState()) {
                current.printState();
                System.out.println("the total cost:" + current.getCost());
                return;
            }
            this.playGame.addToTheBlackList(current);
//            toVisit.addAll(this.playGame.enabledNotRepeatedStates(current));
            for (GridState child : this.playGame.enabledNotRepeatedStates(current)) {
                child.setCost(current.getCost() + child.getCost());
                for (GridState gridState : toVisit) {
                    if(gridState.areEqual(child)) {
                        if(gridState.getCost() > child.getCost()) {
                            gridState.setCost(child.getCost());
                        }
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    toVisit.add(child);
                }
                found = false;
            }
        }
        System.out.println("it's impossible");
    }

}
