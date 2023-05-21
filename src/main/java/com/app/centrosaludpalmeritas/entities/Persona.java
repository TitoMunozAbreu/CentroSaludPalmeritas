package com.app.centrosaludpalmeritas.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

/**
 * Clase Abstracta Persona
 * @author Tito J Muñoz Abreu
 * @version 1.0
 * Entidad o modelo de datos
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Persona{
    /**
     * representa el nombre
     */
    @NotEmpty(message = "nombre no puede estar vacio")
    @NotNull
    @Column(name = "nombre")
    private String nombre;
    /**
     * representa los apellidos
     */
    @NotEmpty(message = "apellido no puede estar vacio")
    @NotNull
    @Column(name="apellidos")
    private String apellidos;
    /**
     * representa el móvil
     */
    @NotNull(message = "movil no puede estar vacio")
    @Digits(integer = 9,fraction = 0, message = "ingresar un telefono valido de 9 digitos")
    @Column(name = "movil")
    private String mobile;

}
