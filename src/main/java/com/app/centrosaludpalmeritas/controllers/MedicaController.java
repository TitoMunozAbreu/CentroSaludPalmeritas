package com.app.centrosaludpalmeritas.controllers;

import com.app.centrosaludpalmeritas.entities.EspecialidadMedica;
import com.app.centrosaludpalmeritas.entities.Medica;
import com.app.centrosaludpalmeritas.entities.Paciente;
import com.app.centrosaludpalmeritas.services.MedicaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Clase MedicaController
 * @author Tito J Muñoz Abreu
 * @version 1.0
 * Capa de presentacion
 * se controlan las llamadas HHTTP relacionadas a los médicos
 */
@Controller
@RequestMapping("/medicas")
public class MedicaController {

    /**
     * representa la logica de negocio de Médica
     */
    private MedicaService medicaService;

    /**
     * Constructor
     * @param medicaService
     */
    @Autowired
    public MedicaController(MedicaService medicaService) {
        this.medicaService = medicaService;
    }

    /***
     * metodo para mostar la lista de médicos
     * @param model
     * @return devuelve la vista de 'gestion-medicas'
     */
    @GetMapping("/")
    public String listarMedicas(Model model) {
        List<Medica> medicas = this.medicaService.findAll();
        model.addAttribute("titlePage", "Gestion-Médicos");
        model.addAttribute("titulo", "Registro de médicos");
        model.addAttribute("medicas", medicas);

        return "gestion-medicas";
    }

    /**
     * metodo que muestra un formulario de registro de médico
     * @param model
     * @return devuelve la vista de 'form-medicas'
     */
    @GetMapping("/crear")
    public String crearMedica(Model model) {
        model.addAttribute("medica", new Medica());
        model.addAttribute("especialidades", EspecialidadMedica.values());
        model.addAttribute("titulo", "Registrar médico");
        return "form-medicas";
    }

    /**
     * metodo para guardar el registro de médico
     * @param model
     * @return devuelve la vista de 'gestion-medicas'
     */
    @PostMapping("/guardar")
    public String guardarMedica(@Valid @ModelAttribute("medica") Medica medica,
                                BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("titulo", "Registrar médico");
            model.addAttribute("especialidades", EspecialidadMedica.values());
            return "form-medicas";
        }
        this.medicaService.save(medica);
        redirectAttributes.addFlashAttribute("succes", "Médico "+ medica.getNombre() + "  creada con exito");

        return "redirect:/medicas/";
    }

    /**
     * metodo para editar el registro de un médico
     * @param model
     * @return devuelve la vista de 'editar-medicas'
     */
    @GetMapping("/{id}/editar")
    public String editarMedica(@PathVariable long id,
                               Model model) {
        Medica medica = this.medicaService.findById(id);

        model.addAttribute("medica", medica );
        model.addAttribute("especialidades", EspecialidadMedica.values());
        model.addAttribute("titulo", "Editar médico");

        return "editar-medicas";
    }

    /**
     * metodo para actualizar el registro de un médico
     * @param model
     * @return devuelve la vista de 'gestion-medicas'
     */
    @PostMapping("/actualizar")
    public String actualizarMedica(@Valid @ModelAttribute("medica") Medica medica,
                                     BindingResult bindingResult,
                                     Model model,
                                     RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()) {
            model.addAttribute("titulo", "Editar medica");
            return "editar-medicas";
        }
        medicaService.update(medica.getCod_medica(),medica);
        redirectAttributes.addFlashAttribute("info",
                "Medica " + medica.getNombre() + " actualizado");

        return "redirect:/medicas/";

    }

    /**
     * metodo para eliminar el registro de un médico
     * @param model
     * @return devuelve la vista de 'gestion-medicas'
     */
    @GetMapping("/{id}/eliminar")
    public String eliminarMedica(@PathVariable long id){
        this.medicaService.delete(id);

        return "redirect:gestion-medicas";
    }

}
