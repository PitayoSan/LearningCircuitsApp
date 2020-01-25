import javax.swing.JFrame;

public class SelectionFrame extends JFrame{
	
	public SelectionFrame() {
		super("Circuit Simulator");
		this.add(new SelectionPanel(this));
		this.setResizable(false);
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		SelectionFrame sf = new SelectionFrame();
	}
}

