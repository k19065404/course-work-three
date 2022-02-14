import java.awt.Color;


/**
 * Write a description of class owl here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Owl extends Predator
{
    public Owl(boolean randomAge, Field field, Location location)
    {
        super(randomAge, field, location, Color.black);
        MAX_AGE = 25;
        BREEDING_AGE = 10;
        BREEDING_PROBABILITY= 0.12;
        MAX_LITTER_SIZE= 4;
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
