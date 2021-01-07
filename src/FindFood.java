import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.*;
public class FindFood {
    private static final String RESTAURANT_FILE_PATH = "/Users/mac/Downloads/Programs/JAVA/RestaturantApp/src/sample.txt";
    private static double latitude,longitude;
    private static int rating;
    static ArrayList<Restaurant> restaurantsList;

    private static double dist(double x1, double y1, double x2, double y2) {
        // convert to radians
        double xr1 = Math.toRadians(x1);
        double xr2 = Math.toRadians(x2);
        double yr1 = Math.toRadians(y1);
        double yr2 = Math.toRadians(y2);

        // use formula given
        double arc = Math.acos(Math.sin(xr1) * Math.sin(xr2)
                + Math.cos(xr1) * Math.cos(xr2) * Math.cos(yr1 - yr2));
        double dist = 1.1516 * 60 * Math.toDegrees(arc);
        return dist;
    }

    private static double findNext(double x, double y, double lat, double lon){
        double distance = dist(x,y,lat,lon);

        return distance;
    }
    private static void markFound(int f, double[] lat, double[] lon) {
        lat[f] = Double.POSITIVE_INFINITY;
        lon[f] = Double.POSITIVE_INFINITY;
    }
    private static int findNext(double x, double y, double[] lat, double[] lon) {
        int N = lat.length;
        double minDist = Double.POSITIVE_INFINITY;
        int closest = 0;
        for (int i = 0; i < N; i++) {
            double d = dist(x, y, lat[i], lon[i]);
            if (minDist > d) {
                minDist = d;
                closest = i;
            }
        }
        return closest;
    }



    public static void main(String[] args) {
        getInput();

        restaurantsList = readRestaurantData();



    }

    private static ArrayList<Restaurant> readRestaurantData(){
        ArrayList<Restaurant> restaurantsList = new ArrayList<>();
        try {
            File myObj = new File(RESTAURANT_FILE_PATH);
            Scanner myReader = new Scanner(myObj);




            int N = Integer.parseInt(myReader.nextLine());
            String[] names = new String[N];
            double[] lat   = new double[N];
            double[] lon   = new double[N];
            int[] ratings  = new int[N];
            int ii = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String [] arr = data.split(" ");
                if(arr.length>1) {
                    Restaurant restaurant = new Restaurant(arr[0], Double.parseDouble(arr[1]), Double.parseDouble(arr[2]), Integer.parseInt(arr[3]),0);
                    restaurantsList.add(restaurant);

                    names[ii] = arr[0];
                    lat[ii]   = Double.parseDouble(arr[1]);
                    lon[ii]   =Double.parseDouble(arr[2]);
                    ratings[ii] = Integer.parseInt(arr[3]);
                }else{
                    N = Integer.parseInt(arr[0]);
                }

            }

            for (int i = 0; i < N; i++) {
                int next = findNext(latitude, longitude, lat, lon);
                if (ratings[next] >= rating) {
                    System.out.println(names[next] + " has a rating of "
                            + ratings[next]);
                    double dist = dist(latitude, longitude, lat[next], lon[next]);
                    System.out.println("Distance:  miles "+dist);
                }
                // remove from consideration
                markFound(next, lat, lon);
            }


        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return restaurantsList;
    }

    private static void getInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your current latitude");
        latitude = scanner.nextDouble();
        System.out.println("Enter your current longitude");
        longitude = scanner.nextDouble();
        System.out.println("Enter minimum rating");
        rating = scanner.nextInt();
    }

}
