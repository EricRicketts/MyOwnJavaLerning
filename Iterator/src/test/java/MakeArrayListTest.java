import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.ListIterator;


class MakeArrayListTest {

    MakeArrayList<String> list;
    Iterator<String> iterator;
    ListIterator<String> listIterator;
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
        listIterator = list.aryList.listIterator();
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

    @Test
    void safelyRemoveAnElementFromAList() {
        while (iterator.hasNext()) {
            String nextString = iterator.next();
            if (nextString.equals("four")) iterator.remove();
        }
        assertEquals(5, list.aryList.size());
    }

    @Test
    void forEachRemainingMethod() {
        String[] str = new String[1];
        iterator.forEachRemaining(element -> {
            if (element.equals("three")) str[0] = element;
        });
        assertEquals("three", str[0]);
    }

    @Test
    void listIteratorGoingForwardsAndBackwards() {
        // an important point of understanding here, an iterator starts where it left off last
        // so in order to go backwards in a list I need to start at the back of a list
        String[] expectedOne = {"zero", "one", "two", "three", "four", "five"};
        String[] expectedTwo = {"five", "four", "three", "two", "one", "zero"};
        String[] resultsOne = new String[6];
        String[] resultsTwo = new String[6];
        int count = 0;
        while (listIterator.hasNext()) {
            resultsOne[count] = listIterator.next();
            count += 1;
        }
        count = 0;
        while (listIterator.hasPrevious()) {
            resultsTwo[count] = listIterator.previous();
            count += 1;
        }
        assertArrayEquals(expectedTwo, resultsTwo);
    }
}