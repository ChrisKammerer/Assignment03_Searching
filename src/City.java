import java.util.ArrayList;

public class City {
    private String name;
    private double x;
    private double y;
    private ArrayList<Neighbor> neighbors;

    public City(String name, double x, double y, ArrayList<Neighbor> neighbors){
        this.name = name;
        this.x = x;
        this.y = y;
        this.neighbors = neighbors;
    }

    public boolean equals(City city){
        return this.name.equals(city.getName());
    }

    public String getName(){return name;}
    public double getX(){return x;}
    public double getY(){return y;}
    public ArrayList<Neighbor> getNeighbors(){return neighbors;}

    public String toString(){return this.name;}
}
