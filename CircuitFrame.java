import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CircuitFrame extends JFrame{
	
	public CircuitFrame(boolean resistance) {
		super("Circuit Simulator");
		this.add(new CircuitPanel(resistance,this));
		this.setResizable(false);
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
	}
}
