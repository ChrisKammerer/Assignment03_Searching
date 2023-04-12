import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GraphReader {
    public HashMap<String, City> getCities(String path){
        HashMap<String, City> cities = new HashMap<>();
        try{
            File file = new File(path);
            Scanner input = new Scanner(file);
            String line = "";
            while(input.hasNext()){
                line = input.nextLine();
                String[] citiesArr =  line.split(", ");
                ArrayList<Neighbor> relatedCities = new ArrayList<>();
                for(int i=3; i<citiesArr.length; i+=2){
                    Neighbor relCity = new Neighbor(citiesArr[i], Double.parseDouble(citiesArr[i+1]));
                    relatedCities.add(relCity);
                }
                String name = citiesArr[0];
                double x = Double.parseDouble(citiesArr[1]);
                double y = Double.parseDouble(citiesArr[2]);
                cities.put(citiesArr[0], new City(name, x, y, relatedCities));
            }

        } catch(Exception e){
            e.printStackTrace();
        }
        return cities;
    }

    public HashMap<String, City> getMaze(String path){
        HashMap<String, City> mazePoints = new HashMap<>();
        try{
            File file = new File(path);
            Scanner input = new Scanner(file);
            double[][] mazeArray = parseMaze(input);
            int xDim = mazeArray.length;
            int yDim = mazeArray[0].length;
            for(int x = 0; x<mazeArray.length; x++){
                for(int y = 0; y<mazeArray[x].length; y++){
                    if(mazeArray[x][y]!=0) {
                        ArrayList<Neighbor> neighbors = new ArrayList<>();
                        if (isValidLocation(mazeArray, x, y - 1)&&mazeArray[x][y-1]!=0) {
                            neighbors.add(new Neighbor(x + "," + (y - 1), 0));
                        }
                        if (isValidLocation(mazeArray, x + 1, y)&&mazeArray[x+1][y]!=0) {
                            neighbors.add(new Neighbor((x + 1) + "," + y, 0));
                        }
                        if (isValidLocation(mazeArray, x, y + 1)&&mazeArray[x][y+1]!=0) {
                            neighbors.add(new Neighbor(x + "," + (y + 1), 0));
                        }
                        if (isValidLocation(mazeArray, x - 1, y)&&mazeArray[x-1][y]!=0) {
                            neighbors.add(new Neighbor((x - 1) + "," + y, 0));
                        }
                        String name = x + "," + y;
                        mazePoints.put(name, new City(name, x, y, neighbors));
                    }
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return mazePoints;
    }


    public double[][] parseMaze(Scanner input) {

        try {

            int size = 0;
            for(int i = 0; i<2; i++) {
                if(i == 1) {
                    String[] data = input.nextLine().split(" ");
                    size = Integer.valueOf(data[0]);
                }
                else {
                    input.nextLine();
                }
            }
            double[][] output = new double[size][size];
            while(input.hasNext()) {
                for(int r = 0; r<size; r++) {
                    String[] b = input.nextLine().split(" ");
                    double[] x = new double[size];
                    for(int i = 0; i<size; i++) {
                        x[i] = Double.valueOf(b[i]);
                    }
                    output[r] = x;
                }
            }
            return output;
        }
        catch(Exception e) {
            e.printStackTrace();;
        }
        return null;
    }

    public boolean isValidLocation(double[][] matrix, int x, int y){
        return x>=0 && x<matrix.length &&
                y>=0 && y<matrix[0].length;
    }
}
