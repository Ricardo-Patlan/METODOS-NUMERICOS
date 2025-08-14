
package mx.edu.itses.rpp.MetodosNumericos.domain;


import java.util.ArrayList;
import lombok.Data;

@Data
public class Gauss {
 
    private int MN; 
    private ArrayList<Double> matrizA;  
    private ArrayList<Double> vectorB; 
    private ArrayList<Double> vectorX; 
    private double[][] matriz;          
    private double[] resultados; 
    private double[] solucion;
    
}