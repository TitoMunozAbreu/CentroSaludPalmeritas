package com.app.centrosaludpalmeritas.controllers;

import com.app.centrosaludpalmeritas.entities.EspecialidadMedica;
import com.app.centrosaludpalmeritas.entities.Ingreso;
import com.app.centrosaludpalmeritas.entities.Medica;
import com.app.centrosaludpalmeritas.entities.Paciente;
import com.app.centrosaludpalmeritas.services.IngresoService;
import com.app.centrosaludpalmeritas.services.MedicaService;
import com.app.centrosaludpalmeritas.services.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase IngresoController
 * @author Tito J Muñoz Abreu
 * @version 1.0
 * Capa de presentacion
 * se controlan las llamadas HHTTP relacionadas a los ingresos
 */
@Controller
@RequestMapping("/ingresos")
public class IngresoController {

    /**
     * representa la logica de negocio de Ingreso
     */
    private IngresoService ingresoService;
    /**
     * representa la logica de negocio de Médica
     */
    private MedicaService medicaService;
    /**
     * representa la logica de negocio de Paciente
     */
    private PacienteService pacienteService;

    /**
     * Contructor
     * @param ingresoService
     * @param medicaService
     * @param pacienteService
     */
    @Autowired
    public IngresoController(IngresoService ingresoService, MedicaService medicaService, PacienteService pacienteService) {
        this.ingresoService = ingresoService;
        this.medicaService = medicaService;
        this.pacienteService = pacienteService;
    }

    /***
     * metodo para mostar la lista de ingresos
     * @param model
     * @return devuelve la vista de 'gestion-ingresos'
     */
    @GetMapping("/")
    public String listarIngresos(Model model) {
        List<Ingreso> ingresos = this.ingresoService.findAll();
        model.addAttribute("title", "Gestion-Ingresos");
        model.addAttribute("titulo", "Registro de Ingresos");
        model.addAttribute("ingresos", ingresos);
        return "gestion-ingresos";
    }

    /***
     * metodo que muestra un formulario de registro de ingreso
     * @param model
     * @return devuelve la vista 'form-ingresos'
     */
    @GetMapping("/crear")
    public String crearIngreso(Model model) {
        Ingreso ingreso = new Ingreso();

        model.addAttribute("titulo", "Registrar ingreso");
        model.addAttribute("ingreso", ingreso);
        model.addAttribute("pacientes", this.pacienteService.findAllNoIngresados());
        model.addAttribute("medicas", this.medicaService.findAllDisponibles());
        return "form-ingresos";
    }

    /***
     * metodo para guardar el registro de ingreso
     * @param model
     * @return devuelve la vista 'gestion-ingresos'
     */
    @PostMapping("/guardar")
    public String guardarIngreso(@Valid @ModelAttribute("ingreso") Ingreso ingreso,
                                 BindingResult bindingResult,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("titulo", "Registrar prueba ingreso");
            model.addAttribute("ingreso", ingreso);
            model.addAttribute("pacientes", this.pacienteService.findAllNoIngresados());
            model.addAttribute("medicas", this.medicaService.findAllDisponibles());
            return "form-ingresos";
        }

        this.ingresoService.save(ingreso);
        redirectAttributes.addFlashAttribute("succes",
                ingreso.getPaciente().getNombre()+ " ha sido ingresado.");
        return "redirect:/ingresos/";
    }

    /***
     * metodo para editar el registro de un ingreso
     * @param model
     * @return devuelve la vista 'editar-ingresos'
     */
    @GetMapping("/{id}/editar")
    public String editarIngreso(@PathVariable long id,
                               Model model) {
        Ingreso ingreso = this.ingresoService.findById(id);

        model.addAttribute("ingreso", ingreso );
        model.addAttribute("pacientes", this.pacienteService.findAllNoIngresados());
        model.addAttribute("medicas", this.medicaService.findAllDisponibles());
        model.addAttribute("titulo", "Editar ingreso");

        return "editar-ingresos";
    }

    /***
     * metodo para actualizar el registro de un ingreso
     * @param model
     * @return devuelve la vista 'gestion-ingresos'
     */
    @PostMapping("/actualizar")
    public String actualizarIngreso(@Valid @ModelAttribute("ingreso") Ingreso ingreso,
                                     BindingResult bindingResult,
                                     Model model,
                                     RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()) {
            model.addAttribute("titulo", "Editar ingreso");
            model.addAttribute("ingreso", ingreso );
            model.addAttribute("pacientes", this.pacienteService.findAllNoIngresados());
            model.addAttribute("medicas", this.medicaService.findAllDisponibles());
            return "editar-ingresos";
        }
        ingresoService.update(ingreso.getCod_ingreso(),ingreso);
        redirectAttributes.addFlashAttribute("info",
                "Ingreso  actualizado");

        return "redirect:/ingresos/";

    }

    /***
     * metodo para eliminar el registro de un ingreso
     * @param model
     * @return devuelve la vista 'editar-ingresos'
     */
    @GetMapping("/{id}/eliminar")
    public String eliminarMedica(@PathVariable long id){
        this.ingresoService.delete(id);

        return "redirect:gestion-ingresos";
    }

}
