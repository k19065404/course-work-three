import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Write a description of class Weather here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Weather
{
    // instance variables - replace the example below with your own
    private int x;
    private String currentWeather;
    private List<String> weathers;
    /**
     * Constructor for objects of class Weather
     */
    public Weather()
    {
        currentWeather= "sunny";
        weathers = new ArrayList<>();
        weathers.add("sun");
        weathers.add("rain");
        weathers.add("wind");
        weathers.add("fog");
    }
    
    public void changeWeather(){
        Random rand;
        rand= Randomizer.getRandom();
        int random = rand.nextInt(4);
        currentWeather = weathers.get(random);
        System.out.println("The weather is now " + currentWeather);
        
    }
    
    public String getCurrentWeather(){
        return currentWeather;
    }

    
}
