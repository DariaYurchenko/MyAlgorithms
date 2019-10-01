package stacks_queues.task_1;

/*Implement a queue with two stacks so that each queue operations takes a constant amortized number of stack operations.*/

public class TwoStackQueue<Item> {
    private GenericArrayStack<Item> pushStack;
    private GenericArrayStack<Item> popStack;

    public TwoStackQueue() {
        this.popStack = new GenericArrayStack<>();
        this.pushStack = new GenericArrayStack<>();
    }

    public void enqueue(Item item) {
       pushStack.push(item);
    }

    public Item dequeue() {
        if(popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
        return popStack.pop();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return popStack.size() + pushStack.size();
    }

    public static void main(String[] args) {
        TwoStackQueue<Integer> queue = new TwoStackQueue<>();

        queue.enqueue(12);
        queue.enqueue(13);

        System.out.println(queue.size());

        queue.dequeue();

        System.out.println(queue.size());

    }


}
