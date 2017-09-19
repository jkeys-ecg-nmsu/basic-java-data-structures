/**
 * Created by jkeys on 10/14/2015.
 */

import java.util.*;

public class ArrayQueue<T> implements QueueInterface<T> {
    private int front;
    private int back;
    private int size;
    private ArrayList<T> data;

    public ArrayQueue() {
        size = 0;
        front = back = -1;
        data = new ArrayList<T>();
    }

    public void enqueue(T e) {
        if(size == 0) {
            data.add(e);
            front = back = 0;
            size++;
            return;
        }

        back++;
        data.add(back, e);
        size++;
    }

    public T dequeue() {
        if(size == 0)
            return null;

        T result = data.get(0);
        data.remove(0);
        back--;
        size--;
        return result;
    }

    public T front() {
        if (size == 0)
            return null;

        return data.get(0);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }
}
