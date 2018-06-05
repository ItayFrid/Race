package game.racers.naval;

import game.racers.Racer;
import utilities.EnumContainer.RowType;
import utilities.EnumContainer.Team;

/**
 * This is the RowBoat racer class
 * @author Idan Aharon, Itay Fridman
 * 			 305437774 305360653
 * @see Racer, NavalRacer
 */
public class RowBoat extends Racer implements NavalRacer {
	
	/**
	 * These are class defaults
	 */
	private final static double DEFAULT_MAXSPEED = 75;
	private final static double DEFAULT_ACCLERATION = 10;

	private final static RowType DEFAULT_ROWTYPE = RowType.SKULLING;
	private final static Team DEFAULT_TEAM = Team.DOUBLE;
	
	
	/**
	 * This is the parameter based constructor
	 * @param name
	 * @param maxSpeed
	 * @param acceleration
	 */
	public RowBoat(String name, double maxSpeed, double acceleration) {
		super(name, maxSpeed, acceleration);
		this.addAttribute("type", DEFAULT_ROWTYPE);
		this.addAttribute("team", DEFAULT_TEAM);
		if(!(this.setMaxSpeed(maxSpeed)))
			this.setMaxSpeed(DEFAULT_MAXSPEED);
		if(!(this.setAcceleration(acceleration)))
			this.setAcceleration(DEFAULT_ACCLERATION);
	}

	/**
	 * This is the default constructor
	 */
	public RowBoat() {
		super(null, DEFAULT_MAXSPEED, DEFAULT_ACCLERATION);
		this.addAttribute("type", DEFAULT_ROWTYPE);
		this.addAttribute("team", DEFAULT_TEAM);
	}
	
	@Override
	public String describeSpecific() {
		//TODO: Change describeSpecific()
		return "Boat Type: "/* + this.type + ", Team: " + this.team + "\n"*/;
	}

	@Override
	public String className() {
		return "RowBoat";
	}
}