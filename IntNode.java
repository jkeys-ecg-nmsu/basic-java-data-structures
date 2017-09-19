/**
 * Created by jkeys on 9/13/2015.
 */
public class IntNode {
    /* private member variables */
    private int data;       //the data to be stored (integer)
    private IntNode next;   //ref to next node in list

    /* standard constructors */
    public IntNode() {
        data = 0;
        next = null;
    }

    public IntNode(int _data, IntNode _node) {
        data = _data;
        next = _node;
    }

    /* standard getters and setters */
    public void setData(int _data) {
        data = _data;
    }

    public void setNext(IntNode _next) {
        next = _next;
    }

    public int getData() {
        return data;
    }

    public IntNode getNext() {
        return next;
    }

    public String toString() {
        IntNode rover = this; //use a roving reference var to walk over list; make it start at the current object
        StringBuilder sb = new StringBuilder(); //stringbuilder to efficiently append result string

        //use while loop to walk over list, until reaching a null reference (i.e. the end of the list)
        while (rover != null) {
            sb.append("(" + rover.data + ")"); //append the data
            if (rover.next != null) //if we're not at the end, append an arrow too
                sb.append("--->");

            rover = rover.next; //then look at the next node in the list (if null, loop does not execute again)
        }

        return sb.toString(); //return string representation of StringBuilder
    }

    /**
     * @param newdata The data to be inserted into the newly created node.
     */
    public void addNodeAfterThis(int newdata) {
        IntNode newNode = new IntNode(newdata, this.next); //create the new node, give it the correct link (this.next)
        this.next = newNode; //then replace the current this.next with a ref to the newNode.
    }

    /**
     * @param head The head of the list. (Each node is a list unto itself.)
     * @return count The number of nodes in the list.
     */
    public static int listLength(IntNode head) {
        if (head == null) //check precondition
            return 0;

        //counter variable and rover to wlak over list
        int count = 0;
        IntNode rover = head;

        //walk over list using while loop
        while (rover != null) {
            count++;            //add one each time you look at a node
            rover = rover.next; //then look at the next node
        }

        return count; //return the number of nodes
    }

    /**
     * @param head The first node in the list
     * @param data The data to be searched for
     * @return true if the list contains element equal to "data"; false otherwise
     */
    public static boolean search(IntNode head, int data) {
        if (head == null) //check precondition
            return false;

        IntNode rover = head; //rover reference to walk over list

        while (rover != null) {
            if (data == rover.getData()) { //if data is equal, return true
                return true;
            }

            rover = rover.next; //look at next node (might be null)
        }

        return false; //if reach end of list w/o finding, return false (unfound)
    }

    public void removeNodeAfterThis() {
        IntNode newNext = this.next.getNext();
        this.next.setNext(null); //unlink the removed node's next (make it null)
        this.next = newNext; //remove reference to removed node, let garbage collector do its thing
    }

    /**
     * @param n The number to be checked for odd parity
     * @return True if n is odd, false otherwise.
     */
    private static boolean isOdd(int n) {
        return n % 2 != 0; //a number is odd if it has a remainder other than 0 when divided by 2; therefore use modulo
    }

    /**
     * @param head The head of the list
     * @return The count of odd numbers in the list
     */
    public static int listOddNumber(IntNode head) {
        if(head == null) //check precondition
            return 0;

        int count = 0; //counting var
        IntNode rover = head; //rover reference to walk over list

        while(rover != null) { //walk over list until you hit null (past the end)
            if(isOdd(rover.data)) { //check if the current element's data is odd
                count++;            //and count it if so
            }

            rover = rover.getNext(); //and move to the next element in the list.
        }

        return count; //return the count of odd numbers
    }

    public void addToEnd(int newdata) {
        IntNode rover = this; //rover reference to walk over list

        while(rover.getNext() != null) { //walk until
            rover = rover.getNext(); //walk over until you hit the tail element (until rover.getNext() == null)
        }

        IntNode newNode = new IntNode(newdata, null); //create the new node and give it the newdata
        rover.setNext(newNode); //and link it up to rover's next
    }

    //recursive solution b/c why not
    private static int sumLastRecurHelper(IntNode head, int numLeft) {
        if(numLeft == 0 || head == null) //base case
            return 0; //return 0; this anchors the sum, so you eventually get like 1 + 2 + 3 + 4 + 0
        else //otherwise, return the current node's data plus the sum of its sublist. (Eventually sum of sublist == 0; base case.)
            return head.getData() + sumLastRecurHelper(head.getNext(), numLeft - 1);
    }

