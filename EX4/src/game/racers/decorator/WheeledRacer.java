package game.racers.decorator;

import game.racers.IRacer;

/**
 * This is the WheeledRacer class
 * @author Idan Aharon, Itay Fridman
 * 			 305437774 305360653
 * @see Racer, IRacer, RacerDecorator
 */
public class WheeledRacer extends RacerDecorator {
	/**
	 * This is the class data member
	 */
	private final String ATTRIBUTENAME = "numOfWheels";
	
	/**
	 * This is the class constructor
	 * @param racer
	 * @param numOfWheels
	 */
	public WheeledRacer(IRacer racer, int numOfWheels) {
		super(racer);
		this.decoratedRacer.addAttribute(ATTRIBUTENAME, numOfWheels);
	}
	
}