import java.util.NoSuchElementException;

public class BSTWithFastIter<E extends Comparable<E>> extends BST {

    public BSTWithFastIter() {
    }

    public BSTWithFastIter(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            add(objects[i]);
    }

    @Override
    public java.util.Iterator<E> iterator() {
        return new InorderIterator();
    }

    private class InorderIterator implements java.util.Iterator<E> {

        private java.util.Stack<TreeNode<E>> stack = new java.util.Stack<>();
        private TreeNode<E> current = root;
        private E lastReturned = null;

        public InorderIterator(){
            while(current != null){
                stack.push(current);
                current = current.left;
            }
        }

        @Override
        public boolean hasNext(){
            if(current != null || !stack.isEmpty()) {
                return true;
            }
            else{
                return false;
            }
        }

        @Override
        public E next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            while(current != null){
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            TreeNode<E> temp = current;
            lastReturned = temp.element;
            current = current.right;
            return lastReturned;
        }

        @Override
        public void remove(){
            if(lastReturned == null){
                throw new IllegalStateException();
            }
            delete(lastReturned);
            lastReturned = null;
        }
    }
}
