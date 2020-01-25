import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class NodePanel extends JPanel implements MouseListener{
	
	public static String[] abc = {"A","B","C","D","E","F","G","H","I","J","K","L"};
	private int index;
	private CircuitGraphicPanel cp;
	
	public NodePanel(int index, CircuitGraphicPanel cp) {
		super();
		this.cp=cp;
		this.index=index;
		this.setPreferredSize(new Dimension(30,30));
		this.setBackground(Color.WHITE);
		this.addMouseListener(this);
		this.add(new JLabel(NodePanel.abc[index-1]));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(34,139,34));
		g.fillOval(0, 0, 30, 30);
		g.setColor(Color.GREEN);
		g.fillOval(5, 5, 20, 20);
	}
	
	public int getIndex() {
		return this.index;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(this.cp.getCN()==null) {
			this.cp.setCN(this);
		}
		else if(this.cp.getNN()!=null){
			this.cp.setCN(this);
			this.cp.setNN(null);
		}
		else if(Math.abs(this.getIndex()-this.cp.getCN().getIndex())==1||(this.getIndex()*this.cp.getCN().getIndex()<40&&this.getIndex()+this.cp.getCN().getIndex()==13)){
			this.cp.setNN(this);
		}
		else {
			JOptionPane.showMessageDialog(null, "These nodes can't be connected. Please try again");
			this.cp.setCN(null);
			this.cp.setNN(null);
		}
		this.cp.drawNodeLabel();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
