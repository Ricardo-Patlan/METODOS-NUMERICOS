package mx.edu.itses.rpp.MetodosNumericos.domain;
import lombok.Data;
@Data
public class GaussSeidel {
       private int n;                
    private double[][] matriz;   
    private double[] x0;           
    private int iteraciones;       
    private double tolerancia;     
    
    
}
