import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    Map<String, Person> hMap;
    Map<String, Person> hMapNext;
    @BeforeEach
    void setUp() {
        hMap = new HashMap<>();
        hMapNext = new HashMap<>();
        hMap.put("first", new Person("Fred", "Flintstone", 35));
        hMap.put("second", new Person("Wilma", "Flintstone", 33));
        hMap.put("third", new Person("Barney", "Rubble", 34));
        hMap.put("fourth", new Person("Betty", "Rubble", 32));

        hMapNext.put("fifth", new Person("Bugs", "Bunny", 33));
        hMapNext.put("sixth", new Person("Daffy", "Duck", 32));
        hMapNext.put("seventh", new Person("Elmer", "Fudd", 31));
        hMapNext.put("eighth", new Person("Foghorn", "Leghorn", 30));
    }

    @Test
    void getValueFromKey() {
        assertEquals("Fred", hMap.get("first").getFirstName());
    }

    @Test
    void cycleThroughAllValues() {
        Set<String> results = new HashSet<>();
        for (Person person:hMap.values()) {
            String personInfo = person.getFirstName() + " " + person.getLastName() + " " + person.getAge();
            results.add(personInfo);
        }
        Set<String> expected = new HashSet<>();
        expected.add("Fred Flintstone 35");
        expected.add("Wilma Flintstone 33");
        expected.add("Barney Rubble 34");
        expected.add("Betty Rubble 32");

        assertEquals(expected, results);
    }

    @Test
    void cycleThroughAllKeys() {
        Set<String> results = new HashSet<>();
        Set<String> expected = new HashSet<>();
        for (String str:hMap.keySet()) {
            results.add(str);
        }
        expected.add("first");
        expected.add("second");
        expected.add("third");
        expected.add("fourth");

        assertEquals(expected, results);
    }

    @Test
    void unionTwoHashes() {
        int[] expected = {4, 8};
        int[] results = new int[2];
        results[0] = hMap.size();
        hMap.putAll(hMapNext);
        results[1] = hMap.size();

        assertArrayEquals(expected, results);
    }

    @Test
    void getValueOrDefault() {
        Person defaultPerson = hMap.getOrDefault("foo", new Person("Foo", "Bar", 10));
        String[] expected = {"Foo", "Bar", "10"};
        String[] results = new String[]{defaultPerson.getFirstName(), defaultPerson.getLastName(),
            Integer.toString(defaultPerson.getAge())};
        assertArrayEquals(expected, results);
    }

    @Test
    void checkIfMapContainsAKey() {
        assertTrue(hMap.containsKey("first"));
    }

    @Test
    void checkIfMapContainsAValue() {
        Object[] expected = {"Fred", "Flintstone", 35};
        Person person = hMap.get("first");
        Object[] results = {person.getFirstName(), person.getLastName(), person.getAge()};
        assertArrayEquals(expected, results);
    }
}