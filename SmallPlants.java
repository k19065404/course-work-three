
/**
 * Write a description of class SmallPlants here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SmallPlants extends Plant
{
    private int foodValue;
    
    public SmallPlants(Field field, Location location){
        super(field, location);
        
        randomAge = random.nextBoolean();
        if(randomAge){
           age = random.nextInt(4); 
        } else {
            age = 0;
        }
        
        
        MAX_AGE = 6;
        foodValue = 1;
    }
    
    public void incrementAge(){
        age++;
        foodValue++;
        if(age > MAX_AGE) {
            setDead();
        }
    }
    
    public int getPlantFoodValue(){
        return foodValue;
    }
}
