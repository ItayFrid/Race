package game.racers.land;

import game.racers.IWheeled;
import game.racers.Racer;
import game.racers.Wheeled;
import utilities.EnumContainer.Color;
import utilities.EnumContainer.Engine;

/**
 * This is the Car racer class
 * @author Idan Aharon, Itay Fridman
 * 			 305437774 305360653
 * @see Racer, LandRacer, Wheeled
 */
public class Car extends Racer implements LandRacer, IWheeled {
	/**
	 * These are class data members
	 */
	private Wheeled wheels;
	private Engine engine;
	
	/**
	 * These are class defaults
	 */
	private final static double DEFAULT_MAXSPEED = 400;
	private final static double DEFAULT_ACCLERATION = 20;
	private final static Color DEFAULT_COLOR = Color.RED;
	private final static int DEFAULT_NUMOFWHEELS = 4;
	private final static Engine DEFAULT_ENGINE = Engine.FOURSTROKE;
	
	/**
	 * This is the default class constructor
	 */
	public Car() {
		super(null, DEFAULT_MAXSPEED, DEFAULT_ACCLERATION, DEFAULT_COLOR);
		this.wheels = new Wheeled(DEFAULT_NUMOFWHEELS);
		this.setEngine(DEFAULT_ENGINE); //Personally made the choice to make this default
	}
	
	/**
	 * This is the parameter based class constructor
	 * @param name
	 * @param maxSpeed
	 * @param acceleration
	 * @param color
	 * @param numOfWheels
	 * @param engine
	 */
	public Car(String name, double maxSpeed, double acceleration, Color color, int numOfWheels) {
		super(name, maxSpeed, acceleration, color);
		if(numOfWheels == 0)
			this.wheels = new Wheeled(DEFAULT_NUMOFWHEELS);
		else
			this.wheels = new Wheeled(numOfWheels);
		this.setEngine(DEFAULT_ENGINE);
		if(!(this.setMaxSpeed(maxSpeed)))
			this.setMaxSpeed(DEFAULT_MAXSPEED);
		if(!(this.setAcceleration(acceleration)))
			this.setAcceleration(DEFAULT_ACCLERATION);
		if(!(this.wheels.setNumOfWheels(numOfWheels)))
			this.wheels.setNumOfWheels(DEFAULT_NUMOFWHEELS);
	}

	@Override
	public String describeSpecific() {
		return "Number of wheels: " + this.wheels.getNumOfWheels() + ", Engine: " + this.engine + "\n";
	}

	@Override
	public String className() {
		return "Car";
	}

	/**
	 * These are Car class getters & setters
	 */
	public Wheeled getWheels() {
		return wheels;
	}
	
	public boolean setWheels(Wheeled wheels) {
		this.wheels = wheels;
		return true;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}
}