/**
 * Created by jkeys on 10/14/2015.
 */
public interface QueueInterface<T> {
    public void enqueue(T e);

    public T dequeue();

    public T front();

    public int size();

    public boolean isEmpty();
}
