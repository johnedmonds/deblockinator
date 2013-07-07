package com.pocketcookies.deblockinator;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
        return getPathToEndState(startState, Sets.<BoardState>newHashSet());
    }

    private static class BFSScope {

        private final BoardState boardState;
        private final Movement movement;
        private final List<Movement> previousMovements;

        public BFSScope(BoardState boardState, Movement movement, List<Movement> previousMovements) {
            this.boardState = boardState;
            this.movement = movement;
            this.previousMovements = previousMovements;
        }
    }

    private static List<Movement> getPathToEndState(BoardState startState, Set<BoardState> visitedBoardStates) {
        Queue<BFSScope> scopes = new LinkedList<BFSScope>();
        for (Movement movement : startState.getValidMovements()) {
            scopes.add(new BFSScope(startState, movement, ImmutableList.<Movement>of()));
        }
        while (!scopes.isEmpty()) {
            BFSScope scope = scopes.poll();
            BoardState boardState = scope.boardState.applyMovementToBlocks(scope.movement);
            if (!visitedBoardStates.contains(boardState)) {
                visitedBoardStates.add(boardState);
                if (boardState.isEndState()) {
                    return ImmutableList.<Movement>builder().addAll(scope.previousMovements).add(scope.movement).build();
                }
                for (Movement movement : boardState.getValidMovements()) {
                    scopes.add(new BFSScope(boardState, movement, ImmutableList.<Movement>builder().addAll(scope.previousMovements).add(scope.movement).build()));
                }
            }
        }
        return null;
    }
}
