package symbol_tables;

//operation on keys - equals()
public class SequentialSymbolTable<Key, Value> {

    private int size;

    private Node first;

    private class Node {
        private Node next;
        private Key key;
        private Value value;

        public Node(Node next, Key key, Value value) {
            this.next = next;
            this.key = key;
            this.value = value;
        }
    }

    //N - the worst, N - average
    public void put(Key key, Value value) {
        for(Node x = first; x != null; x = x.next) {
            if(x.key.equals(key)) {
                x.value = value;
                return;
            }
        }
        first = new Node(first, key, value);
        size++;
    }

    //N - the worst, N/2 - average
    public Value get(Key key) {
        for(Node x = first; x != null; x = x.next) {
            if(x.key.equals(key)) {
                return x.value;
            }
        }
        return null;
    }

    public Value delete(Key key) {
        if(first == null) {
            return null;
        }

        for(Node x = first; x.next != null; x = x.next) {
            if(x.next.key.equals(key)) {
                Value value = x.next.value;
                x.next = x.next.next;
                size--;
                return value;
            }
        }
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
