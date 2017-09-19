import java.util.EmptyStackException;

/**
 * Created by jkeys on 10/14/2015.
 */

public class LinkedQueue<T> implements QueueInterface<T> {
    private SNode<T> front;
    private SNode<T> back;
    private int size;

    public LinkedQueue() {
        front = back = null;
        size = 0;
    }


    public void enqueue(T e) {
        if(front == null) {
            front = new SNode<T>(e, back);
            back = front;
            size++;
            return;
        }

        SNode<T> newBack = new SNode<T>(e, null);
        back.setNext(newBack);
        back = newBack;
        size++;
    }

    public T dequeue() {
        if(front == null)
            return null;

        T result = front.getData();
        SNode<T> newFront = front.getNext();
        front.setNext(null);
        front = newFront;

        size--;
        return result;

    }

    public T front() {
        if (front == null)
            return null;

        return front.getData();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }
}
