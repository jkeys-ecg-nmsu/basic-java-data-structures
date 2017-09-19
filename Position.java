/**
 * Created by jkeys on 9/28/2015.
 */
public class Position {
    //public because this is an internal class, no need to really hide its innards
    public int colid;
    public int rowid;

    public Position(int _colid, int _rowid) {
        colid = _colid;
        rowid = _rowid;
    }

    public String toString() {
        return "rowid: " + rowid + " colid: " + colid;
    }
}
