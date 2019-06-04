package yrx.space.digit.test;

import java.util.*;

/**
 * Created by r.x on 2019/6/4.
 */
public class Main {
    private static final Map<Integer, List<String>> digitLetterMapping = new HashMap<>(10);

    static {
        List<String> one = new ArrayList<>();
        List<String> two = Arrays.asList("a", "b", "c");
        List<String> three = Arrays.asList("d", "e", "f");
        List<String> four = Arrays.asList("g", "h", "i");
        List<String> five = Arrays.asList("j", "k", "l");
        List<String> six = Arrays.asList("m", "n", "o");
        List<String> seven = Arrays.asList("p", "q", "r", "s");
        List<String> eight = Arrays.asList("t", "u", "v");
        List<String> nine = Arrays.asList("w", "x", "y", "z");
        List<String> zero = new ArrayList<>();

        digitLetterMapping.put(1, one);
        digitLetterMapping.put(2, two);
        digitLetterMapping.put(3, three);
        digitLetterMapping.put(4, four);
        digitLetterMapping.put(5, five);
        digitLetterMapping.put(6, six);
        digitLetterMapping.put(7, seven);
        digitLetterMapping.put(8, eight);
        digitLetterMapping.put(9, nine);
        digitLetterMapping.put(0, zero);
    }

    public static Stack<List<String>> convertDigit2Letter(int[] digits) {
        Stack<List<String>> stack = new Stack<>();
        for (int i = digits.length - 1; i > -1; i--) {
            int digit = digits[i];
            if (digit < 2 || digit > 9) {
                continue;
            }
            stack.add(digitLetterMapping.get(digits[i]));
        }
        for (int i = 0; i < digits.length - 1; i++) {
            if (stack.size() == 1) {
                return stack;
            }
            List<String> combination = combine(stack.pop(), stack.pop());
            stack.push(combination);
        }
        return stack;
    }

    private static List<String> combine(List<String> first, List<String> second) {
        List<String> result = new ArrayList<>(first.size() * second.size());
        for (String f : first) {
            for (String s : second) {
                result.add(f + s);
            }
        }
        return result;
    }
}
