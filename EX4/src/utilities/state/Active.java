package utilities.state;

import game.racers.Racer;

public class Active implements IState {

	@Override
	public void action(Racer racer) {
		if(!racer.getArena().getActiveRacers().contains((Racer)racer))
			racer.getArena().getActiveRacers().add(((Racer)racer));
		racer.getArena().getBrokenRacers().remove(((Racer)racer));
	}

}
