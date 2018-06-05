package game.racers;
import java.util.Hashtable;
import java.util.Observable;

import game.arenas.Arena;
import game.gui.GUI;
import utilities.EnumContainer.Color;
import utilities.EnumContainer.RacerEvent;
import utilities.Fate;
import utilities.Mishap;
import utilities.Point;

/**
 * This is the Racer class, an abstract class
 * @author Idan Aharon, Itay Fridman
 * 			 305437774 305360653
 */

public abstract class Racer extends Observable implements Runnable,IRacer {

	/**
	 * The following are the class data members
	 */
	private int serialNumber;
	private String name;
	private Point currentLocation;
	private Point finish;
	private Arena arena;
	private double maxSpeed;
	private double acceleration;
	private double currentSpeed;
	private double failureProbability;

	private Mishap malfunction;
	private Hashtable<String,Object> attributes;
	
	private static int numOfRacers = 0;		//Keeping track of serial numbers
	private static final double DEFAULT_PROBABILITY = 0.05;
	
	/**
	 * This is the Racer class constructor
	 * @param name
	 * @param maxSpeed
	 * @param acceleration
	 * @param color
	 */
	public Racer(String name, double maxSpeed, double acceleration){
		this.setSerialNumber();
		this.setName(name);
		this.setCurrentLocation(new Point(0, 0));
		this.setFinish(null);
		this.arena = null;
		this.setMaxSpeed(maxSpeed);
		this.setAcceleration(acceleration);
		this.setCurrentSpeed(0);
		this.setFailureProbability(DEFAULT_PROBABILITY);
		this.setMalfunction(null);
		this.attributes = new Hashtable<String,Object>();
	}
	
	/**
	 * The following are the class getters & setters
	 * @return
	 */
	public int getSerialNumber() {
		return serialNumber;
	}
	
	public boolean setSerialNumber() {
		Racer.numOfRacers++;
		this.serialNumber = Racer.numOfRacers;
		return true;
	}

	public String getName() {
		return name;
	}

	public boolean setName(String name) {
		if(name == null)
			name = this.getDefaultName();
		this.name = name;
		return true;
	}
	
	public Point getCurrentLocation() {
		return currentLocation;
	}

	public boolean setCurrentLocation(Point currentLocation) {
		this.currentLocation = currentLocation;
		return true;
	}

	public Point getFinish() {
		return finish;
	}

	public boolean setFinish(Point finish) {
		this.finish = finish;
		return true;
	}

	public Arena getArena() {
		return arena;
	}

	public boolean setArena(Arena arena) {
		this.arena = arena;
		this.addObserver(this.arena);
		return true;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public boolean setMaxSpeed(double maxSpeed) {
		if (maxSpeed <= 0)
			return false;
		this.maxSpeed = maxSpeed;
		return true;
	}

	public double getAcceleration() {
		return acceleration;
	}

	public boolean setAcceleration(double acceleration) {
		if(acceleration <= 0)
			return false;
		this.acceleration = acceleration;
		return true;
	}

	public double getCurrentSpeed() {
		return currentSpeed;
	}

	public boolean setCurrentSpeed(double currentSpeed) {
		if(currentSpeed < 0)
			return false;
		if(currentSpeed > this.maxSpeed)		//Making sure set dosen't exceed speed limit
			currentSpeed = this.maxSpeed;
		this.currentSpeed = currentSpeed;
		return true;
	}

	public double getFailureProbability() {
		return failureProbability;
	}

	public boolean setFailureProbability(double failureProbability) {
		if(failureProbability < 0)
			return false;
		this.failureProbability = failureProbability;
		return true;
	}

	public Mishap getMalfunction() {
		return malfunction;
	}

	public void setMalfunction(Mishap malfunction) {
		this.malfunction = malfunction;
	}

	/**
	 * The following are class methods
	 */
	/**
	 * This function initializes a race for a racer
	 * @param arena
	 * @param start
	 * @param finish
	 */
	public void initRace(Arena arena, Point start, Point finish) {
		this.setArena(arena);
		this.setCurrentLocation(start);
		this.setFinish(finish);
	}
	
	/**
	 * This method moves a racer forward and increasing it's speed
	 * @param friction
	 * @return Point of a new location after movement
	 */
	public Point move(double friction) {
		double addition = this.acceleration * friction;
		if(this.malfunction == null) {			//If there is no mishap
			if(Fate.breakDown(this.failureProbability) == true) {		//Rolling for a new mishap
				this.malfunction = Fate.generateMishap();	//Generating a new mishap
			}
		}
		if(this.malfunction != null) {		//If there's a mishap
			if(this.malfunction.isFixable() == true && this.malfunction.getTurnsToFix() == 0) {		//If mishap has ended
				this.malfunction = null;
				this.notifyObservers(RacerEvent.REPAIRED);
				this.arena.update(this, RacerEvent.REPAIRED);
			}
			else if(!(this.malfunction.isFixable())) {
				this.notifyObservers(RacerEvent.DISABLED);
				this.arena.update(this, RacerEvent.DISABLED);
			}
			else if(this.malfunction.isFixable() == true && this.malfunction.getTurnsToFix() > 0) {
				addition *= this.malfunction.getReductionFactor();
				this.malfunction.nextTurn();
			}
		}
		
		if(!(this.arena.getDisabledRacers().contains(this))) {		//Moving only if racer isn't disabled
			this.setCurrentSpeed(this.currentSpeed + (addition));	//Increasing speed
			if(this.currentLocation.getX() + this.currentSpeed>=this.arena.getLength()) {
				this.currentLocation.setX(this.arena.getLength());
				this.arena.update(this, RacerEvent.FINISHED);
			}
			else
				this.currentLocation.setX(this.currentLocation.getX() + this.currentSpeed);	//Moving forward
		}
		GUI.updateImageRacer(this);
		return this.currentLocation;
		
	}
	
	/**
	 * This method returns a string describing basic racer details
	 */
	public String describeBasics() {
		return "[" + this.className() + "] Name: " + this.name  + ", Serial Number: " + this.serialNumber + ", Max Speed: " + this.maxSpeed + ", Acceleration: "+ this.acceleration + ", Color: " + this.color +" ";
	}
	
	/**
	 * This method returns a string describing basic and specific racer details
	 */
	public String describeRacer() {
		return this.describeBasics() + this.describeSpecific();
	}
	
	/**
	 * This method prints out a racer's details
	 */
	public void introduce() {
		System.out.print(this.describeRacer());
	}
	
	/**
	 * This method generates a default racer name
	 * @return
	 */
	public String getDefaultName() {
		return this.className() + " #" + this.getSerialNumber();
	}
	
	/**
	 * The following are all abstract methods
	 */
	public abstract String describeSpecific();
	public abstract String className();
	
	
	public void run() {
		while(!(this.move(this.arena.getFRICTION())).hasCrossed(finish)) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
			if(this.arena.getDisabledRacers().contains(this)) {
				return;
			}
				
		}
		this.arena.update(this, RacerEvent.FINISHED);
		
	}
	public Hashtable<String,Object> getAttributes() {
		return this.attributes;
	}
	
	public void addAttribute(String key,Object value) {
		attributes.put(key, value);
	}
}