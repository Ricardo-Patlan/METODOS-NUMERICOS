package mx.edu.itses.rpp.MetodosNumericos.domain;
import lombok.Data;

@Data
public class Secante {
          private String FX;
    private double Xi;
    private double Xi_1;
    private double FXi;
    private double Xi1;
    private double Ea;
    private int IteracionesMaximas;
    private double F_Xi_1;

    
}
