package mx.edu.itses.rpp.MetodosNumericos.services;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.rpp.MetodosNumericos.domain.Biseccion;
import mx.edu.itses.rpp.MetodosNumericos.domain.NewtonRaphson;
import mx.edu.itses.rpp.MetodosNumericos.domain.PuntoFijo;
import mx.edu.itses.rpp.MetodosNumericos.domain.ReglaFalsa;
import mx.edu.itses.rpp.MetodosNumericos.domain.Secante;
import mx.edu.itses.rpp.MetodosNumericos.domain.SecanteModificado;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UnidadIIServiceImpl implements UnidadIIService {

    @Override
    public ArrayList<Biseccion> AlgoritmoBiseccion(Biseccion biseccion) {
        ArrayList<Biseccion> respuesta = new ArrayList<>();
        double XL, XU, XRa, XRn, FXL, FXU, FXR, Ea;

        XL = biseccion.getXL();
        XU = biseccion.getXU();
        XRa = 0;
        Ea = 100;
        // Verificamos que en el intervalo definido haya un cambio de signo
        FXL = Funciones.Ecuacion(biseccion.getFX(), XL);
        FXU = Funciones.Ecuacion(biseccion.getFX(), XU);
        if (FXL * FXU < 0) {
            for (int i = 1; i <= biseccion.getIteracionesMaximas(); i++) {
                XRn = (XL + XU) / 2;
                FXL = Funciones.Ecuacion(biseccion.getFX(), XL);
                FXU = Funciones.Ecuacion(biseccion.getFX(), XU);
                FXR = Funciones.Ecuacion(biseccion.getFX(), XRn);
                if (i != 1) {
                    Ea = Funciones.ErrorRelativo(XRn, XRa);
                }
                Biseccion renglon = new Biseccion();
                renglon.setXL(XL);
                renglon.setXU(XU);
                renglon.setXR(XRn);
                renglon.setFXL(FXL);
                renglon.setFXU(FXU);
                renglon.setFXR(FXR);
                renglon.setEa(Ea);
                if (FXL * FXR < 0) {
                    XU = XRn;
                } else if (FXL * FXR > 0) {
                    XL = XRn;
                } else if (FXL * FXR == 0) {
                    break;
                }
                XRa = XRn;
                respuesta.add(renglon);
                if (Ea <= biseccion.getEa()) {
                    break;
                }
            }
        } else {
            Biseccion renglon = new Biseccion();
            //renglon.setIntervaloInvalido(true);
            respuesta.add(renglon);
        }

        return respuesta;
    }

    @Override

    public ArrayList<ReglaFalsa> AlgoritmoReglaFalsa(ReglaFalsa reglafalsa) {
        ArrayList<ReglaFalsa> respuestaReglaFalsa = new ArrayList<>();
        double XL, XU, XRa = 0, XRn, FXL, FXU, FXR, Ea = 100;
        XL = reglafalsa.getXL();
        XU = reglafalsa.getXU();

        FXL = Funciones.Ecuacion(reglafalsa.getFX(), XL);
        FXU = Funciones.Ecuacion(reglafalsa.getFX(), XU);

        if (FXL * FXU < 0) {
            for (int i = 1; i <= reglafalsa.getIteracionesMaximas(); i++) {
                // Fórmula de Regla Falsa
                XRn = XU - (FXU * (XL - XU)) / (FXL - FXU);
                FXR = Funciones.Ecuacion(reglafalsa.getFX(), XRn);

                if (i != 1) {
                    Ea = Funciones.ErrorRelativo(XRn, XRa);
                }

                ReglaFalsa renglon = new ReglaFalsa();
                renglon.setIteracionesMaximas(i);
                renglon.setXL(XL);
                renglon.setXU(XU);
                renglon.setXR(XRn);
                renglon.setFXL(FXL);
                renglon.setFXU(FXU);
                renglon.setFXR(FXR);
                renglon.setEa(Ea);

                // AGREGAR ESTA LÍNEA PARA CALCULAR EL PRODUCTO
                renglon.setProductoFXL_FXR(FXL * FXR);

                if (FXL * FXR < 0) {
                    XU = XRn;
                    FXU = FXR;
                } else {
                    XL = XRn;
                    FXL = FXR;
                }

                XRa = XRn;
                respuestaReglaFalsa.add(renglon);

                if (Ea <= reglafalsa.getEa()) {
                    break;
                }
            }
        } else {
            respuestaReglaFalsa.add(new ReglaFalsa());
        }
        return respuestaReglaFalsa;
    }

    @Override

    public ArrayList<PuntoFijo> AlgoritmoPuntoFijo(PuntoFijo puntofijo) {
        ArrayList<PuntoFijo> respuestaPuntoFijo = new ArrayList<>();
        double Xi, XiAnterior = 0, GXi, Ea = 100;

        // Obtener el valor inicial
        Xi = puntofijo.getXi();

        // Iteraciones del método de punto fijo
        for (int i = 1; i <= puntofijo.getIteracionesMaximas(); i++) {
            // Evaluar g(xi) - aquí necesitas la función g(x)
            GXi = Funciones.Ecuacion(puntofijo.getFX(), Xi);

            // Calcular error relativo (excepto en la primera iteración)
            if (i != 1) {
                Ea = Funciones.ErrorRelativo(GXi, XiAnterior);
            }

            PuntoFijo renglon = new PuntoFijo();
            renglon.setIteracionesMaximas(i);
            renglon.setXi(Xi);
            renglon.setGXi(GXi);
            renglon.setEa(Ea);

            respuestaPuntoFijo.add(renglon);

            // Verificar criterio de convergencia
            if (i > 1 && Ea <= puntofijo.getEa()) {  //
                break;
            }

            // Preparar para la siguiente iteración
            XiAnterior = Xi;
            Xi = GXi;  // Para punto fijo: xi+1 = g(xi)
        }

        return respuestaPuntoFijo;
    }

    public ArrayList<NewtonRaphson> AlgoritmoNewtonRaphson(NewtonRaphson newtonRaphson) {
        ArrayList<NewtonRaphson> respuestaNewtonRaphson = new ArrayList<>();
        double Xi, Xii = 0, Xia = 0, FXi, FXii, Ea = 100;

        Xi = newtonRaphson.getXi();

        // Verificar que la derivada no sea nula en el punto inicial
        FXii = Funciones.Ecuacion(newtonRaphson.getFXDerivada(), Xi);
        if (Math.abs(FXii) < 1e-10) {
            // Si la derivada es muy pequeña, retornar lista vacía o con error
            NewtonRaphson error = new NewtonRaphson();
            error.setIteracionesMaximas(0);
            error.setXi(Xi);
            error.setEa(-1); // Indicador de error
            respuestaNewtonRaphson.add(error);
            return respuestaNewtonRaphson;
        }

        for (int i = 1; i <= newtonRaphson.getIteracionesMaximas(); i++) {
            // Evaluar f(Xi)
            FXi = Funciones.Ecuacion(newtonRaphson.getFX(), Xi);

            // Evaluar f'(Xi)
            FXii = Funciones.Ecuacion(newtonRaphson.getFXDerivada(), Xi);

            // Verificar que la derivada no sea cero
            if (Math.abs(FXii) < 1e-10) {
                break; // Salir si la derivada es muy pequeña
            }

            // Fórmula de Newton-Raphson: Xii = Xi - f(Xi)/f'(Xi)
            Xii = Xi - (FXi / FXii);

            // Calcular error relativo (solo después de la primera iteración)
            if (i != 1) {
                Ea = Funciones.ErrorRelativo(Xii, Xia);
            }

            // Crear objeto para almacenar resultados de esta iteración
            NewtonRaphson renglon = new NewtonRaphson();
            renglon.getIteracionesMaximas();
            renglon.setXi(Xi);
            renglon.setFXi(FXi);
            renglon.setFXii(FXii);
            renglon.setXii(Xii);
            renglon.setEa(Ea);

            // Agregar a la lista de resultados
            respuestaNewtonRaphson.add(renglon);

            // Verificar criterio de convergencia
            if (i > 1 && Ea <= newtonRaphson.getEa()) {
                break;
            }

            // Actualizar valores para próxima iteración
            Xia = Xi;  // Guardar Xi anterior para cálculo de error
            Xi = Xii;  // Xi+1 se convierte en el nuevo Xi
        }

        return respuestaNewtonRaphson;
    }

   @Override
    public ArrayList<Secante> AlgoritmoSecante(Secante secante) {
        ArrayList<Secante> respuesta = new ArrayList<>();

        double Xi_1 = secante.getXi_1();
        double Xi = secante.getXi();   
        double Xi1;                     
        double F_Xi_1, F_Xi, Ea = 100;

        int maxIteraciones = secante.getIteracionesMaximas();

        for (int i = 1; i <= maxIteraciones; i++) {
            F_Xi_1 = Funciones.Ecuacion(secante.getFX(), Xi_1);
            F_Xi = Funciones.Ecuacion(secante.getFX(), Xi);


            
            if ((F_Xi_1 - F_Xi) == 0) {
                System.out.println("Denominador cercano a cero, deteniendo iteración.");
                break;
            }

            Xi1 = Xi - (F_Xi * (Xi_1 - Xi)) / (F_Xi_1 - F_Xi);

            // Calcular error relativo
            if (i != 1) {
                Ea = Math.abs((Xi1 - Xi) / Xi1) * 100;
            }

            // Crear iteración
            Secante iter = new Secante();
            iter.setXi(Xi);
            iter.setXi_1(Xi_1);
            iter.setXi1(Xi1);
            iter.setFXi(F_Xi);
            iter.setF_Xi_1(F_Xi_1);
            iter.setEa(Ea);
            iter.setIteracionesMaximas(i);
            iter.setFX(secante.getFX());


            // Guardar resultados de esta iteración
            respuesta.add(iter);

            // Verificar si error deseado se alcanzó
            if (Ea <= secante.getEa()) {
                break;
            }

            // Desplazar valores para siguiente iteración
            Xi_1 = Xi;
            Xi = Xi1;
        }

        return respuesta;
    }


@Override
public ArrayList<SecanteModificado> AlgoritmoSecanteModificado(SecanteModificado secantemodificado) {
    ArrayList<SecanteModificado> respuesta = new ArrayList<>();

    double Xi = secantemodificado.getXi();
    double Xi1;
    double Ea = 100;
    int maxIteraciones = secantemodificado.getIteracionesMaximas();
    double sigma = secantemodificado.getSigma();

    for (int i = 1; i <= maxIteraciones; i++) {
        double deltaXi = sigma * Xi;
        double FXi = Funciones.Ecuacion(secantemodificado.getFX(), Xi);
        double FXiSigma = Funciones.Ecuacion(secantemodificado.getFX(), Xi + deltaXi);

        double denominador = FXiSigma - FXi;

        // 🔍 Imprimir depuración completa
        System.out.printf(
            "Iteración %d:\n  Xi=%.6f\n  FXi=%.6f\n  FXi+deltaXi=%.6f\n  DeltaXi=%.6f\n  Denominador=%.6f\n",
            i, Xi, FXi, FXiSigma, deltaXi, denominador
        );

        if (Double.isNaN(FXi) || Double.isNaN(FXiSigma)) {
            System.out.println(" Se detectó NaN en la evaluación de la función. Revisa la expresión ingresada.");
            break;
        }

        if (Math.abs(denominador) < 1e-8) {
            System.out.println(" Denominador muy pequeño, deteniendo para evitar división por cero.");
            break;
        }

        Xi1 = Xi - (deltaXi * FXi) / denominador;

        if (i != 1) {
            Ea = Funciones.ErrorRelativo(Xi1, Xi);
        }

        System.out.printf("  Xi+1=%.6f\n  Error=%.6f%%\n\n", Xi1, Ea);

        // Guardar resultados
        SecanteModificado iter = new SecanteModificado();
        iter.setXi(Xi);
        iter.setXi1(Xi1);
        iter.setFXi(FXi);
        iter.setFXiSigma(FXiSigma);
        iter.setEa(Ea);
        iter.setIteracionesMaximas(i);
        iter.setFX(secantemodificado.getFX());
        iter.setSigma(sigma);

        respuesta.add(iter);

        if (Ea <= secantemodificado.getEa()) {
            break;
        }

        Xi = Xi1;
    }

    return respuesta;
}

}
