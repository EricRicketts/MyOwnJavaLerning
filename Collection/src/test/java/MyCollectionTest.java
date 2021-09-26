import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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

    @Test
    void addAnElementToACollection() {
        expected = new Integer[]{0, 1, 2, 3, 4, 5, 6};
        myCollection.getCollection().add(6);
        assertArrayEquals(expected, myCollection.getCollection().toArray());
    }

    @Test
    void removeAnObjectFromACollection() {
        Integer[] nums = new Integer[]{2, 0, 1, 5, 4, 3};
        Integer[] expected = new Integer[]{2, 1, 5, 4, 3};
        myCollection = new MyCollection<Integer>(nums);
        Integer argument = Integer.valueOf(0);
        myCollection.getCollection().remove(argument);
        assertArrayEquals(expected, myCollection.getCollection().toArray());
    }

    @Test
    void removeAnObjectFromACollectionByIndex() {
        Integer[] nums = new Integer[]{2, 0, 1, 5, 4, 3};
        Integer[] expected = new Integer[]{2, 1, 5, 4, 3};
        myCollection = new MyCollection<Integer>(nums);
        int i = 1;
        myCollection.getCollection().remove(i);
        assertArrayEquals(expected, myCollection.getCollection().toArray());
    }

    @Test
    void addACollectionToAnotherCollection() {
        Integer[] nums = new Integer[]{6, 7, 8, 9};
        List list = Arrays.asList(nums);
        myCollection.getCollection().addAll(list);
        assertEquals(10, myCollection.getCollection().size());
    }

    @Test
    void removeACollectionFromAnotherCollection() {
        Integer[] nums = new Integer[]{2, 3};
        List list = Arrays.asList(nums);
        myCollection.getCollection().removeAll(list);
        Integer[] expected = new Integer[]{0, 1, 4, 5};
        assertEquals(4, myCollection.getCollection().size());
        assertArrayEquals(expected, myCollection.getCollection().toArray());
    }

    @Test
    void collectionContainsAnElement() {
        Integer element = Integer.valueOf(10);
        assertFalse(myCollection.getCollection().contains(element));
    }
}