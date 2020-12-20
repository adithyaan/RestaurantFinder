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

    private static double dist(double x1,double y1, double x2, double y2){

        double d = 0;
        double nx1=Math.toRadians(x1);
        double nx2=Math.toRadians(x2);
        double ny1=Math.toRadians(y1);
        double ny2=Math.toRadians(y2);

        double sx1=Math.cos(x1);
        double sx2=Math.sin(x2);
        double cx1=Math.cos(x1);
        double cx2=Math.cos(x2);
        double yd=ny1-ny2;
        double cyd=Math.cos(yd);

        d = 1.1516 * 60 * Math.acos( (sx1*sx2) + (cx1 * cx2 *cyd));
        return d;
    }

    private static double findNext(double x, double y, double lat, double lon){
        double distance = dist(x,y,lat,lon);

        return distance;
    }

    void markFound(int f, double[] lat, double[] lon){

    }

    public static void main(String[] args) {
        restaurantsList = readRestaurantData();
        ArrayList<Restaurant> outputList = new ArrayList<>();
        getInput();
        restaurantsList.forEach((restaurant -> {
            if(restaurant.getRating() > rating) {
                double distance = findNext(latitude, longitude, restaurant.getLatitude(), restaurant.getLongitude());
                restaurant.setDistance(distance);
                outputList.add(restaurant);
            }
        }));

        for (Restaurant re:outputList){
            System.out.println(re.getName()+"has a rating of  "+re.getRating() );
            System.out.println("Distance "+ re.getDistance()+" miles");
        }


    }

    private static ArrayList<Restaurant> readRestaurantData(){
        ArrayList<Restaurant> restaurantsList = new ArrayList<>();
        try {
            File myObj = new File(RESTAURANT_FILE_PATH);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String arr[] = data.split(" ");
                if(arr.length>1) {
                    Restaurant restaurant = new Restaurant(arr[0], Double.parseDouble(arr[1]), Double.parseDouble(arr[2]), Integer.parseInt(arr[3]),0);
                    restaurantsList.add(restaurant);
                }
            }
            myReader.close();
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
