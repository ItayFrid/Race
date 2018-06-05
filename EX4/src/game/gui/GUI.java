package game.gui;
import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import factory.RaceBuilder;
import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import utilities.EnumContainer.Color;

/**
 * This class handles user's input and manages the race
* @author Idan Aharon, Itay Fridman
* 			305437774	305360653
 * @see Arena Class, Right & Left Panel classes
 */
public class GUI  {

	/*Frame panels*/
	private static RightPanel right;
	private static LeftPanel left;
	
	private static JFrame mainFrame;
	public final static String ICON_PATH = "src/game/gui/icons/";
	private static ArrayList<JLabel> imageRacers = null;
	
	private static Arena activeArena;		//Reference to current active arena
	private static boolean activeRace;		//Indicates ongoing race
	private static RaceBuilder builder = RaceBuilder.getInstance();
	
	
	
	private static final int MIN_LENGTH = 100;
	private static final int MAX_LENGTH = 3000;
	private static final int MIN_RACERS = 1;
	private static final int MAX_RACERS = 20;
	
	public GUI() {
		mainFrame = new JFrame("Race");
		activeArena = null;
		activeRace = false;
		mainFrame.setLayout(new BorderLayout());
		left = new LeftPanel();
		right = new RightPanel();
		
		JPanel dummyRight = new JPanel(new BorderLayout());
		dummyRight.add(right,BorderLayout.CENTER);
		dummyRight.add(new JSeparator(JSeparator.VERTICAL),BorderLayout.WEST);
		
	
		mainFrame.add(left, BorderLayout.CENTER);
		mainFrame.add(dummyRight, BorderLayout.EAST);

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(1200, 700);
		mainFrame.setVisible(true);
		
	}
	
	/**
	 * 
	 * @param color
	 * @return
	 */
	private static String getColor(Color color) {
		switch (color) {
		case BLACK:
			return "Black";
		case BLUE:
			return "Blue";
		case GREEN:
			return "Green";
		case RED:
			return "Red";
		case YELLOW:
			return "Yellow";
		}
		return null;
	}
	
	public static Arena getActiveArena() {
		return activeArena;
	}
	
	public static void updateImageRacer(Racer racer) {
		left.updateImageRacer(racer);
	}
	
	/**
	 * This method sets a new arena given user's input
	 * @param name - Arena type
	 * @param length - Arena's length
	 * @param racers - Arena's max racers limit
	 */
	public static void setArena(String name, double length, int racers)  {
		if(activeRace == false || activeArena.raceFinished()) {		//Creating new arena only if there isn't an ongoing race
			if(length >= MIN_LENGTH && length <= MAX_LENGTH && racers >= MIN_RACERS && racers <= MAX_RACERS) {	//Checking input length & racers
			try {
				if(activeArena != null)
					left.clean();
				activeArena = builder.buildArena(name, length, racers);
				if(activeRace==true)
					activeRace=false;
				JOptionPane.showMessageDialog(null, "New arena: " + activeArena.className());
				left.setBG(activeArena.className(), racers, (int) length);
				imageRacers = activeArena.getImageRacers();
				LeftPanel.setImageRacer(imageRacers);
				if (racers > 14)
					mainFrame.setSize((int) length + 200, racers * 50);
				else
					mainFrame.setSize((int) length + 200, 700);
				mainFrame.validate();
				mainFrame.repaint();
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Unable to create arena");
			}
					
			}
			else
				JOptionPane.showMessageDialog(null, "Invalid input values! Please try again.");
		
		
		}
		else
			JOptionPane.showMessageDialog(null, "Cannot create arena during active race");
	}

	/**
	 * This method creates a new racer and adds the new racer to arena's active racers
	 * @param type - Racer's full path name
	 * @param name - Racer's name
	 * @param speed - Racer's max speed limit
	 * @param accel - Racer's acceleration
	 * @param color - Racer's color
	 * @param isWheeled - Determines if a racer is a wheeled racer or not
	 */
	public static void setRacer(String type, String name, double speed, double accel, Color color, boolean isWheeled) {
		if (activeArena == null) // If there isn't any built arena
			JOptionPane.showMessageDialog(null, "Please build arena first and add racers!");
		else if (activeRace == true) // If there's an ongoing race
			JOptionPane.showMessageDialog(null, "Cannot add racer during active race");
		else if(name.isEmpty() || speed <= 0 || accel <= 0)
			JOptionPane.showMessageDialog(null, "Invalid input values! Please try again.");
		else { // Adding new racer
			Racer newRacer = null;
			try {
				if (isWheeled == true) // Building wheeled racer
					newRacer = builder.buildWheeledRacer(type, name, speed, accel, 0);
				else // Building non-wheeled racer
					newRacer = builder.buildRacer(type, name, speed, accel);
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {

				JOptionPane.showMessageDialog(null, "Unable to create racer");
			}

			try {
				activeArena.addRacer(newRacer); // Trying to add racer to arena
				JOptionPane.showMessageDialog(null, "Racer added succesfully"); // Ignore

				JLabel imageRacer;
				try {
					Image racerImage = (new ImageIcon(
							ImageIO.read(new File(ICON_PATH + newRacer.className() + getColor(color) + ".png"))))
									.getImage();
					racerImage = racerImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
					imageRacer = new JLabel(new ImageIcon(racerImage));
					imageRacers.add(imageRacer);
					left.setImageRacer(newRacer);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (RacerTypeException e) {
				JOptionPane.showMessageDialog(null, "Error! Wrong racer type for " + activeArena.className());

			} catch (RacerLimitException e) {
				JOptionPane.showMessageDialog(null, "Error! Amount of racers limit has been reached");

			}

		}
	}	
	/**
	 * This method handles actions done with pressing "Start race" button
	 */
	public static void startRace() {
		if(activeArena == null)		//If there is no built arena
			JOptionPane.showMessageDialog(null, "Please build arena first and add racers!");
		else if(activeRace == true)		//If there is an ongoing race
			JOptionPane.showMessageDialog(null, "There is already an ongoing race");
		else if(activeArena.getActiveRacers().size() < 1)	//If there isn't any racers in arena
			JOptionPane.showMessageDialog(null, "There are no racers! Please add racers before starting a race");
		else {		//Starting a new race
			activeRace = true;
			JOptionPane.showMessageDialog(null, "Race started!");	//Ignore
			
			activeArena.initRace();
			activeArena.startRace();
		}
		
		
	}

	/**
	 * This method handles actions done with pressing "Show info" button
	 */
	public static void showInfo() {
		if(activeArena == null || activeArena.getAllRacers().size()==0)
			JOptionPane.showMessageDialog(null, "No racers to show");
		else {
			@SuppressWarnings("unused")
			InfoTable table = new InfoTable(activeArena);
		}
	}
	
	public static boolean getActiveRace() {
		return activeRace;
	}
	}