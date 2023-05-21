package com.app.centrosaludpalmeritas.services;

import com.app.centrosaludpalmeritas.entities.Medica;
import com.app.centrosaludpalmeritas.repositories.MedicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Clase MedicaService
 * @author Tito J Muñoz Abreu
 * @version 1.0
 * Capa de logica de negocio
 */
@Service
public class MedicaService implements BaseService<Medica> {

    /**
     * representa la persistencia de Médica
     */
    private MedicaRepository medicaRepository;

    /**
     * Constructor
     * @param medicaRepository
     */
    @Autowired
    public MedicaService(MedicaRepository medicaRepository) {
        this.medicaRepository = medicaRepository;
    }

    /**
     * metodo para listar los médicos
     * @return  delvuelve una lista de médicos
     */
    @Override
    public List<Medica> findAll() {
        return medicaRepository.findAll();
    }

    /**
     * metodo para listar los médicos dispinibles
     * @return  delvuelve una lista de médicos
     */
    public List<Medica> findAllDisponibles() {
        return findAll()
                .stream()
                .filter(medica -> medica.isDisponible() == true)
                .collect(Collectors.toList());
    }

    /**
     * metodo para buscar un médico por ID
     * @return  devuelve un objeto de tipo Médica
     */
    @Override
    public Medica findById(Long id) {
        Optional<Medica> medicaOptional = medicaRepository.findById(id);
        return medicaOptional.orElseThrow();
    }

    /**
     * metodo para guardar un médico
     * @param entity
     * @return devuelve cero
     */
    @Override
    public int save(Medica entity) {
        int respuesta = 0;
        //persistir datos
        Medica medica = this.medicaRepository.save(entity);
        if (!medica.equals(null)) {
            respuesta = 1;
        }
        return respuesta;
    }

    /**
     * metodo para actualizar un médico
     * @param id
     * @param entity
     * @return devuelve un objeto de tipo Médica
     */
    @Override
    public Medica update(Long id, Medica entity) {
        //almacenar el objeto encontrado de la BBDD
        Optional<Medica> medicaOptional = medicaRepository.findById(id);
        //comprobar que el médico contenga datos asociados
        if (medicaOptional.isPresent()) {
            Medica medica = medicaOptional.get();

            //actualizar los datos del medico encontrado
            medica.setNombre(entity.getNombre());
            medica.setApellidos(entity.getApellidos());
            medica.setMobile(entity.getMobile());
            medica.setEspecialidadMedica(entity.getEspecialidadMedica());
            //persistir datos
            medica = medicaRepository.save(medica);
            return medica;
        }
        return medicaOptional.orElseThrow();
    }

    /**
     * metodo para eliminar un médico
     * @param id
     */
    @Override
    public void delete(Long id) {
        //persistir datos
        medicaRepository.deleteById(id);

    }
}
