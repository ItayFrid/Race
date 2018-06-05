package game.racers.air;
import game.racers.IWheeled;
import game.racers.Racer;
import game.racers.decorator.Wheeled;
import utilities.EnumContainer.Color;
/**
 * This is the Airplane racer class
 * @author Idan Aharon, Itay Fridman
 * 			 305437774 305360653
 * @see AerialRacer, Racer, Wheeled
 */
public class Airplane extends Racer implements AerialRacer, IWheeled { 
	/**
	 * This is the class data members
	 */
	private Wheeled wheels;
	
	/**
	 * These are class defaults
	 */
	private final static double DEFAULT_MAXSPEED = 885;
	private final static double DEFAULT_ACCLERATION = 100;
	private final static Color DEFAULT_COLOR = Color.BLACK;
	private final static int DEFAULT_NUMOFWHEELS = 3;

	
	
	/**
	 * The following are Airplane class constructors
	 * This is the default class constructor
	 */
	public Airplane() {
		super(null, DEFAULT_MAXSPEED, DEFAULT_ACCLERATION, DEFAULT_COLOR);
		this.wheels = new Wheeled(DEFAULT_NUMOFWHEELS);
	}
	
	/**
	 * This is the parameter based class constructor
	 * @param name
	 * @param maxSpeed
	 * @param acceleration
	 * @param color
	 * @param numOfWheels
	 */
	public Airplane(String name, double maxSpeed, double acceleration, Color color, int numOfWheels) {
		super(name, maxSpeed, acceleration, color);
		if(numOfWheels == 0)
			this.wheels = new Wheeled(DEFAULT_NUMOFWHEELS);
		else
			this.wheels = new Wheeled(numOfWheels);
		if(!(this.setMaxSpeed(maxSpeed)))
			this.setMaxSpeed(DEFAULT_MAXSPEED);
		if(!(this.setAcceleration(acceleration)))
			this.setAcceleration(DEFAULT_ACCLERATION);
		if(!(this.wheels.setNumOfWheels(numOfWheels)))
			this.wheels.setNumOfWheels(DEFAULT_NUMOFWHEELS);
	}

	/**
	 * This function returns a string describing specific racer details
	 */
	@Override
	public String describeSpecific() {
		return "Number of wheels: " + this.wheels.getNumOfWheels() + "\n";
	}
 
	/**
	 * This function returns a string with this class name
	 */
	@Override
	public String className() {
		return "Airplane";
	}

	/**
	 * These are class getters & setters
	 * @return
	 */
	public Wheeled getWheels() {
		return wheels;
	}

	public boolean setWheels(Wheeled wheels) {
		this.wheels = wheels;
		return true;
	}

}
