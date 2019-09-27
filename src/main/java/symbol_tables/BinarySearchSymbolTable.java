package symbol_tables;

//IMPLEMENT SOME METHODS!!!
//Keys must be sorted
//operation on keys - compareTo()
public class BinarySearchSymbolTable<Key extends Comparable<Key>, Value> {
    private static final int INIT_SIZE = 8;

    private int size;

    private Key[] keys;
    private Value[] values;

    public BinarySearchSymbolTable(int capacity) {
        this.keys = (Key[]) new Comparable[capacity];
        this.values = (Value[]) new Object[capacity];
    }

    private void resize(int capacity) {
        Key[] newKeys = (Key[]) new Comparable[capacity];
        Value[] newValues = (Value[]) new Object[capacity];
        for(int i = 0; i < keys.length; i++) {
            newKeys[i] = keys[i];
            newValues[i] = values[i];
        }
        keys = newKeys;
        values = newValues;
    }

    //N - the worst, N/2 - average
    public void put(Key key, Value value) {
        if(size >= values.length) {
            resize(2*size);
        }

        if(contains(key)) {
            int i = bsearch(key);
            values[i] = value;
        }

        int i = size;
        while(i > 0 && key.compareTo(keys[i-1]) < 0) {
            keys[i] = keys[i-1];
            values[i] = values[i-1];
            i--;
        }
        keys[i] = key;
        values[i] = value;
        size++;
    }

    //lgN - the worst, lgN - average
    public Value get(Key key) {
        int element = bsearch(key);
        if(element == -1) {
            return null;
        }
        return values[element];
    }

    public boolean contains(Key key) {
        return bsearch(key) != -1;
    }

    public Value delete(Key key) {
        if(!contains(key)) {
            return null;
        }
        int element = bsearch(key);
        Value value = values[element];

        for(int i = size-1; i > element; i--) {
            keys[i-1] = keys[i];
            values[i-1] = values[i];
        }

        keys[size-1] = null;
        values[size -1] = null;

        return value;
    }

    private int bsearch(Key key) {
        int low = 0;
        int high = size-1;
        while(low <= high) {
            int mid = low + (high - low)/2;
            int cmp = key.compareTo(keys[mid]);
            if(cmp == 0) {
                return mid;
            }
            if(cmp > 0) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
