/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pocketcookies.deblockinator;

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
}
