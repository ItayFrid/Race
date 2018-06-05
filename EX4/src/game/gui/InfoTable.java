package game.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import game.arenas.Arena;
/**
 * This class represents the info table shown by clicking the "Show info" button
 * @author Idan Aharon, Itay Fridman
 * 			305437774	305360653
 * @see GUI Class
 */
public class InfoTable extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTable table;
	
	/**
	 * Class constructor
	 * @param activeArena - The arena from which information is gathered
	 */
	public InfoTable(Arena activeArena) {
		super("Racers information");
		setLayout(new FlowLayout());
		
		/*Column names*/
		String[] columns = {"Racer name", "Current speed", "Max speed", "Current X location", "Finished" };
		
		
		Object[][] data = new Object[activeArena.getAllRacers().size()][5];
		
		/*Gathering information from active arena*/
		for(int i = 0; i < activeArena.getAllRacers().size(); i++) {
			data[i][0] = activeArena.getAllRacers().get(i).getName();
			data[i][1] = activeArena.getAllRacers().get(i).getCurrentSpeed();
			data[i][2] = activeArena.getAllRacers().get(i).getMaxSpeed();
			data[i][3] = activeArena.getAllRacers().get(i).getCurrentLocation().getX();
			
			if(activeArena.getCompletedRacers().contains(activeArena.getAllRacers().get(i)) || 
					activeArena.getBrokenRacers().contains(activeArena.getAllRacers().get(i)))
				data[i][4] = "Yes";
			else
				data[i][4] = "No";
		}
		/*Creating and setting the info table*/
		table = new JTable(data, columns);
		table.setPreferredScrollableViewportSize(new Dimension(500,13+13*activeArena.getAllRacers().size()));
		table.setFillsViewportHeight(true);
		JScrollPane scroller = new JScrollPane(table);
		this.add(scroller);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
}