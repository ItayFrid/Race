package game.gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import game.racers.Racer;

/**
 * This class handles the interface of the left panel
 * 
 * @author Idan Aharon, Itay Fridman 305437774 305360653
 * @see GUI Class
 */
public class LeftPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private Image background;
	private static ArrayList<JLabel> imageRacers = null;

	public LeftPanel() {
		this.setLayout(null);
		this.setSize(1000, 700);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
	}

	public void setBG(String name, int maxRacers, int length) {
		try {
			background = ImageIO.read(new File("src/game/gui/icons/" + name + ".jpg"));
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Unable to load Image");
		}
		if (maxRacers > 14)
			setSize(length, maxRacers * 50);
		else
			setSize(length, 700);
		validate();
		repaint();
	}

	public void setImageRacer(Racer racer) {
		JLabel imageRacer = imageRacers.get(GUI.getActiveArena().getActiveRacers().lastIndexOf(racer));
		add(imageRacer, BorderLayout.CENTER);

		imageRacer.setBounds((int) racer.getCurrentLocation().getX(), (int) racer.getCurrentLocation().getY() * 5, 50,
				50);

		revalidate();
		repaint();
	}

	/**
	 * @param imageRacer
	 *            the imageRacer to set
	 */

	public static void setImageRacer(ArrayList<JLabel> imageRacers) {
		LeftPanel.imageRacers = imageRacers;
	}

	public void clean() {
		for (JLabel image : imageRacers) {
			remove(image);
		}
		revalidate();
		repaint();
	}
	
	public void updateImageRacer(Racer racer) {
		JLabel label= imageRacers.get(GUI.getActiveArena().getAllRacers().indexOf(racer));
			label.setBounds((int) racer.getCurrentLocation().getX(), (int) racer.getCurrentLocation().getY()*5, 50,
				50);
		revalidate();
		repaint();
	}
}