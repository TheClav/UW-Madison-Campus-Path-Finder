import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Backend  implements BackendInterface {

    Double totatPathCost = 0.0;
    GraphADT graph;
    public Backend(GraphADT<String, Double> graph){

        this.graph = graph;
    };
    @Override
    public boolean readData(String filepath) throws FileNotFoundException {

        if(filepath ==null){
            throw new FileNotFoundException();
        }
        Scanner sc = new Scanner (new File (filepath));
        sc.nextLine(); //skip first line

        while(sc.hasNextLine()){
            if(sc.nextLine().equals("}")){
                break;
            }
        String str = sc.nextLine();//read next line
        String[] values = str.split("\"");//split string along "

        String startBuilderName = values[1];//location 1
        String endBuilderName = values[3];//location 2

        String pathcost = values[4].substring(values[4].indexOf('=')+1,values[4].indexOf(']'));//find cost
        Double cost = Double.parseDouble(pathcost);//path cost

            if(!graph.containsNode(startBuilderName)){
                graph.insertNode(startBuilderName);
            }

            if(!graph.containsNode(endBuilderName)){
                graph.insertNode(endBuilderName);
            }

            graph.insertEdge(startBuilderName,endBuilderName, cost);
            totatPathCost = totatPathCost + cost

        }
        return true;
    }

    @Override
    public PathInterface getShortestRoute(String start, String destination) throws NoSuchElementException {
        List<String> pathList = graph.shortestPathData(start, destination);
        Double totalCost = graph.shortestPathCost(start, destination);

        DijkstraGraph.SearchNode sn = ((DijkstraGraph)graph).computeShortestPath(start,destination);
        List<Double> pathCosts = new LinkedList<>();

        DijkstraGraph.SearchNode current = sn;
        while(current.predecessor != null){
            ((LinkedList<Double>)pathCosts).addFirst(current.cost);
            current = current.predecessor;
        }

        ShortestPath path = new ShortestPath(pathList,pathCosts,totalCost);

        return path;
    }

    @Override
    public String getDataSummary() {
        return "Buildings: " + graph.getNodeCount() + ", Paths: " + graph.getEdgeCount() + ", Total Walking Time: "+ totatPathCost;
    }



}
