/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pocketcookies.deblockinator;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import java.util.Arrays;
import junit.framework.TestCase;

/**
 *
 * @author jack
 */
public class ParserTest extends TestCase {

    private static final Function<String, char[]> TO_CHAR_ARRAY = new Function<String, char[]>() {
        public char[] apply(String f) {
            return f.toCharArray();
        }
    };

    private static char[][] toCharArray(String[] lines) {
        return transpose(Lists.transform(Arrays.asList(lines), TO_CHAR_ARRAY).toArray(new char[][]{{}}));
    }

    private static char[][] transpose(char[][] in) {
        char[][] out = new char[in[0].length][in.length];
        for (int i = 0; i < in.length; i++) {
            for (int j = 0; j < in[0].length; j++) {
                out[j][i] = in[i][j];
            }
        }
        return out;
    }

    public void testParse() {
        BoardState actualState = Parser.parse(toCharArray(new String[]{
            "Hhh.Hhh.",
            "Hhh.V   ",
            "    v   ",
            "X.  v   ",
            "    .   ",}), 1, 1);
        assertEquals(new BoardState(ImmutableSet.of(
                new Block(0, 0, 4, Orientation.HORIZONTAL, false),
                new Block(4, 0, 4, Orientation.HORIZONTAL, false),
                new Block(0, 1, 4, Orientation.HORIZONTAL, false),
                new Block(4, 1, 4, Orientation.VERTICAL, false),
                new Block(0, 3, 2, Orientation.HORIZONTAL, true)), 8, 5, 1, 1),
                actualState);
    }
}
