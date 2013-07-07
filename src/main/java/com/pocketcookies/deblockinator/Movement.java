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
class Movement {

    private final int xDisplacement;
    private final int yDisplacement;
    private final Block block;

    public Movement(int xDisplacement, int yDisplacement, Block block) {
        this.xDisplacement = xDisplacement;
        this.yDisplacement = yDisplacement;
        this.block = block;
    }

    int getXDisplacement() {
        return xDisplacement;
    }

    int getYDisplacement() {
        return yDisplacement;
    }

    Block getBlock() {
        return block;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Movement) {
            Movement movement = (Movement) o;
            return Objects.equal(xDisplacement, movement.xDisplacement)
                    && Objects.equal(yDisplacement, movement.yDisplacement)
                    && Objects.equal(block, movement.block);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(xDisplacement, yDisplacement, block);
    }
}
