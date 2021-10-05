import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    Map<String, Person> hMap = new HashMap<>();
    List<Person> myList = new ArrayList<>();
    Stream<Person> streamFromList;
    Stream<Map.Entry<String, Person>> streamFromMap;
    @BeforeEach
    void setUp() {
        Person personOne = new Person("Bugs", "Bunny", 30);
        Person personTwo = new Person("Daffy", "Duck", 31);
        Person personThree = new Person("Elmer", "Fudd", 32);
        Person personFour = new Person("Foghorn", "Leghorn", 33);
        Person personFive = new Person("Tasmanian", "Devil", 34);
        Person personSix = new Person("Tweety", "Bird", 15);
        Person personSeven = new Person("Yosemite", "Sam", 40);
        Person personEight = new Person("Porky", "Pig", 25);

        Person[] people = {
            personOne, personTwo, personThree, personFour,
            personFive, personSix, personSeven, personEight
        };
        int count = 0;
        for (Person person:people) {
            String key = Integer.toString(count);
            hMap.put(key, person);
            myList.add(person);
            count += 1;
        }

        streamFromList = myList.stream();
        streamFromMap = hMap.entrySet().stream();
    }

    @Test
    void createStreamFromList() {
        assertTrue(streamFromList instanceof Stream);
    }

    @Test
    void createStreamFromMap() {
        assertTrue(streamFromMap instanceof Stream);
    }

    @Test
    void getTheSizeOfAStream() {
        long[] expected = {8, 8};
        long[] results = new long[]{streamFromList.count(), streamFromMap.count()};
        assertArrayEquals(expected, results);
    }

    @Test
    void forEachOnAStream() {
        long[] expected = {32, 42};
        long[] results = new long[2];
        results[0] = hMap.get("2").getAge();
        streamFromMap.forEach((entry) -> {
            String key = entry.getKey();
            Person person = entry.getValue();
            if (key.equals("2")) {
                person.setAge(42);
            }
        });
        results[1] = hMap.get("2").getAge();
        assertArrayEquals(expected, results);
    }

    @Test
    void useStreamMapToTransformAMap() {
        Integer[] expected = {40, 41, 42, 43, 44, 25, 50, 35};
        List<Integer> results = new ArrayList<>();
        Stream<Map.Entry<String, Person>> newStreamFromMap = streamFromMap.map((entry) -> {
            Person person = entry.getValue();
            person.setAge(person.getAge() + 10);
            return entry;
        });
        newStreamFromMap.forEach((entry) -> {
            int age = entry.getValue().getAge();
            results.add(age);
        });
        Set<Integer> setOfExpected = new HashSet<>(Arrays.asList(expected));
        Set<Integer> setOfResults = new HashSet<>();
        for (Integer result:results) { setOfResults.add(result); }
        assertEquals(setOfExpected, setOfResults);
    }
}