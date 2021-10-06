import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EssentialRegularExpressionsTest {

    String regex;
    String text1, text2, text3;
    Pattern pattern;
    Matcher matcher;
    @Test
    void aSimpleMatch() {
        Integer[] expected = {5, 7, 23, 25, 68, 70};
        List<Integer> results = new ArrayList<>();
        regex = "\\bis\\b";
        text1 = "This is the text which is to be search for occurrences of the word 'is'.";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text1);
        while (matcher.find()) {
            Integer start = matcher.start();
            Integer offset = matcher.end();
            results.add(start);
            results.add(offset);
        }
        assertArrayEquals(expected, results.toArray());
    }

    @Test
    void beginningAndEndingOfALine() {
        int start, end;
        int[] expected = {0, 0, 20, 20};
        int[] results = new int[4];
        text1 = "Line 1\nLine 2\nLine 3";
        regex = "^";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(text1);
        while (matcher.find()) {
            start = matcher.start();
            end = matcher.end();
            results[0] = start;
            results[1] = end;
        }
        regex = "$";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(text1);
        while (matcher.find()) {
            start = matcher.start();
            end = matcher.end();
            results[2] = start;
            results[3] = end;
        }
        assertArrayEquals(expected, results);
    }

    @Test
    void nonWordBoundary() {
        int index = 0;
        int[] expected = {1, 1, 2, 2, 3, 3, 6, 6, 7, 7};
        int[] results = new int[expected.length];
        regex = "\\B";
        text1 = "Mary had";
        pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text1);
        while (matcher.find()) {
            results[index] = matcher.start();
            index += 1;
            results[index] = matcher.end();
            index += 1;
        }
        assertArrayEquals(expected, results);
    }

    @Test
    void oneTimePatternMatching() {
        // when using Pattern.matches the regex must match the entire line, this is pathetic
        // Pattern.matches should exhibit the same behavior as when you compile a pattern and match
        // it against a text
        boolean[] expected = {false, true};
        boolean[] results = new boolean[2];
        text1 = "foobar fizzbuzz";
        regex = "ob";
        boolean result = Pattern.matches(regex, text1);
        results[0] = result;
        regex = ".*ob.*";
        result = Pattern.matches(regex, text1);
        results[1] = result;
        assertArrayEquals(expected, results);
    }

    @Test
    void patternMatchingWithSecondArgument() {
        Integer[] expected = {2, 4, 8, 10};
        List<Integer> results = new ArrayList<>();
        text1 = "A [T or [t";
        regex = "\\[t";
        pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(text1);
        while (matcher.find()) {
            results.add(matcher.start());
            results.add(matcher.end());
        }
        assertArrayEquals(expected, results.toArray());
    }
}
