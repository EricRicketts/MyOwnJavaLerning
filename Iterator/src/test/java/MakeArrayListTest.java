import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class MakeArrayListTest {

    MakeArrayList<String> list;
    Iterator<String> iterator;
    @BeforeEach
    void setUp() {
        list = new MakeArrayList();
        list.aryList.add("zero");
        list.aryList.add("one");
        list.aryList.add("two");
        list.aryList.add("three");
        list.aryList.add("four");
        list.aryList.add("five");
        iterator = list.aryList.iterator();
    }

    @Test
    void iterateWithIterator() {
        String[] expected = {"zero", "one", "two", "three", "four", "five"};
        String[] results = new String[6];
        int index = 0;
        while (iterator.hasNext()) {
            String nextString = iterator.next();
            results[index] = nextString;
            index += 1;
        }
        assertArrayEquals(expected, results);
    }
}