import java.awt.Color;
import java.util.List;

/**
 * Write a description of class Mouse here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Mouse extends Prey
{   
    
    private static String species = "mouse";
    
    public Mouse(boolean randomAge, Field field, Location location, boolean gender){
        super(field, location, gender);
        age = 0;
        og_BREEDING_AGE = 0;
        og_MAX_AGE = 50;
        og_BREEDING_PROBABILITY = 0.4;
        og_MAX_LITTER_SIZE = 6;
        isAsleep = false;
        colour = Color.red;
        species = "Mouse";
        PLANT_FOOD_VALUE = 10;
        foodLevel = 50;
        this.setOriginal();
        if(randomAge){
            age = rand.nextInt(MAX_AGE);
        }
    }
    
    public String getSpecies(){
        return species;
    }
    
    public Color getColor(){
        return colour;
    }
    
    public void giveBirth(List<Animal> newPrey){
        // New foxes are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        Location location = getLocation();
        Object foundAnimal = null;
        boolean breadable = false;

        List <Animal> foundAnimals = field.getAnimalAt(location);
        
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        for(int i = 0; i<foundAnimals.size(); i++){
            //System.out.println("prey found");
            if(foundAnimals.get(i).getSpecies().equals(this.getSpecies()) && foundAnimals.get(i).getGender() != this.getGender()){
                breadable = true;
                //System.out.println("mouse pair found");
                //break;
            }
        }
        if(breadable){
            int births = breed();
            for(int b = 0; b < births && free.size() > 0; b++) {
                Location loc = free.remove(0);
                boolean gender = rand.nextBoolean();
                Prey young = new Mouse(false, field, loc, gender);
                newPrey.add(young);
                //System.out.println("mouse born");
            }
        }
    }
    
    private int breed(){
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