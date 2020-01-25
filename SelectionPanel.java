import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelectionPanel extends JPanel{

	public SelectionPanel(JFrame frame) {
		super();
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,0,0,0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth=2;
		this.setPreferredSize(new Dimension(300,300));
		
		JLabel title = new JLabel("Select the type of circuit");
		title.setHorizontalAlignment(JLabel.CENTER);
		
		this.add(title, c);
		
		c.gridwidth=1;
		c.insets = new Insets(50,0,0,0);
		c.gridy=1;
		
		JButton btResistance = new JButton("Resistances");
		btResistance.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.dispose();
				CircuitFrame cf = new CircuitFrame(true);
			}
			
		});
		
		this.add(btResistance,c);
		
		JButton btCapacitors = new JButton("Capacitors");
		btCapacitors.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.dispose();
				CircuitFrame cf = new CircuitFrame(false);
			}
			
		});
		
		this.add(btCapacitors,c);
	}
}
