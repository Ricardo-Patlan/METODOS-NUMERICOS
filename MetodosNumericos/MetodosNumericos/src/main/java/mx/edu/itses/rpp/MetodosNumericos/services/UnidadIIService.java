package mx.edu.itses.rpp.MetodosNumericos.services;

import java.util.ArrayList;
import mx.edu.itses.rpp.MetodosNumericos.domain.Biseccion;
import mx.edu.itses.rpp.MetodosNumericos.domain.PuntoFijo;
import mx.edu.itses.rpp.MetodosNumericos.domain.ReglaFalsa;


public interface UnidadIIService {

 
    public ArrayList<Biseccion> AlgoritmoBiseccion(Biseccion biseccion);
          public ArrayList<ReglaFalsa> AlgoritmoReglaFalsa(ReglaFalsa reglafalsa); 
             public ArrayList<PuntoFijo> AlgoritmoPuntoFijo(PuntoFijo puntofijo); 
    
}
