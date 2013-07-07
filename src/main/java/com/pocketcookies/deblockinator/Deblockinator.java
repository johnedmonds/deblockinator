package com.pocketcookies.deblockinator;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class Deblockinator {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
    
    private static List<BoardState> getReachableStates(BoardState boardState) {
        ImmutableList.Builder<BoardState> reachableStates = ImmutableList.builder();
        for (Movement movement : boardState.getValidMovements()) {
            reachableStates.add(boardState.applyMovementToBlocks(movement));
        }
        return reachableStates.build();
    }

    static BoardState getEndState(BoardState startState, Set<BoardState> visitedBoardStates) {
        for (BoardState boardState : getReachableStates(startState)) {
            if (!visitedBoardStates.contains(boardState)) {
                visitedBoardStates.add(boardState);
                if (boardState.isEndState()) {
                    return boardState;
                }
                BoardState foundEndState = getEndState(boardState, visitedBoardStates);
                if (foundEndState != null) {
                    return foundEndState;
                }
            }
        }
        return null;
    }
}
