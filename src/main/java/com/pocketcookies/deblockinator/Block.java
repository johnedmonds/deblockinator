/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pocketcookies.deblockinator;

import com.google.common.base.Objects;

/**
 *
 * @author jack
 */
class Block {

    private final int x;
    private final int y;
    private final int length;
    private final Orientation orientation;
    private final boolean isExitingBlock;

    public Block(int x, int y, int length, Orientation orientation, boolean isExitingBlock) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.orientation = orientation;
        this.isExitingBlock = isExitingBlock;
    }

    int getLength() {
        return length;
    }

    Orientation getOrientation() {
        return orientation;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    boolean isExitingBlock() {
        return isExitingBlock;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Block) {
            Block block = (Block) o;
            return Objects.equal(x, block.x)
                    && Objects.equal(y, block.y)
                    && Objects.equal(orientation, block.orientation)
                    && Objects.equal(length, block.length);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(x, y, orientation, length);
    }
}
