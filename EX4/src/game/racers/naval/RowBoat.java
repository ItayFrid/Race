package game.racers.naval;

import game.racers.Racer;
import utilities.EnumContainer.RowType;
import utilities.EnumContainer.Color;
import utilities.EnumContainer.Team;

/**
 * This is the RowBoat racer class
 * @author Idan Aharon, Itay Fridman
 * 			 305437774 305360653
 * @see Racer, NavalRacer
 */
public class RowBoat extends Racer implements NavalRacer {

	/**
	 * These are RowBoat class data members
	 */
	private RowType type;
	private Team team;
	
	/**
	 * These are class defaults
	 */
	private final static double DEFAULT_MAXSPEED = 75;
	private final static double DEFAULT_ACCLERATION = 10;
	private final static Color DEFAULT_COLOR = Color.RED;
	private final static RowType DEFAULT_ROWTYPE = RowType.SKULLING;
	private final static Team DEFAULT_TEAM = Team.DOUBLE;
	
	
	/**
	 * This is the parameter based constructor
	 * @param name
	 * @param maxSpeed
	 * @param acceleration
	 * @param color
	 * @param type
	 * @param team
	 */
	public RowBoat(String name, double maxSpeed, double acceleration, Color color) {
		super(name, maxSpeed, acceleration, color);
		this.setType(DEFAULT_ROWTYPE);
		this.setTeam(DEFAULT_TEAM);
		if(!(this.setMaxSpeed(maxSpeed)))
			this.setMaxSpeed(DEFAULT_MAXSPEED);
		if(!(this.setAcceleration(acceleration)))
			this.setAcceleration(DEFAULT_ACCLERATION);

	}

	/**
	 * This is the default constructor
	 */
	public RowBoat() {
		super(null, DEFAULT_MAXSPEED, DEFAULT_ACCLERATION, DEFAULT_COLOR);
		this.setType(DEFAULT_ROWTYPE);
		this.setTeam(DEFAULT_TEAM);
	}
	
	@Override
	public String describeSpecific() {
		return "Boat Type: " + this.type + ", Team: " + this.team + "\n";
	}

	@Override
	public String className() {
		return "RowBoat";
	}

	public RowType getType() {
		return type;
	}

	public boolean setType(RowType type) {
		this.type = type;
		return true;
	}

	public Team getTeam() {
		return team;
	}

	public boolean setTeam(Team team) {
		this.team = team;
		return true;
	}
}