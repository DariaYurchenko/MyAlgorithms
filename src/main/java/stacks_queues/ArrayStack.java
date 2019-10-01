package stacks_queues;

//is faster then LinkedList
public class ArrayStack {
    private String[] strings;
    private int size;

    /*public ArrayStack(int capacity) {
        this.strings = new String[capacity];
    }*/

    public ArrayStack() {
        this.strings = new String[1];
    }

    public void push(String s) {
        if(size == strings.length) {
            resize(strings.length*2);
        }
        strings[size++] = s;
    }

    public String pop() {
        String string = strings[--size];
        strings[size] = null;
        if(size > 0 && size == strings.length/4) {
            resize(strings.length/2);
        }
        return string;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize(int capacity){
        String[] copy = new String[strings.length*2];
        for(int i = 0; i < size; i++) {
            copy[i] = strings[i];
        }
        strings = copy;
    }

}