    //this method makes the sumLast method a lot simpler
    private int size() {
        int count = 0;
        IntNode rover = this;

        while(rover != null) {
            count++;
            rover = rover.getNext();
        }

        return count;
    }

    public static int sumLast(IntNode head, int num) {
        if(head == null || num <= 0)
            return 0;

        IntNode rover = head;

        //walk over as many elements need to be ignored (size - num); if list of size 10 and num of 6,
        //you want to ignore the first 10-6=4 elements. So walk over four times, then recursively calculate the list.
        for (int i = 0; i < head.size() - num; i++) {
            if(rover.getNext() != null) {
                rover = rover.getNext(); //move to next node
            }
            else {
                break; //break to avoid null dereference (is this a segfault in Java? NULL->method() is a segfault in C++.)
            }
        }

        return sumLastRecurHelper(rover, num); //then let the recursive helper method do the actual summation.
    }

    /**
     * @param head The head of the list.
     * @return The new head of the copied list.
     */
    public static IntNode copyOdd(IntNode head) {
        if(head == null)
            return null;

        IntNode rover = head; //rover reference to walk over list

        IntNode newHead = null; //this will hold the new head
        IntNode newRover = newHead; //and this will be the rover to walk over the new list once the new head has been created

        //walk over the list
        while(rover != null) {
            if(isOdd(rover.getData())) { //check if the current node of the list to be copied is odd
                if (newHead == null) { //if we havent' already created the new list
                    newHead = new IntNode(rover.getData(), null); //then create the newHead IntNode object
                    newRover = newHead; //and start the rover equal to its reference
                } else { //else the new list has been created
                    newRover.addNodeAfterThis(rover.getData()); //so just add the new node
                    newRover = newRover.getNext(); //and move its rover down one
                }
            }

            rover = rover.getNext(); //and walk down the initial list again; this happens regardless of whether rover is odd
        }

        return newHead; //can be null if no odd elements
    }


    /**
     * @param head The head of the list.
     * @param e The element to be found and removed.
     * @return The new head of the list.
     */
    public static IntNode removeFirst(IntNode head, int e) {
        if(head == null) { //check precondition
            return null;
        }

        IntNode rover = head; //rover reference to walk over list
        IntNode roverNext = rover.getNext(); //and the next elem in the list

        //check case where e == head.data
        if (rover.getData() == e) {
            head.setNext(null); //unlink the head (it is being removed)
            return roverNext; //and return the newHead (the second element in the list)
        }

        if(roverNext == null) //if roverNext == null and head.data != e, then return the 1-node list
            return head;

        //otherwise, walk over the list until we find one
        do {
            if(roverNext != null && roverNext.getData() == e) { //check if roverNext's data is equal to e
                rover.removeNodeAfterThis(); //if it is, remove roverNext (by calling removeNodeAfterThis on rover)
                break; //and break out of the loop
            }

            rover = roverNext; //inchworm the rover down
            if(rover != null) //and if it did not inchworm to the end,
                roverNext = rover.getNext(); //then set roverNext equal to next node.
        } while(rover != null); //go until we hit null (past tail of list).

        return head; //return the head; we already checked case where head.data == e, so if we get this far, we guarantee the original head is the new head.
    }

    //this is a lazy method that just calls removeFirst repeatedly until nothing is removed.
    public static IntNode removeAll(IntNode head, int e) {
        int currentSize = head.size(); //keep track of the list's current size

        //lazy loop condition
        while(true) {
            IntNode newHead = IntNode.removeFirst(head, e); //remove the first element equal to e and store the newHead
            if(newHead.size() == currentSize) //if the newHead size equals the original (or if already removed, current) size, nothing removed:
                return newHead; //so return the newHead. (If nothing removed at all, newHead == head)
            else
                currentSize = newHead.size(); //otherwise, sizes no longer match, a node was removed. Store the new size and do it again.
        }
    }


    public static IntNode reverse(IntNode head) {
        IntNode reversed = null; //store a reference to the reversed bit of the list; so far we haven't reversed anything, so null. Could also be called "newHead"
        IntNode rover = head; //and start with the current node at the head of the list.
        while (rover != null) { //go until we have reversed every node
            IntNode roverNext = rover.getNext(); //So store the next element after the current node
            rover.setNext(reversed); //then, reverse the current node by making its next point in the opposite direction; initially this is null (because the head becomes the tail, and tail.next == null)
            reversed = rover; //make the reversed sublist point to the node that was just reversed
            rover = roverNext; //and walk the rover down one more node.
        }

        return reversed; //and finally, return the now fully reversed list.
    }


}
