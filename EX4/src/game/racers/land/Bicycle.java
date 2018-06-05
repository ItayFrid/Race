package game.racers.land;

import game.racers.IWheeled;
import game.racers.Racer;
import game.racers.decorator.Wheeled;
import utilities.EnumContainer.Color;
import utilities.EnumContainer.BikeType;

/**
 * This is the Bicycle racer class
 * @author Idan Aharon, Itay Fridman
 * 			 305437774 305360653
 * @see Racer, LandRacer, Wheeled
 */
public class Bicycle extends Racer implements LandRacer, IWheeled {
	/**
	 * These are Bicycle class data members
	 */
	private Wheeled wheels;
	private BikeType type;
	
	/**
	 * These are class defaults
	 */
	private final static double DEFAULT_MAXSPEED = 270;
	private final static double DEFAULT_ACCLERATION = 10;
	private final static Color DEFAULT_COLOR = Color.GREEN;
	private final static int DEFAULT_NUMOFWHEELS = 2;
	private final static BikeType DEFAULT_BIKETYPE = BikeType.MOUNTAIN;
	
	/**
	 * This is the class parameter based constructor
	 * @param name
	 * @param maxSpeed
	 * @param acceleration
	 * @param color
	 * @param numOfWheels
	 * @param type
	 */
	public Bicycle(String name, double maxSpeed, double acceleration, Color color, int numOfWheels) {
		super(name, maxSpeed, acceleration, color);
		if(numOfWheels == 0)
			this.wheels = new Wheeled(DEFAULT_NUMOFWHEELS);
		else
			this.wheels = new Wheeled(numOfWheels);
		this.setType(DEFAULT_BIKETYPE);
		if(!(this.setMaxSpeed(maxSpeed)))
			this.setMaxSpeed(DEFAULT_MAXSPEED);
		if(!(this.setAcceleration(acceleration)))
			this.setAcceleration(DEFAULT_ACCLERATION);
		if(!(this.wheels.setNumOfWheels(numOfWheels)))
			this.wheels.setNumOfWheels(DEFAULT_NUMOFWHEELS);
	}

	/**
	 * This is the default constructor
	 */
	public Bicycle() {
		super(null, DEFAULT_MAXSPEED, DEFAULT_ACCLERATION, DEFAULT_COLOR);
		this.wheels = new Wheeled(DEFAULT_NUMOFWHEELS);
		this.setType(DEFAULT_BIKETYPE); //Personally made the choice to make this default
	}
	
	@Override
	public String describeSpecific() {
		return "Number of wheels: " + this.wheels.getNumOfWheels() + ", Type: " + this.type + "\n";
	}

	@Override
	public String className() {
		return "Bicycle";
	}

	/**
	 * The following are class getters & setters
	 */
	public Wheeled getWheels() {
		return wheels;
	}

	public boolean setWheels(Wheeled wheels) {
		this.wheels = wheels;
		return true;
	}

	public BikeType getType() {
		return type;
	}

	public boolean setType(BikeType type) {
		this.type = type;
		return true;
	}
}