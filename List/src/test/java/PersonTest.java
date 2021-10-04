import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    List<Person> myList;
    List<AnotherPerson> myListNext;
    @BeforeEach
    void setUp() {
        myList = new ArrayList<>();
        myList.add(new Person("Fred", "Flintstone", 35));
        myList.add(new Person("Wilma", "Flintstone", 33));
        myList.add(new Person("Barney", "Rubble", 34));
        myList.add(new Person("Betty", "Rubble", 32));

        myListNext = new ArrayList<>();
        myListNext.add(new AnotherPerson("Bugs", "Bunny", 33));
        myListNext.add(new AnotherPerson("Daffy", "Duck", 32));
        myListNext.add(new AnotherPerson("Elmer", "Fudd", 31));
        myListNext.add(new AnotherPerson("Foghorn", "Leghorn", 30));
    }

    @Test
    void retrieveElementFromList() {
        Person person = myList.get(0);
        assertEquals(35, person.getAge());
    }

    @Test
    void insertElementIntoList() {
        int[] expected = {4, 5};
        int[] results = new int[2];
        results[0] = myListNext.size();
        myListNext.add(new AnotherPerson("Yosemite", "Sam", 40));
        results[1] = myListNext.size();
        assertArrayEquals(expected, results);
    }

    @Test
    void insertingNullIsAllowed() {
        myList.add(null);
        assertNull(myList.get(4));
    }

    @Test
    void insertingAtExistingIndexDisplacesElements() {
        myListNext.add(0, new AnotherPerson("Yosemite", "Sam", 40));
        Object[] expected = new Object[]{"Bugs", "Bunny", 33};
        AnotherPerson person = myListNext.get(1);
        Object[] results = new Object[]{person.getFirstName(), person.getLastName(), person.getAge()};
        assertArrayEquals(expected, results);
    }

    @Test
    void replaceAtAnExistingIndex() {
        Object[] expected = new Object[]{"Wilma", "Flintstone", 33, "Pebbles", "Flintstone", 5};
        Object[] results = new Object[6];
        Person person = myList.get(1);
        results[0] = person.getFirstName();
        results[1] = person.getLastName();
        results[2] = person.getAge();
        myList.set(1, new Person("Pebbles", "Flintstone", 5));
        person = myList.get(1);
        results[3] = person.getFirstName();
        results[4] = person.getLastName();
        results[5] = person.getAge();
        assertArrayEquals(expected, results);
    }

    @Test
    void addOneJavaListToAnother() {
        List<Person> myNewList = new ArrayList<>();
        myNewList.add(new Person("Foo", "Bar", 20));
        myNewList.add(new Person("Fizz", "Buzz", 21));
        myNewList.add(new Person("Up", "Tight", 22));
        int[] expected = {4, 7};
        int[] results = new int[2];
        results[0] = myList.size();
        myList.addAll(myNewList);
        results[1] = myList.size();
        assertArrayEquals(expected, results);
    }

    @Test
    void findByIndexUsingApacheCommons() {
        Person person = new Person("Barney", "Rubble", 34);
        int index = myList.indexOf(person);
        assertEquals(2, index);
    }

    @Test
    void findByIndexUsingManualOverrides() {
        AnotherPerson person = new AnotherPerson("Elmer", "Fudd", 31);
        int index = myListNext.indexOf(person);
        assertEquals(2, index);
    }

    @Test
    void checkIfAListContainsAnElement() {
        Person person = new Person("Betty", "Rubble", 32);
        assertTrue(myList.contains(person));
    }

    @Test
    void removeElement() {
        int[] expected = {4, 3};
        int[] results = new int[2];
        results[0] = myListNext.size();
        AnotherPerson removedPerson = myListNext.remove(2);
        results[1] = myListNext.size();
        assertArrayEquals(expected, results);
    }

    @Test
    void clearAList() {
        int[] expected = {4, 0};
        int[] results = new int[2];
        results[0] = myList.size();
        myList.clear();
        results[1] = myList.size();
        assertArrayEquals(expected, results);
    }
}