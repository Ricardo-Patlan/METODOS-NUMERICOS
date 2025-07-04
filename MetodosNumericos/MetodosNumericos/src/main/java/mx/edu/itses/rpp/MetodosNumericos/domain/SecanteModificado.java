
package mx.edu.itses.rpp.MetodosNumericos.domain;

import lombok.Data;

@Data 
public class SecanteModificado {
    private String FX;
private double Xi;
private double Xi1;
private double FXi;
private double FXiSigma;
private double Ea;
private int IteracionesMaximas;
private double Sigma;
}
