import java.util.List;
import java.util.Random;

/**
 * A class representing shared characteristics of animals.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29 (2)
 */
public abstract class Animal
{
    // Whether the animal is alive or not.
    private boolean alive;
    // The animal's field.
    private Field field;
    // The animal's position in the field.
    private Location location;
    //determines the gender
    protected boolean isFemale;
    protected int BREEDING_AGE;
    protected int MAX_AGE;
    protected double BREEDING_PROBABILITY;
    protected int MAX_LITTER_SIZE;
    protected int og_BREEDING_AGE;
    protected int og_MAX_AGE;
    protected double og_BREEDING_PROBABILITY;
    protected int og_MAX_LITTER_SIZE;
    protected double diseaseProbability;
    protected Random rand = Randomizer.getRandom();
    protected static int counter = 0;
    
    protected String Species;
    
    /**
     * Create a new animal at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param gender true if female, false if male
     */
    public Animal(Field field, Location location, boolean gender)
    {
        alive = true;
        isFemale = gender;
        this.field = field;
        diseaseProbability = 0.00001;
        setLocation(location);
        
    }
    
    /**
     * Make this animal act - that is: make it do
     * whatever it wants/needs to do.
     * @param newAnimals A list to receive newly born animals.
     */
    abstract public void act(List<Animal> newAnimals, Time time);
    
    
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
    }

    /**
     * Return the animal's location.
     * @return The animal's location.
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
    }
    
    /**
     * Return the animal's field.
     * @return The animal's field.
     */
    protected Field getField()
    {
        return field;
    }
    
    protected boolean getGender(){
        return isFemale;
    }
    
    public String getSpecies(){
        return Species;
    }
    
    public void changeWeather(String weather){
        setOriginal();
        if (weather.equals("sun")){
            MAX_LITTER_SIZE= og_MAX_LITTER_SIZE *3;
        } else if (weather.equals("rain")){
            BREEDING_PROBABILITY= og_BREEDING_PROBABILITY * 2;
        } else if (weather.equals("fog")){
            BREEDING_PROBABILITY= og_BREEDING_PROBABILITY / 2;
        } else {
            MAX_LITTER_SIZE = og_MAX_LITTER_SIZE /2 ;
        }
        }
        
    protected void setOriginal(){
        MAX_LITTER_SIZE= og_MAX_LITTER_SIZE;
        BREEDING_PROBABILITY= og_BREEDING_PROBABILITY;
        MAX_AGE= og_MAX_AGE;
        BREEDING_AGE = og_BREEDING_AGE;
    }
    
    public void Disease(double disease){
        if(disease < diseaseProbability){
            double deathProbability = 0.001;
            double die = rand.nextDouble();
            counter++;
            
            if(die < deathProbability){
                this.setDead();
                System.out.println("animal died due to disease: " + counter);
            } else{
                this.BREEDING_PROBABILITY = 0;
                this.MAX_AGE = MAX_AGE/2;
                System.out.println("animal crippled due to disease: " + counter);
            }
        }
    }
    }


