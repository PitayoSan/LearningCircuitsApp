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
public class Resultado extends Componente {
    private double resistividad;
    private double capacitividad;
    private double carga;
    
    public Resultado() {
        this(0.0,0.0,0.0,0.0,0.0);
    }
    
    public Resultado(double potencial, double corriente, double capacitividad, double carga, double resistividad) {
		//super(potencial, corriente);
		this.capacitividad = capacitividad;
		this.carga = carga;
		this.resistividad = resistividad;
	}

    public double getResistividad() {
        return resistividad;
    }

    public void setResistividad(double resistividad) {
        this.resistividad = resistividad;
    }

    public double getCapacitividad() {
        return capacitividad;
    }

    public void setCapacitividad(double capacitividad) {
        this.capacitividad = capacitividad;
    }

    public double getCarga() {
        return carga;
    }

    public void setCarga(double carga) {
        this.carga = carga;
    }
    
    
    
}
