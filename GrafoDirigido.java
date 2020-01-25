/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafodirigido;

import java.util.ArrayList;

/**
 *
 * @author fernandobenavides
 */
public class GrafoDirigido {
    
    private Arista[][] grafo;
    
    public GrafoDirigido(int nNodos) {
        grafo = new Arista[nNodos][nNodos];
        
        /*
        for(int fila=0; fila<nNodos; fila++) {
            for(int columna=0; columna<nNodos; columna++) {
                
            }
        }
        */
    }
    
    
    public boolean existeArista(int fila, int columna) {
        return grafo[fila][columna] != null;
    }
    
    public void addComponente(int fila, int columna, Componente auxCompoenente) {
        if (fila != columna) {
            if(!existeArista(fila,columna)) {
                addArista(fila,columna);
            }
            grafo[fila][columna].getLista().add(auxCompoenente);
        }
    }
     
    private void addArista(int fila, int columna) {
        grafo[fila][columna] = grafo[columna][fila] =  new Arista();
    }
    
    
    public void calcular(Double voltajeInicial, Double corrienteIncial) {
        ArrayList<Arista> visitado = new ArrayList();
        
        for(int fila=0; fila<grafo.length; fila++) {
            for(int columna=0; columna<grafo.length; columna++) {
                if (fila == columna) {
                    continue;
                } else if (grafo[fila][columna] != null && !isIn(visitado, grafo[fila][columna])) {
                    //grafo[fila][columna].calcular();
                    visitado.add(grafo[fila][columna]);
                }
                
            }
        }
    }
    
    private boolean isIn(ArrayList<Arista> array, Arista finded) {
        return array.stream().anyMatch((elem) -> (elem == finded));
    }
    
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
