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

public class Resistencia extends Componente {
	
	private double resistividad;
	
	//Constructores
	public Resistencia() {
		this(0.0,0.0,0.0);
	}
	
	public Resistencia(double potencial, double corriente, double resistividad) {
		super(potencial, corriente);
		this.resistividad = resistividad;
	}

    public double getResistividad() {
        return resistividad;
    }

    public void setResistividad(double resistividad) {
        this.resistividad = resistividad;
    }
        
        
}
