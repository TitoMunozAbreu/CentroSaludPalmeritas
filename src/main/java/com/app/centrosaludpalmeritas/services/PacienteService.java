package com.app.centrosaludpalmeritas.services;

import com.app.centrosaludpalmeritas.entities.Paciente;
import com.app.centrosaludpalmeritas.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Clase PacienteService
 * @author Tito J Muñoz Abreu
 * @version 1.0
 * Capa de logica de negocio
 */
@Service
public class PacienteService implements BaseService<Paciente> {

    /**
     * representa la persistencia de Paciente
     */
    private PacienteRepository pacienteRepository;

    /**
     * Constructor
     * @param pacienteRepository
     */
    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    /**
     * metodo para listar los pacientes
     * @return  delvuelve una lista de pacientes
     */
    @Override
    @Transactional //indica que  estos metodos realizan transacciones en la BBDD
    public List<Paciente> findAll() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacientes;

    }

    /**
     * metodo para listar los pacientes dispinibles
     * @return  delvuelve una lista de pacientes
     */
    public List<Paciente> findAllNoIngresados() {
        return findAll()
                .stream()
                .filter(paciente -> paciente.isEstaIngresado() == false)
                .collect(Collectors.toList());
    }

    /**
     * metodo para buscar un paciente por ID
     * @return  devuelve un objeto de tipo Paciente
     */
    @Override
    @Transactional
    public Paciente findById(Long id) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id); //Se crea de tipo Optional porque no sabemos si en la BBDD se encontrata un registro con ese ID
        return pacienteOptional.get();
    }

    /**
     * metodo para guardar un paciente
     * @param entity
     * @return devuelve cero
     */
    @Override
    @Transactional
    public int save(Paciente entity) {
        int respuesta = 0;
        //persistir datos
        Paciente paciente = this.pacienteRepository.save(entity);
        if (!paciente.equals(null)) {
            respuesta = 1;
        }
        return respuesta;

    }

    /**
     * metodo para actualizar un paciente
     * @param id
     * @param entity
     * @return  devuelve un objeto de tipo Paciente
     */
    @Override
    @Transactional
    public Paciente update(Long id, Paciente entity) {
        //almacenar el objeto encontrado de la BBDD
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        //comprobar que el paciente contenga datos asociados
        if (pacienteOptional.isPresent()) {
            Paciente paciente = pacienteOptional.get();

            //actualizar los datos del paciente encontrado
            paciente.setNombre(entity.getNombre());
            paciente.setApellidos(entity.getApellidos());
            paciente.setMobile(entity.getMobile());
            paciente.setDireccion(entity.getDireccion());
            paciente.setFechaNac(entity.getFechaNac());
            paciente.setCodPostal(entity.getCodPostal());
            paciente.setProvincia(entity.getProvincia());
            paciente.setPoblacion(entity.getPoblacion());
            //persistir datos
            paciente = pacienteRepository.save(paciente);
            return paciente;
        }
        return pacienteOptional.orElseThrow();

    }

    /**
     * metodo para eliminar un paciente
     * @param id
     */
    @Override
    @Transactional
    public void delete(Long id) {
        //persistir datos
        pacienteRepository.deleteById(id);
    }

}
