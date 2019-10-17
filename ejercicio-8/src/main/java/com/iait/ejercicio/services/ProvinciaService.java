package com.iait.ejercicio.services;

import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.joining;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iait.ejercicio.dtos.ProvinciaDto;
import com.iait.ejercicio.entities.LocalidadEntity;
import com.iait.ejercicio.entities.ProvinciaEntity;
import com.iait.ejercicio.repositories.LocalidadRepository;
import com.iait.ejercicio.repositories.ProvinciaRepository;

@Service
public class ProvinciaService {
    
    @Autowired private ProvinciaRepository provinciaRepository;
    @Autowired private LocalidadRepository localidadRepository;
    
    @Transactional
    public Optional<ProvinciaEntity> buscarPorId(Long id) {
        return provinciaRepository.findById(id);
    }
    
    @Transactional
    public ProvinciaEntity nueva(ProvinciaDto provinciaDto) {
        
        Long id = provinciaRepository.maxId().orElse(0L) + 1;
        String nombre = provinciaDto.getNombre();
        
        ProvinciaEntity provinciaEntity = new ProvinciaEntity(id, nombre);
        provinciaRepository.save(provinciaEntity);
        
        return provinciaEntity;
    }
    
    @Transactional
    public ProvinciaEntity actualizar(Long id, ProvinciaDto provinciaDto) {
        
        ProvinciaEntity provincia = provinciaRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(
                        String.format("El ID de Provincia %s no existe!", id)));
        
        provincia.setNombre(provinciaDto.getNombre());
        provinciaRepository.save(provincia);
        return provincia;
    }
    
    @Transactional
    public ProvinciaEntity eliminar(Long id) {
        
        List<LocalidadEntity> localidades = localidadRepository.findByProvinciaId(id);
        if (!localidades.isEmpty()) {
            throw new RuntimeException(String.format(
                    "No se puede eliminar la provincia porque posee estas localidades: %s",
                    localidades.stream().map(LocalidadEntity::getNombre).collect(joining(", "))));
        }
        
        ProvinciaEntity provincia = provinciaRepository
                .deleteById(id)
                .orElseThrow(() -> new RuntimeException(
                        String.format("El ID de Provincia %s no existe!", id)));
        return provincia;
    }

}
