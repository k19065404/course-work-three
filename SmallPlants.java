
/**
 * Write a description of class SmallPlants here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SmallPlants extends Plant
{
    private int foodvalue;
    
    public SmallPlants(Field field, Location location){
        super(field, location);
        age = 0;
        MAX_AGE = 6;
        foodvalue = 1;
    }
    
    public void incrementAge(){
        age++;
        foodvalue++;
        if(age > MAX_AGE) {
            setDead();
        }
    }
}
