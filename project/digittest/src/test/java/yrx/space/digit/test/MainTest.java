package yrx.space.digit.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Stack;

/**
 * Created by r.x on 2019/6/4.
 */
public class MainTest {

    @Test
    public void testMain() {
        int[] digits = new int[]{1};
        Stack<List<String>> result = Main.convertDigit2Letter(digits);
        String expected = "[]";
        Assert.assertEquals(expected, result.toString());

        digits = new int[]{1, 2};
        result = Main.convertDigit2Letter(digits);
        expected = "[[a, b, c]]";
        Assert.assertEquals(expected, result.toString());

        digits = new int[]{1, 2, 3};
        expected = "[[ad, ae, af, bd, be, bf, cd, ce, cf]]";
        result = Main.convertDigit2Letter(digits);
        Assert.assertEquals(expected, result.toString());

        digits = new int[]{1, 2, 3, 1, 2};
        expected = "[[ada, adb, adc, aea, aeb, aec, afa, afb, afc, bda, bdb, bdc, bea, beb, bec, bfa, bfb, bfc, cda, cdb, cdc, cea, ceb, cec, cfa, cfb, cfc]]";
        result = Main.convertDigit2Letter(digits);
        Assert.assertEquals(expected, result.toString());

        digits = new int[]{1, 20, 2, 99, 2};
        expected = "[[aa, ab, ac, ba, bb, bc, ca, cb, cc]]";
        result = Main.convertDigit2Letter(digits);
        Assert.assertEquals(expected, result.toString());
    }
}
