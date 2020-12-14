import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FindFood {
    private static final String RESTAURANT_FILE_PATH = "/Users/mac/Downloads/Programs/JAVA/RestaturantApp/src/sample.txt";
    private static double latitude,longitude;
    private static int rating;

    double dist(double x1, double y1, double x2, double y2) {
        return new Double(1);
    }

    int findNext(double x, double y, double[] lat, double[] lon){
        return 0;
    }

    void markFound(int f, double[] lat, double[] lon){

    }

    public static void main(String[] args) {
        ArrayList<Restaurant> restaurantsList = readRestaurantData();
        getInput();
        restaurantsList.forEach((restaurant -> {
            System.out.println(restaurant.getName());
        }));

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
                    Restaurant restaurant = new Restaurant(arr[0], Double.parseDouble(arr[1]), Double.parseDouble(arr[2]), Integer.parseInt(arr[3]));
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
