import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class MyCollectionTest {

    Integer[] expected, results;
    int count;
    Iterator<Integer> iterator;
    MyCollection<Integer> myCollection;
    @BeforeEach
    void setUp() {
        count = 0;
        expected = new Integer[]{0, 1, 2, 3, 4, 5};
        myCollection = new MyCollection<>(expected);
        iterator = myCollection.getCollection().iterator();
    }

    @Test
    void getCollection() {
        assertArrayEquals(expected, myCollection.getCollection().toArray());
    }

    @Test
    void setCollection() {
        expected = new Integer[]{5, 4, 3, 2, 1, 0};
        myCollection = new MyCollection<Integer>(expected);
        assertArrayEquals(expected, myCollection.getCollection().toArray());
    }
}