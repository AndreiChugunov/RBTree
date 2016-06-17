import java.util.*;

public class RBTree<T extends Comparable<T>> implements Collection<T> {
    private Node<T> nil = new Node<>();
    private Node<T> root = nil;
    private int size = 0;

    private void turnLeft(Node<T> X) {
        Node<T> Y = X.right;
        X.right = Y.left;
        if (Y.left != nil) Y.left.parent = X;
        Y.parent = X.parent;
        if (X.parent == nil) root = Y;
        else if (X == X.parent.left) X.parent.left = Y;
        else X.parent.right = Y;
        Y.left = X;
        X.parent = Y;
    }

    private void turnRight(Node<T> Y) {
        Node<T> X = Y.left;
        Y.left = X.right;
        if (X.right != nil) X.right.parent = Y;
        X.parent = Y.parent;
        if (Y.parent == nil) root = X;
        else if (Y == Y.parent.right) Y.parent.right = X;
        else Y.parent.left = X;
        X.right = Y;
        Y.parent = X;
//        Y.location = X.location;
//        A.location = Location.LEFT;
//        B.location = Location.RIGHT;
//        X.location = Location.LEFT;
//        V.location = Location.RIGHT;
    }

    public T getRootValue() {
        return root.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        T value = (T) o;
        Node<T> currentNode = root;
        while (currentNode != nil) {
            if (currentNode.value.compareTo(value) > 0)
                currentNode = currentNode.left;
            else if (currentNode.value.compareTo(value) < 0)
                currentNode = currentNode.right;
            else return true;
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return new Iterator();
    }

    public class Iterator implements java.util.Iterator<T> {
        private Node<T> it = nil;
        private Stack<Node<T>> stack = new Stack<>();

        public Iterator() {
            it = root;
            if (it == nil) return;
            stack.push(nil);
            while (it.left != nil) {
                stack.push(it);
                it = it.left;
            }
        }

        public Color color() {
            return it.color();
        }

        @Override
        public boolean hasNext() {
            return it != nil;
        }

        @Override
        public T next() {
            T val;
            if (it != nil)
                val = it.value;
            else throw new NoSuchElementException();
            if (it.right != nil) {
                it = it.right;
                while (it.left != nil) {
                    stack.push(it);
                    it = it.left;
                }
            } else it = stack.pop();
            return val;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] arrayed = new Object[size];
        int i = 0;
        for (T it : this) {
            arrayed[i] = it;
            i++;
        }
        return arrayed;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length >= size) {
            int i = 0;
            for (T it : this) {
                a[i] = (T1) it;
                i++;
            }
            return a;
        } else {
            T1[] b = (T1[]) new Object[size];
            int i = 0;
            for (T it : this) {
                b[i] = (T1) it;
                i++;
            }
            return b;
        }
    }

    @Override
    public boolean add(T value) {
        Node<T> Y = nil;
        Node<T> X = root;
        while (X != nil) {
            Y = X;
            Y.left = X.left;
            Y.right = X.right;
            if (value.compareTo(X.value) < 0) X = X.left;
            else if (value.compareTo(X.value) > 0) X = X.right;
            else return false;
        }
        Node<T> Z = new Node<T>(value, Y, nil);
        if (Y == nil) root = Z;
        else if (Z.value.compareTo(Y.value) < 0) {
            Y.left = Z;
        } else {
            Y.right = Z;
        }
        Z.left = nil;
        Z.right = nil;
        Z.color(Color.RED);
        addFixUp(Z);
        size++;
        return true;
    }

    private void addFixUp(Node<T> Z) {
        while (Z.parent.color().isRed) {
            if (Z.parent.parent.left == Z.parent) {
                Node<T> Y = Z.parent.parent.right;
                if (Y.color().isRed) {
                    Z.parent.color(Color.BLACK);
                    Y.color(Color.BLACK);
                    Z.parent.parent.color(Color.RED);
                    Z = Z.parent.parent;
                } else {
                    if (Z == Z.parent.right) {
                        Z = Z.parent;
                        turnLeft(Z);
                    }
                    Z.parent.color(Color.BLACK);
                    Z.parent.parent.color(Color.RED);
                    turnRight(Z.parent.parent);
                }
            } else if (Z.parent.parent.right == Z.parent) {
                Node<T> Y = Z.parent.parent.left;
                if (Y.color().isRed) {
                    Z.parent.color(Color.BLACK);
                    Y.color(Color.BLACK);
                    Z.parent.parent.color(Color.RED);
                    Z = Z.parent.parent;
                } else {
                    if (Z == Z.parent.left) {
                        Z = Z.parent;
                        turnRight(Z);
                    }
                    Z.parent.color(Color.BLACK);
                    Z.parent.parent.color(Color.RED);
                    turnLeft(Z.parent.parent);
                }
            }
        }
        root.color(Color.BLACK);
    }

