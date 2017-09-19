/**
 * Created by jkeys on 9/28/2015.
 */
public class LinkStack<T> implements StackInterface<T> {
    private SNode<T> top = null;


     /**
     * Method to push new element to top of stack.
     * @param e Element to be pushed. If e is null, return without modifying the stack structure.
     */
    public void push(T e) {
        if(e == null)
            return;

        top = new SNode<T>(e, top); //create new node and push to top, while linking up with old top
    }

    /**
     * Method to pop an element off of the top of the stack.
     * @return Element popped from the top of the stack.
     */
    public T pop() {
        if (top == null)
            return null;

        T result = top.getData(); //Node to return
        SNode<T> newTop = top.getNext(); //the new top node
        top.setNext(null);
        top = newTop;
        return result;
    }

    /**
     * Method to peek at the top element of the stack.
     * @return The top element of the stack.
     */
    public T top() {
        if(top == null)
            return null;


        return top.getData();
    }

    /**
     * Method to get the size of the stack.
     * @return The size of the stack.
     */
    public int size() {
        int count = 0;
        SNode<T> rover = top;

        while(rover != null) {
            count++;
            rover = rover.getNext();
        }

        return count;
    }

    /**
     * Method to return whether the stack is empty.
     * @return true if size == 0, else false.
     */
    public boolean isEmpty() {
        return top == null;
    }


}
