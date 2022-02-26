import java.util.List;
import java.util.Iterator;
import java.util.Random;
import java.awt.Color;

/**
 * A simple model of a fox.
 * Foxes age, move, eat rabbits, and die.
 * 
 * @author David J. Barnes and Michael KÃ¶lling
 * @version 2016.02.29 (2)
 */
public abstract class Predator extends Animal
{
    // Characteristics shared by all foxes (class variables).

    // The age at which a fox can start to breed.
    //protected int BREEDING_AGE;
    // The age to which a fox can live.
    //protected int MAX_AGE;
    // The likelihood of a fox breeding.
    //protected double BREEDING_PROBABILITY;
    // The maximum number of births.
    //protected int MAX_LITTER_SIZE;
    // The food value of a single rabbit. In effect, this is the
    // number of steps a fox can go before it has to eat again.
    protected int PREY_FOOD_VALUE;
    //is the animal asleep
    protected boolean isAsleep;
    // A shared random number generator to control breeding.
    protected Random rand = Randomizer.getRandom();
    //can only eat every 10 steps
    //protected boolean canEat;

    // Individual characteristics (instance fields).
    // The fox's age.
    protected int age;
    // The fox's food level, which is increased by eating rabbits.
    protected int foodLevel;

    protected Color colour; 

    /**
     * Create a fox. A fox can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param randomAge If true, the fox will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Predator(Field field, Location location, boolean gender)
    {
        super(field, location, gender);
    }

    /**
     * This is what the fox does most of the time: it hunts for
     * rabbits. In the process, it might breed, die of hunger,
     * or die of old age.
     * @param field The field currently occupied.
     * @param newPredator A list to return newly born foxes.
     */
    public void act(List<Animal> newPredator, Time time)
    {
        //incrementAge();
        //incrementHunger();
        toggleAsleep(time);
        if (!isAsleep){
            incrementHunger();
            if(isAlive()){
                giveBirth(newPredator);   
                
                // Move towards a source of food if found.

                Location newLocation = findFood();
                if(newLocation == null) { 
                    // No food found - try to move to a free location.
                    newLocation = getField().freeAdjacentLocation(getLocation());
                }
                // See if it was possible to move.
                if(newLocation != null) {
                    setLocation(newLocation);
                }
                else {
                    // Overcrowding.
                    this.setDead();
                    System.out.println("over crowding");
                }
                Disease(rand.nextDouble());

            }
        }

        // if(isAlive() && !isAsleep) {

        // giveBirth(newPredator);            
        // // Move towards a source of food if found.
        // Location newLocation = findFood();
        // if(newLocation == null) { 
        // // No food found - try to move to a free location.
        // newLocation = getField().freeAdjacentLocation(getLocation());
        // }
        // // See if it was possible to move.
        // if(newLocation != null) {
        // setLocation(newLocation);
        // }
        // else {
        // // Overcrowding.
        // setDead();
        // }
        // }
    }

    abstract public void toggleAsleep(Time time);

    /**
     * Increase the age. This could result in the fox's death.
     */
    public void incrementAge()
    {
        age = age+1;
        //System.out.println("Age now: " + age + " Max age: " + MAX_AGE);
        if(age > MAX_AGE) {
            //System.out.println("predator dies of old age");
            setDead();
        }
    }

    /**
     * Make this fox more hungry. This could result in the fox's death.
     */
    private void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }


    /**
     * Look for prey adjacent to the current location.
     * Only the first live rabbit is eaten.
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood()
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            if(animal instanceof Prey) {
                Prey prey = (Prey) animal;
                if(prey.isAlive()) { 
                    prey.setDead();
                    foodLevel += PREY_FOOD_VALUE;
                    return where;
                }
            }
        }
        return null;
    }

    /**
     * Check whether or not this fox is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newPredator A list to return newly born foxes.
     */
    public abstract void giveBirth(List<Animal> newPredator);

    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */

    /**
     * A fox can breed if it has reached the breeding age.
     */

    public Color getColor(){
        return colour;
    }
}