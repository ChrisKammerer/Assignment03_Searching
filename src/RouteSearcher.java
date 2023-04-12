import java.util.*;

public class RouteSearcher {
    public Path breadthFirst(HashMap<String, City> citiesMap, City start, City end){
        int count = 1;
        Queue<Path> frontier = new LinkedList<>();
        ArrayList<City> explored = new ArrayList<>();
        if (start==end){
            return new Path();
        }
        Path path = new Path();
        path.addCity(start);
        frontier.add(path);
        while(!frontier.isEmpty()){
            count++;
            path = frontier.remove();
            explored.add(path.getLastCity());
            for(Neighbor neighbor : path.getLastCity().getNeighbors()){
                City neighborCity = citiesMap.get(neighbor.getName());
                if(!inFrontier(frontier, neighborCity)&&!inExplored(explored, neighborCity)){
                    Path newPath = new Path((ArrayList<City>)path.getPoints().clone());
                    newPath.addCity(neighborCity);
                    if(neighborCity.equals(end)){
                        System.out.println(count);
                        return newPath;
                    }
                    frontier.add(newPath);
                }
            }
        }
        return new Path();
    }

    public Path uniformCost(HashMap<String, City> citiesMap, City start, City end){
        PriorityQueue<Path> frontier = new PriorityQueue<>();
        ArrayList<City> explored = new ArrayList<>();
        int count = 1;
        if(start==end){
            return new Path();
        }
        Path path = new Path();
        path.addCity(start);
        frontier.add(path);
        while(!frontier.isEmpty()){
            count++;
            path = frontier.remove();
            if(path.getLastCity().equals(end)) {
                System.out.println(count);
                return path;
            }
            explored.add(path.getLastCity());
            for(Neighbor neighbor : path.getLastCity().getNeighbors()){
                City neighborCity = citiesMap.get(neighbor.getName());
                if(!inFrontier(frontier, neighborCity)&&!inExplored(explored, neighborCity)) {
                    Path newPath = new Path(path);
                    newPath.addCity(neighborCity, neighbor.getDistance());
                    frontier.add(newPath);
                }
                else if(inFrontier(frontier, neighborCity)){
                    Path pathToRemove = getPathFromCity(neighborCity, frontier);
                    frontier.remove(pathToRemove);
                    Path newPath = new Path(path);
                    newPath.addCity(neighborCity, neighbor.getDistance());
                    frontier.add(newPath);
                }
            }

        }
        System.out.println(count);
        return new Path();
    }

    public Path uniformCost2(HashMap<String, City> citiesMap, City start, City end){
        PriorityQueue<Path> frontier = new PriorityQueue<>();
        ArrayList<City> explored = new ArrayList<>();
        int count = 1;
        Path path0 = new Path();
        path0.addCity(start);
        frontier.add(path0);
        while(true){
            count++;
            if(frontier.isEmpty())return null;
            Path path = frontier.remove();
            if(path.getLastCity().equals(end)){
                System.out.println(count);
                return path;
            }
            explored.add(path.getLastCity());
            for(Neighbor neighbor : path.getLastCity().getNeighbors()){
                City neighborCity = citiesMap.get(neighbor.getName());
                if(!inFrontier(frontier, neighborCity)&&!explored.contains(neighborCity)){
                    Path newPath = new Path(path);
                    newPath.addCity(neighborCity, neighbor.getDistance());
                    frontier.add(newPath);
                }
                else if(inFrontier(frontier, neighborCity)&&!path.getPoints().contains(neighborCity)){
                    Path pathToRemove = getPathFromCity(neighborCity, frontier);
                    if(pathToRemove.getTotalDistance()>path.getTotalDistance()+ neighbor.getDistance()) {
                        frontier.remove(pathToRemove);
                        Path newPath = new Path(path);
                        newPath.addCity(neighborCity, neighbor.getDistance());
                        frontier.add(newPath);
                    }
                }
            }
        }
    }

    public Path heuristicSearch2(HashMap<String, City> citiesMap, City start, City end){
        PriorityQueue<Path> frontier = new PriorityQueue<>();
        ArrayList<City> explored = new ArrayList<>();
        double endX = end.getX();
        double endY = end.getY();
        int count = 1;
        Path path0 = new Path();
        path0.addCity(start);
        frontier.add(path0);
        while(true){
            count++;
            if(frontier.isEmpty())return null;
            Path path = frontier.remove();
            if(path.getLastCity().equals(end)){
                System.out.println(count);
                return path;
            }
            explored.add(path.getLastCity());
            for(Neighbor neighbor : path.getLastCity().getNeighbors()){
                City neighborCity = citiesMap.get(neighbor.getName());
                if(!inFrontier(frontier, neighborCity)&&!explored.contains(neighborCity)){
                    Path newPath = new Path(path);
                    newPath.addCity(neighborCity, neighbor.getDistance(),
                            getEuclidianDistance(neighborCity.getX(), neighborCity.getY(), endX, endY));
                    frontier.add(newPath);
                }
                else if(inFrontier(frontier, neighborCity)&&!path.getPoints().contains(neighborCity)&&!explored.contains(neighborCity)){
                    Path pathToRemove = getPathFromCity(neighborCity, frontier);
                    Path testPath = new Path(path);
                    testPath.addCity(neighborCity, neighbor.getDistance(),
                            getEuclidianDistance(neighborCity.getX(), neighborCity.getY(), endX, endY));
                    if(pathToRemove.getTotalDistance()>testPath.getTotalDistance()) {
                        frontier.remove(pathToRemove);
                        frontier.add(testPath);
                    }
                }
            }
        }
    }

    public boolean inFrontier(Queue<Path> frontier, City query){
        for(Path path : frontier){
            for(City city : path.getPoints()){
                if(city.equals(query)) return true;
            }
        }
        return false;
    }
    public boolean inExplored(ArrayList<City> explored, City query){
        for(City city : explored){
            if(city.equals(query)) return true;
        }
        return false;
    }
    public double getEuclidianDistance(double x1, double y1, double x2, double y2){
        double x = Math.pow((x2-x1), 2);
        double y = Math.pow((y2-y1), 2);
        return Math.sqrt(x+y);
    }
    public Path getPathFromCity(City city, PriorityQueue<Path> queue){
        for(Path path : queue){
            if(path.getPoints().contains(city)) return path;
        }
        return null;
    }
}
