package mx.edu.itses.rpp.MetodosNumericos.web;

import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.rpp.MetodosNumericos.domain.*;
import mx.edu.itses.rpp.MetodosNumericos.services.UnidadIIService;
import mx.edu.itses.rpp.MetodosNumericos.services.UnidadIIIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/unit2")
public class Unit2Controller {

    @Autowired
    private UnidadIIService unidadIIsrv;

    // Para Cramer usas servicio de la Unidad III (tu lógica actual)
    @Autowired
    private UnidadIIIService unidadIIIsrv;

    // --- Bisección ---
    @GetMapping("/bisection/formbisection")
    public String formBisection(Model model) {
        model.addAttribute("bisection", new Biseccion());
        return "unit2/bisection/formbisection";
    }

    @PostMapping("/bisection/solvebisection")
    public String solveBisection(Biseccion bisection, Model model) {
        var solve = unidadIIsrv.AlgoritmoBiseccion(bisection);
        model.addAttribute("solveBisection", solve);
        return "unit2/bisection/solvebisection";
    }

    // --- Regla Falsa ---
    @GetMapping("/reglafalsa/formReglaFalsa")
    public String formReglaFalsa(Model model) {
        model.addAttribute("reglafalsa", new ReglaFalsa());
        return "unit2/reglafalsa/formReglaFalsa";
    }

    @PostMapping("/reglafalsa/solveReglaFalsa")
    public String solveReglaFalsa(ReglaFalsa reglafalsa, Model model) {
        var solve = unidadIIsrv.AlgoritmoReglaFalsa(reglafalsa);
        model.addAttribute("solveReglaFalsa", solve);
        return "unit2/reglafalsa/solveReglaFalsa";
    }

    // --- Newton-Raphson ---
    @GetMapping("/newtonraphson/formNewtonRaphson")
    public String formNewtonRaphson(Model model) {
        model.addAttribute("newtonraphson", new NewtonRaphson());
        return "unit2/newtonraphson/formNewtonRaphson";
    }

    @PostMapping("/newtonraphson/solveNewtonRaphson")
    public String solveNewtonRaphson(NewtonRaphson nr, Model model) {
        var solve = unidadIIsrv.AlgoritmoNewtonRaphson(nr);
        model.addAttribute("solveNewtonRaphson", solve);
        return "unit2/newtonraphson/solveNewtonRaphson";
    }

    // --- Punto Fijo ---
    @GetMapping("/puntofijo/formPuntoFijo")
    public String formPuntoFijo(Model model) {
        model.addAttribute("puntofijo", new PuntoFijo());
        return "unit2/puntofijo/formPuntoFijo";
    }

    @PostMapping("/puntofijo/solvePuntoFijo")
    public String solvePuntoFijo(@ModelAttribute PuntoFijo pf, Model model) {
        var solve = unidadIIsrv.AlgoritmoPuntoFijo(pf);
        model.addAttribute("solvePuntoFijo", solve);
        return "unit2/puntofijo/solvePuntoFijo";
    }

    // --- Secante ---
    @GetMapping("/secante/formSecante")
    public String formSecante(Model model) {
        model.addAttribute("secante", new Secante());
        return "unit2/secante/formSecante";
    }

    @PostMapping("/secante/solveSecante")
    public String solveSecante(Secante secante, Model model) {
        var solve = unidadIIsrv.AlgoritmoSecante(secante);
        model.addAttribute("solveSecante", solve);
        return "unit2/secante/solveSecante";
    }

    // --- Secante Modificado ---
    @GetMapping("/secantemodificado/formSecanteModificado")
    public String formSecanteModificado(Model model) {
        model.addAttribute("secantemodificado", new SecanteModificado());
        return "unit2/secantemodificado/formSecanteModificado";
    }

    @PostMapping("/secantemodificado/solveSecanteModificado")
    public String solveSecanteModificado(SecanteModificado sm, Model model) {
        var solve = unidadIIsrv.AlgoritmoSecanteModificado(sm);
        model.addAttribute("solveSecanteModificado", solve);
        return "unit2/secantemodificado/solveSecanteModificado";
    }

    // --- Regla de Cramer (según tu vista en unit2/reglacramer) ---
    @GetMapping("/reglacramer/formcramer")
    public String formCramer(Model model) {
        model.addAttribute("modelCramer", new ReglaCramer());
        return "unit2/reglacramer/formcramer";
    }

    @PostMapping("/reglacramer/solvecramer")
    public String solveCramer(ReglaCramer cramer, Model model) {
        var solve = unidadIIIsrv.AlgoritmoReglaCramer(cramer);
        model.addAttribute("solveCramer", solve);
        return "unit2/reglacramer/solvecramer";
    }
}
