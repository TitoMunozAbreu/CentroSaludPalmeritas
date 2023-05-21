package com.app.centrosaludpalmeritas.controllers;

import com.app.centrosaludpalmeritas.entities.Paciente;
import com.app.centrosaludpalmeritas.services.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

/**
 * Clase PacienteController
 * @author Tito J Muñoz Abreu
 * @version 1.0
 * Capa de presentacion
 * se controlan las llamadas HHTTP relacionadas a los pacientes
 */
@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    /**
     * representa la logica de negocio de Paciente
     */
    private PacienteService pacienteService;

    /**
     * Contructor
     * @param pacienteService
     */
    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }


    /***
     * metodo para mostar la lista de pacientes
     * @param model
     * @return devuelve la vista de 'gestion-pacientes'
     */
    @GetMapping("/")
    public String listarPacientes(Model model) {
        model.addAttribute("titlePage","Gestion-Pacientes");
        model.addAttribute("titulo", "Registro de pacientes");
        model.addAttribute("pacientes", this.pacienteService.findAll());
        return "gestion-pacientes";

    }

    /***
     * metodo que muestra un formulario de registro de paciente
     * @param model
     * @return devuelve la vista 'form-pacientes'
     */
    @GetMapping("/crear")
    public String crearPaciente(Model model) {
        Paciente paciente = new Paciente();
        model.addAttribute("titulo", "Registrar paciente");
        model.addAttribute("paciente", paciente);
        return "form-pacientes";
    }

    /***
     * metodo para guardar el registro de paciente
     * @param model
     * @return devuelve la vista 'gestion-pacientes'
     */
    @PostMapping("/guardar")
    public String guardarPaciente(@Valid @ModelAttribute("paciente") Paciente paciente,
                                  BindingResult bindingResult,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("titulo", "Registrar paciente");
            return "form-pacientes";
        }
        pacienteService.save(paciente);
        redirectAttributes.addFlashAttribute("succes",
                "Paciente " + paciente.getNombre() + " creado con exito");

        return "redirect:/pacientes/";

    }

    /***
     * metodo para editar el registro de paciente
     * @param model
     * @return devuelve la vista 'editar-pacientes'
     */
    @GetMapping(value = "/{id}/editar")
    public String editarPaciente(@PathVariable long id,
                                 Model model) {
        Paciente paciente = this.pacienteService.findById(id);

        model.addAttribute("paciente", paciente);
        model.addAttribute("titulo", "Editar paciente");

        return "editar-pacientes";

    }

    /***
     * metodo para actualizar el registro de paciente
     * @param model
     * @return devuelve la vista 'gestion-pacientes'
     */
    @PostMapping("/actualizar")
    public String actualizarPaciente(@Valid @ModelAttribute("paciente") Paciente paciente,
                                     BindingResult bindingResult,
                                     Model model,
                                     RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()) {
            model.addAttribute("titulo", "Editar paciente");
            return "editar-pacientes";
        }
        pacienteService.update(paciente.getCod_paciente(),paciente);
        redirectAttributes.addFlashAttribute("info",
                "Paciente " + paciente.getNombre() + " actualizado");

        return "redirect:/pacientes/";

    }

    /***
     * metodo para eliminar el registro de paciente
     * @param model
     * @return devuelve la vista 'gestion-pacientes'
     */
    @GetMapping("/{id}/eliminar")
    public String eliminarPaciente(@PathVariable long id, Model model) {
        this.pacienteService.delete(id);

        return "redirect:/pacientes/";
    }

}
