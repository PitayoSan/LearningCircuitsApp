import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class CircuitButtonPanel extends JPanel implements ActionListener{
	
	private CircuitGraphicPanel cp;
	private JFrame frame;
	private boolean resistance;
	private double calculable,
				   equivalente;

	public CircuitButtonPanel(boolean resistance, JFrame frame) {
		super();
		this.resistance=resistance;
		this.calculable=this.equivalente=0;
		this.frame=frame;
		String stEq, stCl;
		if(resistance) {
			stEq = "Equivalent Resistance ( Ohms ) = ";
			stCl = "Total current ( A ) = ";
		}
		else {
			stEq = "Equivalent Capacitance ( F ) = ";
			stCl = "Total charge ( C ) = ";
			
		}
		this.setPreferredSize(new Dimension(240,660));
		this.setBorder(new MatteBorder(0,0,0,4,Color.BLACK));
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridy=0;
		c.insets = new Insets(15,0,0,0);
		JLabel title = new JLabel("Circuit");
		title.setFont(new Font("Arial",Font.BOLD,40));
		title.setHorizontalAlignment(JLabel.CENTER);
		this.add(title, c);
		c.gridy=1;
		c.insets = new Insets(0,0,15,0);
		JLabel subtitle = new JLabel("Simulator");
		subtitle.setHorizontalAlignment(JLabel.CENTER);
		subtitle.setFont(new Font("Arial",Font.BOLD,40));
		this.add(subtitle,c);
		
		c.gridy=2;
		String type;
		if(resistance) {
			type = "Resistances";
		}
		else {
				type = "Capacitors";
		}
		
		JLabel subsubtitle = new JLabel(type);
		subsubtitle.setHorizontalAlignment(JLabel.CENTER);
		subsubtitle.setFont(new Font("Arial",Font.PLAIN,25));
		this.add(subsubtitle,c);
		
		c.insets = new Insets(0,0,3,0);
		c.gridy=3;
		this.add(new JLabel("Voltage ( V )"),c);
		
		JLabel lbCalculable = new JLabel(stCl+"N/A");
		JLabel lbEquivalente = new JLabel(stEq+"N/A");
		
		c.gridy=4;
		c.fill=GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,15,0);
		JTextField voltage = new JTextField();
		voltage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					double x =Double.parseDouble(voltage.getText());
					/*for(int i=0;i<12;i++) {
						for(int j=0;j<12;j++) {
							if(CircuitPanel.graph[i][j]!=null) {
								CircuitPanel.graph[i][j]
							}
						}
					}*/
					equivalente=CircuitPanel.getTotalEquivalent();
					lbEquivalente.setText(stEq+new DecimalFormat("#.00").format(equivalente));
					if(resistance) {
						calculable=x/CircuitPanel.getTotalEquivalent();
					}
					else {
						calculable=x*CircuitPanel.getTotalEquivalent();
					}
					lbCalculable.setText(stCl+new DecimalFormat("#.00").format(calculable));
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Please insert a numeric value");
				}
			}
		});
		this.add(voltage,c);
		
		c.insets = new Insets(0,0,3,0);
		
		c.fill=GridBagConstraints.NONE;
		c.gridy=5;
		this.add(lbEquivalente,c);
		c.gridy=6;
		this.add(lbCalculable, c);
	
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(7,0,15,0);
		c.ipady=40;
		c.ipadx=60;
		c.gridx=0;
		c.gridy++;
		
		JButton btSeries = new JButton("Series");
		btSeries.setActionCommand("series");
		btSeries.addActionListener(this);
		this.add(btSeries,c);
		c.gridy++;
		JButton btParallel = new JButton("Parallel");
		btParallel.setActionCommand("parallel");
		btParallel.addActionListener(this);
		this.add(btParallel,c);
		c.gridy++;
		JButton btCancel = new JButton("Cancel");
		btCancel.setActionCommand("cancel");
		btCancel.addActionListener(this);
		this.add(btCancel,c);
		c.gridy++;
		JButton btBack = new JButton("Back");
		btBack.setActionCommand("back");
		btBack.addActionListener(this);
		this.add(btBack,c);
	}
	
	public void setCP(CircuitGraphicPanel cp) {
		this.cp=cp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("series".equals(e.getActionCommand())) {
			cp.drawParallel(1);
		}
		else if("parallel".equals(e.getActionCommand())) {
			cp.drawParallel();
		}
		else if("cancel".equals(e.getActionCommand())) {
			this.cp.clear();
			this.cp.joinNodes();
			CircuitPanel.graph[this.cp.getNN().getIndex()-1][this.cp.getCN().getIndex()-1] = null;
			CircuitPanel.graph[this.cp.getCN().getIndex()-1][this.cp.getNN().getIndex()-1] = null;
			if(this.cp.getCN().getIndex()+this.cp.getNN().getIndex()==13&&this.cp.getCN().getIndex()*this.cp.getNN().getIndex()!=42) {
				this.cp.eraseConnectionAcross();
			}
			this.cp.setCN(null);
			this.cp.setNN(null);
			this.cp.drawNodeLabel();
		}
		else if("back".equals(e.getActionCommand())) {
			this.frame.dispose();
			SelectionFrame sf = new SelectionFrame();
		}
	}

}