
package mx.edu.itses.rpp.MetodosNumericos.domain;

import java.util.ArrayList;
import lombok.Data;

@Data
public class GaussJordan {
    private int MN;  // Dimensión del sistema
    private ArrayList<Double> matrizA;  // Matriz de coeficientes como lista
    private ArrayList<Double> vectorB;  // Vector de términos independientes
    private ArrayList<Double> vectorX;  // Vector solución
    private double[][] matriz;          // Matriz bidimensional para cálculos internos
    private double[] resultados;        // Array de resultados para cálculos internos
    private double[] solucion;   
    
}
