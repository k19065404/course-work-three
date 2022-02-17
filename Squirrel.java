import java.awt.Color;
import java.util.List;

/**
 * Write a description of class Rabbit here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Squirrel extends Prey
{

    private static String species = "rabbit"; 
    
    public Squirrel(boolean randomAge, Field field, Location location, boolean gender){
        super(field, location, gender);
        age = 0;
        BREEDING_AGE = 2;
        MAX_AGE = 100;
        BREEDING_PROBABILITY = 0.9;
        MAX_LITTER_SIZE = 100;
        isAsleep = false;
        colour = Color.orange;
        Species = "Rabbit";
        PLANT_FOOD_VALUE = 4;
        foodLevel = 50;


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

    public void giveBirth(List<Animal> newSquirrel)
    {
        // New foxes are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        Location location = getLocation();
        Object foundAnimal = null;
        boolean breadable = false;

        List <Animal> foundAnimals = field.getAnimalAt(location);
        System.out.println("Squirrel giveBirth method call");
        System.out.println(foundAnimals.size());
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        for(int i = 0; i<foundAnimals.size(); i++){
            System.out.println("prey found");
            if(foundAnimals.get(i).getSpecies().equals(this.getSpecies()) && foundAnimals.get(i).getGender() != this.getGender()){
                breadable = true;
                System.out.println("mouse pair found");
                //break;
            }
        }
        if(breadable){
            int births = breed();
            for(int b = 0; b < births && free.size() > 0; b++) {
                Location loc = free.remove(0);
                boolean gender = rand.nextBoolean();
                Prey young = new Mouse(false, field, loc, gender);
                newSquirrel.add(young);
                System.out.println("squirrel born");
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
