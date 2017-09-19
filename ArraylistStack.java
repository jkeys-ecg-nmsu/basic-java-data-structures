/**
 * Created by jkeys on 9/28/2015.
 */

import java.util.ArrayList;

public class ArraylistStack<T> implements StackInterface<T> {
    ArrayList<T> data = new ArrayList<T>();
    private int size = 0;

    /**
     * Method to push new element to top of stack.
     * @param e Element to be pushed. If e is null, return without modifying the stack structure.
     */
    public void push(T e) {
        if(e == null)
            return;

        data.add(e);
        size++;
    }

    /**
     * Method to pop an element off of the top of the stack.
     * @return Element popped from the top of the stack.
     */
    public T pop() {
        if(size == 0)
            return null;

        size--;

        T result = data.get(size);
        data.remove(size);
        return result;
    }

    /**
     * Method to peek at the top element of the stack.
     * @return The top element of the stack.
     */
    public T top() {
        if(size == 0)
            return null;

        return data.get(size - 1);
    }

    /**
     * Method to get the size of the stack.
     * @return The size of the stack.
     */
    public int size() {
        return size;
    }

    /**
     * Method to return whether the stack is empty.
     * @return true if size == 0, else false.
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }
}
