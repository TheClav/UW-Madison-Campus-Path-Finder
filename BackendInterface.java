import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.NoSuchElementException;

public interface BackendInterface {

    // Backend Constructor : takes an Instance of Graph ADT

    /**
     * Reads data from a file and returns a boolean accordingly
     * @param filepath - file from which data is to be read
     * @return true if file read, else false
     */
    public boolean readData(String filepath) throws FileNotFoundException;

    /**
     * Getter method for the shortest path from the start to the destination
     * building
     * @param start - start building.
     * @param destination - destination building.
     * @return shortest path
     */
    public PathInterface getShortestRoute(String start, String destination) throws NoSuchElementException;

    /**
     * Returns a string with statistics about the dataset
     *
     * @return string which contains number of nodes (buildings), the number of
     * edges, and the total walking time (sum of weights) for all edges in the
     * graph
     */
    public String getDataSummary();

}
