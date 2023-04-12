import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DisplayMaps {

    public void displayMaze(HashMap<String,City> maze, int width, int height){

        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(width*20, height*20);

        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.setPenColor(StdDraw.BOOK_BLUE);
        maze.forEach((key, point) -> {
            StdDraw.filledRectangle(point.getY()+.5, height-.5-point.getX(), .5, .5);

        });
        StdDraw.show();
    }

    public void displayMazePath(HashMap<String,City> maze, Path path, int width, int height, int scale){
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(width*scale, height*scale);

        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);

        StdDraw.setPenColor(StdDraw.BOOK_BLUE);
        maze.forEach((key, point) -> {
            StdDraw.filledRectangle(point.getY()+.5, height-.5-point.getX(), .5, .5);
        });
        StdDraw.setPenColor(StdDraw.RED);
        for(City point : path.getPoints()){
            StdDraw.filledRectangle(point.getY()+.5, height-.5-point.getX(), .3, .3);

        }
        StdDraw.show();
    }

    public void displayCities(HashMap<String, City> cities){
        int width = 750;
        int height = 750;

        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(width, height);

        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);


        cities.forEach((key, city) -> {
            StdDraw.setPenColor(0,0,0);
            StdDraw.filledCircle(city.getX(), city.getY(), 3);
            StdDraw.text(city.getX(), city.getY()+10, city.getName());
            for(Neighbor relatedCity : city.getNeighbors()){
                City city2 = cities.get(relatedCity.getName());
                StdDraw.line(city.getX(), city.getY(), city2.getX(), city2.getY());
                StdDraw.setPenColor(0, 0, 255);
                StdDraw.text((city.getX()+city2.getX())/2,
                        (city.getY()+city2.getY())/2,
                        Double.toString(relatedCity.getDistance()));
                StdDraw.setPenColor(0, 0, 0);
            }
        });

        StdDraw.show();
    }

    public void displayCitiesPath(HashMap<String, City> cities, Path path){
        int width = 750;
        int height = 750;

        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(width, height);

        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);


        cities.forEach((key, city) -> {
            StdDraw.setPenColor(0,0,0);
            StdDraw.filledCircle(city.getX(), city.getY(), 3);
            StdDraw.text(city.getX(), city.getY()+10, city.getName());
            for(Neighbor relatedCity : city.getNeighbors()){
                City city2 = cities.get(relatedCity.getName());
                StdDraw.line(city.getX(), city.getY(), city2.getX(), city2.getY());
                StdDraw.setPenColor(0, 0, 255);
                StdDraw.text((city.getX()+city2.getX())/2,
                        (city.getY()+city2.getY())/2,
                        Double.toString(relatedCity.getDistance()));
                StdDraw.setPenColor(0, 0, 0);
            }
        });
        ArrayList<City> points = path.getPoints();
        StdDraw.setPenColor(Color.red);
        for(int i=0; i<points.size()-1; i++){
            double x0 = points.get(i).getX();
            double y0 = points.get(i).getY();
            double x1 = points.get(i+1).getX();
            double y1 = points.get(i+1).getY();
            StdDraw.line(x0, y0, x1, y1);
        }
        StdDraw.show();
    }


}
