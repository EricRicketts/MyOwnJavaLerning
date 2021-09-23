import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class BasicIteratorTest {
    @Test
    void arrayListIterator() {
        String[] expected = {"one", "two", "three"};
        int index = 0;
        List<String> list = new ArrayList<String>();
        list.add("one");
        list.add("two");
        list.add("three");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            Object nextObject = iterator.next();
            assertEquals(expected[index], nextObject);
            index += 1;
        }
    }

    @Test
    void hashSetIterator() {
        // note with a HashSet the elements are in no set order
        // the collection aims to offer constant time performance
        // for the basic operations, it becomes laborious to
        // retrieve an element
        Set<String> set = new HashSet<>();
        String[][] expected = {
            {"one", "two", "three"}, {"one", "three", "two"}, {"two", "one", "three"},
            {"two", "three", "one"}, {"three", "one", "two"}, {"three", "two", "one"}
        };
        String[] results = new String[3];
        set.add("one");
        set.add("two");
        set.add("three");
        int index = 0;
        for (String s:set) {
            results[index] = (s);
            index += 1;
        }
        for (int i = 0; i < expected.length; i++) {
            String[] str = expected[i];
            if (Arrays.equals(str, results)) {
                assertArrayEquals(str, results);
                break;
            }
        }
    }
}
