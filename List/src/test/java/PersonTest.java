import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    List<Person> myList;
    List<Person> myListNext;
    @BeforeEach
    void setUp() {
        myList = new ArrayList<>();
        myList.add(new Person("Fred", "Flintstone", 35));
        myList.add(new Person("Wilma", "Flintstone", 33));
        myList.add(new Person("Barney", "Rubble", 34));
        myList.add(new Person("Betty", "Rubble", 32));

        myListNext = new ArrayList<>();
        myListNext.add(new Person("Bugs", "Bunny", 33));
        myListNext.add(new Person("Daffy", "Duck", 32));
        myListNext.add(new Person("Elmer", "Fudd", 31));
        myListNext.add(new Person("Foghorn", "Leghorn", 30));
    }

    @Test
    void retrieveElementFromList() {
        Person person = myList.get(0);
        assertEquals(35, person.getAge());
    }
}