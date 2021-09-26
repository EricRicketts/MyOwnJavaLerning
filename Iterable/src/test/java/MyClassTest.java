import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class MyClassTest {

    MyClass<String> myList;
    Iterator<String> iterator;
    String[] expected, results;
    int count;
    @BeforeEach
    void setUp() {
        String[] strArray = {"zero", "one", "two", "three", "four", "five"};
        myList = new MyClass<String>(strArray);
        iterator = myList.getArrayList().iterator();
        expected = new String[]{"zero", "one", "two", "three", "four", "five"};
        count = 0;
    }

    @Test
    void getArrayList() {
        assertArrayEquals(expected, myList.getArrayList().toArray());
    }

    @Test
    void setArrayList() {
        String[] strArray = {"five", "four", "three", "two", "one", "zero"};
        expected = new String[]{"five", "four", "three", "two", "one", "zero"};
        myList = new MyClass<>(strArray);
        assertArrayEquals(strArray, myList.getArrayList().toArray());
    }

    @Test
    void iterateUsingForEach() {
        results = new String[6];
        for (String str:myList.getArrayList()) {
            results[count] = str;
            count += 1;
        }
        assertArrayEquals(expected, results);
    }

    @Test
    void iterateUsingAnIterator() {
        results = new String[6];
        while (iterator.hasNext()) {
            results[count] = iterator.next();
            count += 1;
        }
        assertArrayEquals(expected, results);
    }

    @Test
    void iterateUsingForEachMethod() {
        results = new String[6];
        myList.getArrayList().forEach((str) -> {
            results[count] = str;
            count += 1;
        });
        assertArrayEquals(expected, results);
    }

}