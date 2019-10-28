/**
 * Title: Explorer Class
 *  Description: this class creates a explorer to explore in the Sea.
 *  @author Chuhuan Huang A13342061 cs12fbx
 *
 * */
import java.util.*;
public class Explorer {

  // The exploreList to hold the search as it proceeds.
  private ExploreList exploreList;
  // The Sea to solve.
  private Sea sea = new Sea();
  // The path to the Nemo, if any
  private String path="Found Nemo!";

  private boolean gameOver = false; // game over, exit has(n't) been found
  private boolean foundNemo = false; // exit has been found

  public int getTotalVisited(){
    return  this.getSea().countVisited();
  }


  public void setGameOver() {
    gameOver = true;
  }

  public void setFoundNemo() {
    foundNemo = true;
  }

  public boolean gameOver() {
    return gameOver || foundNemo;
  }

  public boolean isFoundNemo() {
    return foundNemo;
  }

  public void makeEmpty() {
    // remove Squares until empty
    while (!exploreList.isEmpty()) {
      exploreList.getNext();
    }
  }

  public ExploreList getExploreList() {
    return this.exploreList;
  }

  /** isEmpty
  * @return true if the exploreList is empty, false otherwise
  */
  public boolean isEmpty() {
    return this.exploreList.isEmpty();
  }

  /** size of the exploreList
  * @return The number of elements in the exploreList
  */
  public int size() {
    return this.exploreList.size();
  }

  /** Make a new Solver with a given Sea and Worklist
  * @param theSea The Sea to solve
  * @param theExploreList The exploreList to use
  */
  Explorer(Sea theSea, ExploreList theExploreList){
    this.sea = theSea;
    this.exploreList = theExploreList;
  }

  /**
  * Get the Sea object
  * @return the sea
  */
  public Sea getSea() {
    return this.sea;
  }

  /**
  * Solve the sea, if possible.
  * If a solution is found, set the path variable and the
  * foundNemo variable appropriately.
  */
  public void solve() {
    // TODO
    // This function should use next.  You should also create and use any
    // other helper fuctions you find helpful here.
    Square s = sea.getStart();
    s.setPrevious(null);
    exploreList.add(s);
    while (!exploreList.isEmpty() && !this.foundNemo) {
      s = this.step();
    }
  }

  /** Take the next step toward the goal
  * PRECONDITION: The exploreList is not empty
  * @return The next Square that has just been visited.
  */
  public Square step() {
    Square temp = exploreList.getNext();
    Square prev = temp.getPrevious();
    // fake shxt above
    temp = this.sea.getSea()[temp.getRow()][temp.getCol()];
    if (temp.isVisited()) return temp;
    else {
      if(prev != null) {prev = this.sea.getSea()[prev.getRow()][prev.getCol()];}
      // get the real shxt
      temp.setVisited();
      if (temp.getPrevious() == null)
      {temp.setPrevious(prev);}
      /*System.out.println("temp"+temp.getRow()+"+"+temp.getCol());
      if(prev != null) {
      System.out.println("prev"+prev.getRow()+"+"+prev.getCol());
    }*/
    ArrayList<Square> neighbors = sea.getAdjacentArea(temp);
    if (temp.hasNemo()) {
      this.setFoundNemo();
      this.setPath(temp);
    }
    else {
      for (int i = 0; i < neighbors.size() ;i++ ) {
        //if (!neighbors.get(i).isVisited()) {
          Square sendToList = new Square(neighbors.get(i).getRow(),
          neighbors.get(i).getCol(),neighbors.get(i).getType());
          sendToList.setPrevious(temp);
          exploreList.add(sendToList);
        //}
      }
    }
    return temp;
  }
}

  // Set the squares in the path appropriately and set the path
  // from start to finish.
  public void setPath(Square finish) {
    if (!finish.isStart()) {
      Square mySq = finish;
      path = path + "\n"+"Path from start to finish: ";
      String coordinates = "";
      while ((mySq != null)) {
        int curRow = mySq.getRow();
        int curCol = mySq.getCol();
        mySq.setFinalPath();
        String currentCoord = "[" + curRow + "," + curCol + "] ";
        coordinates = currentCoord.concat(coordinates);
        mySq = mySq.getPrevious();
      }
      path = path + coordinates;
    }
    else {
      int curRow = finish.getRow();
      int curCol = finish.getCol();
      String currentCoord = "[" + curRow + "," + curCol + "] ";
      path = path + "\n"+"Path from start to finish: "+ currentCoord + currentCoord;
    }
  }

  /**
  * Get the number of elements that are left on the exploreList
  * @return The size of the exploreList
  */
  public int getExploreListSize() {
    return exploreList.size();
  }

  /**
  * Get the path from start to exit, if any.
  * @return Path from S to E as a list of coordinates [row,col]
  * If not solvable, the path is a message
  */
  public String getPath() {
    if (foundNemo) {
      return path;
    } else {
      path = "Uh Oh!! Could not find Nemo!!";
      return path;
    }
  }


  /** A program to solve a sea using either BFS or DFS */
  public static void main(String[] args) {
    System.out.println("hello");
    Sea sea;
    Explorer dory, marlin;
    int doryBetter=0, marlinBetter=0;
    for (int j=0; j<10; j++) {
      for (int i = 5; i < 105; i += 5) {
        sea = new Sea(i, i);
        dory = new Explorer(sea, new StackExploreList());
        dory.solve();
        int d, m;
        d = dory.getTotalVisited();
        sea.clearMaze();
        marlin = new Explorer(sea, new QueueExploreList());
        marlin.solve();
        m = marlin.getTotalVisited();
        if (d>m) doryBetter++;
        else marlinBetter ++;
      }
    }
    System.out.println("Number of times Dory found Nemo faster: "+doryBetter+"\nNumber of times Marlin found Nemo Faster: "+marlinBetter);
  }
}
