package com.app.centrosaludpalmeritas.entities;

/**
 * Enum EspecialidadMedica
 * @author Tito J Muñoz Abreu
 * @version 1.0
 * se detallan los tipos de espcialidades de los médicos
 */
public enum EspecialidadMedica {

    PEDIATRA ("medica pediatra"),
    CIRUJANA ("medica cirujana"),
    TRAUMATOLOGA ("medica traumatologo");

    /**
     * representa la descripcion de la especialidad
     */
    private String descripcion;

    /**
     * Constructor
     * @param d
     */
    private EspecialidadMedica(String d){this.descripcion = d;}

    /**
     * metodo para mostrar la especialidad
     * @return devuelve el tipo de especialidad
     */
    public String getDescripcion() {
        return descripcion;
    }
}
