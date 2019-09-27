package my_algorithms.reverse_linkedlist;

import java.util.NoSuchElementException;

public class ReversedLinkedList<T> implements MyList<T>{

    private int size = 0;
    private Node<T> first;
    private Node<T> last;

    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T element, Node<T> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }

        public Node(T item) {
            this.item = item;
        }
    }

    /**
     * Returns the amount of elements in the list.
     * @return the amount of elements in the list.
     */

    @Override
    public int size() {
        return size;
    }

    /**
     * Add element in the end of the list.
     * @param element to add.
     */

    @Override
    public void add(T element) {
        linkLast(element);
    }

    /**
     * Add element in the list's position with some index.
     * Other elements are shifted to the right on one position.
     * Increase the size of the list by 1 point.
     * @param index of the list an element is added to.
     * @param element to add.
     */

    @Override
    public void add(int index, T element) {
        validateIndex(index);

        if (index == size) {
            linkLast(element);
        } else {
            linkInside(element, findNode(index));
        }
    }

    /**
     * Add element on the last position of the list.
     * Increase the size of the list by 1 point.
     * @param element to add to the last position.
     */

    public void addLast(T element) {
        linkLast(element);
    }

    /**
     * Add element on the first position of the list.
     * Other elements are shifted to the right on one position.
     * Increase the size of the list by 1 point.
     * @param element to add to the first position.
     */

    public void addFirst(T element) {
        linkFirst(element);
    }

    /**
     * Make links for the first element in the list (first node).
     * Increase the size of the list by 1 point.
     * @param element to add to the first position.
     */

    private void linkFirst(T element) {
        final Node<T> f = first;
        final Node<T> newNode = new Node<>(null, element, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
    }

    /**
     * Make links for the last element in the list (last node).
     * Increase the size of the list by 1 point.
     * @param element to add to the last position.
     */

    private void linkLast(T element) {
        final Node<T> l = last;
        final Node<T> newNode = new Node<>(l, element, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    /**
     * Make links for the element inserted to some position in the list.
     * Other elements are shifted to the right on one position.
     * Increase the size of the list by 1 point.
     * @param element to add to the position between two others.
     * @param inside means the node standing right after the position the element is added to.
     */

    private void linkInside(T element, Node<T> inside) {
        final Node<T> prev = inside.prev;
        final Node<T> newNode = new Node<>(prev, element, inside);
        inside.prev = newNode;
        if (prev == null)
            first = newNode;
        else
            prev.next = newNode;
        size++;
    }

    /**
     * Find node by it's index in the list.
     * @param index means the position of the node to find.
     * @return the node found.
     */

    private Node<T> findNode(int index) {
        if (index < (size >> 1)) {
            Node<T> nodeToFind = first;
            for (int i = 0; i < index; i++)
                nodeToFind = nodeToFind.next;
            return nodeToFind;
        } else {
            Node<T> nodeToFind = last;
            for (int i = size - 1; i > index; i--)
                nodeToFind = nodeToFind.prev;
            return nodeToFind;
        }
    }

    /**
     * Check if the index is correct. Throws an exception if not.
     * @param index to check.
     */

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(indexOutOfBoundMessage(index));
        }
    }

    public String indexOutOfBoundMessage(int index) {
        return "Index " + index + ", size " + size;
    }

    /**
     * Return the element of the list by index.
     * @param index of element to return.
     * @return element with desired index.
     */

    @Override
    public T get(int index) {
        validateIndex(index);
        return findNode(index).item;
    }

    /**
     * Return the first element of the list.
     * @return the first element of the list
     */

    public T getFirst() {
        final Node<T> f = first;
        if (f == null) {
            throw new NoSuchElementException("No such element.");
        } else {
            return f.item;
        }
    }

    /**
     * Return the last element of the list.
     * @return the last element of the list
     */

    public T getLast() {
        final Node<T> l = last;
        if (l == null) {
            throw new NoSuchElementException();
        } else {
            return l.item;
        }
    }

    /**
     * Remove element from position with entered index.
     * Other elements are shifted to the left on one position.
     * Decrease the size of the list by 1 point.
     * @param index of the element to remove.
     * @return the removed element.
     */

    @Override
    public T remove(int index) {
        validateIndex(index);
        return unLink(findNode(index));
    }

    /**
     * Remove the first element.
     * Other elements are shifted to the left on one position.
     * Decrease the size of the list by 1 point.
     * @return the removed element.
     */

    public T removeFirst() {
        final Node<T> f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return unlinkFirst(f);
    }

    /**
     * Remove the last element.
     * Other elements are shifted to the left on one position.
     * Decrease the size of the list by 1 point.
     * @return the removed element.
     */

    public T removeLast() {
        final Node<T> l = last;
        if (l == null) {
            throw new NoSuchElementException();
        }
        return unlinkLast(l);
    }

    /**
     * Remove the element.
     * Other elements are shifted to the left on one position.
     * Decrease the size of the list by 1 point.
     * @param o is the object to remove from the list.
     * @return boolean if the element was removed successfully.
     */

    @Override
    public boolean remove(Object o) {
        Node<T> node = first;
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (node.item == null) {
                    unLink(node);
                    return true;
                }
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (node.item.equals(o)) {
                    unLink(node);
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    /**
     * Delete links for the first element in the list (first node).
     * Decrease the size of the list by 1 point.
     * @param firstNode is the node to unlink.
     * @return item of unlinked node.
     */

    private T unlinkFirst(Node<T> firstNode) {
        final T element = firstNode.item;
        final Node<T> next = firstNode.next;
        firstNode.next = null;
        firstNode.item = null;
        first = next;
        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }
        size--;
        return element;
    }

    /**
     * Delete links for the last element in the list (last node).
     * Decrease the size of the list by 1 point.
     * @param lastNode is the node to unlink.
     * @return item of unlinked node.
     */

    private T unlinkLast(Node<T> lastNode) {
        T element = lastNode.item;
        Node<T> prev = lastNode.prev;
        lastNode.prev = null;
        lastNode.item = null;
        last = prev;
        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
        }
        size--;
        return element;
    }

    /**
     * Delete links for the node with entered index in the list.
     * Decrease the size of the list by 1 point.
     * @param nodeToRemove is the node to unlink.
     * @return item of unlinked node.
     */

    private T unLink(Node<T> nodeToRemove) {
        final T element = nodeToRemove.item;
        final Node<T> prev = nodeToRemove.prev;
        final Node<T> next = nodeToRemove.next;
        if (nodeToRemove.prev == null) {
            first = nodeToRemove.next;
        }
        if (nodeToRemove.next == null) {
            last = nodeToRemove.prev;
        }

        nodeToRemove.item = null;
        nodeToRemove.prev = null;
        nodeToRemove.next = null;

        prev.next = next;
        next.prev = prev;

        size--;
        return element;
    }

    /**
     * Clear the list removing all the elements and nodes.
     */

    @Override
    public void clear() {
        Node<T> nodeI = first;
        for (int i = 0; i < size; i++) {
            Node<T> next = nodeI.next;
            nodeI.next = null;
            nodeI.prev = null;
            nodeI.item = null;
            nodeI = next;
        }
        first = null;
        last = null;
        size = 0;
    }

    public Node<T> getFirstNode() {
        return first;
    }

    public void setFirstNode(Node<T> node) {
        first = node;
    }

    public void setLastNode(Node<T> node) {
        last = node;
    }

    public Node<T> getLastNode() {
        return last;
    }

    public Node<T> reverse(Node<T> head) {
        Node<T> prev = null;
        Node<T> next = null;
        Node<T> current = head;

        while(current != null) {
            next = current.next;
            current.next = prev;
            current.prev = next;
            prev = current;
            current = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ReversedLinkedList<Integer> myList = new ReversedLinkedList<>();
        Node<Integer> a = new Node<>(10);
        Node<Integer> b = new Node<>(20);
        Node<Integer> c = new Node<>(30);

        myList.setFirstNode(a);
        myList.getFirstNode().next = b;

        myList.getFirstNode().next.next = c;
        myList.getFirstNode().next.prev = a;

        myList.getFirstNode().next.next.prev = b;

        myList.size = 3;

        myList.setLastNode(myList.getFirstNode().next.next);

        myList.printList(myList.getFirstNode());

        myList.printList(myList.reverse(myList.getFirstNode()));
    }

    void printList(Node node)
    {
        while (node != null) {
            System.out.print(node.item + " ");
            node = node.next;
        }
    }

}
