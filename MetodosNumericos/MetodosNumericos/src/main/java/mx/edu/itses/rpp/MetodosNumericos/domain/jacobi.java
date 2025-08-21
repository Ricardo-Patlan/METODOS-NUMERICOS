package mx.edu.itses.rpp.MetodosNumericos.domain;
import lombok.Data;

@Data
public class jacobi {
      private int n;                    
    private double[][] matriz;         
    private double[] x0;              
    private double tol;              
    private int iteracionesMax;        
    private double[] resultados; 
    
}
