package binary_search_trees;

import java.util.ArrayDeque;
import java.util.Queue;

//operation on keys - compareTo()
public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node{
        private Node right;
        private Node left;
        private Key key;
        private Value value;
        //counts Nodes in subtree
        private int count;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    //1 + Node's depth (~2lnN - N elements in random order - average, N - worst)
    private Node put(Node x, Key key, Value value) {
        if(x == null) {
            return new Node(key, value);
        }
        int cmp = key.compareTo(x.key);
        if(cmp > 0) {
            x.right = put(x.right, key, value);
        }
        if(cmp < 0) {
           x.left = put(x.left, key, value);
        }
        if(cmp == 0) {
            x.value = value;
        }
        x.count = size(x.right) + size(x.left) + 1;
        return x;
    }


    //1 + Node's depth (~2lnN - N elements in random order - average, N - worst)
    public Value get(Key key) {
        Node node = root;
        while(node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp == 0) {
                return node.value;
            }
            if (cmp > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    public Key floor(Key key) {
        Node node = floor(root, key);
        if(node == null) {
            return null;
        }
        return node.key;
    }

    //the same for ceiling
    private Node floor(Node x, Key key) {
        if(x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if(cmp == 0) {
            return x;
        }
        if(cmp < 0) {
            return floor(x.left, key);
        }

        Node t = floor(x.right, key);
        if(t != null) {
            return null;
        }
        return x;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if(x == null) {
            return  0;
        }
        else {
            return x.count;
        }
    }

    //how many keys < key
    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if(x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if(cmp < 0) {
            return rank(key, x.left);
        }
        if(cmp > 0) {
            return size(x.left) + size(x.right) + 1;
        }
        else {
            return size(x.left);
        }

    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new ArrayDeque<>();
        inorder(root, queue);
        return queue;
    }

    private void inorder(Node x, Queue<Key> queue) {
        if(x == null) {
            return;
        }
        inorder(x.left, queue);
        queue.add(x.key);
        inorder(x.right, queue);
    }

    //the same for deleteMax()
    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if(x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.count = size(x.right) + size(x.left) + 1;
        return x;
    }

    public void delete(Key key) {
        root = delete(key, root);
    }

    //unbalances tree leading to the N^1/2 height
    private Node delete(Key key, Node x) {
        if(x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if(cmp < 0) {
            x.left = delete(key, x);
        }
        else if(cmp > 0) {
            x.right = delete(key, x);
        }
        else {
            if(x.right == null) {
                return x.left;
            }
            if(x.left == null) {
                return x.right;
            }
            else {
                Node t = x;
                x = min(t.right);
                x.right = deleteMin(t.right);
                x.left = t.left;
            }
        }
        x.count = size(x.right) + size(x.left) + 1;
        return x;
    }

    public Node min(Node x) {
        while(x.left != null) {
            x = x.left;
        }
        return x;
    }

}
