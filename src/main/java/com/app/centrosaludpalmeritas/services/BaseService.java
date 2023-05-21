package com.app.centrosaludpalmeritas.services;

import java.util.List;

/**
 * Interfaz BaseService
 * @author Tito J Muñoz Abreu
 * @version 1.0
 * Contiene un listado de metodos
 * para el uso de las clases de servicios
 */
public interface BaseService<E> {
    public List<E> findAll() throws Exception;
    public E findById(Long id) throws Exception;
    public int save(E entity) throws  Exception;
    public E update(Long id, E entity) throws Exception;
    public void delete(Long id) throws Exception;

}
