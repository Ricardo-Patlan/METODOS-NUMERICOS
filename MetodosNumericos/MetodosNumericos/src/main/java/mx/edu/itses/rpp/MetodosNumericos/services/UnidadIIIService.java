
package mx.edu.itses.rpp.MetodosNumericos.services;


import mx.edu.itses.rpp.MetodosNumericos.domain.Gauss;
import mx.edu.itses.rpp.MetodosNumericos.domain.ReglaCramer;


public interface UnidadIIIService {
   public ReglaCramer AlgoritmoReglaCramer(ReglaCramer modelCramer);
   public Gauss AlgoritmoGauss(Gauss modelGauss);
}
