package game.gui;
import javax.swing.*;

/**
 * This class composes all panels on the right
 * @author Idan Aharon, Itay Fridman
 * 			 305437774 305360653
 *
 */
public class RightPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private ArenaBuilderPanel top;
	private RacerBuilderPanel middle;
	private BottomPanel bottom;
	
	/**
	 * Class constructor sets all components
	 */
	public RightPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		top = new ArenaBuilderPanel();
		middle = new RacerBuilderPanel();
		bottom = new BottomPanel();
		
		
		this.add(top);
		this.add(new JSeparator(JSeparator.HORIZONTAL));
		this.add(middle);
		this.add(new JSeparator(JSeparator.HORIZONTAL));
		this.add(bottom);
	}	
}