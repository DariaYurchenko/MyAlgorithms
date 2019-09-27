package balanced_search_tree;

public class RedBlackTree<Key extends Comparable<Key>, Value > {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node{
        private Node right;
        private Node left;
        private Key key;
        private Value value;
        //counts Nodes in subtree
        private int count;
        private boolean color;

        public Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
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

    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value value) {
        if(x == null) {
            return new Node(key, value, RED);
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

        if(isRed(x.right) && !isRed(x.left)) {
            x = rotateLeft(x);
        }
        if(isRed(x.right) && isRed(x.left)) {
            flipColors(x);
        }
        if(isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        }
        x.count = size(x.right) + size(x.left) + 1;
        return x;
    }

    private boolean isRed(Node node) {
        if(node == null) {
            return false;
        }
        return node.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node s = h.right;
        h.right = s.left;
        s.left = h;
        s.color = h.color;
        h.color = RED;
        return s;
    }

    private Node rotateRight(Node h) {
        Node r = h.left;
        h.left = r.right;
        r.right = h;
        r.color = h.color;
        h.color = RED;
        return r;

    }

    private void flipColors(Node h) {
        h.color = RED;
        h.right.color = BLACK;
        h.left.color = BLACK;

    }

    //color is ignored
    //the same as in BST
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


}
