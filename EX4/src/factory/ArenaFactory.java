package factory;

import game.arenas.Arena;
import game.arenas.air.AerialArena;
import game.arenas.land.LandArena;
import game.arenas.naval.NavalArena;

public class ArenaFactory {

	public Arena getArena(String arenaType) {
		Arena newArena = null;
		switch(arenaType) {
		case "Land": newArena = new LandArena();
		break;
		case "Navy": newArena = new NavalArena();
		break;
		case "Air": newArena = new AerialArena();
		break;
		}
		return newArena;
	}
}