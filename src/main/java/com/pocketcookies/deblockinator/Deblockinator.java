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
    
    static List<Movement> getPathToEndState(BoardState startState) {
        return getPathToEndState(startState, Sets.<BoardState>newHashSet()).build().reverse();
    }
    
    private static ImmutableList.Builder<Movement> getPathToEndState(BoardState startState, Set<BoardState> visitedBoardStates) {
        for (Movement movement : startState.getValidMovements()) {
            BoardState boardState = startState.applyMovementToBlocks(movement);
            if (!visitedBoardStates.contains(boardState)) {
                visitedBoardStates.add(boardState);
                if (boardState.isEndState()) {
                    return ImmutableList.<Movement>builder().add(movement);
                }
                ImmutableList.Builder<Movement> foundPath = getPathToEndState(boardState, visitedBoardStates);
                if (foundPath != null) {
                    return foundPath.add(movement);
                }
            }
        }
        return null;
    }
}
