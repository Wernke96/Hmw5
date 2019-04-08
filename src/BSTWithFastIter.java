import java.util.NoSuchElementException;

public class BSTWithFastIter<E extends Comparable<E>> extends BST {
    private java.util.Stack<TreeNode<E>> stack = new java.util.Stack<>();
    private TreeNode<E> current = root;
    private E lastReturned = null;

    public BSTWithFastIter() {
        super();
    }
    public BSTWithFastIter(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            add( objects[i]);
    }

    public java.util.Iterator<E> iterator() {
        return new Iterator();
    }

    private class Iterator implements java.util.Iterator<E> {
            public Iterator(){

            }
        public boolean hasNext() {
            if (current != null || stack.empty())
                return true;
            else
                return false;

        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            while (current != null) {
                stack.push(current);
                current = current.left;
                TreeNode<E> node = stack.pop();
                lastReturned = node.element;
                current = node.right;


            }
            return lastReturned;


        }

        public void remove() {
            if (lastReturned == null)
                throw new IllegalStateException();
            else {
                delete(lastReturned);
                lastReturned = null;
            }

        }
    }
}