/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fernandobenavides
 */
public class Componente {
    
    	private double potencial;
        private double calculable;
        private double fixed;
	
	//Constructores
	public Componente() {
		this(0.0,0.0,0.0);
	}
	
	public Componente(double fixed) {
        this.fixed=fixed;
}
	
	public Componente(double potencial, double calculable, double fixed) {
                this.potencial = potencial;
                this.calculable = calculable;
                this.fixed=fixed;
	}

    public double getPotencial() {
        return potencial;
    }

    public void setPotencial(double potencial) {
        this.potencial = potencial;
    }

    public double getCalculable() {
        return this.calculable;
    }

    public void setCalculable(double calculable) {
        this.calculable = calculable;
    }

    public double getFixed() {
        return fixed;
    }

    public void setCarga(double fixed) {
        this.fixed = fixed;
    }	
    
}
