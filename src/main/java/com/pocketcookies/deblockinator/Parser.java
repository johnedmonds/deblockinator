/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pocketcookies.deblockinator;

import com.google.common.collect.ImmutableSet;

/**
 *
 * @author jack
 */
public class Parser {

    private static class BlockBuilder {

        private Orientation orientation;
        private int x;
        private int y;
        private int length;
        private boolean exiting;

        BlockBuilder setOrientation(Orientation orientation) {
            this.orientation = orientation;
            return this;
        }

        BlockBuilder setX(int x) {
            this.x = x;
            return this;
        }

        BlockBuilder setY(int y) {
            this.y = y;
            return this;
        }

        BlockBuilder setLength(int length) {
            this.length = length;
            return this;
        }

        BlockBuilder setExiting(boolean exiting) {
            this.exiting = exiting;
            return this;
        }

        Block build() {
            return new Block(x, y, length, orientation, exiting);
        }
    }

    static BoardState parse(char[][] grid, int xExit, int yExit) {
        ImmutableSet.Builder<Block> blocks = ImmutableSet.builder();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                switch (grid[i][j]) {
                    case 'H':
                    case 'X':
                        blocks.add(new BlockBuilder()
                                .setExiting(grid[i][j]=='X')
                                .setLength(getLength(grid,i,j, Orientation.HORIZONTAL))
                                .setOrientation(Orientation.HORIZONTAL)
                                .setX(i)
                                .setY(j)
                                .build());
                        break;
                    case 'V':
                        blocks.add(new BlockBuilder()
                                .setExiting(false)
                                .setLength(getLength(grid,i,j, Orientation.VERTICAL))
                                .setOrientation(Orientation.VERTICAL)
                                .setX(i)
                                .setY(j)
                                .build());
                }
            }
        }
        return new BoardState(blocks.build(), grid.length, grid[0].length, xExit, yExit);
    }

    private static int getLength(char[][] grid, int x, int y, Orientation orientation) {
        switch (orientation) {
            case HORIZONTAL:
                for (int length = 1; length < grid.length; length++) {
                    if (grid[length + x][y] == '.') {
                        return length + 1;
                    }
                }
            case VERTICAL:
                for (int length = 1; length < grid[x].length; length++) {
                    if (grid[x][length + y] == '.') {
                        return length + 1;
                    }
                }
            default:
                throw new IllegalArgumentException("Invalid orientation: " + orientation);
        }
    }
}
