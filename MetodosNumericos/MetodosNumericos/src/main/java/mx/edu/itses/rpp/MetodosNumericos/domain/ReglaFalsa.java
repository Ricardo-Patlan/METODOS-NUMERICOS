package mx.edu.itses.rpp.MetodosNumericos.domain;
import lombok.Data;
public class ReglaFalsa {
          private String FX;// funcion evaluar
    private double XL;
    private double XU;
    private double XR;
    private double FXL;
    private double FXU;
    private double FXR;
    private double Ea;
    private int IteracionesMaximas;
    private double FXL_FXR;

    public String getFX() {
        return FX;
    }

    public void setFX(String FX) {
        this.FX = FX;
    }

    public double getXL() {
        return XL;
    }

    public void setXL(double XL) {
        this.XL = XL;
    }

    public double getXU() {
        return XU;
    }

    public void setXU(double XU) {
        this.XU = XU;
    }

    public double getXR() {
        return XR;
    }

    public void setXR(double XR) {
        this.XR = XR;
    }

    public double getFXL() {
        return FXL;
    }

    public void setFXL(double FXL) {
        this.FXL = FXL;
    }

    public double getFXU() {
        return FXU;
    }

    public void setFXU(double FXU) {
        this.FXU = FXU;
    }

    public double getFXR() {
        return FXR;
    }

    public void setFXR(double FXR) {
        this.FXR = FXR;
    }

    public double getEa() {
        return Ea;
    }

    public void setEa(double Ea) {
        this.Ea = Ea;
    }

    public int getIteracionesMaximas() {
        return IteracionesMaximas;
    }

    public void setIteracionesMaximas(int IteracionesMaximas) {
        this.IteracionesMaximas = IteracionesMaximas;
    }

    public double getFXL_FXR() {
        return FXL_FXR;
    }


public void setFXL_FXR(double FXFR) {
    this.FXL_FXR = FXFR;  
}

    
    
}
