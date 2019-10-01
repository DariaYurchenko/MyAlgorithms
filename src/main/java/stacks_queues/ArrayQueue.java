package stacks_queues;

public class ArrayQueue {
    private String[] strings;
    private int head;
    private int tail;
    private int size;

    public ArrayQueue() {
        this.strings = new String[1];
    }

    public void enqueue(String item) {
        if(tail == strings.length) {
            resize(strings.length*2);
        }
        strings[tail++] = item;
        size++;
    }

    public String dequeue() {
        String item = strings[head++];
        strings[head-1] = null;
        if(tail > 0 && tail == strings.length/4) {
            resize(strings.length/2);
        }
        size--;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize(int capacity){
        String[] copy = new String[capacity*2];
        for(int i = 0; i < size; i++) {
            copy[i] = strings[i];
        }
        strings = copy;
    }
}
