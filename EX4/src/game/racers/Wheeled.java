package game.racers;
/**
 * This is the Wheeled racer class
 * @author Idan Aharon, Itay Fridman
 * 			 305437774 305360653
 * @see Racer, LandRacer
 */
public class Wheeled {
	/**
	 * This is the class data member
	 */
	private int numOfWheels;
	
	/**
	 * This is the class constructor
	 * @param numOfWheels
	 */
	public Wheeled(int numOfWheels) {
		this.setNumOfWheels(numOfWheels);
	}
	
	
	/**
	 * These are this class get & set methods
	 */
	public int getNumOfWheels() {
		return numOfWheels;
	}

	public boolean setNumOfWheels(int numOfWheels) {
		if(numOfWheels <= 0)
			return false;
		this.numOfWheels = numOfWheels;
		return true;
	}
}