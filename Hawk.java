import java.awt.Color;
import java.util.List;

/**
 * Write a description of class hwak here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Hawk extends Predator
{
    
    public Hawk(boolean randomAge, Field field, Location location, boolean gender){
        super(field, location, gender);
        BREEDING_AGE = 35;
        MAX_AGE = 70;
        BREEDING_PROBABILITY = 0.6;
        MAX_LITTER_SIZE = 3;
        PREY_FOOD_VALUE = 8;
        age = 0;
        colour = Color.green;
        foodLevel = 80;
        Species = "Hawk";
        
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
        }
    }
    
    public String getSpecies(){
        return Species;
    }
    
    public Color getColor(){
        return colour;
    }
    
    public void giveBirth(List<Animal> newPredator){
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
                Predator young = new Hawk(false, field, loc, gender);
                newPredator.add(young);
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
}
