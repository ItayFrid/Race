package game.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * This class handles the interface of the bottom right panel
 * @author Idan Aharon, Itay Fridman
 * 			305437774	305360653
 * @see GUI Class, RightPanel Class
 */
public class BottomPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	/*Buttons*/
	private JButton start;
	private JButton info;
	
	private GridBagConstraints arrange;
	
	/**
	 * Class constructor sets components
	 */
	public BottomPanel(){
		super(new GridBagLayout());
		
		start = new JButton("Start race");
		info = new JButton("Show info");
		
		arrange = new GridBagConstraints();
		arrange.insets = new Insets(5, 5, 5, 5);
		arrange.gridx = 0;
		
		arrange.gridy = 0;
		this.start.addActionListener(new RaceHandler());
		this.add(start, arrange);
		
		arrange.gridy = 1;
		this.info.addActionListener(new RaceHandler());
		this.add(info, arrange);
	}
	
	/**
	 * This class handles action made by pressing "Start race" or "Show info" buttons
	 */
	private class RaceHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getActionCommand() == "Start race") {
				//TODO:Add to Idan
				if(GUI.getActiveArena()==null)
					JOptionPane.showMessageDialog(null, "Build arena to start a race.");
				else if(GUI.getActiveArena().getAllRacers().isEmpty())
					JOptionPane.showMessageDialog(null, "Add racers to start a race.");
				else if(GUI.getActiveRace())
					JOptionPane.showMessageDialog(null, "race is active.");
				else
					GUI.startRace();
			}
			else
				GUI.showInfo();
		}
	}
}