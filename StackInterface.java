/**
 * Created by jkeys on 9/28/2015.
 */
public interface StackInterface<T> {
    public void push(T e);
    public T pop();
    public T top();
    public int size();
    public boolean isEmpty();

}
