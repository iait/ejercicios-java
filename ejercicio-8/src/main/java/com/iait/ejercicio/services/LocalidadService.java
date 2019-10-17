package com.iait.ejercicio.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iait.ejercicio.dtos.LocalidadDto;
import com.iait.ejercicio.entities.LocalidadEntity;
import com.iait.ejercicio.entities.ProvinciaEntity;
import com.iait.ejercicio.repositories.LocalidadRepository;
import com.iait.ejercicio.repositories.ProvinciaRepository;

@Service
public class LocalidadService {

    @Autowired private LocalidadRepository localidadRepository;
    @Autowired private ProvinciaRepository provinciaRepository;

    @Transactional
    public Optional<LocalidadEntity> buscarPorId(Long id) {
        return localidadRepository.findById(id);
    }
    
    @Transactional
    public LocalidadEntity nueva(LocalidadDto localidadDto) {
        
        Long id = localidadRepository.maxId().orElse(0L) + 1;
        String nombre = localidadDto.getNombre();
        ProvinciaEntity provincia = provinciaRepository
                .findById(localidadDto.getProvinciaId())
                .orElseThrow(() -> new RuntimeException(
                        String.format(
                                "El ID de Provincia %s no existe!", 
                                localidadDto.getProvinciaId())));
        
        LocalidadEntity localidadEntity = new LocalidadEntity(id, nombre, provincia);
        localidadRepository.save(localidadEntity);
        
        return localidadEntity;
    }
    
    @Transactional
    public LocalidadEntity actualizar(Long id, LocalidadDto localidadDto) {
        
        LocalidadEntity localidad = localidadRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(
                        String.format("El ID de Localidad %s no existe!", id)));
        
        localidad.setNombre(localidadDto.getNombre());
        localidad.setProvincia(localidad.getProvincia());
        localidadRepository.save(localidad);
        return localidad;
    }
    
    @Transactional
    public LocalidadEntity eliminar(Long id) {
        
        LocalidadEntity localidad = localidadRepository
                .deleteById(id)
                .orElseThrow(() -> new RuntimeException(
                        String.format("El ID de Localidad %s no existe!", id)));
        return localidad;
    }
}
