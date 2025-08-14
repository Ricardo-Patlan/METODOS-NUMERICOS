
package mx.edu.itses.rpp.MetodosNumericos.domain;

import lombok.Data;

@Data
public class ReglaFalsa {
          private String FX;// funcion ecvaluar
       private double XL;
    private double XU;
    private double XR;
    private double FXL;
    private double FXU;
    private double FXR;
    private double ProductoFXL_FXR; // Para almacenar F(XL) * F(XR)
    private double Ea;
    private int IteracionesMaximas;

    
}
