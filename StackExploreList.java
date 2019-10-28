/**
 * CLass Header: StackExploreList CLass
 * Class description: this class create a list in last in first out logic.
 * Author: Chuhuan Huang cs12fbx
 *
 */
import java.util.*;
// This is not a generic type as it is designed specifically
// to work with Squares from a Sea.
public class StackExploreList implements ExploreList
{
  public LinkedList<Square> workList;

  public StackExploreList() {
    this.workList = new LinkedList<Square> ();
  }
  /** Add a Square to the worklist, as appropriate
  * @param "The Square to add"
  */

  public void add(Square s) {
    workList.addFirst(s);
  }

  /** Removes and returns the next Square to be explored
  * @return The next Square to explore */
  public Square getNext() {
    return workList.removeFirst();
  }

  /** isEmpty
  * @return true if the worklist is empty, false otherwise
  */
  public boolean isEmpty() {
    return workList.isEmpty();
  }

  /** size of the worklist
  * @return The number of elements in the worklist
  */
  public int size() {
    return workList.size();
  }
}
