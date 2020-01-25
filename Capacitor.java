/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafodirigido;

/**
 *
 * @author fernandobenavides
 */
public class Capacitor extends Componente {
	
	private double carga,
                   capacitancia;
	
	//Constructores
	public Capacitor() {
		this(0.0,0.0,0.0,0.0);
	}
	
	public Capacitor(double potencial, double corriente, double carga, double capacitancia) {
		super(potencial, corriente);
		this.carga = carga;
		this.capacitancia = carga; 
	}
}
