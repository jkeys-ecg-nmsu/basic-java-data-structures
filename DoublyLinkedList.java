/**
 * Created by jkeys on 9/23/2015.
 */

import java.util.*;

public class DoublyLinkedList {
    /* instance variables */
    DIntNode head;
    DIntNode tail;

    /* constructors */
    public DoublyLinkedList() {
        head = new DIntNode();
        tail = new DIntNode();
        head.setNext(tail);
        tail.setPrev(head);
    }

    /* getters and setters */
    public DIntNode getHead() { return head; }
    public DIntNode getTail() { return tail; }
    public void setHead(DIntNode _head) { head = _head; }
    public void setTail(DIntNode _tail) { tail = _tail; }

    /**
     * Add an element to the end of the list.
     * @param element The element to be added.
     */
    public void addEnd(int element) {
        DIntNode tailPrev = tail.getPrev();                         //get ref to the now-second-to-last node
        DIntNode newTail = new DIntNode(element, tailPrev, tail);   //create the new last node
        tail.setPrev(newTail);                                      //and link it with the tail dummy node
        tailPrev.setNext(newTail);
    }

    //let n = the number of nodes in the list.
    //To remove a node from the head, takes 5+0n operations. (Same number of operations if one node in list, or one thousand.)
    //Specifically, two accesses to reference variables and three assignments.
    //Therefore, this method has O(1) running time, constant.
    /**
     * Remove the head node.
     */
    public void removeFromHead() {
        DIntNode nodeToRemove = head.getNext();     //get reference to the node we're removing
        DIntNode newHead = nodeToRemove.getNext();  //and reference to the new actual head
        nodeToRemove.setPrev(null);                 //remove links contained in node we're removing
        nodeToRemove.setNext(null);
        head.setNext(newHead);                      //and link the new actual head back with the dummy head.
        newHead.setPrev(head);
    }

    //simple toString method
    public String toString() {
        StringBuilder result = new StringBuilder("(Forward)\t"); //StringBuilder for efficient concat; much quicker
        DIntNode rover = head.getNext();                        //than constant String allocation (String being immutable in Java)

        //walk forward
        while(rover != tail) {
            result.append("(" + rover.getData() + ")");
            if(rover.getNext() != tail) //skip adding arrow for final iteration
                result.append("<-->");

            rover = rover.getNext(); //walk fwd
        }

        result.append("\n(Backward)\t");
        rover = tail.getPrev();

        //walk backwards
        while(rover != head) {
            result.append("(" + rover.getData() + ")");
            if(rover.getPrev() != head) //skip adding arrow for final iteration
                result.append("<-->");

            rover = rover.getPrev(); //walk backwd
        }

        return result.toString(); //String return type for toString methods, call toString on StringBuilder
    }

    //copied with slight tweaks from Lab4
    private int size() {
        int count = 0;
        DIntNode rover = head.getNext();

        while(rover != tail) {
            count++;
            rover = rover.getNext();
        }

        return count;
    }


    /**
     * Return a DoublyLinkedList containing the sub list starting at beginIdx and ending at endIdx. The precondition(s) is (are)
     * that the indexes must be within bounds, and beginIdx < endIdx.
     * @param beginIdx The index of the new head copied from the old list.
     * @param endIdx The index of the new tail copied from the old list.
     * @return A DoublyLinkedListObject containing the sub list.
     */ //O(n), linear, complexity
    public DoublyLinkedList subList(int beginIdx, int endIdx) {
        if(beginIdx < 0 || beginIdx > endIdx || endIdx >= size())            //3 operations
            throw new IndexOutOfBoundsException();

        DIntNode rover = head.getNext();                                              //1 operation

        //walk forward the appropriate number of times (min of 0, if beginIdx == 0)
        for(int i = 0; i < beginIdx; i++) {                                 //beginIdx operations; beginIdx <= n; worst case, n operations.
            if(rover != tail)                                               //n operations
                rover = rover.getNext();                                    //n operations (worst-case)
        }

        //create the newList which will contain a COPY
        DoublyLinkedList newList = new DoublyLinkedList();        //1 operation (roughly, more like 3 with constructor)

        //create the new head of the list and a couple of reference variables to walk over the old and new list respectively
        //copy the head to begin with and create its node object. Link its prev up to the dummy.
        DIntNode newHead = new DIntNode(rover.getData(), newList.getHead(), null); //1 operation
        DIntNode newListRover = newHead;                                    //1 operation
        DIntNode newNode;

        //loop the number of elements to be copied minus 1 (the head has already been copied, see previous lines)
        for(int i = 0; i < (endIdx - beginIdx); i++) {                      //<= n operations
            rover = rover.getNext();  //move the rover fwd                  //<= n operations

            //create the newNode object to be added to the sub list
            newNode = new DIntNode(rover.getData(), newListRover, null);    //<= n operations

            //then link up the newNode and advance the newListRover
            newListRover.setNext(newNode);                                  //<= n operations
            newNode.setPrev(newListRover);                                  //<= n operations
            newListRover = newNode;                                         //<= n operations
        }

        //after final iteration, newListRover == final node (discounting dummy);
        //so, make its next point to dummy
        newListRover.setNext(newList.getTail());

        //and then link the dummy head up with the actual head
        newList.getHead().setNext(newHead);                                 //2 operations

        //and then link the dummy tail with the actual tail
        newList.getTail().setPrev(newListRover);                            //2 operations

        //and return the copied list
        return newList;                                                     //1 operation
    } //total operations: 3 + 1 + n + n + 1 + 1 +1 + 6n + 2 + 2 + 1 = 8n + 12 operations. Big-O complexity is O(n), linear.

