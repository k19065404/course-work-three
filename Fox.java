import java.awt.Color;

/**
 * Write a description of class Fox here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Fox extends Predator
{
    public Fox(boolean randomAge, Field field, Location location)
    {
        super(randomAge, field, location, Color.orange);
        MAX_AGE = 150;
        BREEDING_AGE = 15;
        BREEDING_PROBABILITY= 0.08;
        MAX_LITTER_SIZE= 2;
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
            //foodLevel = rand.nextInt(PREY_FOOD_VALUE);
            /**
             * have array with all possible prey, randomly generate value from most filling prey
             */
        }
        else {
            age = 0;
            //foodLevel = PREY_FOOD_VALUE;
        }
    }
}
