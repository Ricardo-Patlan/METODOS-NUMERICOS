
package mx.edu.itses.rpp.MetodosNumericos.domain;

import java.util.ArrayList;
import lombok.Data;

@Data
public class Jacobi {
          private int MN;
    private ArrayList<Double> matrizA;
    private ArrayList<Double> vectorB;
    private ArrayList<Double> vectorX;
    private ArrayList<Double> vectorInicial;
    private ArrayList<String> pasos;
    private double tolerancia;
    private int maxIteraciones;
    private int iteracionesRealizadas;
    private boolean convergio;
}