    private Node<T> minmumNode(Node<T> currentNode) {
        while (currentNode.left != nil) currentNode = currentNode.left;
        return currentNode;
    }

    private Node<T> nextNode(Node<T> currentNode) {
        if (currentNode.right != nil) {
            return minmumNode(currentNode.right);
        }
        Node<T> tempNode = currentNode.parent;
        while (tempNode != nil && currentNode != tempNode.right) {
            currentNode = tempNode;
            tempNode = tempNode.parent;
        }
        return tempNode;
    }

    private void advancedTreeRepair(Node<T> currentNode) {
        Node<T> W;
        while (currentNode != root && currentNode.color().isBlack) {
            if (currentNode == currentNode.parent.left) {
                W = currentNode.parent.right;
                if (W.color().isRed) {
                    W.color(Color.BLACK);
                    currentNode.parent.color(Color.RED);
                    turnLeft(currentNode.parent);
                    W = currentNode.parent.right;
                }
                if (W.left.color().isBlack && W.right.color().isBlack) {
                    W.color(Color.RED);
                    currentNode = currentNode.parent;
                } else {
                    if (W.right.color().isBlack) {
                        W.left.color(Color.BLACK);
                        W.color(Color.RED);
                        turnRight(W);
                        W = currentNode.parent.right;
                    }
                    W.color(currentNode.parent.color());
                    currentNode.parent.color(Color.BLACK);
                    W.right.color(Color.BLACK);
                    turnLeft(currentNode.parent);
                    currentNode = root;
                }
            } else {
                W = currentNode.parent.left;
                if (W.color().isRed) {
                    W.color(Color.BLACK);
                    currentNode.parent.color(Color.RED);
                    turnRight(currentNode.parent);
                    W = currentNode.parent.left;
                }
                if (W.right.color().isBlack && W.left.color().isBlack) {
                    W.color(Color.RED);
                    currentNode = currentNode.parent;
                } else {
                    if (W.left.color().isBlack) {
                        W.right.color(Color.BLACK);
                        W.color(Color.RED);
                        turnLeft(W);
                        W = currentNode.parent.left;
                    }
                    W.color(currentNode.parent.color());
                    currentNode.parent.color(Color.BLACK);
                    W.left.color(Color.BLACK);
                    turnRight(currentNode.parent);
                    currentNode = root;
                }

            }
        }
        currentNode.color(Color.BLACK);
    }

    @Override
    public boolean remove(Object o) {
        T value = (T) o;
        Node<T> currentNode = root;
        if (!contains(o)) return false;
        while (currentNode != nil && currentNode.value.compareTo(value) != 0) {
            if (currentNode.value.compareTo(value) < 0) currentNode = currentNode.right;
            else currentNode = currentNode.left;
        }
        if(currentNode == nil) return false;
        Node<T> Z = currentNode;
        Node<T> Y;
        if (Z.left == nil || Z.right == nil) Y = Z;
        else Y = nextNode(Z);
        Node<T> X = new Node<>();
        if (Y.left != nil) X = Y.left;
        else X = Y.right;
        X.parent = Y.parent;
        if (Y.parent == nil)
            root = X;
        else if (Y == Y.parent.left)
            Y.parent.left = X;
        else Y.parent.right = X;
        if (Y != Z)
            Z.value = Y.value;
        if (Y.color().isBlack)
            advancedTreeRepair(X);
        size--;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object it : c) {
            if (!contains(it)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        int counter = this.size;
        for (Object it : c)
            add((T) it);
        if(this.size > counter) return true;
        else return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int counter = this.size;
        for (Object it : c)
            if (contains(it))
                remove((T) it);
        if(this.size < counter)
            return true;
        else return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int counter = this.size;
        LinkedList list = new LinkedList();
        for (T it : this)
            if (!c.contains(it))
                list.add(it);
        removeAll(list);
        return this.size < counter;
    }

    @Override
    public void clear() {
        this.forEach(this::remove);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof RBTree) {
            RBTree<T> tree = (RBTree<T>) o;
            return (size == tree.size && containsAll(tree));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
