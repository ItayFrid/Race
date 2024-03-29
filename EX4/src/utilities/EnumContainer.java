package utilities;

/**
 * Contains all Enums for the game.
 * @author Idan Aharon, Itay Fridman
 */
public class EnumContainer {

	public static enum Vision {
		CLOUDS, SUNNY, FOG
	}
	public static enum Weather {
		DRY, RAIN, SNOW
	}
	public static enum Height {
		LOW, MEDIUM, HIGH
	}
	public static enum Wind {
		LOW, MEDIUM, HIGH
	}
	
	public static enum Water{
		SALTED, SWEET
	}
	
	public static enum WaterSurface{
		FLAT, WAVY
	}
	
	public static enum Body{
		SEA, LAKE, RIVER, OCEAN
	}
	
	public static enum Coverage {
		SAND, GRASS, MUD
	}
	
	public static enum LandSurface {
		FLAT, MOUNTAIN
	}
	
	public static enum Color {
		RED, GREEN, BLUE, BLACK, YELLOW
	}
	
	public static enum Engine {
		FOURSTROKE, VTYPE, STRAIGHT, BOXER, ROTARY
	}
	
	public static enum BikeType	{
		MOUNTAIN, HYBRID, CRUISER, ROAD
	}
	
	public static enum Breed	{
		THOROUGHBRED, STANDARDBRED, MORGAN, FRIESIAN
	}
	
	public static enum RowType	{
		SKULLING, SWEEP
	}
	
	public static enum Team	{
		SINGLE, DOUBLE, QUAD, EIGHT
	}
	
	public static enum RacerEvent{
		FINISHED, DISABLED, BROKENDOWN, REPAIRED
	}
}
