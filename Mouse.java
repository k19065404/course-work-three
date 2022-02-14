import java.awt.Color;


/**
 * Write a description of class Mouse here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Mouse extends Prey
{
   public Mouse(boolean randomAge, Field field, Location location)
    {
        super(randomAge, field, location, Color.black);
        BREEDING_AGE= 4;
        MAX_AGE= 8;
        BREEDING_PROBABILITY= 0.3;
        MAX_LITTER_SIZE = 9;
        FOOD_VALUE=9;
        //rand= Randomizer.getRandom();
        age = 0;
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
        }
    }
}
