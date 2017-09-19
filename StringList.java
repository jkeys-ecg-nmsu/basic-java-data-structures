public class StringList {
    /**
     *	@author	Jeremy Keys
     */

    //private variables
    private int capacity;
    private int size;
    private String[] data;
    private final int defaultCapacity = 2;

    /**
     *  Default constructor, gives initial capacity of 2
     */
    public StringList() {
        capacity = defaultCapacity;
        size = 0;
        data = new String[capacity];
    }

    /**
     *  Constructor, takes int argument equal to the new StringList's capacity
     *	@param	_capacity the object to be copied
     */
    public StringList(int _capacity) {
        if(_capacity <= 0)
            return;

        capacity = _capacity;
        size = 0;
        data = new String[capacity];
    }
    /**
     *  Copy constructor
     *	@param	obj the object to be copied
     */
    public StringList(Object obj) {
        if(obj == null || !(obj instanceof StringList)) {
            return;
        }

        //typecast the stringList object
        StringList other = (StringList) obj;

        //copy data and allocate new array space
        this.size = other.size;
        this.capacity = other.capacity;
        this.data = new String[this.capacity];

        //Intellij IDEA is a pretty sick IDE/pseudo-API. (I have it for free using the educational license; JetBrains is great about that.)
        //I wrote a simple, C++ style loop with an index to iterate over the size, with the statement "this.data[i] = other.data[i]";
        //Intellij told me that I could replace that with a call to System.arraycopy, and then replaced the code for me. Blew me away.
        //More importantly, it taught me about a very useful method. I imagine I'll use it frequently starting now.
        System.arraycopy(other.data, 0, this.data, 0, size);
    }

    public int size() { return size; }

    public int capacity() { return capacity; }

    /**
     *  private method to re-size the data array as needed
     *	@param	minimumCapacity the potential new minimum String capacity of the StringList
     */
    private void ensureCapacity(int minimumCapacity) {
        if(capacity < minimumCapacity) {
            capacity = minimumCapacity;

            String[] temp = new String[capacity];
            System.arraycopy(data, 0, temp, 0, size);

            data = new String[capacity];
            System.arraycopy(temp, 0, data, 0, size);
        }
    }
    /**
     *  Method to add String element to data
     *	@param	str the string to be added (does not need to be unique)
     */
    public void add(String str) {
        assert(size >= 0 && size <= capacity); //let's keep the internal logic consistent

        if(size == capacity) {
            ensureCapacity(capacity * 2);
        }

        data[size] = str;
        size++;
    }

    /**
     *  Return the number of elements equal to parameter
     *	@param	a the string to be counted
     */
    public int count(String a) {
        if (a == null)
            return 0;

        int count = 0;

        //loop over and increment count each time the Strings are equaL
        for(String s : data) {
            if (a.equals(s))
                count++;
        }

        return count;
    }

    /**
     *  Method to remove String element from data
     *	@param	a the string to be removed (must be present)
     */
    public boolean removeLast(String a) {
        if(a == null)
            return false;

        //variable to hold index (assume it doesn't exist, -1 is convenient magic number for the if(last == -1) test
        int last = -1;

        //loop over, find the index of the last string
        for (int i = 0; i < size; i++) {
            if (data[i].equals(a)) {
                last = i;
            }
        }

        if(last == -1)
            return false;

        //shift the elements down a slot
        for(int i = last; i < size-1; i++){
            data[i] = data[i+1];
        }

        //is the ordering guaranteed? i.e. do i need to shift all elements down?
        //data[last] = data[size-1]; //throw that away, we don't need it; fill the gap with the final element in the list.

        //set the last to null (it will be pointed to twice) and then decrement size
        data[size-1] = null;
        size--;
        return true;
    }

    /**
     *  Print to console each data element
     */
    public void print() {
        System.out.println("Data, size: " + size + ", capacity: " + capacity);
        for (String s : data) {
            if(s != null)
                System.out.println(s);
        }

        System.out.println("\n");
    }

    /**
     *  Method to add unique String element to data
     *	@param	str the string to be added (MUST NOT BE PRESENT IN STRINGLIST ALREADY)
     */
    public void addUnique(String str) {
        if (str == null) //return if null
            return;

        for(int i = 0; i < size; i++) { //or if string already exists in data
            if(data[i].equals(str)) {
                return;
            }
        }

        //if there's insufficient space, resize
        if(size == capacity)
            ensureCapacity(capacity * 2);

        //then add the new string
        data[size] = str;
        size++;
    }

    /**
     * test driver
     */
    public static void main(String args[]) {
        StringList s1 = new StringList();
        s1.add("first");
        s1.add("second");
        s1.add("third");
        s1.print();
        s1.add("second");
        System.out.println(s1.count("second"));
        s1.print();

        System.out.println("add two more");
        s1.add("first");
        s1.add("nth");
        s1.print();
        System.out.println("after removing \"second\"");
        s1.removeLast("second");
        s1.print();

        StringList s2 = new StringList(s1);
        System.out.println("s1 copied, s2.print():");
        s2.print();

        StringList s3 = new StringList(3);
        s3.add("one");
        s3.addUnique("one");
        System.out.println("Following array should only contain a single \"one\" string");
        s3.print();

        System.out.println("s3 size should be 1, capacity should be 3:");
        System.out.println("size: " + s3.size());
        System.out.println("capacity: " + s3.capacity());

        System.out.println("s3.ensureCapacity(2): ");
        s3.ensureCapacity(2);
        //s3 capcaity should still be 3
        System.out.println("capacity: " + s3.capacity());
        s3.ensureCapacity(6);
        System.out.println("capacity: " + s3.capacity());

    }
}


