import java.awt.Color;
import java.util.List;

/**
 * Write a description of class owl here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Owl extends Predator
{    
    public Owl(boolean randomAge, Field field, Location location, boolean gender){
        super(field, location, gender);
        og_BREEDING_AGE = 3;
        og_MAX_AGE = 10;
        og_BREEDING_PROBABILITY = 0.1;
        og_MAX_LITTER_SIZE = 2;
        PREY_FOOD_VALUE = 6;
        isAsleep = false;
        age = 0;
        colour = Color.pink;
        foodLevel = 60;
        Species = "Owl";
    this.setOriginal();        
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
                //System.out.println("is breadable: " + breadable);
                break;
            }
        }
        if(breadable){
            int births = breed();
            for(int b = 0; b < births && free.size() > 0; b++) {
                Location loc = free.remove(0);
                boolean gender = rand.nextBoolean();
                Predator young = new Owl(false, field, loc, gender);
                newPredator.add(young);
            }
        }
    }
    
    private int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE+1) + 1;
        }
        return births;
    }
    
    private boolean canBreed()
    {
        return age >= BREEDING_AGE;
    }
    
    public void toggleAsleep(Time time){
        if (time.timeOfDay()){
            isAsleep = false;
        } else {
            isAsleep = true;
        }
    }
}