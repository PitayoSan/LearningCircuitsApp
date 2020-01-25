import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.GrayFilter;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CircuitPanel extends JPanel{
	
	public static boolean resistance;
	public static Arista[][] graph;
	public static double calculable;
	
	public CircuitPanel(boolean resistance,JFrame frame) {
		super();
		CircuitPanel.graph=new Arista[12][12];
		/*
		for(int i=0;i<12;i++) {
			for(int j=0;j<12;j++) {
				CircuitPanel.graph[i][j]=new Arista();
			}
		}
		*/
		this.resistance=resistance;
		CircuitPanel.calculable=0;
		this.setPreferredSize(new Dimension(960,660));
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		CircuitButtonPanel cb = new CircuitButtonPanel(resistance,frame);
		CircuitGraphicPanel cp = new CircuitGraphicPanel(resistance);
		cb.setCP(cp);
		c.gridx=0;
		this.add(cb,c);
		c.gridx=1;
		c.anchor= GridBagConstraints.LINE_END;
		this.add(cp,c);
		
		
		this.setBackground(Color.WHITE);
	}
	
	public static double getTotalEquivalent() {
		ArrayList<Arista> visitado = new ArrayList();
        double res = 0;
        for(int fila=0; fila<graph.length; fila++) {
            for(int columna=0; columna<graph.length; columna++) {
                if (fila == columna) {
                    continue;
                } else if (graph[fila][columna] != null && !isIn(visitado, graph[fila][columna])) {
                	if(!resistance) {
                		res += 1/graph[fila][columna].calcularEquivalente();
                	}
                	else {
                		res += graph[fila][columna].calcularEquivalente();
                	}
                    visitado.add(graph[fila][columna]);
                    visitado.add(graph[columna][fila]);
                }
                
            }
        }
        if(!resistance) {
        	res = 1/res;
        }
        return res;
	}
	
	private static boolean isIn(ArrayList<Arista> array, Arista finded) {
        return array.stream().anyMatch((elem) -> (elem == finded));
    }
	
}