package com.app.centrosaludpalmeritas.entities;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

/**
 * Clase Médica
 * @author Tito J Muñoz Abreu
 * @version 1.0
 * Entidad o modelo de datos
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "medica")
public class Medica extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * representa el codigo de ingreso
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cod_medica;
    /**
     * representa tipo de especialidad
     */
    @Column(name = "especialidad_medica")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Debe asignar la especialidad de la medica")
    private EspecialidadMedica especialidadMedica;
    /**
     * representa la dispinibilidad del médico
     */
    private boolean disponible;
    /**
     * representa la cantidad de pacientes asignados
     */
    @Column(name = "pacientes_asignados")
    private int cantPacientesAsignados;

    /**
     * Constructor vacio
     * por defecto la disponiblidad de medico es igual a true
     * por defecto la cantidad de pacientes asignados es 0
     */
    public Medica(){
        this.disponible = true;
        this.cantPacientesAsignados = 0;
    }

    /**
     * Contructor
     * @param nombre
     * @param apellidos
     * @param mobile
     * @param especialidadMedica
     */
    public Medica(String nombre, String apellidos, String mobile, EspecialidadMedica especialidadMedica) {
        super(nombre,apellidos,mobile);
        this.disponible = true;
        this.especialidadMedica = especialidadMedica;
        this.cantPacientesAsignados = 0;
    }

    /**
     * metodo para asignar un paciente
     */
    public void asignarPaciente() {
        //comprobar que tenga menos tres pacientes asignados
        if(cantPacientesAsignados < 3){
            //sumar paciente
            this.cantPacientesAsignados++;
        }
        //comprobar si los pacientes asignado sean igual a tres
        if (cantPacientesAsignados == 3){
            this.disponible = false;
        }

    }

    /**
     * metoodo para quitar un paciente
     */
    public void quitarPaciente(){
        //restar paciente
        this.cantPacientesAsignados--;
        //comprobar que tenga menos tres pacientes asignados
        if(cantPacientesAsignados < 3){
            this.disponible = true;
        }
    }

}
