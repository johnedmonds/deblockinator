package com.pocketcookies.deblockinator;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import junit.framework.TestCase;

/**
 * Tests for {@link Deblockinator}.
 */
public class DeblockinatorTest extends TestCase {

    public void testGetEndState_simple() {
        assertEquals(new BoardState(ImmutableList.of(new Block(4, 3, 2, Orientation.HORIZONTAL, true)), 6, 6, 5, 3),
                Deblockinator.getEndState(new BoardState(ImmutableList.of(new Block(0, 3, 2, Orientation.HORIZONTAL, true)), 6, 6, 5, 3), Sets.<BoardState>newHashSet()));
    }
}
