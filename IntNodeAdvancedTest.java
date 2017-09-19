/**
 * Created by jkeys on 9/13/2015.
 */
public class IntNodeAdvancedTest {
    public static void main(String args[]) {
        IntNode first = new IntNode();
        first.setData(1);
        first.setNext(null);
        System.out.println("getData, getNext test. Data: " + first.getData() + ". Next node: " + first.getNext()); //should print "1" and "null"

        //when reference is passed to print/ln, it automatically calls toString()
        System.out.println(first); //toString test
        System.out.println("Length of previous list: " + IntNode.listLength(first)); //should print "1"

        first.addNodeAfterThis(2);
        first.getNext().addNodeAfterThis(3); //should now be 1->2->3

        System.out.println(first);
        System.out.println("Length of previous list: " + IntNode.listLength(first)); //should print "3"

        System.out.println("contains 2: " + IntNode.search(first, 2)); //should print "true"

        first.removeNodeAfterThis(); //should print 1->3 after this call

        System.out.println(first);
        System.out.println("Length of previous list: " + IntNode.listLength(first)); //should print "2"

        System.out.println("contains 2: " + IntNode.search(first, 2)); //should print "false"

        IntNode newList = new IntNode(1, null);
        newList.addToEnd(2);
        newList.addToEnd(3);
        newList.addToEnd(4);

        System.out.println(newList);
        IntNode reversed = IntNode.reverse(newList);

        System.out.println("And then reversed: ");

        System.out.println(reversed);

        reversed.addToEnd(0);
        reversed.addToEnd(-1);

        System.out.println("added 0 and -1, " + reversed);

        int reversedSum = IntNode.sumLast(reversed, 10);

        System.out.println("Sum of reversed list: " + reversedSum);

        System.out.println("Sum of last four in reversed list: " + IntNode.sumLast(reversed, 4));

        System.out.println("listOdd, " + IntNode.listOddNumber(reversed));

        reversed = IntNode.copyOdd(reversed);

        System.out.println("copied odd, " + reversed);

        reversed.addToEnd(-1);
        reversed.addToEnd(15);
        reversed.addToEnd(-1);

        System.out.println("added -1, 15, -1, " + reversed);

        reversed = IntNode.removeFirst(reversed, -1);

        System.out.println("removed first -1, " + reversed);

        reversed = IntNode.removeAll(reversed, -1);

        System.out.println("removed all -1s, " + reversed);
    }
}
