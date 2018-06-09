package utilities.state;

import game.racers.Racer;

public class Broken implements IState {

	@Override
	public void action(Racer racer) {
		if(!racer.getArena().getBrokenRacers().contains((Racer)racer))
			racer.getArena().getBrokenRacers().add(((Racer)racer));
		racer.getArena().getActiveRacers().remove(((Racer)racer));
	}

}
