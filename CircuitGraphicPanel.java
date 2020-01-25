import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CircuitGraphicPanel extends JPanel{
	
	private boolean resistance;
	private NodePanel cn,
					  nn;
	private String[] stValues;

	public CircuitGraphicPanel(boolean resistance) {
		super();
		this.stValues= new String[3];
		this.resistance=resistance;
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(720,660));
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.PAGE_START;
		
		c.gridheight=c.gridwidth=1;
		c.gridx=0;
		c.gridy=0;
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(135, 105, 15, 15);
		
		this.add(new NodePanel(1,this),c);
		c.insets = new Insets(135, 75, 15, 15);
		c.gridx++;
		this.add(new NodePanel(2,this),c);
		c.gridx++;
		this.add(new NodePanel(3,this),c);
		c.gridx++;
		this.add(new NodePanel(4,this),c);
		c.gridx++;
		c.insets = new Insets(135, 75, 15, 105);
		this.add(new NodePanel(5,this),c);
		
		c.gridx=0;
		c.gridy=1;
		c.gridwidth=3;
		c.gridheight=2;		
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(0,0,0,0);
		
		JPanel gap = new JPanel();
		gap.setVisible(false);
		
		this.add(gap,c);
		
		c.gridheight=1;
		c.gridwidth=1;
		c.insets = new Insets(75, 75, 15, 105);
		c.fill = GridBagConstraints.NONE;
		c.gridy=1;
		c.gridx=4;
		
		this.add(new NodePanel(6,this),c);
		c.gridy++;
		this.add(new NodePanel(7,this),c);
		c.gridy++;
		
		c.insets = new Insets(75, 105, 135, 15);
		c.gridx=0;
		this.add(new NodePanel(12,this),c);
		c.insets = new Insets(75, 75, 135, 15);
		c.gridx++;
		this.add(new NodePanel(11,this),c);
		c.gridx++;
		this.add(new NodePanel(10,this),c);
		c.gridx++;
		this.add(new NodePanel(9,this),c);
		c.gridx++;
		c.insets = new Insets(75, 75, 135, 105);
		this.add(new NodePanel(8,this),c);
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(4));
		g2.drawLine(45, 150, 600, 150);
		g2.drawLine(45, 510, 600, 510);
		g2.drawLine(600, 150, 600, 510);
		g2.drawLine(45, 140, 45, 160);
		g2.drawLine(45, 485, 45, 535);
		g.setFont(new Font("Arial",Font.PLAIN,20));
		g2.drawString("-", 50, 135);
		g2.drawString("+", 50, 480);
		this.drawNodeLabel();
	}
	
	public void drawNodeLabel() {
		Graphics g=this.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(75, 570, 400,40);
		g.setFont(new Font("Arial",Font.BOLD,40));
		g.setColor(Color.BLACK);
		String nodes = "Selected nodes: ";
		if(this.cn!=null) {
			nodes+=NodePanel.abc[this.cn.getIndex()-1];
		}
		if(this.nn!=null) {
			nodes+=", "+NodePanel.abc[this.nn.getIndex()-1];
		}
		g.drawString(nodes, 75, 600);
	}
	
	public boolean getResistance() {
		return this.resistance;
	}
	
	public void setCN(NodePanel np) {
		this.cn=np;
	}
	
	public NodePanel getCN() {
		return this.cn;
	}
	
	public void setNN(NodePanel np) {
		this.nn=np;
	}
	
	public NodePanel getNN() {
		return this.nn;
	}
	
	public void joinNodes() {
		this.deleteParallel(cn, nn);
		this.NodeLines(cn, nn,Color.BLACK);
		cn.repaint();
		nn.repaint();
	}
	
	public void eraseConnectionAcross() {
		Graphics g = this.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(this.cn.getX(),135, 30, Math.abs(this.cn.getY()-this.nn.getY()));
		cn.repaint();
		nn.repaint();
	}
	
	private void NodeLines(NodePanel a,NodePanel b,Color c) {
		if(Math.abs(a.getIndex()-b.getIndex())==1||(a.getIndex()*b.getIndex()<40&&a.getIndex()+b.getIndex()==13)) {
			Graphics2D g = (Graphics2D) this.getGraphics();
			g.setColor(c);
			g.setStroke(new BasicStroke(4));
			g.drawLine(a.getX()+15, a.getY()+15, b.getX()+15, b.getY()+15);
		}
		else {
			JOptionPane.showMessageDialog(null, "These nodes can't be connected. Please try again");
			this.cn=null;
		}
	}
	
	public void drawResistance(NodePanel a, NodePanel b, int connections) {
		Graphics2D g = (Graphics2D) this.getGraphics();
		g.setStroke(new BasicStroke(4));
		int xlen = Math.abs(a.getX()-b.getX())+30;
		int ylen = Math.abs(a.getY()-b.getY())+30;
		int cX = (a.getX()+b.getX()+30)/2;
		int cY = (a.getY()+b.getY()+30)/2;
		if(connections!=2) {
			if(ylen==30) {
				this.drawHorizontalResistance(cX,cY,0);
				if(connections==3) {
					this.drawHorizontalResistance(cX,cY-2*xlen/8,1);
					this.drawHorizontalResistance(cX,cY+2*xlen/8,2);
				}
			}
			else if(xlen==30) {
				this.drawVerticalResistance(cX, cY,0);
				if(connections==3&&(this.cn.getIndex()+this.nn.getIndex()!=13||this.cn.getIndex()*this.nn.getIndex()==42)) {
					this.drawVerticalResistance(cX+ylen/4,cY,1);
					this.drawVerticalResistance(cX-ylen/4,cY,2);
				}
			}
		}
		else if(connections==2) {
			if(ylen==30) {
				this.drawHorizontalResistance(cX, cY-xlen/8,0);
				this.drawHorizontalResistance(cX, cY+xlen/8,1);
			}
			else if(xlen==30) {
				this.drawVerticalResistance(cX-ylen/8, cY,0);
				this.drawVerticalResistance(cX+ylen/8, cY,1);
			}
		}
	}
	
	public void drawCapacitor(NodePanel a, NodePanel b, int connections) {
		Graphics2D g = (Graphics2D) this.getGraphics();
		g.setStroke(new BasicStroke(4));
		int xlen = Math.abs(a.getX()-b.getX())+30;
		int ylen = Math.abs(a.getY()-b.getY())+30;
		int cX = (a.getX()+b.getX()+30)/2;
		int cY = (a.getY()+b.getY()+30)/2;
		if(connections!=2) {
			if(ylen==30) {
				this.drawHorizontalCapacitor(cX,cY,0);
				if(connections==3) {
					this.drawHorizontalCapacitor(cX,cY-2*xlen/8,1);
					this.drawHorizontalCapacitor(cX,cY+2*xlen/8,2);
				}
			}
			else if(xlen==30) {
				this.drawVerticalCapacitor(cX, cY,0);
				if(connections==3&&(this.cn.getIndex()+this.nn.getIndex()!=13||this.cn.getIndex()*this.nn.getIndex()==42)) {
					this.drawVerticalCapacitor(cX+ylen/4,cY,1);
					this.drawVerticalCapacitor(cX-ylen/4,cY,2);
				}
			}
		}
		else if(connections==2) {
			if(ylen==30) {
				this.drawHorizontalCapacitor(cX, cY-xlen/8,0);
				this.drawHorizontalCapacitor(cX, cY+xlen/8,1);
			}
			else if(xlen==30) {
				this.drawVerticalCapacitor(cX-ylen/8, cY,0);
				this.drawVerticalCapacitor(cX+ylen/8, cY,1);
			}
		}
	}
	
	private void drawVerticalResistance(int x,int y, int stIndex) {
		Graphics2D g = (Graphics2D) this.getGraphics();
		g.setStroke(new BasicStroke(2));
		g.setColor(Color.WHITE);
		g.fillRect(x-8, y-8, 16, 16);
		g.setColor(Color.BLACK);
		g.drawLine(x, y-8, x-8, y-6);
		g.drawLine(x-8, y-6, x+8, y-2);
		g.drawLine(x+8, y-2, x-8, y+2);
		g.drawLine(x-8, y+2, x+8, y+6);
		g.drawLine(x+8, y+6, x, y+8);
		this.paintLabel(x, y, true, this.stValues[stIndex]);
	}
	
	private void drawHorizontalResistance(int x,int y, int stIndex) {
		Graphics2D g = (Graphics2D) this.getGraphics();
		g.setStroke(new BasicStroke(2));
		g.setColor(Color.WHITE);
		g.fillRect(x-8, y-8, 16, 16);
		g.setColor(Color.BLACK);
		g.drawLine(x-8, y, x-6, y-8);
		g.drawLine(x-6, y-8, x-2, y+8);
		g.drawLine(x-2, y+8, x+2, y-8);
		g.drawLine(x+2, y-8, x+6, y+8);
		g.drawLine(x+6, y+8, x+8, y);
		this.paintLabel(x, y, false, this.stValues[stIndex]);
	}
	
	private void drawVerticalCapacitor(int x,int y, int stIndex) {
		Graphics2D g = (Graphics2D) this.getGraphics();
		g.setStroke(new BasicStroke(4));
		g.setColor(Color.WHITE);
		g.fillRect(x-8, y-8, 16, 16);
		g.setColor(Color.BLACK);
		g.drawLine(x-8, y-8, x+8, y-8);
		g.drawLine(x-8, y+8, x+8, y+8);
		this.paintLabel(x, y, true, this.stValues[stIndex]);
	}
	
	private void drawHorizontalCapacitor(int x,int y, int stIndex) {
		Graphics2D g = (Graphics2D) this.getGraphics();
		g.setStroke(new BasicStroke(4));
		g.setColor(Color.WHITE);
		g.fillRect(x-8, y-8, 16, 16);
		g.setColor(Color.BLACK);
		g.drawLine(x-8, y-8, x-8, y+8);
		g.drawLine(x+8, y-8, x+8, y+8);
		this.paintLabel(x, y, false, this.stValues[stIndex]);
	}
	
	public void drawParallel() {
		int connections = Integer.parseInt(JOptionPane.showInputDialog("How many connections?"));
		String kind;
		if(this.resistance) {
			kind = "resistance";
		}
		else {
			kind = "capacitor";
		}
		CircuitPanel.graph[this.cn.getIndex()-1][this.nn.getIndex()-1] = new Arista();
		CircuitPanel.graph[this.cn.getIndex()-1][this.nn.getIndex()-1].setLista(new ArrayList<Componente>());
		CircuitPanel.graph[this.nn.getIndex()-1][this.cn.getIndex()-1] = new Arista();
		CircuitPanel.graph[this.nn.getIndex()-1][this.cn.getIndex()-1].setLista(new ArrayList<Componente>());
		for(int i=0;i<connections;i++) {
			double fixed = Double.parseDouble(JOptionPane.showInputDialog("Insert the value for "+kind+" number "+(i+1)));
			this.stValues[i]=""+fixed;
			CircuitPanel.graph[this.cn.getIndex()-1][this.nn.getIndex()-1].getLista().add(new Componente(fixed));
			CircuitPanel.graph[this.nn.getIndex()-1][this.cn.getIndex()-1].getLista().add(new Componente(fixed));
		}
		this.drawParallel(cn, nn, connections, Color.BLACK);
	}
	
	public void drawParallel(int connections) {
		String kind;
		if(this.resistance) {
			kind = "resistance";
		}
		else {
			kind = "capacitor";
		}
		CircuitPanel.graph[this.cn.getIndex()-1][this.nn.getIndex()-1] = new Arista();
		CircuitPanel.graph[this.cn.getIndex()-1][this.nn.getIndex()-1].setLista(new ArrayList<Componente>());
		CircuitPanel.graph[this.nn.getIndex()-1][this.cn.getIndex()-1] = new Arista();
		CircuitPanel.graph[this.nn.getIndex()-1][this.cn.getIndex()-1].setLista(new ArrayList<Componente>());
		for(int i=0;i<connections;i++) {
			double fixed = Double.parseDouble(JOptionPane.showInputDialog("Insert the value for "+kind+" number "+(i+1)));
			this.stValues[i]=""+fixed;
			CircuitPanel.graph[this.cn.getIndex()-1][this.nn.getIndex()-1].getLista().add(new Componente(fixed));
			CircuitPanel.graph[this.nn.getIndex()-1][this.cn.getIndex()-1].getLista().add(new Componente(fixed));
		}
		this.drawParallel(cn, nn, connections, Color.BLACK);
	}
	
	private void paintLabel(int x, int y, boolean horizontal, String value) {
		Graphics g = this.getGraphics();
		g.setFont(new Font("Arial",Font.PLAIN,8));
		if(horizontal) {
			g.drawString(value, x+10, y+2);
		}
		else {
			g.drawString(value, x-6, y+18);
		}
	}
	
	public void clear() {
		if(this.cn.getX()==this.nn.getX()&&this.cn.getIndex()+this.nn.getIndex()==13&&this.cn.getIndex()*this.nn.getIndex()!=42) {
			this.clearCarefully();
		}
		else {
			this.clearArea();
		}
	}
	
	private void clearArea() {
		Graphics2D g = (Graphics2D) this.getGraphics();
		g.setStroke(new BasicStroke(4));
		g.setColor(Color.WHITE);
		int xlen = Math.abs(this.cn.getX()-this.nn.getX())+30;
		int ylen = Math.abs(this.cn.getY()-this.nn.getY())+30;
		int cX = (this.cn.getX()+this.nn.getX()+30)/2;
		int cY = (this.cn.getY()+this.nn.getY()+30)/2;
		if(ylen==30) {
			g.fillRect(cX-8, cY-2*xlen/8-9, 16, xlen/2+35);
		}
		else if(xlen==30) {
			g.fillRect(cX-2*ylen/8-9,cY-8 , ylen/2+35,16 );
		}
	}
	
	private void deleteParallel(NodePanel a,NodePanel b) {
		Graphics2D g = (Graphics2D) this.getGraphics();
		g.setStroke(new BasicStroke(4));
		int xlen = Math.abs(a.getX()-b.getX())+30;
		int ylen = Math.abs(a.getY()-b.getY())+30;
		int cX = (a.getX()+b.getX()+30)/2;
		int cY = (a.getY()+b.getY()+30)/2;
		g.setColor(Color.WHITE);
		g.drawRect(cX-3*xlen/16, cY-xlen/8, 3*xlen/8, xlen/4);
		g.drawRect(cX-ylen/8, cY-3*ylen/16, ylen/4, 3*ylen/8);
		if(a.getIndex()+b.getIndex()!=13||a.getIndex()*b.getIndex()==42) {
			g.drawRect(cX-3*xlen/16, cY-2*xlen/8, 3*xlen/8, 2*xlen/4);
			g.drawRect(cX-2*ylen/8, cY-3*ylen/16, 2*ylen/4, 3*ylen/8);
		}
	}
	
	private void clearCarefully() {
		Graphics2D g = (Graphics2D) this.getGraphics();
		g.setStroke(new BasicStroke(4));
		g.setColor(Color.WHITE);
		int xlen = Math.abs(this.cn.getX()-this.nn.getX())+30;
		int ylen = Math.abs(this.cn.getY()-this.nn.getY())+30;
		int cX = (this.cn.getX()+this.nn.getX()+30)/2;
		int cY = (this.cn.getY()+this.nn.getY()+30)/2;
		g.fillRect(cX-ylen/8-9,cY-8 ,ylen/4+60,16 );
	}
	
	private void drawParallel(NodePanel a,NodePanel b,int connections,Color c) {
		if(connections==1) {
			if(a.getIndex()+b.getIndex()==13&&a.getIndex()*b.getIndex()!=42) {
				this.clearCarefully();
			}
			else {
				this.clearArea();
			}
			this.joinNodes();
			if(this.resistance) {
				this.drawResistance(cn, nn, 1);
			}
			else {
				this.drawCapacitor(cn,nn,1);
			}
		}
		else if(connections>3||connections<=0){
			JOptionPane.showMessageDialog(null, "Can't make more than three parallel connections. Please try again");
		}
		else {
			Graphics2D g = (Graphics2D) this.getGraphics();
			g.setStroke(new BasicStroke(4));
			int xlen = Math.abs(a.getX()-b.getX())+30;
			int ylen = Math.abs(a.getY()-b.getY())+30;
			int cX = (a.getX()+b.getX()+30)/2;
			int cY = (a.getY()+b.getY()+30)/2;
			if(connections==2&&ylen==30) {
				this.clearArea();
				this.joinNodes();
				g.setColor(Color.WHITE);
				g.drawLine(cX-3*xlen/16, a.getY()+15, cX+3*xlen/16, b.getY()+15);
				g.setColor(c);
				g.drawRect(cX-3*xlen/16, cY-xlen/8, 3*xlen/8, xlen/4);
				if(this.resistance) {
					this.drawResistance(cn, nn, connections);
				}
				else {
					this.drawCapacitor(cn,nn,connections);
				}
			}
			else if(connections==2&&xlen==30) {
				if(a.getIndex()+b.getIndex()==13||a.getIndex()*b.getIndex()!=42) {
					this.clearCarefully();
				}
				else {
					this.clearArea();
				}
				this.joinNodes();
				g.setColor(Color.WHITE);
				g.drawLine(a.getX()+15, cY-3*ylen/16, b.getX()+15, cY+3*ylen/16);
				g.setColor(c);
				g.drawRect(cX-ylen/8, cY-3*ylen/16, ylen/4, 3*ylen/8);
				if(this.resistance) {
					this.drawResistance(cn, nn, connections);
				}
				else {
					this.drawCapacitor(cn,nn,connections);
				}
			}
			else if(connections==3&&ylen==30) {
				this.clearArea();
				this.joinNodes();
				g.setColor(c);
				g.drawRect(cX-3*xlen/16, cY-2*xlen/8, 3*xlen/8, 2*xlen/4);
				if(this.resistance) {
					this.drawResistance(cn, nn, connections);
				}
				else {
					this.drawCapacitor(cn,nn,connections);
				}
			}
			else if(connections==3&&xlen==30&&(a.getIndex()+b.getIndex()!=13||a.getIndex()*b.getIndex()==42)){
				if(a.getIndex()+b.getIndex()==13||a.getIndex()*b.getIndex()!=42) {
					this.clearCarefully();
				}
				else {
					this.clearArea();
				}
				this.joinNodes();
				g.setColor(c);
				g.drawRect(cX-2*ylen/8, cY-3*ylen/16, 2*ylen/4, 3*ylen/8);
				if(this.resistance) {
					this.drawResistance(cn, nn, connections);
				}
				else {
					this.drawCapacitor(cn,nn,connections);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Can't make more than two parallel connections between these nodes. Please try again");
			}
		}
	}
}
