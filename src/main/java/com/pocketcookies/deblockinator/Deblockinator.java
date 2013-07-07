package com.pocketcookies.deblockinator;

import com.google.common.collect.Sets;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class Deblockinator {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
    private static Set<BoardState> visitedBoardStates = Sets.newHashSet();

    static BoardState getEndState(BoardState startState) {
        for (BoardState boardState : startState.getReachableStates()) {
            if (!visitedBoardStates.contains(boardState)) {
                visitedBoardStates.add(boardState);
                if (boardState.isEndState()) {
                    return boardState;
                }
                BoardState foundEndState = getEndState(boardState);
                if (foundEndState != null) {
                    return foundEndState;
                }
            }
        }
        return null;
    }
}
