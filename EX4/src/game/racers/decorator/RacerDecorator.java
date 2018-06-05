package game.racers.decorator;

import game.racers.IRacer;

public abstract class RacerDecorator  {

	protected IRacer decoratedRacer;
	/**/
	public RacerDecorator(IRacer racer) {
		this.decoratedRacer = racer;
	}
}
