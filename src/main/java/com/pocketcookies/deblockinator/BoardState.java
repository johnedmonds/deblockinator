package com.pocketcookies.deblockinator;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * The state of the board at one point in time.
 */
public class BoardState {

    private final Collection<Block> blocks;
    private final int width;
    private final int height;
    // Where the exiting block has to reach.
    private final int xExit;
    private final int yExit;

    public BoardState(Collection<Block> blocks, int width, int height, int xExit, int yExit) {
        this.blocks = blocks;
        this.width = width;
        this.height = height;
        this.xExit = xExit;
        this.yExit = yExit;
    }

    List<Movement> getValidMovements() {
        CollisionGrid grid = new CollisionGrid(blocks, width, height);
        ImmutableList.Builder<Movement> builder = ImmutableList.builder();
        for (Block block : blocks) {
            for (Movement movement : generatePotentialMovements(block)) {
                if (!grid.movementBlocked(movement)) {
                    builder.add(movement);
                }
            }
        }
        return builder.build();
    }

    private List<Movement> generatePotentialMovements(Block block) {
        ImmutableList.Builder<Movement> builder = ImmutableList.builder();
        switch (block.getOrientation()) {
            case HORIZONTAL:
                builder
                        .add(new Movement(-1, 0, block))
                        .add(new Movement(1, 0, block));
                break;
            case VERTICAL:
                builder
                        .add(new Movement(0, -1, block))
                        .add(new Movement(0, 1, block));
                break;
            default:
                throw new IllegalArgumentException("Unsupported orientation: " + block.getOrientation() + " for block: " + block);
        }
        return builder.build();
    }

    boolean isEndState() {
        Block exitingBlock = null;
        for (Block block : blocks) {
            if (block.isExitingBlock()) {
                exitingBlock = block;
                break;
            }
        }
        assert exitingBlock != null;
        assert exitingBlock.getOrientation() == Orientation.HORIZONTAL;
        if (exitingBlock.getX() + exitingBlock.getLength() - 1 == xExit && exitingBlock.getY() == yExit) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof BoardState) {
            BoardState state = (BoardState) o;
            return Objects.equal(width, state.width)
                    && Objects.equal(height, state.height)
                    && Objects.equal(xExit, state.xExit)
                    && Objects.equal(yExit, state.yExit)
                    && Objects.equal(blocks, state.blocks);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(width, height, xExit, yExit, blocks);
    }

    BoardState applyMovementToBlocks(Movement movement) {
        ImmutableList.Builder<Block> builder = ImmutableList.builder();
        for (Block block : blocks) {
            if (block == movement.getBlock()) {
                builder.add(new Block(
                        block.getX() + movement.getXDisplacement(),
                        block.getY() + movement.getYDisplacement(),
                        block.getLength(),
                        block.getOrientation(),
                        block.isExitingBlock()));
            } else {
                builder.add(block);
            }
        }
        return new BoardState(builder.build(), width, height, xExit, yExit);
    }
}
