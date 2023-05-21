package com.app.centrosaludpalmeritas.entities;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Paciente
 * @author Tito J Muñoz Abreu
 * @version 1.0
 * Entidad o modelo de datos
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "paciente")
public class Paciente extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * representa el codigo de paciente
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cod_paciente;
    /**
     * representa la direccion
     */
    @NotEmpty(message = "es necesario rellenar la direccion")
    private String direccion;
    /**
     * representa la fecha de nacimiento
     */
    @Column(name = "fecha_nac",
            columnDefinition = "DATE")
    @NotNull(message = "es necesario rellenar la fecha")
    @PastOrPresent(message = "La fecha no puede ser posterior a la actual")
    private LocalDate fechaNac;
    /**
     * representa el codigo postal
     */
    @Column(name ="cod_postal")
    @NotEmpty(message = "ingresar codigo postal de  5 digitos")
    @Pattern(regexp = "^(0[1-9]|([1-4][0-9]|5[0-2]))[0-9]{3}$",
                message = "Ingresar un codigo postal valido")
    private String codPostal;
    /**
     * representa la provincia
     */
    @Column
    @NotEmpty(message = "provincia no puede estar vacia")
    @Pattern(regexp = "^[A-Za-z]+$",
            message = "Ingresar solo caracteres")
    private String provincia;
    /**
     * representa la poblacion
     */
    @Column
    @NotNull(message = "poblacion no puede estar vacia")
    @Pattern(regexp = "^[A-Za-z]+$",
            message = "Ingresar solo caracteres")
    private String poblacion;
    /**
     * representa el ingreso del paciente
     */
    private boolean estaIngresado = false;

    /**
     * metodo para validar el ingreso del paciente
     * @param ingresar
     */
    private void ingresarPaciente(boolean ingresar){
        setEstaIngresado(ingresar);
    }


}
