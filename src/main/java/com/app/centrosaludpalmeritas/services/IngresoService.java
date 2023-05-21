package com.app.centrosaludpalmeritas.services;

import com.app.centrosaludpalmeritas.entities.Ingreso;
import com.app.centrosaludpalmeritas.repositories.IngresoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;
import java.util.Optional;

/**
 * Clase IngresoService
 * @author Tito J Muñoz Abreu
 * @version 1.0
 * Capa de logica de negocio
 */
@Service
public class IngresoService implements BaseService<Ingreso> {

    /**
     * representa la persistencia de Ingeso
     */
    private IngresoRepository ingresoRepository;

    /**
     * Constructor
     * @param ingresoRepository
     */
    @Autowired
    public IngresoService(IngresoRepository ingresoRepository) {
        this.ingresoRepository = ingresoRepository;
    }

    /**
     * metodo para listar los ingresos
     * @return  delvuelve una lista de ingresos
     */
    @Override
    public List<Ingreso> findAll() {
        return ingresoRepository.findAll();

    }

    /**
     * metodo para buscar un ingreso por ID
     * @return  devuelve un objeto de tipo Ingreso
     */
    @Override
    @Transactional
    public Ingreso findById(Long id) {
        Optional<Ingreso> ingresoOptional = ingresoRepository.findById(id);
        return ingresoOptional.get();

    }

    /**
     * metodo para guardar un ingreso
     * @param entity
     * @return devuelve cero
     */
    @Override
    @Transactional
    public int save(Ingreso entity) {
        //asignar el paciente al medico
        entity.getMedica().asignarPaciente();
        //validar el ingreso del paciente
        entity.getPaciente().setEstaIngresado(true);
        //persistir datos
        this.ingresoRepository.save(entity);

        return 0;
    }

    /**
     * metodo para actualizar un ingreso
     * @param id
     * @param entity
     * @return devuelve un objeto de tipo Ingreso
     */
    @Override
    @Transactional
    public Ingreso update(Long id, Ingreso entity) {
        //almacenar el objeto encontrado de la BBDD
        Optional<Ingreso> ingresoOptional = ingresoRepository.findById(id);
        //comprobar que el ingreso contenga datos asociados
        if (ingresoOptional.isPresent()) {
            Ingreso ingreso = ingresoOptional.get();

            //actualizar los datos del ingreso encontrado
            ingreso.getPaciente().setEstaIngresado(false);
            ingreso.getMedica().quitarPaciente();


            //actualizar datos nuevos del ingreso
            ingreso.setFechaIngreso(entity.getFechaIngreso());
            ingreso.setPaciente(entity.getPaciente());
            ingreso.setMedica(entity.getMedica());

            //asignar paciente
            ingreso.getMedica().asignarPaciente();
            //validar el ingreso del paciente
            ingreso.getPaciente().setEstaIngresado(true);
            //persistir datos
            this.ingresoRepository.save(ingreso);
            return ingreso;
        }
        return ingresoOptional.orElseThrow();
    }

    /**
     * metodo para eliminar un ingreso
     * @param id
     */
    @Override
    @Transactional
    public void delete(Long id) {
        Optional<Ingreso> ingresoOptional = this.ingresoRepository.findById(id);
        ingresoOptional.get().getMedica().quitarPaciente();

        if(ingresoOptional.get().getMedica().getCantPacientesAsignados() <= 3){
            ingresoOptional.get().getMedica().setDisponible(true);
        }
        ingresoOptional.get().getPaciente().setEstaIngresado(false);

        ingresoRepository.deleteById(id);
    }
}