    //let n = number of nodes in the list.
    //The total operations is 4n + 3 operations. Big-O time is O(n), linear.

    /**
     * Count the occurrences of a given value, e, in the list.
     * @param e The value to be searched for and counted.
     * @return The number of times e appears in the list.
     */
    public int countOccurrence(int e) {
        int count = 0;                                                      //1 op
        DIntNode rover = head.getNext();                                    //1 op

        while(rover != tail) {                                              //n ops
            if (rover.getData() == e)                                       //n ops
                count++;                                                    //n ops (worst-case)

            rover = rover.getNext();                                        //n ops
        }

        return count;                                                       //1 op
    }

    //create removeOne, so removeAll is easy: call removeOne until it returns false (nothing removed).
    private boolean removeOne(int e) {
        DIntNode roverPrev = head;
        DIntNode rover = head.getNext(); //track three nodes at once, to make removal of the middle node (the searched node) simple
        DIntNode roverNext = rover.getNext();

        while(rover != tail) { //iterate over the list until dummy node
            if(rover.getData() == e) { //if e found
                roverPrev.setNext(roverNext);                                                       //removeOne(2) =>
                roverNext.setPrev(roverPrev);   //take care of linking up the prev and next nodes: (1)<---->(2)<---->(3) becomes => (1)<->(3)
                rover.setNext(null);            //null out the removed node                      (prev)<->(rover)<->(next)
                rover.setPrev(null);
                return true;                    //true if removed
            }

            roverPrev = rover; //advance the ref vars
            rover = rover.getNext();
            if(rover != null) //avoid null (de)reference
                roverNext = rover.getNext();
        }

        return false; //if end of method reached, nothing found; return false.
    }


    //I believe this (removeAll) is O(n^2), where n = number of nodes in list. To removeOne(e) will take n trips; to removeAll will take n*n trips.
    //the caveat is that n shrinks by 1 every iteration, so that makes it a weird summation of n + (n-1) + (n-2) + ... + (n-(n-1)) iterations.
    //I don't know what to call that besides O(n^2).

    /**
     * Remove all occurrences of the value e in the list.
     * @param e The value to be searched for and removed.
     * @return true if e is found (and removed), false otherwise.
     */
    public boolean removeAll(int e) {
        boolean result = false;

        //this line is doing the bulk of this method's work.
        while(removeOne(e)) { //take advantage of fact that while loop is just an if statement that repeats.
            result = true; //go until !removeOne(e)
        }

        return result; //if removeOne != true (never triggers the loop), this is false. Else true.
    }

    /**
     * Print statistics for the list (each unique value, and its number of occurrences).
     */
    public void printStatistics() {
        HashMap<Integer, Integer> dict = new HashMap<Integer, Integer>(); //HashMap examples taken from StackOverflow
        DIntNode rover = head.getNext();

        while(rover != tail) { //iterate over list
            if(!dict.containsKey(rover.getData()))  //if key not in dictionary
                dict.put(rover.getData(), 1);       //then give init value of 1 (first time finding key)
            else
                dict.put(rover.getData(), (dict.get(rover.getData()) + 1) ); //else increase key's value by 1 (found one more time)

            rover = rover.getNext(); //march onward
        }

        System.out.println("number\t\toccurrence");
        for(Map.Entry<Integer, Integer> entry : dict.entrySet()) { //use special for loop to loop over Entry.s (ex taken from StackOverflow)
            System.out.println(entry.getKey() + "\t\t\t" + entry.getValue()); //print key, couple of tabs, value
        }
    }

}
