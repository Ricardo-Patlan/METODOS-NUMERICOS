package mx.edu.itses.rpp.MetodosNumericos.domain;

import lombok.Data;


@Data
public class PuntoFijo {
    private String FX;               // f(x)
    private double Xi;               // valor inicial
    private double GXi;              // resultado de g(xi)
    private int IteracionesMaximas;  // límite de iteraciones
    private double Ea;               // error estimado
    private String gx;               // función g(x)
}

