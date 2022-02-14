import java.awt.Color;

/**
 * Write a description of class Rabbit here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Rabbit extends Prey
{
    public Rabbit(boolean randomAge, Field field, Location location)
    {
        super(randomAge, field, location, Color.blue);
        BREEDING_AGE= 4;
        MAX_AGE= 40;
        BREEDING_PROBABILITY= 0.6;
        MAX_LITTER_SIZE = 10;
        FOOD_VALUE= 9;
        //rand= Randomizer.getRandom();
        age = 0;
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
        }
    }
}
