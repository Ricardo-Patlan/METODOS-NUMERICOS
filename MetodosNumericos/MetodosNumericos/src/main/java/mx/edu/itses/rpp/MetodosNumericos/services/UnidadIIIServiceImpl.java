package mx.edu.itses.rpp.MetodosNumericos.services;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.rpp.MetodosNumericos.domain.Gauss;
import mx.edu.itses.rpp.MetodosNumericos.domain.GaussJordan;
import mx.edu.itses.rpp.MetodosNumericos.domain.GaussSeidel;
import mx.edu.itses.rpp.MetodosNumericos.domain.ReglaCramer;
import mx.edu.itses.rpp.MetodosNumericos.domain.Jacobi;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UnidadIIIServiceImpl implements UnidadIIIService {
    
     // almacenamo los determianntes d la matris
        ArrayList<Double> determinantes = new ArrayList<>();
        ArrayList<Double> vectorX = new ArrayList();

        //tamano del sistema lineal
        switch (modelCramer.getMN()) {
            case 2 -> {
                ArrayList<Double> A = modelCramer.getMatrizA();
                ArrayList<Double> b = modelCramer.getVectorB();

                double[][] MatrizA = {
                    {A.get(0), A.get(1)},
                    {A.get(2), A.get(3)}

                };

                determinantes.add(Det2(MatrizA));
                log.info("Det A " + determinantes.get(0));

                //Calculamos determinates para X1
                double[][] MatrizX1 = {
                    {b.get(0), A.get(1)},
                    {b.get(1), A.get(3)}

                };

                determinantes.add(Det2(MatrizX1));
                log.info("Matriz X1: " + determinantes.get(1));

                //Calculamos determinates para X2
                double[][] MatrizX2 = {
                    {A.get(0), b.get(0)},
                    {A.get(2), b.get(1)}

                };
                determinantes.add(Det2(MatrizX2));
                log.info("Matriz X2: " + determinantes.get(2));

                vectorX.add(determinantes.get(1) / determinantes.get(0));
                vectorX.add(determinantes.get(2) / determinantes.get(0));

            }

            case 3 -> {

                ArrayList<Double> A = modelCramer.getMatrizA();
                ArrayList<Double> b = modelCramer.getVectorB();

//  matriz A 3x3
                double[][] MatrizA = new double[modelCramer.getMN()][modelCramer.getMN()];//thimelif ech
                int index = 0;
                for (int i = 0; i < modelCramer.getMN(); i++) {// renglon
                    for (int j = 0; j < modelCramer.getMN(); j++) {// coluemna
                        MatrizA[i][j] = A.get(index);
                        index++;
                    }
                }
                /*    double[][] MatrizA = {
                    {A.get(0), A.get(1), A.get(2)},
                    {A.get(3), A.get(4), A.get(5)},
                    {A.get(6), A.get(7), A.get(8)}
                };*/

// Determinante de A
                determinantes.add(Det3(MatrizA));
                log.info("Det A: " + determinantes.get(0));

// Matriz para X1 
                double[][] MatrizX1 = {
                    {b.get(0), A.get(1), A.get(2)},
                    {b.get(1), A.get(4), A.get(5)},
                    {b.get(2), A.get(7), A.get(8)}
                };
                determinantes.add(Det3(MatrizX1));
                log.info("Matriz X1: " + determinantes.get(1));

// Matriz para X2 
                double[][] MatrizX2 = {
                    {A.get(0), b.get(0), A.get(2)},
                    {A.get(3), b.get(1), A.get(5)},
                    {A.get(6), b.get(2), A.get(8)}
                };
                determinantes.add(Det3(MatrizX2));
                log.info("Matriz X2: " + determinantes.get(2));

// Matriz para X3
                double[][] MatrizX3 = {
                    {A.get(0), A.get(1), b.get(0)},
                    {A.get(3), A.get(4), b.get(1)},
                    {A.get(6), A.get(7), b.get(2)}
                };
                determinantes.add(Det3(MatrizX3));
                log.info("Matriz X3: " + determinantes.get(3));

// Calculamos el vector X
                vectorX.add(determinantes.get(1) / determinantes.get(0));
                vectorX.add(determinantes.get(2) / determinantes.get(0));
                vectorX.add(determinantes.get(3) / determinantes.get(0));
            }

        }
        modelCramer.setVectorX(vectorX);
        modelCramer.setDeterminantes(determinantes);
        return modelCramer;
}
    

    private double Det2(double[][] A) {

        return A[0][0] * A[1][1] - A[0][1] * A[1][0];

    }

    private double Det3(double[][] m) {
        return m[0][0] * (m[1][1] * m[2][2] - m[1][2] * m[2][1])
                - m[0][1] * (m[1][0] * m[2][2] - m[1][2] * m[2][0])
                + m[0][2] * (m[1][0] * m[2][1] - m[1][1] * m[2][0]);
    }

    @Override
    public ReglaCramer AlgoritmoReglaCramer(ReglaCramer modelCramer) {
        // almacenamo los determianntes d la matris
        ArrayList<Double> determinantes = new ArrayList<>();
        ArrayList<Double> vectorX = new ArrayList();

        //tamano del sistema lineal
        switch (modelCramer.getMN()) {
            case 2 -> {
                ArrayList<Double> A = modelCramer.getMatrizA();
                ArrayList<Double> b = modelCramer.getVectorB();

                double[][] MatrizA = {
                    {A.get(0), A.get(1)},
                    {A.get(2), A.get(3)}

                };

                determinantes.add(Det2(MatrizA));
                log.info("Det A " + determinantes.get(0));

                //Calculamos determinates para X1
                double[][] MatrizX1 = {
                    {b.get(0), A.get(1)},
                    {b.get(1), A.get(3)}

                };

                determinantes.add(Det2(MatrizX1));
                log.info("Matriz X1: " + determinantes.get(1));

                //Calculamos determinates para X2
                double[][] MatrizX2 = {
                    {A.get(0), b.get(0)},
                    {A.get(2), b.get(1)}

                };
                determinantes.add(Det2(MatrizX2));
                log.info("Matriz X2: " + determinantes.get(2));

                vectorX.add(determinantes.get(1) / determinantes.get(0));
                vectorX.add(determinantes.get(2) / determinantes.get(0));

            }

            case 3 -> {

                ArrayList<Double> A = modelCramer.getMatrizA();
                ArrayList<Double> b = modelCramer.getVectorB();

//  matriz A 3x3
                double[][] MatrizA = new double[modelCramer.getMN()][modelCramer.getMN()];//thimelif ech
                int index = 0;
                for (int i = 0; i < modelCramer.getMN(); i++) {// renglon
                    for (int j = 0; j < modelCramer.getMN(); j++) {// coluemna
                        MatrizA[i][j] = A.get(index);
                        index++;
                    }
                }
                /*    double[][] MatrizA = {
                    {A.get(0), A.get(1), A.get(2)},
                    {A.get(3), A.get(4), A.get(5)},
                    {A.get(6), A.get(7), A.get(8)}
                };*/

// Determinante de A
                determinantes.add(Det3(MatrizA));
                log.info("Det A: " + determinantes.get(0));

// Matriz para X1 
                double[][] MatrizX1 = {
                    {b.get(0), A.get(1), A.get(2)},
                    {b.get(1), A.get(4), A.get(5)},
                    {b.get(2), A.get(7), A.get(8)}
                };
                determinantes.add(Det3(MatrizX1));
                log.info("Matriz X1: " + determinantes.get(1));

// Matriz para X2 
                double[][] MatrizX2 = {
                    {A.get(0), b.get(0), A.get(2)},
                    {A.get(3), b.get(1), A.get(5)},
                    {A.get(6), b.get(2), A.get(8)}
                };
                determinantes.add(Det3(MatrizX2));
                log.info("Matriz X2: " + determinantes.get(2));

// Matriz para X3
                double[][] MatrizX3 = {
                    {A.get(0), A.get(1), b.get(0)},
                    {A.get(3), A.get(4), b.get(1)},
                    {A.get(6), A.get(7), b.get(2)}
                };
                determinantes.add(Det3(MatrizX3));
                log.info("Matriz X3: " + determinantes.get(3));

// Calculamos el vector X
                vectorX.add(determinantes.get(1) / determinantes.get(0));
                vectorX.add(determinantes.get(2) / determinantes.get(0));
                vectorX.add(determinantes.get(3) / determinantes.get(0));
            }

        }
        modelCramer.setVectorX(vectorX);
        modelCramer.setDeterminantes(determinantes);
        return modelCramer;
    }

    @Override
    public Gauss AlgoritmoGauss(Gauss modelGauss) {
      ArrayList<String> pasos = new ArrayList<>();
        ArrayList<Double> vectorX = new ArrayList<>();
        
        // Convertir ArrayList a matriz bidimensional
        ArrayList<Double> A = modelGauss.getMatrizA();
        ArrayList<Double> b = modelGauss.getVectorB();
        int n = modelGauss.getMN();
        
        double[][] matriz = new double[n][n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = A.get(index);
                index++;
            }
        }
        
        double[] resultados = new double[n];
        for (int i = 0; i < n; i++) {
            resultados[i] = b.get(i);
        }
        
        pasos.add("Sistema inicial:");
        pasos.add(formatearSistemaGauss(matriz, resultados, n));
        
        try {
            // Fase de eliminación hacia adelante
            for (int i = 0; i < n - 1; i++) {
                // Buscar el pivote máximo
                int filaPivote = buscarPivoteMaximo(matriz, i, n);
                
                // Intercambiar filas si es necesario
                if (filaPivote != i) {
                    intercambiarFilas(matriz, resultados, i, filaPivote);
                    pasos.add("Intercambio de fila " + (i + 1) + " con fila " + (filaPivote + 1));
                    pasos.add(formatearSistemaGauss(matriz, resultados, n));
                }
                
                // Verificar si el pivote es cero
                if (Math.abs(matriz[i][i]) < 1e-10) {
                    throw new ArithmeticException("El sistema no tiene solución única");
                }
                
                // Eliminación hacia adelante
                for (int j = i + 1; j < n; j++) {
                    if (Math.abs(matriz[j][i]) > 1e-10) {
                        double factor = matriz[j][i] / matriz[i][i];
                        
                        pasos.add("Eliminando x" + (i + 1) + " de ecuación " + (j + 1) + 
                                " (Factor: " + String.format("%.4f", factor) + ")");
                        
                        for (int k = i; k < n; k++) {
                            matriz[j][k] -= factor * matriz[i][k];
                        }
                        resultados[j] -= factor * resultados[i];
                        
                        pasos.add(formatearSistemaGauss(matriz, resultados, n));
                    }
                }
            }
            
            // Sustitución hacia atrás
            double[] solucion = new double[n];
            pasos.add("Sustitución hacia atrás:");
            
            for (int i = n - 1; i >= 0; i--) {
                double suma = 0;
                for (int j = i + 1; j < n; j++) {
                    suma += matriz[i][j] * solucion[j];
                }
                solucion[i] = (resultados[i] - suma) / matriz[i][i];
                
                pasos.add("x" + (i + 1) + " = " + String.format("%.6f", solucion[i]));
                vectorX.add(solucion[i]);
            }
            
            log.info("Solución Gauss: " + vectorX);
            
        } catch (Exception e) {
            log.error("Error en Gauss: " + e.getMessage());
            pasos.add("Error: " + e.getMessage());
            throw new RuntimeException("Error en eliminación Gaussiana: " + e.getMessage());
        }
        
        // Establecer resultados en el modelo
        modelGauss.setVectorX(vectorX);
        return modelGauss;
    }


     private int buscarPivoteMaximo(double[][] matriz, int columna, int n) {
        int maxFila = columna;
        for (int i = columna + 1; i < n; i++) {
            if (Math.abs(matriz[i][columna]) > Math.abs(matriz[maxFila][columna])) {
                maxFila = i;
            }
        }
        return maxFila;
    }

     private void intercambiarFilas(double[][] matriz, double[] resultados, int fila1, int fila2) {
        double[] tempFila = matriz[fila1];
        matriz[fila1] = matriz[fila2];
        matriz[fila2] = tempFila;
        
        double tempResultado = resultados[fila1];
        resultados[fila1] = resultados[fila2];
        resultados[fila2] = tempResultado;
    }

     private String formatearSistemaGauss(double[][] matriz, double[] resultados, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0 && matriz[i][j] >= 0) sb.append(" + ");
                else if (j > 0) sb.append(" ");
                
                sb.append(String.format("%.4f", matriz[i][j]));
                sb.append("x").append(j + 1);
            }
            sb.append(" = ").append(String.format("%.4f", resultados[i])).append("\n");
        }
        return sb.toString();
    }


    @Override
    public ArrayList<GaussJordan> AlgoritmoGaussJordan(GaussJordan gaussJordan) {
  ArrayList<Double> vectorX = new ArrayList<>();
        
        // Convertir ArrayList a matriz bidimensional
        ArrayList<Double> A = modelGaussJordan.getMatrizA();
        ArrayList<Double> b = modelGaussJordan.getVectorB();
        int n = modelGaussJordan.getMN();
        
        double[][] matriz = new double[n][n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            double suma = 0;
            for (int j = 0; j < n; j++) {
                matriz[i][j] = A.get(index);
                index++;
            }
}

        }
        
        double[] resultados = new double[n];
        for (int i = 0; i < n; i++) {
            resultados[i] = b.get(i);
        }
        
        try {
            // Fase de eliminación hacia adelante (igual que Gauss)
            for (int i = 0; i < n; i++) {
                // Buscar el pivote máximo
                int filaPivote = buscarPivoteMaximoGJ(matriz, i, n);
                
                // Intercambiar filas si es necesario
                if (filaPivote != i) {
                    intercambiarFilasGJ(matriz, resultados, i, filaPivote);
                }
                
                // Verificar si el pivote es cero
                if (Math.abs(matriz[i][i]) < 1e-10) {
                    throw new ArithmeticException("El sistema no tiene solución única");
                }
                
                // Normalizar la fila pivote (hacer el pivote = 1)
                double pivote = matriz[i][i];
                
                for (int j = 0; j < n; j++) {
                    matriz[i][j] /= pivote;
                }
                resultados[i] /= pivote;
                
}              
                // GAUSS-JORDAN: Eliminación hacia adelante Y hacia atrás
                for (int j = 0; j < n; j++) {
                    if (j != i && Math.abs(matriz[j][i]) > 1e-10) {
                        double factor = matriz[j][i];
                        
                        
                        for (int k = 0; k < n; k++) {
                            matriz[j][k] -= factor * matriz[i][k];
                        }
                        resultados[j] -= factor * resultados[i];
                        
                    }
                }
            }
            
            // En Gauss-Jordan, la solución está directamente en el vector resultados
            for (int i = 0; i < n; i++) {
                vectorX.add(resultados[i]);
            }
            
            log.info("Solución Gauss-Jordan: " + vectorX);
            
        } catch (Exception e) {
            log.error("Error en Gauss-Jordan: " + e.getMessage());
            throw new RuntimeException("Error en eliminación Gauss-Jordan: " + e.getMessage());
        }
        
        // Establecer resultados en el modelo
        modelGaussJordan.setVectorX(vectorX);
        return modelGaussJordan;
    }

    private int buscarPivoteMaximoGJ(double[][] matriz, int columna, int n) {
        int maxFila = columna;
        for (int i = columna + 1; i < n; i++) {
            if (Math.abs(matriz[i][columna]) > Math.abs(matriz[maxFila][columna])) {
                maxFila = i;
            }
        }
        return maxFila;
    }
    private void intercambiarFilasGJ(double[][] matriz, double[] resultados, int fila1, int fila2) {
        double[] tempFila = matriz[fila1];
        matriz[fila1] = matriz[fila2];
        matriz[fila2] = tempFila;
        
        double tempResultado = resultados[fila1];
        resultados[fila1] = resultados[fila2];
        resultados[fila2] = tempResultado;
    }
    private String formatearSistemaGaussJordan(double[][] matriz, double[] resultados, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0 && matriz[i][j] >= 0) sb.append(" + ");
                else if (j > 0) sb.append(" ");
                
                sb.append(String.format("%.4f", matriz[i][j]));
                sb.append("x").append(j + 1);
            }
            sb.append(" = ").append(String.format("%.4f", resultados[i])).append("\n");
        }
        return sb.toString();
    }

    @Override
    public ArrayList<Jacobi> AlgoritmoJacobi(Jacobi jacobi) {
 ArrayList<String> pasos = new ArrayList<>();
        ArrayList<Double> vectorX = new ArrayList<>();
        
        // Convertir ArrayList a matriz bidimensional
        ArrayList<Double> A = modelJacobi.getMatrizA();
        ArrayList<Double> b = modelJacobi.getVectorB();
        ArrayList<Double> x0 = modelJacobi.getVectorInicial();
        int n = modelJacobi.getMN();
        
        double[][] matriz = new double[n][n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = A.get(index);
                index++;
            }
        }
        
        double[] resultados = new double[n];
        for (int i = 0; i < n; i++) {
            resultados[i] = b.get(i);
        }
        
        // Vector inicial
        double[] xAnterior = new double[n];
        double[] xNuevo = new double[n];
        
        if (x0.size() == n) {
            for (int i = 0; i < n; i++) {
                xAnterior[i] = x0.get(i);
            }
        } else {
            // Si no se proporciona vector inicial, usar ceros
            for (int i = 0; i < n; i++) {
                xAnterior[i] = 0.0;
            }
        }
        
        pasos.add("=== MÉTODO DE JACOBI ===");
        pasos.add("Sistema original:");
        pasos.add(formatearSistemaJacobi(matriz, resultados, n));
        pasos.add("Vector inicial x⁰: " + formatearVector(xAnterior));
        pasos.add("Tolerancia: " + modelJacobi.getTolerancia());
        pasos.add("Máximo de iteraciones: " + modelJacobi.getMaxIteraciones());
        pasos.add("");
        
        try {
            // Verificar dominancia diagonal
            if (!verificarDominanciaDiagonal(matriz, n)) {
                pasos.add("⚠️ ADVERTENCIA: La matriz no es diagonalmente dominante.");
                pasos.add("El método puede no converger.");
                pasos.add("");
            }
            
            boolean convergio = false;
            int iteracion = 0;
            double error = 0;
            
            while (iteracion < modelJacobi.getMaxIteraciones() && !convergio) {
                iteracion++;
                
                // Fórmula de Jacobi: xi^(k+1) = (bi - Σ(aij * xj^k)) / aii
                for (int i = 0; i < n; i++) {
                    double suma = 0;
                    for (int j = 0; j < n; j++) {
                        if (i != j) {
                            suma += matriz[i][j] * xAnterior[j];
                        }
                    }
                    xNuevo[i] = (resultados[i] - suma) / matriz[i][i];
                }
                
                // Calcular error (norma infinita)
                error = 0;
                for (int i = 0; i < n; i++) {
                    double errorLocal = Math.abs(xNuevo[i] - xAnterior[i]);
                    if (errorLocal > error) {
                        error = errorLocal;
                    }
                }
                
                // Mostrar iteración
                pasos.add("Iteración " + iteracion + ":");
                pasos.add("x^" + iteracion + " = " + formatearVector(xNuevo));
                pasos.add("Error = " + String.format("%.8f", error));
                
                // Verificar convergencia
                if (error < modelJacobi.getTolerancia()) {
                    convergio = true;
                    pasos.add("✅ CONVERGENCIA ALCANZADA");
                }
                
                pasos.add("");
                
                // Copiar xNuevo a xAnterior para la siguiente iteración
                System.arraycopy(xNuevo, 0, xAnterior, 0, n);
            }
            
            if (!convergio) {
                pasos.add("❌ NO CONVERGIÓ en " + modelJacobi.getMaxIteraciones() + " iteraciones");
                pasos.add("Error final: " + String.format("%.8f", error));
            }
            
            // Guardar resultados
            for (int i = 0; i < n; i++) {
                vectorX.add(xNuevo[i]);
            }
            
            modelJacobi.setIteracionesRealizadas(iteracion);
            modelJacobi.setConvergio(convergio);
            
            
            log.info("Solución Jacobi: " + vectorX);
            log.info("Convergió: " + convergio + ", Iteraciones: " + iteracion + ", Error: " + error);
            
        } catch (Exception e) {
            log.error("Error en Jacobi: " + e.getMessage());
            pasos.add("Error: " + e.getMessage());
            throw new RuntimeException("Error en método de Jacobi: " + e.getMessage());
        }
        
        // Establecer resultados en el modelo
        modelJacobi.setVectorX(vectorX);
        modelJacobi.setPasos(pasos);
        return modelJacobi;
    }

    private boolean verificarDominanciaDiagonal(double[][] matriz, int n) {
        for (int i = 0; i < n; i++) {
            double diagonal = Math.abs(matriz[i][i]);
            double suma = 0;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    suma += Math.abs(matriz[i][j]);
                }
            }
            if (diagonal <= suma) {
                return false;
            }
        }
        return true;
    }
    private String formatearSistemaJacobi(double[][] matriz, double[] resultados, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0 && matriz[i][j] >= 0) sb.append(" + ");
                else if (j > 0) sb.append(" ");
                
                sb.append(String.format("%.4f", matriz[i][j]));
                sb.append("x").append(j + 1);
            }
            sb.append(" = ").append(String.format("%.4f", resultados[i])).append("\n");
        }
        return sb.toString();
    }
    private String formatearVector(double[] vector) {
        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < vector.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(String.format("%.6f", vector[i]));
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public ArrayList<GaussSeidel> AlgoritmoGaussSeidel(GaussSeidel gaussseidel) {
 ArrayList<String> pasos = new ArrayList<>();
        ArrayList<Double> vectorX = new ArrayList<>();
        
        ArrayList<Double> A = modelGaussSeidel.getMatrizA();
        ArrayList<Double> b = modelGaussSeidel.getVectorB();
        ArrayList<Double> x0 = modelGaussSeidel.getVectorInicial();
        int n = modelGaussSeidel.getMN();
        
        // Convertir a matriz
        double[][] matriz = new double[n][n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = A.get(index++);
            }
        }
        
        double[] resultados = new double[n];
        for (int i = 0; i < n; i++) {
            resultados[i] = b.get(i);
        }
        
        // Vector inicial
        double[] xAnterior = new double[n];
        double[] xNuevo = new double[n];
        
        for (int i = 0; i < n; i++) {
            xAnterior[i] = (i < x0.size()) ? x0.get(i) : 0.0;
            xNuevo[i] = xAnterior[i];
        }
        
        pasos.add("=== MÉTODO DE GAUSS-SEIDEL ===");
        pasos.add("Sistema original:");
        pasos.add(formatearSistemaGS(matriz, resultados, n));
        pasos.add("Vector inicial x⁰: " + formatVectorGS(xAnterior));
        pasos.add("Tolerancia: " + modelGaussSeidel.getTolerancia());
        pasos.add("Máximo de iteraciones: " + modelGaussSeidel.getMaxIteraciones());
        pasos.add("");
        
        try {
            boolean convergio = false;
            int iteracion = 0;
            double error = 0;
            
            while (iteracion < modelGaussSeidel.getMaxIteraciones() && !convergio) {
                iteracion++;
                
                // GAUSS-SEIDEL: Usa valores NUEVOS ya calculados en la misma iteración
                for (int i = 0; i < n; i++) {
                    double suma = 0;
                    for (int j = 0; j < n; j++) {
                        if (i != j) {
                            suma += matriz[i][j] * xNuevo[j]; // ¡Usa valores NUEVOS!
                        }
                    }
                    xNuevo[i] = (resultados[i] - suma) / matriz[i][i];
                }
                
                // Calcular error (norma infinita)
                error = 0;
                for (int i = 0; i < n; i++) {
                    double errorLocal = Math.abs(xNuevo[i] - xAnterior[i]);
                    if (errorLocal > error) {
                        error = errorLocal;
                    }
                }
                
                // Mostrar iteración
                pasos.add("Iteración " + iteracion + ":");
                pasos.add("x^" + iteracion + " = " + formatVectorGS(xNuevo));
                pasos.add("Error = " + String.format("%.8f", error));
                
                // Verificar convergencia
                if (error < modelGaussSeidel.getTolerancia()) {
                    convergio = true;
                    pasos.add("✅ CONVERGENCIA ALCANZADA");
                }
                
                pasos.add("");
                
                // Copiar xNuevo a xAnterior para la siguiente iteración
                System.arraycopy(xNuevo, 0, xAnterior, 0, n);
            }
            
            if (!convergio) {
                pasos.add("❌ NO CONVERGIÓ en " + modelGaussSeidel.getMaxIteraciones() + " iteraciones");
                pasos.add("Error final: " + String.format("%.8f", error));
            }
            
            // Guardar resultados
            for (int i = 0; i < n; i++) {
                vectorX.add(xNuevo[i]);
            }
            
            modelGaussSeidel.setIteracionesRealizadas(iteracion);
            modelGaussSeidel.setConvergio(convergio);
            modelGaussSeidel.setVectorX(vectorX);
            modelGaussSeidel.setPasos(pasos);
            
            log.info("Solución Gauss-Seidel: " + vectorX);
            log.info("Convergió: " + convergio + ", Iteraciones: " + iteracion + ", Error: " + error);
            
        } catch (Exception e) {
            log.error("Error en Gauss-Seidel: " + e.getMessage());
            pasos.add("Error: " + e.getMessage());
            throw new RuntimeException("Error en método de Gauss-Seidel: " + e.getMessage());
        }
        
        return modelGaussSeidel;

    }

    private String formatearSistemaGS(double[][] matriz, double[] resultados, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0 && matriz[i][j] >= 0) sb.append(" + ");
                else if (j > 0) sb.append(" ");
                
                sb.append(String.format("%.4f", matriz[i][j]));
                sb.append("x").append(j + 1);
            }
            sb.append(" = ").append(String.format("%.4f", resultados[i])).append("\n");
        }
        return sb.toString();
    }
    private String formatVectorGS(double[] vector) {
        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < vector.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(String.format("%.6f", vector[i]));
        }
        sb.append(")");
        return sb.toString();
    }
    
}