package utilities;
import factory.ArenaFactory;
import factory.CarRaceBuilder;
import game.arenas.Arena;
import game.arenas.land.LandArena;
import game.gui.GUI;
/**
 * 
 * @author Idan Aharon, Itay Fridman
 * 			 305437774 305360653
 *
 */
public class Program {

	public static void main(String [] args) {
	//	new GUI();
		
		CarRaceBuilder raceBuild = new CarRaceBuilder(5);
		
		LandArena race = raceBuild.getRace();
		
		
		for(int i = 0; i<race.getAllRacers().size(); i++) {
			System.out.println(race.getAllRacers().get(i).describeRacer());
		}
	}
}
