
package mx.edu.itses.rpp.MetodosNumericos.services;


import java.util.ArrayList;
import mx.edu.itses.rpp.MetodosNumericos.domain.Gauss;
import mx.edu.itses.rpp.MetodosNumericos.domain.ReglaCramer;
import mx.edu.itses.rpp.MetodosNumericos.domain.Jacobi;
import mx.edu.itses.rpp.MetodosNumericos.domain.GaussSeidel;
import mx.edu.itses.rpp.MetodosNumericos.domain.GaussJordan;


public interface UnidadIIIService {
   public ReglaCramer AlgoritmoReglaCramer(ReglaCramer modelCramer);
   public Gauss AlgoritmoGauss(Gauss modelGauss);
       ArrayList<GaussJordan> AlgoritmoGaussJordan(GaussJordan gaussJordan);
       public ArrayList<Jacobi> AlgoritmoJacobi(Jacobi jacobi);
       public ArrayList<GaussSeidel> AlgoritmoGaussSeidel(GaussSeidel gaussseidel);
}
