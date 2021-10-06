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
}
