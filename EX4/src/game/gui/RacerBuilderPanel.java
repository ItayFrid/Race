package game.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import factory.RacingClassesFinder;
import utilities.EnumContainer.Color;

/**
 * This class handles the interface of the middle right panel
 * 
 * @author Idan Aharon, Itay Fridman
 * 			 305437774 305360653
 * @see GUI Class, RightPanel Class
 */
public class RacerBuilderPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/* Combo Boxes */
	private JComboBox<String> racerType;
	private JComboBox<String> racerColor;

	/* Text Fields */
	private JTextField racerName;
	private JTextField maxSpeed;
	private JTextField acceleration;

	/* Buttons */
	private JButton addRacer;

	/* Labels */
	private JLabel typeChoice;
	private JLabel colorChoice;
	private JLabel nameChoice;
	private JLabel speedChoice;
	private JLabel accelerationChoice;

	private GridBagConstraints arrange;

	/* Color names for combo box */
	private static String[] colors = { "Black", "Blue", "Green", "Red", "Yellow" };

	/**
	 * Class constructor sets all components
	 */
	public RacerBuilderPanel() {
		super(new GridBagLayout());

		racerType = new JComboBox<>();

		for (String string : RacingClassesFinder.getInstance().getRacersNamesList())
			racerType.addItem(string);

		racerColor = new JComboBox<String>(colors);

		racerName = new JTextField(10);
		maxSpeed = new JTextField(10);
		acceleration = new JTextField(10);

		addRacer = new JButton("Add racer");

		typeChoice = new JLabel("Choose racer:");
		colorChoice = new JLabel("Choose color:");
		nameChoice = new JLabel("Racer name:");
		speedChoice = new JLabel("Max speed:");
		accelerationChoice = new JLabel("Acceleration:");

		arrange = new GridBagConstraints();
		arrange.insets = new Insets(0, 5, 5, 5);

		arrange.gridx = 0;

		arrange.gridy = 0;
		this.add(typeChoice, arrange);
		arrange.gridy = 1;
		this.add(racerType, arrange);

		arrange.gridy = 2;
		this.add(colorChoice, arrange);
		arrange.gridy = 3;
		this.add(racerColor, arrange);

		arrange.gridy = 4;
		this.add(nameChoice, arrange);
		arrange.gridy = 6;
		this.add(racerName, arrange);

		arrange.gridy = 7;
		this.add(speedChoice, arrange);
		arrange.gridy = 8;
		this.add(maxSpeed, arrange);

		arrange.gridy = 9;
		this.add(accelerationChoice, arrange);
		arrange.gridy = 10;
		this.add(acceleration, arrange);

		arrange.gridy = 11;
		this.add(addRacer, arrange);
		this.addRacer.addActionListener(new RacerHandler());
	}

	/**
	 * 
	 * This class handles actions done with pressing the "Add racer" button
	 */
	private class RacerHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			/* Determining weather selected type is wheeled racer or not */
			boolean isWheeled = false;
			String type = (String) racerType.getSelectedItem();
			if (type.equals("Airplane") || type.equals("Car") || type.equals("Bicycle"))
				isWheeled = true;

			/* Getting text fields and combo box input and converting */
			type = this.getPath(type);
			String name = (String) racerName.getText();

			Color color = this.getColor((String) racerColor.getSelectedItem());

			double speed = Double.parseDouble(maxSpeed.getText());
			double accel = Double.parseDouble(acceleration.getText());

			/* Moving input to GUI class */
			GUI.setRacer(type, name, speed, accel, color, isWheeled);

		}

		/**
		 * This method returns a string containing a racer full path name
		 * @param name - given arena name
		 * @return - given arena path
		 */
		private String getPath(String name) {
			int index = RacingClassesFinder.getInstance().getRacersNamesList().indexOf(name);
			return RacingClassesFinder.getInstance().getRacersList().get(index);
		}
		
		/**
		 * This method "Converts" a string to Color object
		 * @param color
		 * @return
		 */
		private Color getColor(String color) {
			switch (color) {
			case "Black":
				return Color.BLACK;
			case "Blue":
				return Color.BLUE;
			case "Green":
				return Color.GREEN;
			case "Red":
				return Color.RED;
			case "Yellow":
				return Color.YELLOW;
			}
			return null;
		}
	}
}