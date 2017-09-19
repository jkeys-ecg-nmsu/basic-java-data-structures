/**
 * Created by jkeys on 9/28/2015.
 */
public class StackTest {
    public static void main(String[] args) {
        LinkStack<Integer> ls1 = new LinkStack<Integer>();
        ArraylistStack<Integer> as1 = new ArraylistStack<Integer>();

        ls1.push(null); //no effect
        as1.push(null); //no effect

        ls1.push(5);        as1.push(5);

        System.out.println("link stack size (should be `1`):      " + ls1.size());
        System.out.println("Arraylist stack size (should be `1`): " + as1.size());

        ls1.push(4);        as1.push(4);
        ls1.push(3);        as1.push(3);
        ls1.push(2);        as1.push(2);
        ls1.push(1);        as1.push(1);

        System.out.println("link stack empty (should be false):      " + ls1.isEmpty());
        System.out.println("Arraylist stack empty (should be false): " + as1.isEmpty());

        System.out.println("link stack top (should be `1`):       " + ls1.top());
        System.out.println("Arraylist stack top (should be `1`):  " + as1.top());

        System.out.println("link stack size (should be `5`):      " + ls1.size());
        System.out.println("Arraylist stack size (should be `5`): " + as1.size());

        Integer i1 = ls1.pop();
        Integer i2 = as1.pop();

        System.out.println("Pop one by one and print contents: ");
        System.out.println("LS1\t\tAS1");
        while(i1 != null && i2 != null) {
            System.out.println(i1 + "\t\t" + i2);
            i1 = ls1.pop();
            i2 = as1.pop();
        }

        System.out.println("link stack size (should be `0`):      " + ls1.size());
        System.out.println("Arraylist stack size (should be `0`): " + as1.size());

        System.out.println("link stack empty (should be true):      " + ls1.isEmpty());
        System.out.println("Arraylist stack empty (should be true): " + as1.isEmpty());


    }


}
