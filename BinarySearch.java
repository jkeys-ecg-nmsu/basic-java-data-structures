/**
 * Created by jkeys on 12/3/2015.
 */
public class search {
    public static int binarySearch(int[] A, int e) {
        int low = 0;
        int high = A.length - 1;

        while(low < high) {
            int mid = (low + high) / 2;

            if(A[mid] == e)
                return mid;
            else if(A[mid] < e)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] a = { 2, 4, 6, 8, 10, 20, 50, 100, 250, 500, 1000};

        System.out.println(binarySearch(a, 2)); //should print "0"

        System.out.println(binarySearch(a, 20)); //should print "5"

        System.out.println(binarySearch(a, 250)); //should print "8"

        System.out.println(binarySearch(a, 133)); //should print "-1"
    }
}
