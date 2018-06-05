package game.gui;
import javax.swing.*;
import factory.RacingClassesFinder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * This class handles the interface of the upper right panel
 * @author Idan Aharon, Itay Fridman
 * 			305437774	305360653
 * @see GUI Class, RightPanel Class
 */
public class ArenaBuilderPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	/*Combo Boxes*/
	private JComboBox<String> arenaType;
	
	/*Text Fields*/
	private JTextField arenaLength;
	private JTextField maxRacers;
	
	/*Buttons*/
	private JButton buildArena;
	 
	/*Labels*/
	private JLabel arenaChoice;
	private JLabel lengthChoice;
	private JLabel racersChoice;
		
	private GridBagConstraints arrange;
	
	
		/**
		 * Class constructor sets all components
		 */
	public ArenaBuilderPanel() {
		super(new GridBagLayout());
		arenaType = new JComboBox<>();
		for (String string : RacingClassesFinder.getInstance().getArenasNamesList())
		    arenaType.addItem(string);
		
		arenaLength = new JTextField("1000", 10);
		maxRacers = new JTextField("8", 10);
		buildArena = new JButton("Build Arena");
		
		arenaChoice = new JLabel("Choose arena:");
		lengthChoice = new JLabel("Arena length:");
		racersChoice = new JLabel("Max racers number:");
				
		arrange = new GridBagConstraints();
		arrange.insets = new Insets(0, 5, 5, 5);
		arrange.gridx = 0;
		
		arrange.gridy = 0;
		this.add(arenaChoice, arrange);
		arrange.gridy = 1;
		this.add(arenaType, arrange);
		
		arrange.gridy = 2;
		this.add(lengthChoice, arrange);
		arrange.gridy = 3;
		this.add(arenaLength, arrange);
		
		arrange.gridy = 4;
		this.add(racersChoice, arrange);
		arrange.gridy = 6;
		this.add(maxRacers, arrange);
		
		arrange.gridy = 7;
		buildArena.addActionListener(new ArenaHandler());
		this.add(buildArena, arrange);
		
	}
	
	/**
	 * This class handles action done with Arena Builder input
	 * @author Idan Aharon
	 */
	private class ArenaHandler implements ActionListener{
		
		/**
		 * This method handles the action made with pressing the "Build Arena" button
		 */
		@Override
		public void actionPerformed(ActionEvent event) {
						
			/*Getting text fields and combo box input and converting*/
			String name = this.getPath((String) arenaType.getSelectedItem());
			double length = Double.parseDouble(arenaLength.getText());
			int racers = Integer.parseInt(maxRacers.getText());
			
			/*Moving input to GUI class*/
			GUI.setArena(name, length, racers);
		}
		
		/**
		 * This method returns a string containing an arena full path name
		 * @param name - given arena name
		 * @return - given arena path
		 */
		private String getPath(String name) {
			int index = RacingClassesFinder.getInstance().getArenasNamesList().indexOf(name);
			return RacingClassesFinder.getInstance().getArenasList().get(index);
		}
	}
}