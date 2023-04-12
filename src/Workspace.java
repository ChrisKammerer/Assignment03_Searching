import java.util.HashMap;

public class Workspace {
    public static void main(String[] args) {
        GraphReader reader = new GraphReader();
        RouteSearcher searcher = new RouteSearcher();
        DisplayMaps display = new DisplayMaps();


//        String path = "romania.txt";
//        HashMap<String, City> cities = reader.getCities(path);
//        City start = cities.get("Oradea");
//        City end = cities.get("Vasiui");
//        City start = cities.get("Neamt");
//        City end = cities.get("Timisoara");
//        Path route = searcher.breadthFirst(cities, start, end);
//        Path costRoute = searcher.uniformCost2(cities, start, end);
//        Path heuristicRoute = searcher.heuristicSearch2(cities, start, end);
//        System.out.println(route);
//        System.out.println(costRoute);
//        System.out.println(heuristicRoute);
//        display.displayCitiesPath(cities, route);
//        display.displayCitiesPath(cities, costRoute);
//        display.displayCitiesPath(cities, heuristicRoute);

//        String mazePath = "map_data/maze04-small.txt";
//        HashMap<String, City> maze = reader.getMaze(mazePath);
//        City mazeStart = maze.get("0,0");
//        City mazeEnd = maze.get("10,10");
//        Path pathMaze = searcher.heuristicSearch2(maze, mazeStart, mazeEnd);
//        display.displayMazePath(maze, pathMaze, 11, 11, 20);

//        String mazePath = "map_data/maze06-medium.txt";
//        HashMap<String, City> maze = reader.getMaze(mazePath);
//        City mazeStart = maze.get("0,0");
//        City mazeEnd = maze.get("30,30");
//        Path pathMaze = searcher.breadthFirst(maze, mazeStart, mazeEnd);
//        Path pathMazeA = searcher.heuristicSearch2(maze, mazeStart, mazeEnd);
//        display.displayMazePath(maze, pathMaze, 31, 31, 20);
//        display.displayMazePath(maze, pathMazeA, 31, 31, 20);

        String mazePath = "map_data/maze08-large.txt";
        HashMap<String, City> maze = reader.getMaze(mazePath);
        City mazeStart = maze.get("0,0");
        City mazeEnd = maze.get("80,80");
        Path pathMaze = searcher.breadthFirst(maze, mazeStart, mazeEnd);
        Path pathMazeA = searcher.heuristicSearch2(maze, mazeStart, mazeEnd);
        display.displayMazePath(maze, pathMaze, 81, 81, 10);
//        display.displayMazePath(maze, pathMazeA, 81, 81, 10);

//        String mazePath = "map_data/maze09-huge.txt";
//        HashMap<String, City> maze = reader.getMaze(mazePath);
//        City mazeStart = maze.get("0,0");
//        City mazeEnd = maze.get("160,160");
//        Path pathMaze = searcher.breadthFirst(maze, mazeStart, mazeEnd);
//        Path pathMazeA = searcher.heuristicSearch2(maze, mazeStart, mazeEnd);
//        display.displayMazePath(maze, pathMaze, 161, 161, 5);
//        display.displayMazePath(maze, pathMazeA, 161, 161, 5);


    }
}
