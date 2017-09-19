import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by jkeys on 12/3/2015.
 */
public class MovieTable {
    //private Object[] keys;
    //private Object[] dataX;
    private boolean[] used;
    private int num;
    Movie data[];

    public MovieTable() {
        //keys = new Object[501];
        //dataX = new Object[501];
        used = new boolean[501];
        data = new Movie[501];
        num = 0;

        for(int i = 0; i < used.length; i++) {
            data[i] = null;
            used[i] = false;
        }
    }



    private int hash(Movie m) {
        return m.getMovieID() % used.length;
    }

    public void put(Movie m) {
        int mHash = hash(m);

        while(data[mHash] != null)
            mHash++;

        data[mHash] = m;
        num++;
    }

    public boolean remove(int movieid) {
        if(search(movieid) == -1) return false;

        int idx = search(movieid);
        used[idx] = true;
        data[idx] = null;
        num--;
        return true;
    }

    public int search(int movieid) {
        int result = -1;
        int mHash = movieid % used.length;

        if(data[mHash] != null)
            return mHash;

        while(true) {
            if(data[mHash] == null)
                return -1;

            if(data[mHash].getMovieID() == movieid)
                return mHash;

            mHash++;
        }
    }

    public static void main(String[] args) {
        MovieTable mt = new MovieTable();

        Movie m1 = new Movie("hello world");
        Movie m2 = new Movie("star trek");
        Movie m3 = new Movie("tarantino");

        m1.setMovieID(5555);
        m2.setMovieID(1111);
        m3.setMovieID(3333);

        System.out.println(mt.search(m1.getMovieID())); //should print "-1"
        System.out.println(mt.search(m2.getMovieID())); //should print "-1"
        System.out.println(mt.search(m3.getMovieID())); //should print "-1"

        mt.put(m1);
        mt.put(m2);
        mt.put(m3);

        System.out.println(mt.search(m1.getMovieID()));
        System.out.println(mt.search(m2.getMovieID()));
        System.out.println(mt.search(m3.getMovieID()));

        mt.remove(m1.getMovieID());
        mt.remove(m2.getMovieID());
        mt.remove(m3.getMovieID());

        System.out.println(mt.search(m1.getMovieID())); //should print "-1"
        System.out.println(mt.search(m2.getMovieID())); //should print "-1"
        System.out.println(mt.search(m3.getMovieID())); //should print "-1"
    }
}

