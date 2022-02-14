import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;

/**
 * A simple predator-prey simulator, based on a rectangular field
 * containing rabbits and foxes.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29 (2)
 */
public class Simulator
{
    // Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 120;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 80;
    // The probability that a fox will be created in any given grid position.
    private static final double PREDATOR_CREATION_PROBABILITY = 0.02;
    // The probability that a rabbit will be created in any given grid position.
    private static final double PREY_CREATION_PROBABILITY = 0.08;    

    // List of animals in the field.
    private List<Animal> animals;
    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    private int step;
    // object of time class.
    private Time time = new Time();

    // current time in string formate.
    private String currentTime;

    // A graphical view of the simulation.
    private SimulatorView view;

    /**
     * Construct a simulation field with default size.
     */
    public Simulator()
    {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }

    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width)
    {
        if(width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }

        animals = new ArrayList<>();
        field = new Field(depth, width);

        // Create a view of the state of each location in the field.
        view = new SimulatorView(depth, width);
        view.setColor(Rabbit.class, Color.green);
        view.setColor(Mouse.class, Color.blue);
        view.setColor(Hawk.class, Color.orange);
        view.setColor(Owl.class, Color.red);
        view.setColor(Fox.class, Color.black);

        // Setup a valid starting point.
        reset();
    }

    /**
     * Run the simulation from its current state for a reasonably long period,
     * (4000 steps).
     */
    public void runLongSimulation()
    {
        simulate(4000);
    }

    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     * @param numSteps The number of steps to run for.
     */
    public void simulate(int numSteps)
    {
        for(int step = 1; step <= numSteps && view.isViable(field); step++) {
            simulateOneStep();
            // delay(60);   // uncomment this to run more slowly
        }
    }

    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each
     * fox and rabbit.
     */
    public void simulateOneStep()
    {
        step++;
        time.incrimentMinutes();
        currentTime = time.getTime();

        // Provide space for newborn animals.
        List<Animal> newAnimals = new ArrayList<>();        
        // Let all rabbits act.
        for(Iterator<Animal> it = animals.iterator(); it.hasNext(); ) {
            Animal animal = it.next();
            if(step % 1440 == 0){
                animal.incrementAge();
            }
            animal.act(newAnimals, time);

            if(! animal.isAlive()) {
                it.remove();
            }
        }

        
        // Add the newly born foxes and rabbits to the main lists.
        animals.addAll(newAnimals);

        view.showStatus(step, field, currentTime);
    }

    /**
     * Reset the simulation to a starting position.
     */
    public void reset()
    {
        step = 0;
        animals.clear();
        populate();

        // Show the starting state in the view.
        view.showStatus(step, field, currentTime);
    }

    /**
     * Randomly populate the field with foxes and rabbits.
     */
    private void populate()
    {
        Random rand = Randomizer.getRandom();
        Random randGender= Randomizer.getRandom();
        field.clear();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                if(rand.nextDouble() <= PREDATOR_CREATION_PROBABILITY) {
                    int predator1 = rand.nextInt(3);
                    boolean predatorGender = randGender.nextBoolean();
                    if(predator1 == 0){
                        Location location = new Location(row, col);
                        Predator predator = new Fox(false, field, location, predatorGender);
                        animals.add(predator);
                    } else if(predator1 == 1){
                        Location location = new Location(row, col);

                        Predator predator = new Hawk(false, field, location, predatorGender);
                        animals.add(predator);
                    } else{
                        Location location = new Location(row, col);
                        Predator predator = new Owl(false, field, location, predatorGender);
                        animals.add(predator);
                    }
                }
                else if(rand.nextDouble() <= PREY_CREATION_PROBABILITY) {
                    int prey1 = rand.nextInt(2);
                    boolean preyGender = randGender.nextBoolean();
                    if(prey1 == 0){
                        Location location = new Location(row, col);
                        Prey prey = new Mouse(false, field, location, preyGender);
                        animals.add(prey);
                    } else{
                        Location location = new Location(row, col);
                        Prey prey = new Rabbit(false, field, location, preyGender);
                        animals.add(prey);
                    }
                }
                // else leave the location empty.
            }
        }
    }

    /**
     * Pause for a given time.
     * @param millisec  The time to pause for, in milliseconds
     */
    private void delay(int millisec)
    {
        try {
            Thread.sleep(millisec);
        }
        catch (InterruptedException ie) {
            // wake up
        }
    }
}
