/**
 * Created by jkeys on 10/14/2015.
 */

public class SNode<T> {
    private T data;
    private SNode<T> next;

    public SNode(T _data) {
        data = _data;
        next = null;
    }

    public SNode(T _data, SNode<T> _next) {
        data = _data;
        next = _next;
    }

    public SNode<T> getNext() {
        return next;
    }

    public T getData() {
        return data;
    }

    public void setNext(SNode<T> _next) {
        next = _next;
    }

    public void setData(T _data) {
        data = _data;
    }

    public String toString() {
        if(next == null)
            return data.toString();
        else
            return data.toString() + "--->";
    }
}
