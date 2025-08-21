package mx.edu.itses.rpp.MetodosNumericos.web;
import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.rpp.MetodosNumericos.domain.Biseccion;
import mx.edu.itses.rpp.MetodosNumericos.domain.GaussJordan;
import mx.edu.itses.rpp.MetodosNumericos.domain.GaussSeidel;
import mx.edu.itses.rpp.MetodosNumericos.domain.NewtonRaphson;
import mx.edu.itses.rpp.MetodosNumericos.domain.PuntoFijo;
import mx.edu.itses.rpp.MetodosNumericos.domain.ReglaFalsa;
import mx.edu.itses.rpp.MetodosNumericos.domain.Secante;
import mx.edu.itses.rpp.MetodosNumericos.domain.SecanteModificado;
import mx.edu.itses.rpp.MetodosNumericos.domain.jacobi;
import mx.edu.itses.rpp.MetodosNumericos.services.UnidadIIIService;
import mx.edu.itses.rpp.MetodosNumericos.services.UnidadIIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j

public class Unit2Controller {

    @Autowired
    private UnidadIIService serService;
    


@GetMapping("unit2/formbisection")
public String formBisection(Model model) {

        Biseccion bisection = new Biseccion();

        model.addAttribute("bisection", bisection);

        return "unit2/bisection/formbisection";
    }

@PostMapping("unit2/solvebisection")
public String solvebisection(Biseccion bisection, Model model) {

        //  double valorFX = Funciones.Ecuacion(bisection.getFX(), bisection.getXL());
      //  log.info("Valor de FX: " + bisection.getFX());
        var solveBisection = serService.AlgoritmoBiseccion(bisection);

        //log.info("Arreglo: " + solveBisection);
        model.addAttribute("solveBisection", solveBisection);

        return "unit2/bisection/solvebisection";
    }   
    
@GetMapping("unit2/formReglaFalsa")
public String formReglaFalsa(Model model) {

          ReglaFalsa reglafalsa = new ReglaFalsa();

        model.addAttribute("reglafalsa", reglafalsa);
        return "unit2/reglafalsa/formReglaFalsa";
    }
@PostMapping("unit2/solveReglaFalsa")
public String solveReglaFalsa(ReglaFalsa reglafalsa, Model model) {
    var solveReglaFalsa = serService.AlgoritmoReglaFalsa(reglafalsa);
    model.addAttribute("solveReglaFalsa", solveReglaFalsa);
    return "unit2/reglafalsa/solveReglaFalsa";
}



@GetMapping("unit2/formNewtonRaphson")
    public String formNewtonRaphson(Model model) {

          NewtonRaphson newtonraphson = new NewtonRaphson();

        model.addAttribute("newtonraphson", newtonraphson);
        return "unit2/newtonraphson/formNewtonRaphson";
    }
@PostMapping("unit2/solveNewtonRaphson")
public String solveNewtonRaphson(NewtonRaphson newtonraphson, Model model) {
    var solveNewtonRaphson = serService.AlgoritmoNewtonRaphson(newtonraphson);
    model.addAttribute("solveNewtonRaphson", solveNewtonRaphson);
    return "unit2/newtonraphson/solveNewtonRaphson";
}



@GetMapping("unit2/formPuntoFijo")
public String formPuntoFijo(Model model) {

          PuntoFijo puntofijo = new PuntoFijo();

        model.addAttribute("puntofijo", puntofijo);
        return "unit2/puntofijo/formPuntoFijo";
    }
@PostMapping("/unit2/solvePuntoFijo")
public String solvePuntoFijo(@ModelAttribute PuntoFijo puntofijo, Model model) {
    System.out.println("gx recibida: " + puntofijo.getGx()); // depuración
    var resultado = serService.AlgoritmoPuntoFijo(puntofijo);
    model.addAttribute("solvePuntoFijo", resultado);
    return "unit2/puntofijo/solvePuntoFijo"; // nombre del HTML de resultados
}
@GetMapping("unit2/formSecante")
    public String formSecante(Model model) {

          Secante secante = new Secante();

        model.addAttribute("secante", secante);
        return "unit2/secante/formSecante";
    }
@PostMapping("unit2/solveSecante")
public String solveSecante(Secante secante, Model model) {
    var solveSecante = serService.AlgoritmoSecante(secante);
    model.addAttribute("solveSecante", solveSecante);
    return "unit2/secante/solveSecante";
}


@GetMapping("unit2/formSecanteModificado")
    public String formSecanteModificado(Model model) {

          SecanteModificado secantemodificado = new SecanteModificado();

        model.addAttribute("secantemodificado", secantemodificado);
        return "unit2/secantemodificado/formSecanteModificado";
    }
@PostMapping("unit2/solveSecanteModificado")
public String solveSecanteModificado(SecanteModificado secantemodificado, Model model) {
    var solveSecanteModificado = serService.AlgoritmoSecanteModificado(secantemodificado);
    model.addAttribute("solveSecanteModificado", solveSecanteModificado);
    return "unit2/secantemodificado/solveSecanteModificado";
}

@GetMapping("/unit2/formgaussjordan")
public String formGaussJordan(Model model) {
    GaussJordan gaussjordan = new GaussJordan();
    model.addAttribute("gaussjordan", gaussjordan);
    return "/unit3/gaussjordan/formgaussjordan";
}

@PostMapping("/unit3/solvegaussjordan")
public String solveGaussJordan(GaussJordan gaussjordan, Model model) {
    var solveGaussJordan = serService.AlgoritmoGaussJordan(gaussjordan);

    log.info("Resultado Gauss-Jordan: " + solveGaussJordan);
    model.addAttribute("pasos", solveGaussJordan);

    return "/unit2/gaussjordan/solvegaussjordan";
}

@GetMapping("/unit2/formjacobi")
public String formJacobi(Model model) {
    jacobi jacobi = new jacobi();
    model.addAttribute("jacobi", jacobi);
    return "/unit3/jacobi/formjacobi";
}

@PostMapping("/unit2/solvejacobi")
public String solveJacobi(jacobi jacobi, Model model) {
    var solveJacobi = serService.AlgoritmoJacobi(jacobi);

    log.info("Resultado Jacobi: " + solveJacobi);
    model.addAttribute("pasos", solveJacobi);
    model.addAttribute("n", jacobi.getN());

    return "/unit2/jacobi/solvejacobi";
}

@GetMapping("/unit2/formseidel")
public String formSeidel(Model model) {
    GaussSeidel gaussSeidel = new GaussSeidel();
    model.addAttribute("gaussSeidel", gaussSeidel);
    return "/unit3/seidel/formseidel";
}

@PostMapping("/unit2/solveseidel")
public String solveSeidel(GaussSeidel gaussSeidel, Model model) {
    var solveSeidel = serService.AlgoritmoGaussSeidel(gaussSeidel);

    log.info("Resultado Gauss-Seidel: " + solveSeidel);
    model.addAttribute("pasos", solveSeidel);

    return "/unit2/seidel/solveseidel";
}
}

