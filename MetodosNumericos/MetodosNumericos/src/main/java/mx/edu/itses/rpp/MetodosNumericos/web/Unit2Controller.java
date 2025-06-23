package mx.edu.itses.rpp.MetodosNumericos.web;
import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.rpp.MetodosNumericos.domain.Biseccion;
import mx.edu.itses.rpp.MetodosNumericos.domain.PuntoFijo;
import mx.edu.itses.rpp.MetodosNumericos.domain.ReglaFalsa;
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
    private UnidadIIService bisectionservice;
    @Autowired
      private UnidadIIService reglafalsaService;
     @Autowired
      private UnidadIIService puntofijoService;


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
        var solveBisection = bisectionservice.AlgoritmoBiseccion(bisection);

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
    var solveReglaFalsa = reglafalsaService.AlgoritmoReglaFalsa(reglafalsa);
    model.addAttribute("solveReglaFalsa", solveReglaFalsa);
    return "unit2/reglafalsa/solveReglaFalsa";
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
    var resultado = puntofijoService.AlgoritmoPuntoFijo(puntofijo);
    model.addAttribute("solvePuntoFijo", resultado);
    return "unit2/puntofijo/solvePuntoFijo"; // nombre del HTML de resultados
}


}
