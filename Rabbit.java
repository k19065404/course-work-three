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
        BREEDING_AGE = 1;
        MAX_AGE = 6;
        BREEDING_PROBABILITY = 0.8;
        MAX_LITTER_SIZE = 4;
        isAsleep = false;
        colour = Color.orange;
        Species = "Rabbit";
        PLANT_FOOD_VALUE = 4;
        foodLevel = 6;

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
        // New foxes are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        Location location = getLocation();
        Object foundAnimal = null;
        boolean breadable = false;

        List <Animal> foundAnimals = field.getAnimalAt(location);
        
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        for(int i = 0; i<foundAnimals.size(); i++){
            if(foundAnimals.get(i).getSpecies().equals(this.getSpecies()) && foundAnimals.get(i).getGender() != this.getGender()){
                breadable = true;
                break;
            }
        }
        if(breadable){
            int births = breed();
            for(int b = 0; b < births && free.size() > 0; b++) {
                Location loc = free.remove(0);
                boolean gender = rand.nextBoolean();
                Prey young = new Rabbit(false, field, loc, gender);
                newRabbits.add(young);
                System.out.println("rabbit born");
            }
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