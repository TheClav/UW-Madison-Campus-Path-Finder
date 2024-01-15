import java.util.List;
/**
 * An interface for a class that stores the results of the shortest
 * path search
 */
public interface PathInterface {

  /**
   * Getter method for shortest path between two buildings
   * @return list of buildings along the path
   */
  public List<String> shortestPath();

  /**
   * Getter method for a list of the walking times of the buildings
   * listed in the shortest path list
   * @return list of the walking times between buildings along the path
   */
  public List<Double> walkingTimes();

  /**
   * Getter method for the total path cost of the time it takes to walk
   * from the start to the destination building
   * @return path cost as the estimated time
   */
  public double pathCost();
}

