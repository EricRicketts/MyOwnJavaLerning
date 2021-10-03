import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.platform.commons.util.CollectionUtils.*;

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

    @Test
    void usingAKeyIterator() {
        Set<String> expected = new HashSet<>();
        String[] values = {"Fred", "Barney", "Wilma", "Betty"};
        for (String firstName:values) { expected.add(firstName); }
        Set<String> results = new HashSet<>();
        Iterator<String> iterator = hMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            results.add(hMap.get(key).getFirstName());
        }
        assertEquals(expected, results);
    }

    @Test
    void usingForEachLoopKeys() {
        Set<String> expected = new HashSet<>();
        String[] values = {"Bugs", "Daffy", "Elmer", "Foghorn"};
        for (String firstName:values) { expected.add(firstName); }
        Set<String> results = new HashSet<>();
        for (String key:hMapNext.keySet()) {
            results.add(hMapNext.get(key).getFirstName());
        }
        assertEquals(expected, results);
    }

    @Test
    void usingAKeyStream() {
        Set<String> expected = new HashSet<>();
        Set<String> results = new HashSet<>();
        String[] values = {"Bugs", "Daffy", "Elmer", "Foghorn"};
        for (String firstName:values) { expected.add(firstName); }
        Stream<String> keyStream = hMapNext.keySet().stream();
        keyStream.forEach((key) -> {
            results.add(hMapNext.get(key).getFirstName());
        });
        assertEquals(expected, results);
    }

    @Test
    void usingAValuesIterator() {
        Set<String> expected = new HashSet<>();
        Set<String> results = new HashSet<>();
        String[] values = {"Bugs", "Daffy", "Elmer", "Foghorn"};
        for (String firstName:values) { expected.add(firstName); }
        Iterator<Person> iterator = hMapNext.values().iterator();
        while (iterator.hasNext()) {
            Person person = iterator.next();
            results.add(person.getFirstName());
        }
        assertEquals(expected, results);
    }

    @Test
    void valuesForEachLoop() {
        Set<String> expected = new HashSet<>();
        Set<String> results = new HashSet<>();
        String[] values = {"Bugs", "Daffy", "Elmer", "Foghorn"};
        for (String firstName:values) { expected.add(firstName); }
        for (Person person:hMapNext.values()) { results.add(person.getFirstName()); }
        assertEquals(expected, results);
    }

    @Test
    void valuesWithAStream() {
        Set<String> expected = new HashSet<>();
        Set<String> results = new HashSet<>();
        String[] values = {"Bugs", "Daffy", "Elmer", "Foghorn"};
        for (String firstName:values) { expected.add(firstName); }
        Stream<Person> stream = hMapNext.values().stream();
        stream.forEach((person) -> {
            results.add(person.getFirstName());
        });
        assertEquals(expected, results);
    }

    @Test
    void entriesWithAnIterator() {
        String[][] values = {{"first", "Fred"}, {"second", "Wilma"}, {"third", "Barney"}, {"fourth", "Betty"}};
        SortedMap<String, String> expected = new TreeMap<>();
        SortedMap<String, String> results = new TreeMap<>();
        for (String[] value:values) { expected.put(value[0], value[1]); }
        Iterator<Map.Entry<String, Person>> iterator = hMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Person> entry = iterator.next();
            results.put(entry.getKey(), entry.getValue().getFirstName());
        }
        assertEquals(expected, results);
    }

    @Test
    void entriesWithAForEachLoop () {
        String[][] values = {{"first", "Fred"}, {"second", "Wilma"}, {"third", "Barney"}, {"fourth", "Betty"}};
        SortedMap<String, String> expected = new TreeMap<>();
        SortedMap<String, String> results = new TreeMap<>();
        for (String[] value:values) { expected.put(value[0], value[1]); }
        for (Map.Entry<String, Person> entry:hMap.entrySet()) {
            results.put(entry.getKey(), entry.getValue().getFirstName());
        }
        assertEquals(expected, results);
    }

    @Test
    void removeAnEntryFromAMap() {
        int[] expected = {4, 3};
        int[] results = new int[2];
        results[0] = hMap.size();
        hMap.remove("first");
        results[1] = hMap.size();
        assertArrayEquals(expected, results);
    }

    @Test
    void removeAllEntriesFromAMap() {
        int[] expected = {4, 0};
        int[] results = new int[2];
        results[0] = hMap.size();
        hMap.clear();
        results[1] = hMap.size();
        assertArrayEquals(expected, results);
    }

    @Test
    void cannotReplaceNonExistentEntry() {
        assertNull(hMap.replace("10", new Person("Foo", "Bar", 10)));
    }

    @Test
    void canOnlyReplaceExistingEntry() {
        Person person = new Person("Yosemite", "Sam", 40);
        Object[] expected = new Object[]{"Bugs", "Bunny", 33, "Yosemite", "Sam", 40};
        Object[] results = new Object[6];
        Person currentPerson = hMapNext.get("fifth");
        results[0] = currentPerson.getFirstName();
        results[1] = currentPerson.getLastName();
        results[2] = currentPerson.getAge();
        Person value = hMapNext.replace("fifth", person);
        currentPerson = hMapNext.get("fifth");
        results[3] = currentPerson.getFirstName();
        results[4] = currentPerson.getLastName();
        results[5] = currentPerson.getAge();
        assertArrayEquals(expected, results);
    }

    @Test
    void testIfMapIsEmpty() {
        hMap.clear();
        assertTrue(hMap.isEmpty());
    }
}