package com.app.centrosaludpalmeritas.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase Ingreso
 * @author Tito J Muñoz Abreu
 * @version 1.0
 * Entidad o modelo de datos
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ingreso")
public class Ingreso implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * representa el codigo de ingreso
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cod_ingreso;
    /**
     * representa el codigo de habitación
     */
    @Column(name = "cod_habitacion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cod_habitacion;
    /**
     * representa el codigo de cama
     */
    @Column(name = "cod_cama")
    private long cod_cama;
    /**
     * representa la fecha del ingreso
     */
    @Column(name = "fecha_ingreso",columnDefinition = "DATE") // le indica a JPA que debe utilizar el tipo de datos "DATE" de Mysql para la columna "fecha" en la tabla.
    @NotNull(message = "ingresar una fecha de ingreso")
    @PastOrPresent
    private LocalDate fechaIngreso;
    /**
     * representa el paciente ingresado
     */
    @ManyToOne(cascade = CascadeType.PERSIST) //se utiliza persist en el caso que un ingreso se borrado, no se elimine el objeto paciente
    @JoinColumn(name = "cod_paciente")
    private Paciente paciente;
    /**
     * representa el médico asignado
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cod_medica")
    private Medica medica;


    /**
     * metodo para validar el ingreso de un paciente
     * @param ingresar
     */
    public void ingresarPaciente(boolean ingresar){
        this.paciente.setEstaIngresado(ingresar);
    }

    /**
     * metodo para asignar el médico
     * @param medica
     */
    public void asignarMedica(Medica medica){

        try {
            //comprobar si el medico esta disponible
            if(medica.isDisponible()){
                this.medica = medica;
                //registar la asignacion del paciente
                this.medica.asignarPaciente();
            }
        }catch (Exception e){
            System.out.println("Medica no disponible");
        }

    }

    /***
     * metodo para generar el codigo automatico
     * cuando se realiza un ingreso
     */
    @PrePersist
    public void generarCodigos() {
        // Generar código para cod_habitacion
        this.cod_habitacion = generarCodigoUnico();
        // Generar código para cod_cama
        this.cod_cama = generarCodigoUnico();
    }

    private long generarCodigoUnico() {
        // Lógica para generar un código único
        long codigo = obtenerSiguienteCodigo();
        incrementarContador();

        return codigo;
    }

    private long obtenerSiguienteCodigo() {
        // Lógica para obtener el siguiente código disponible
        return ContadorCodigo.obtenerSiguienteCodigo();
    }

    private void incrementarContador() {
        // Lógica para incrementar el contador
        ContadorCodigo.incrementarContador();
    }

}

class ContadorCodigo {
    private static long contador = 0;

    public static long obtenerSiguienteCodigo() {
        return contador + 1;
    }

    public static void incrementarContador() {
        contador++;
    }
}

