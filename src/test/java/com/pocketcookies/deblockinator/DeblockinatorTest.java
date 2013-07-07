package com.pocketcookies.deblockinator;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import java.util.List;
import junit.framework.TestCase;

/**
 * Tests for {@link Deblockinator}.
 */
public class DeblockinatorTest extends TestCase {

    public void testGetEndState_simple() {
        List<Movement> expectedMovements = ImmutableList.of(
                new Movement(1, 0, new Block(0, 3, 2, Orientation.HORIZONTAL, true)),
                new Movement(1, 0, new Block(1, 3, 2, Orientation.HORIZONTAL, true)),
                new Movement(1, 0, new Block(2, 3, 2, Orientation.HORIZONTAL, true)),
                new Movement(1, 0, new Block(3, 3, 2, Orientation.HORIZONTAL, true)));
        List<Movement> actualMovements = Deblockinator.getPathToEndState(new BoardState(ImmutableList.of(new Block(0, 3, 2, Orientation.HORIZONTAL, true)), 6, 6, 5, 3));
        assertEquals(expectedMovements, actualMovements);
    }
}
