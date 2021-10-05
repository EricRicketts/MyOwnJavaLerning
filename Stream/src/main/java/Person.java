import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Person implements Comparable<Person> {
    private String firstName;
    private String lastName;
    private int age;

    Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public int getAge() { return age; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public void setAge(int age) { this.age = age; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    @Override
    public int compareTo(Person other) {
        if (lastName != other.getLastName()) {
            return lastName.compareTo(other.getLastName());
        } else if (firstName != other.getFirstName()) {
            return firstName.compareTo(other.getFirstName());
        } else {
            return Integer.compare(age, other.getAge());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            Person other = (Person) obj;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(age, other.age);
            builder.append(lastName, other.lastName);
            builder.append(firstName, other.firstName);
            return builder.isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(lastName);
        builder.append(firstName);
        builder.append(age);
        return builder.toHashCode();
    }
}
