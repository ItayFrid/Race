package game.racers.land;
import game.racers.Racer;
import utilities.EnumContainer.Breed;
import utilities.EnumContainer.Color;

/**
 * This is the Horse racer class
 * @author Idan Aharon, Itay Fridman
 * 			 305437774 305360653
 * @see Racer, LandRacer
 */
public class Horse extends Racer implements LandRacer {
	/**
	 * This is the class data members
	 */
	private Breed breed;
	
	/**
	 * These are class defaults
	 */
	private final static double DEFAULT_MAXSPEED = 50;
	private final static double DEFAULT_ACCLERATION = 3;
	private final static Color DEFAULT_COLOR = Color.BLACK;
	private final static Breed DEFAULT_BREED = Breed.THOROUGHBRED;
	
	/**
	 * This is the parameter base constructor
	 * @param name
	 * @param maxSpeed
	 * @param acceleration
	 * @param color
	 * @param breed
	 */
	public Horse(String name, double maxSpeed, double acceleration, Color color) {
		super(name, maxSpeed, acceleration, color);
		this.setBreed(DEFAULT_BREED);
		if(!(this.setMaxSpeed(maxSpeed)))
			this.setMaxSpeed(DEFAULT_MAXSPEED);
		if(!(this.setAcceleration(acceleration)))
			this.setAcceleration(DEFAULT_ACCLERATION);
	}
	
	/**
	 * This is the default constructor
	 */
	public Horse() {
		super(null, DEFAULT_MAXSPEED, DEFAULT_ACCLERATION, DEFAULT_COLOR);
		this.setBreed(DEFAULT_BREED);
	}
	
	@Override
	public String describeSpecific() {
		return ", Breed: " + this.breed + "\n";
	}

	@Override
	public String className() {
		return "Horse";
	}

	/**
	 * These are the class getters & setters
	 * @return
	 */
	public Breed getBreed() {
		return breed;
	}

	public void setBreed(Breed breed) {
		this.breed = breed;
	}
}