import java.awt.Color;
import java.util.List;

/**
 * Write a description of class Rabbit here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Rabbit extends Prey
{

    private static String species = "rabbit"; 
    
    public Rabbit(boolean randomAge, Field field, Location location, boolean gender){
        super(field, location, gender);
        age = 0;
        BREEDING_AGE = 2;
        MAX_AGE = 100;
        BREEDING_PROBABILITY = 0.8;
        MAX_LITTER_SIZE = 100;
        isAsleep = false;
        colour = Color.orange;
        Species = "Rabbit";

        if(randomAge){
            age = rand.nextInt(MAX_AGE);
        }
    }

    public String getSpecies(){
        return Species;
    }
    
    public Color getColor(){
        return colour;
    }

    public void giveBirth(List<Animal> newRabbits)
    {
        // New rabbits are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            boolean gender = rand.nextBoolean();
            Prey young = new Mouse(false, field, loc, true);
            newRabbits.add(young);
        }
    }

    private int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

    private boolean canBreed()
    {
        return age >= BREEDING_AGE;
    }
    
    public void toggleAsleep(Time time){
        if (time.timeOfDay()){
            isAsleep = true;
        } else {
            isAsleep = false;
        }
    }
}
