import java.util.*;

/**
 *	@author	Jeremy Keys
 */

public class Movie {
    /* private member variables */
    private String title;
    private String genre;
    private String directors[];
    private int numDirectors;
    private int movieid;
    private static final int maxDirectors = 3; //constant, to de-magic-number-fy array allocations and loop iterations

    /* constructors*/
    /**
     *  Copy constructor
     *	@param	nTitle The movie title which the member "title" variable will be initialized to
     */
    public Movie(String nTitle) {
        title = nTitle;
        genre = "";
        numDirectors = 0;
        movieid = -1;
        directors = new String[maxDirectors];
    }

    /**
     *	@param	obj
     *	 The object this copy constructor will attempt to copy
     */
    //use of "this" reference most useful in instances where multiple objects of same type used;
    //therefore, I use in copy constructor/equals method, and omit elsewhere.
    public Movie(Object obj) {
        if (obj == null || !(obj instanceof Movie)) //done if not correct type or null object to copy
            return;

        Movie m = (Movie) obj; //typecast the object to be copied

        this.numDirectors = m.numDirectors;
        this.movieid = m.movieid;
        this.title = new String(m.title);
        this.genre = new String(m.genre);
        this.directors = new String[maxDirectors];

        //then manually allocate a new String, copy its contents, then set the reference in the new object's array
        for (int i = 0; i < m.numDirectors; i++) {
            this.directors[i] = new String(m.directors[i]);
        }
    }

    public Movie() {
        title = "";
        genre = "";
        numDirectors = 0;
        movieid = -1;
        directors = new String[maxDirectors];
    }

    /**
     *	@param	obj
     *	 The object this equals method will attempt to check for equality
     *	@return
     *  true or false, depending on some comparison function
     */
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Movie) ) //done if not correct type or null object to compare
            return false;

        Movie m = (Movie) obj;

        return this.title.equals(m.title); //return true if titles match, else false
    }

    /**
     *	@return
     *  return the current object's "title" string variable
     */
    public String getTitle() {
        return title;
    }
    /**
     *	@param	nTitle
     *	 the new value for the "title" member variable
     *	the "title" member variable will be given the value of param "nTitle"
     */
    public void setTitle(String nTitle) {
        title = nTitle;
    }

    /**
     *	@param nGenre
     *	 the new value for the "genre" member variable
     */
    public void setType(String nGenre) {
        genre = nGenre;
    }

    /**
     *	@return
     *  return the current object's numDirectors variable
     */
    public int getDirectorNumber() {
        return numDirectors;
    }

    /**
     *	@return
     *  return the current object's "genre" string variable
     */
    public String getMovieType() {
        return genre;
    }

    public int getMovieID() {
        return movieid;
    }

    public void setMovieID(int movieidNew) {
        movieid = movieidNew;
    }

    /**
     *	@param _director
     *	 the new value for the "title" member variable
     *	@precondition
     *  the actual number of directors is less than capacity, i.e. numDirectors < maxDirectors
     *	@postcondition
     *	the numDirectors-th array element is occupied by the new director String, and the numDirectors has been increased by 1
     *	@return
     *  false if no space for new director, else true
     */
    public boolean addDirector(String _director) {
        if (numDirectors >= maxDirectors)
            return false;

        directors[numDirectors] = new String(_director); //put the String object in the right spot
        numDirectors++; //then increment the numDirectors
        return true;
    }

    /**
     *	@author	Jeremy Keys
     *	@param	b1
     *	 the first Movie object to pull directors from
     *	@param	b2
     *	 the second Movie object to pull directors from
     *	@precondition
     *  b1 and b2 are not null
     *	@postcondition
     *
     *	@return
     *  a String array containing all unique directors
     *	@throws
     */
    public static String[] getAllDirectors(Movie b1, Movie b2) {
        if (b1 == null || b2 == null)
            return null;

        //this is the laziest method I could think of to do this.
        //Create a set, add each element in turn, and take advantage of uniqueness property of sets.
        HashSet<String> s = new HashSet<>();
        for (int i = 0; i < b1.getDirectorNumber(); i++) {
            s.add(b1.directors[i]);
        }

        for (int i = 0; i < b2.getDirectorNumber(); i++) {
            s.add(b2.directors[i]);
        }

        //then convert the Set back into a String array and return it; code taken from
        //http://docs.oracle.com/javase/7/docs/api/java/util/Set.html#toArray()
        return s.toArray(new String[0]);
    }

    /**
     *	@return
     *  a String representation of the Movie object
     */
    public String toString() {
        String t = title + ", " + genre + ", " + numDirectors + ", "; //get the initial string with the easy stuff concat
        StringBuilder sb = new StringBuilder(t); //create a StringBuilder

        //loop over the number of directors and append each one in turn
        for (int i = 0; i < numDirectors; i++) {
            sb.append(directors[i]); //append the name of the director to the string builder
            if (i != numDirectors - 1) //don't copy the comma after the last element
                sb.append(", ");
        }

        //return a String representation of the Movie
        return sb.toString();
    }
}
