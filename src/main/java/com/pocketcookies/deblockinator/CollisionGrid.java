/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pocketcookies.deblockinator;

import static com.pocketcookies.deblockinator.Orientation.HORIZONTAL;
import java.util.Collection;

/**
 * A grid containing which spaces are occupied by blocks. It helps us to
 * determine whether a particular move is valid.
 */
class CollisionGrid {

    private final boolean[][] grid;

    public CollisionGrid(Collection<Block> blocks, int width, int height) {
        grid = new boolean[width][height];

        for (Block block : blocks) {
            updateGrid(block, true);
        }
    }

    /**
     * Updates the grid with the block
     *
     * @param block The block to update the grid with.
     * @param addBlock True to add the block and false to remove the block.
     */
    private void updateGrid(Block block, boolean addBlock) {
        for (int i = 0; i < block.getLength(); i++) {
            final int gridX, gridY;
            switch (block.getOrientation()) {
                case HORIZONTAL:
                    gridX = block.getX() + i;
                    gridY = block.getY();
                    break;
                case VERTICAL:
                    gridX = block.getX();
                    gridY = block.getY() + i;
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported orientation: " + block.getOrientation() + " for block: " + block);
            }
            assert grid[gridX][gridY] == !addBlock;
            grid[gridX][gridY] = addBlock;
        }
    }

    public boolean movementBlocked(Movement movement) {
        try {
            updateGrid(movement.getBlock(), false);
            if (movement.getBlock().getX() + movement.getXDisplacement() < 0 || movement.getBlock().getY() + movement.getYDisplacement() < 0) {
                return true;
            }
            switch (movement.getBlock().getOrientation()) {
                case HORIZONTAL:
                    if (movement.getBlock().getX() + movement.getBlock().getLength() - 1 + movement.getXDisplacement() >= grid.length) {
                        return true;
                    }
                    break;
                case VERTICAL:
                    if (movement.getBlock().getY() + movement.getBlock().getLength() - 1 + movement.getYDisplacement() >= grid[0].length) {
                        return true;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported orientation: " + movement.getBlock().getOrientation() + " for block: " + movement.getBlock());
            }

            for (int i = 0; i < movement.getBlock().getLength(); i++) {
                int x;
                int y;
                switch (movement.getBlock().getOrientation()) {
                    case HORIZONTAL:
                        x = movement.getBlock().getX() + i;
                        y = movement.getBlock().getY();
                        break;
                    case VERTICAL:
                        x = movement.getBlock().getX();
                        y = movement.getBlock().getY() + i;
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported orientation: " + movement.getBlock().getOrientation() + " for block: " + movement.getBlock());
                }
                if (grid[x + movement.getXDisplacement()][y + movement.getYDisplacement()] == true) {
                    return true;
                }
            }
            return false;
        } finally {
            updateGrid(movement.getBlock(), true);
        }
    }
}
