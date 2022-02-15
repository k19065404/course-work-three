
/**
 * Write a description of class Trees here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tree extends Plant
{
    
    public Tree(Field field, Location location){
        super(field, location);
        randomAge = random.nextBoolean();
        if(randomAge){
           age = random.nextInt(40); 
        } else {
            age = 0;
        }
        MAX_AGE = 60;
    }
    
    public void incrementAge(){
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }
    
    
}
