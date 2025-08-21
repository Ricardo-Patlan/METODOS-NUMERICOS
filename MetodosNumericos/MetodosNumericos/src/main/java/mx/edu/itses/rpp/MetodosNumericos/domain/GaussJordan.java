
package mx.edu.itses.rpp.MetodosNumericos.domain;

import lombok.Data;

@Data
public class GaussJordan {
    private int n; 
    private double[][] matriz; 
    private double[] resultados; 
    
}
