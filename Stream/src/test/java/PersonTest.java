import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
}