import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyClass<T> {
    private ArrayList<T> arrayList;

    MyClass(T[] array) {
        this.arrayList = new ArrayList<>();
        for (T t:array) arrayList.add(t);
    }

    public ArrayList<T> getArrayList() {
        return arrayList;
    }

    public void setArrayList(T[] array) {
        this.arrayList = new ArrayList<>();
        for (T t:array) arrayList.add(t);
    }
}
