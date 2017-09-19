import java.lang.reflect.Array;

/**
 * Created by jkeys on 10/14/2015.
 */
public class QueueTest {
    public static void main(String args[]) {
        LinkedQueue<Integer> lq = new LinkedQueue<Integer>();
        ArrayQueue<Integer> aq = new ArrayQueue<>();

        System.out.println("Size of lq after instantiation: " + lq.size() + "\t\t\tSize of aq after instantiation: " + aq.size());
        System.out.println("Front of lq after instantiation: " + lq.front() + "\t\tFront of lq after instantiation: " + aq.front());


        System.out.println("lq.dequeue() empty queue, no effect if return null: " + lq.dequeue());
        System.out.println("aq.dequeue() empty queue, no effect if return null: " + aq.dequeue());


        for(int i = 1; i <= 10; i++) {
            lq.enqueue(i);
            aq.enqueue(i);
        }


        System.out.println("Size of lq after enqueue 1-10: " + lq.size() + "\t\t\tSize of aq after enqueue 1-10: " + aq.size());
        System.out.println("Front of lq after enqueue 1-10: " + lq.front() + "\t\t\tFront of lq after enqueue 1-10: " + aq.front());

        while(!lq.isEmpty()) {
            System.out.println("lq.dequeue() == " + lq.dequeue() + "\t\t\taq.dequeue() == " + aq.dequeue());
        }

        System.out.println("Size of lq after lq.isEmpty(): " + lq.size() + "\t\t\tSize of aq after aq.isEmpty(): " + aq.size());
        System.out.println("Front of lq after lq.isEmpty(): " + lq.front() + "\t\tFront of aq after aq.isEmpty(): " + aq.front());
    }
}
