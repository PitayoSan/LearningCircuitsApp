import java.util.ArrayList;

public class Arista {
    
    private Double voltajeInicial;
    private Double voltajeFinal;
    private Double equivalente;
    private ArrayList<Componente> lista;
    private boolean resistance;
    
    public Arista() {
        this.lista = new ArrayList<>();
        this.voltajeInicial = 0.0;
        this.voltajeFinal = 0.0;
        this.equivalente = 0.0;
    }
    
    public double calcularEquivalente() {
        double aux = 0;
        
        if(CircuitPanel.resistance) {
        	if(this.lista.size()>1) {
        		for (Componente elem: this.lista) {
                    aux += 1/elem.getFixed();
                }
                
                aux = Math.pow(aux, -1);
        	}
        	else {
        		aux = this.lista.get(0).getFixed();
        	}
        }
        else {
        	if(this.lista.size()>1) {
        		for (Componente elem: this.lista) {
                    aux += elem.getFixed();
                }
        	}
        	else {
        		aux = this.lista.get(0).getFixed();
        	}
        }

        this.setEquivalente(aux);
        return this.getEquivalente();
    }
    
    public Double calcularVoltajeFinal() {
    	if(this.lista.size()>1) {
    		return this.voltajeInicial;
    	}
    	else if(this.resistance){
    		return this.voltajeInicial+CircuitPanel.calculable*this.lista.get(0).getFixed();
    	}
    	else {
    		return this.voltajeInicial+CircuitPanel.calculable/this.lista.get(0).getFixed();
    	}
    }
    
    public double calcularCalculable() {
    	double aux = 0;
    	for (Componente elem: this.lista) {
            aux += elem.getCalculable();
        }
    	return aux;
    }
    
    // Getters & Setters
    public Double getVoltajeInicial() {
        return voltajeInicial;
    }

    public void setVoltajeInicial(Double voltajeInicial) {
        this.voltajeInicial = voltajeInicial;
    }

    public Double getVoltajeFinal() {
        return voltajeFinal;
    }

    public void setVoltajeFinal(Double voltajeFinal) {
        this.voltajeFinal = voltajeFinal;
    }

    public Double getEquivalente() {
        return equivalente;
    }

    public void setEquivalente(Double equivalente) {
        this.equivalente = equivalente;
    }

    public ArrayList<Componente> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Componente> lista) {
        this.lista = lista;
    }
    
    
    
    

    
}
