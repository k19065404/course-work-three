import java.util.List;
import java.util.*;

/**
 * A class representing shared characteristics of animals.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29 (2)
 */
public abstract class Plant
{
    // Whether the animal is alive or not.
    private boolean alive;
    // The animal's field.
    private Field field;
    // The animal's position in the field.
    private Location location;
    
    protected int age;
    protected int MAX_AGE;
    protected Random random = new Random();
    protected boolean randomAge;
    
    /**
     * Create a new plant at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param gender true if female, false if male
     */
    public Plant(Field field, Location location)
    {
        alive = true;
        this.field = field;
        
        setLocation(location);
        
        location.setIsPlant(true);
        //location.toggleIsPlant();
    }
    
    /**
     * Increase the age. This could result in the fox's death.
     */
    abstract public void incrementAge();
    

    /**
     * Check whether the animal is alive or not.
     * @return true if the animal is still alive.
     */
    protected boolean isAlive()
    {
        return alive;
    }

    /**
     * Indicate that the animal is no longer alive.
     * It is removed from the field.
     */
    protected void setDead()
    {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
        System.out.println("plant died");
    }

    /**
     * Return the plant's location.
     * @return The plant's location.
     */
    protected Location getLocation()
    {
        return location;
    }
    
    /**
     * Place the animal at the new location in the given field.
     * @param newLocation The animal's new location.
     */
    protected void setLocation(Location newLocation)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
        //newLocation.setIsPlant(true);
        //System.out.println("PLANT LOCATION SET: " + newLocation.getIsPlant());
    }
    
    /**
     * Return the animal's field.
     * @return The animal's field.
     */
    protected Field getField()
    {
        return field;
    }
    
    
}

