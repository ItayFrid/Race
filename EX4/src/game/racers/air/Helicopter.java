package game.racers.air;
import game.racers.Racer;
import utilities.EnumContainer.Color;
/**
 * This is the Helicopter racer class
 * @author Idan Aharon, Itay Fridman
 * 			 305437774 305360653
 * @see AerialRacer, Racer
 */
public class Helicopter extends Racer implements AerialRacer {
	/**
	 * These are class defaults
	 */
	private final static double DEFAULT_MAXSPEED = 400;
	private final static double DEFAULT_ACCLERATION = 50;
	private final static Color DEFAULT_COLOR = Color.BLUE;
	
	
	/**
	 * The following are Airplane class constructors
	 * This is the default class constructor
	 */
	public Helicopter() {
		super(null, DEFAULT_MAXSPEED, DEFAULT_ACCLERATION, DEFAULT_COLOR);
	}
	
	/**
	 * This is the parameter based class constructor
	 * @param name
	 * @param maxSpeed
	 * @param acceleration
	 * @param color
	 */
	public Helicopter(String name, double maxSpeed, double acceleration, Color color) {
		super(name, maxSpeed, acceleration, color);
		if(!(this.setMaxSpeed(maxSpeed)))
			this.setMaxSpeed(DEFAULT_MAXSPEED);
		if(!(this.setAcceleration(acceleration)))
			this.setAcceleration(DEFAULT_ACCLERATION);
	}

	/**
	 * This function returns a string describing specific racer details
	 */
	@Override
	public String describeSpecific() {
		return "\n";		//Helicopter class does not have any special properties
	}

	/**
	 * This function returns a string with this class name
	 */
	@Override
	public String className() {
		return "Helicopter";
	}
}
