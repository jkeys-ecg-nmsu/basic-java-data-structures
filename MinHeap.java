import java.util.ArrayList;
import java.lang.Math;

/**
 * Created by jkeys on 12/2/2015.
 */
public class MinHeap {
    ArrayList<Integer> data = new ArrayList<Integer>();

    private void swap(ArrayList<Integer> data, int i, int j) {
        Integer temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);

        return;
    }

    private void reheapUpward(int pos) {
        int parent = (pos - 1) / 2;
        while(data.get(pos) < data.get(parent)) {
            swap(data, pos, parent);
            pos = parent;
            parent = (pos - 1) / 2;
        }
    }

    public void add(int e) {
        data.add(e);

        if(data.size() == 1) return;

        int i = data.lastIndexOf(e);

        reheapUpward(i);
    }

    private void reheapDown(int pos) {
        int left = 1;
        int right = 2;
        while((left < data.size() && data.get(pos) > data.get(left)) || (right < data.size() && data.get(pos) > data.get(right)) ) {
            if(data.get(left) < data.get(right)) {
                swap(data, left,  pos);
                pos = left;
            }
            else {
                swap(data, right, pos);
                pos = right;
            }

            left = 2 * pos + 1;
            right = 2 * pos + 2;
        }
    }

    public int remove() {
        if(data.size() == 0) throw new Error("Empty heap");

        if(data.size() == 1) {
            int result = data.get(0);
            data.remove(0);
            return result;
        }

        int result = top();

        swap(data, 0, data.size() - 1);
        data.remove(data.size() - 1);

        reheapDown(0);

        return result;

    }

    public int top() {
        return data.get(0);
    }

    public static void sort(int[] array) {
        MinHeap mh = new MinHeap();
        for (int i : array) {
            mh.add(i);
        }

        for(int i = 0; i < mh.data.size(); i++) {
            System.out.print(mh.data.get(i) + "  ");
            if (i % 10 == 0) System.out.println();
        }
    }

    private void prt() {
        for(int i = 0; i < data.size(); i++) {
            System.out.print(data.get(i) + "  ");
            if ((i+1) % 10 == 0) System.out.println();
        }
    }

    public static void main(String args[]) {
        MinHeap h1 = new MinHeap();

        h1.add(5);
        h1.add(14);
        h1.add(23); //vals taken from here https://www.cs.cmu.edu/~tcortina/15-121sp10/Unit06B.pdf
        h1.add(32);
        h1.add(41);
        h1.add(53);
        h1.add(64);
        h1.add(50);
        h1.add(87);
        h1.add(90);

        h1.prt();

        System.out.println();

        int five = h1.remove();
        int fourteen = h1.remove();

        h1.prt();
    }
}
