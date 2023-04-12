import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Path implements Comparable<Path> {
    private double totalDistance;
    private double nodeDistance;
    private ArrayList<City> points;
//    HashMap<String, City> cities;

    public Path(){
        this.totalDistance = 0.0;
        this.nodeDistance = 0.0;
        this.points = new ArrayList<>();
    }

    public Path(ArrayList<City> cities){
        this.totalDistance = 0.0;
        this.nodeDistance = 0.0;
        this.points = cities; // (ArrayList<City>)path.getPoints().clone()
    }

    public Path(Path other){
        this.totalDistance = other.getTotalDistance();
//        this.totalDistance = 0.0;
        this.nodeDistance = other.getNodeDistance();
        this.points = (ArrayList<City>)other.getPoints().clone();
    }

    public void addCity(City city){points.add(city);}

    public void addCity(City city, double weight){
        points.add(city);
        totalDistance+=weight;
    }
    public void addCity(City city, double weight, double nodeDistance){
        points.add(city);
        totalDistance-=this.nodeDistance;
        this.nodeDistance = nodeDistance;
        totalDistance+=this.nodeDistance;
        totalDistance+=weight;
    }
    public ArrayList<City> getPoints(){return points;}

    public double getTotalDistance(){return totalDistance;}

    public City getLastCity(){return points.get(points.size()-1);}

    public double getNodeDistance(){return nodeDistance;}

    public String toString(){return points.toString();}

    @Override
    public int compareTo(Path path) {
        if(this.totalDistance<path.getTotalDistance())
            return -1;
        else if(this.totalDistance==path.getTotalDistance())
            return 0;
        else
            return 1;
    }
}
