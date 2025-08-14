
package mx.edu.itses.rpp.MetodosNumericos.domain;
 import lombok.Data;

@Data 

public class NewtonRaphson {
       private String FX;              // función a evaluar
    private String FXDerivada;      // derivada de la función
    private double Xi;              // valor inicial Xi
    private double FXi;             // f(Xi)
    private double FXii;            // f'(Xi) - derivada evaluada
    private double Xii;             // Xi+1 (siguiente iteración)
    private double Ea;              // error relativo
    private int IteracionesMaximas; // número máximo de iteraciones

    
    
}
