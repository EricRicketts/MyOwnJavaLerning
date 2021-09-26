import java.util.ArrayList;

public class MyCollection<T> {
    private ArrayList<T> collection;

    MyCollection(T[] array) {
        collection = new ArrayList<T>();
        for (T t:array) collection.add(t);
    }

    public ArrayList<T> getCollection() {
        return collection;
    }

    public void setCollection(ArrayList<T> collection) {
        this.collection = collection;
    }
}
