import java.util.*;
public class ExplorerMain{
  public static void main(String[] args) {
    Sea sea;
    Explorer dory, marlin;
    int doryBetter=0, marlinBetter=0;
    for (int j=0; j<1; j++) {
      for (int i = 5; i < 105; i += 5) {
        sea = new Sea(i, i);
        dory = new Explorer(sea, new QueueExploreList());
        dory.solve();
        int d, m;
        d = dory.getTotalVisited();
        sea.clearMaze();
        marlin = new Explorer(sea, new StackExploreList());
        marlin.solve();
        m = marlin.getTotalVisited();
        if (d>m) doryBetter++;
        else marlinBetter ++;
      }
    }
    System.out.println("Number of times Dory found Nemo faster: "+doryBetter+"\nNumber of times Marlin found Nemo Faster: "+marlinBetter);
  }
}
