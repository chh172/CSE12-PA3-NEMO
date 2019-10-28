/**
 * CLass Header: QueueExploreList CLass
 * Class description: this class create a list in first in first out logic.
 * Author: Chuhuan Huang cs12fbx
 *
 */
import java.util.*;
// This is not a generic type as it is designed specifically
// to work with Squares from a Sea.
public class QueueExploreList implements ExploreList
{
    public LinkedList<Square> workList;

    public QueueExploreList() {
      this.workList = new LinkedList<Square> ();
    }

    /** Add a Square to the worklist, as appropriate
    * @param "The Square to add"
    */
    public void add(Square s) {
      this.workList.add(s);
    }

    /** Removes and returns the next Square to be explored
    * @return The next Square to explore */
    public Square getNext() {
      return this.workList.removeFirst();
    }

    /** isEmpty
    * @return true if the worklist is empty, false otherwise
    */
    public boolean isEmpty() {
      return this.workList.isEmpty();
    }

    /** size of the worklist
    * @return The number of elements in the worklist
    */
    public int size() {
      return this.workList.size();
    }
}
