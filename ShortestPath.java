import java.util.List;

public class ShortestPath implements PathInterface{

    private List<String> shortestPath;
    private List<Double> walkingTimes;
    private double pathCost;

    public ShortestPath(List<String> shortestPath, List<Double> walkingTimes, double pathCost){
        this.shortestPath = shortestPath;
        this.walkingTimes =walkingTimes;
        this.pathCost=pathCost;
    }


    @Override
    public List<String> shortestPath() {
        return shortestPath;
    }

    @Override
    public List<Double> walkingTimes() {
        return walkingTimes;
    }

    @Override
    public double pathCost() {
        return pathCost;
    }
}
