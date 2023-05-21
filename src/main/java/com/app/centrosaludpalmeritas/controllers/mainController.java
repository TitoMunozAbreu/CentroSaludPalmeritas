package com.app.centrosaludpalmeritas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Clase mainController
 * @author Tito J Muñoz Abreu
 * @version 1.0
 * Capa de presentacion
 * se controla la pagina principal del sitio web
 */
@Controller
public class mainController {

    /**
     * metodo para mostrar redirigir a la pagina principal
     * @return devuelve a la vista 'index'
     */
    @GetMapping("/")
    public String root() {
        return "redirect:/index.html";
    }

    /**
     * metodo para mostrar la pagina principal
     * @return devuelve a la vista 'index'
     */
    @GetMapping("/index.html")
    public String index() {
        return "index";
    }


}
