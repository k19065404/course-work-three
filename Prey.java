import java.util.List;
import java.util.Random;
import java.awt.Color;

/**
 * A simple model of a rabbit.
 * Rabbits age, move, breed, and die.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29 (2)
 */
public abstract class Prey extends Animal
{
    // Characteristics shared by all rabbits (class variables).

    // The age at which a rabbit can start to breed.
    protected int BREEDING_AGE = 5;
    // The age to which a rabbit can live.
    protected int MAX_AGE = 40;
    // The likelihood of a rabbit breeding.
    protected double BREEDING_PROBABILITY = 0.12;
    // The maximum number of births.
    protected int MAX_LITTER_SIZE = 4;
    // A shared random number generator to control breeding.
    protected Random rand = Randomizer.getRandom();
    
    protected boolean isAsleep;
    
    // Individual characteristics (instance fields).
    
    // The rabbit's age.
    protected int age;
    
    protected Color colour;

    /**
     * Create a new rabbit. A rabbit may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the rabbit will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Prey(Field field, Location location, boolean gender)
    {
        super(field, location, gender);
    }
    
    /**
     * This is what the rabbit does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.
     * @param newRabbits A list to return newly born rabbits.
     */
    public void act(List<Animal> newPrey, Time time)
    {
        //incrementAge();
        toggleAsleep(time);
        if(isAlive() && !isAsleep) {
            giveBirth(newPrey);            
            // Try to move into a free location.
            Location newLocation = getField().freeAdjacentLocation(getLocation());
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
            }
        }
    }
    
    abstract public void toggleAsleep(Time time);

    /**
     * Increase the age.
     * This could result in the rabbit's death.
     */
    public void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }
    
    /**
     * Check whether or not this rabbit is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newRabbits A list to return newly born rabbits.
     */
    abstract public void giveBirth(List<Animal> newRabbits);
    
        // New rabbits are born into adjacent locations.
        // Get a list of adjacent free locations.
        //Field field = getField();
        //List<Location> free = field.getFreeAdjacentLocations(getLocation());
        //int births = breed();
        //for(int b = 0; b < births && free.size() > 0; b++) {
        //    Location loc = free.remove(0);
        //    Prey young = new Prey(field, loc);
        //    newRabbits.add(young);
        
    
        
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    

    /**
     * A rabbit can breed if it has reached the breeding age.
     * @return true if the rabbit can breed, false otherwise.
     */
    
    
    public Color getColor(){
        return colour;
    }
}
