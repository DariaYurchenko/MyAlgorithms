package my_algorithms.reverse_linkedlist;

public interface MyList<T> {

    int size();

    void add(T item);

    void add(int index, T item);

    T get(int index);

    T remove(int index);

    boolean remove(Object o);

    void clear();

}
