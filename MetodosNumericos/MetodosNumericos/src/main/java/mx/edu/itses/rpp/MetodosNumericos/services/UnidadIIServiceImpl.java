package mx.edu.itses.rpp.MetodosNumericos.services;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.rpp.MetodosNumericos.domain.Biseccion;
import mx.edu.itses.rpp.MetodosNumericos.domain.PuntoFijo;
import mx.edu.itses.rpp.MetodosNumericos.domain.ReglaFalsa;
import mx.edu.itses.rpp.MetodosNumericos.domain.Secante;
import mx.edu.itses.rpp.MetodosNumericos.domain.SecanteModificado;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UnidadIIServiceImpl implements UnidadIIService {

    @Override
    public ArrayList<Biseccion> AlgoritmoBiseccion(Biseccion biseccion){
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
            
           
            renglon.setFXL_FXR(FXL * FXR);
            
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
        double Xi = puntofijo.getXi();
        double XiAnterior = Xi;
        double GXi, Ea = 100;

        if (puntofijo.getGx() == null || puntofijo.getGx().isEmpty()) {
            throw new IllegalArgumentException("La función g(x) es requerida para el método de punto fijo");
        }

        for (int i = 1; i <= puntofijo.getIteracionesMaximas(); i++) {
            GXi = Funciones.Ecuacion(puntofijo.getGx(), Xi);

            if (i > 1) {
                Ea = Math.abs((Xi - XiAnterior) / Xi) * 100;
            }

            PuntoFijo renglon = new PuntoFijo();
            renglon.setFX(puntofijo.getFX());
            renglon.setXi(Xi);
            renglon.setGXi(GXi);
            renglon.setEa(Ea);
            renglon.setGx(puntofijo.getGx());

            respuestaPuntoFijo.add(renglon);

            if ((i > 1 && Ea <= puntofijo.getEa()) || Math.abs(GXi - Xi) < 1e-10) {
                break;
            }

            XiAnterior = Xi;
            Xi = GXi;
        }

        return respuestaPuntoFijo;
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

           
            
            
            
            
            
            if (i != 1) {
                Ea = Math.abs((Xi1 - Xi) / Xi1) * 100;
            }

            Secante iter = new Secante();
            iter.setXi(Xi);
            iter.setXi_1(Xi_1);
            iter.setXi1(Xi1);
            iter.setFXi(F_Xi);
            iter.setF_Xi_1(F_Xi_1);
            iter.setEa(Ea);
            iter.setIteracionesMaximas(i);
            iter.setFX(secante.getFX());


         
            respuesta.add(iter);

          
            if (Ea <= secante.getEa()) {
                break;
            }
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


        Xi1 = Xi - (deltaXi * FXi) / denominador;

        if (i != 1) {
            Ea = Funciones.ErrorRelativo(Xi1, Xi);
        }

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

