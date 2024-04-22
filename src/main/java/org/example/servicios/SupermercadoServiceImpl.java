package org.example.servicios;


import org.example.entidades.Producto;
import org.example.entidades.Supermercado;
import org.example.repositorios.SupermercadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SupermercadoServiceImpl implements SupermercadoService {

    @Autowired
    private SupermercadoRepository supermercadoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Supermercado> findAll() {return supermercadoRepository.findAll();}

    @Override
    @Transactional(readOnly = true)
    public Optional<Supermercado> findById(Long id) {return supermercadoRepository.findById(id);}


    @Override
    @Transactional
    public Supermercado create(Supermercado supermercado) {
        return supermercadoRepository.save(supermercado);
    }

    @Override
    @Transactional
    public Optional<Supermercado> update(Long id, Supermercado supermercado) {
        Optional <Supermercado> supermercadoOptional = supermercadoRepository.findById(id);
        if(supermercadoOptional.isPresent()){
            Supermercado supermrecadoDb = supermercadoOptional.orElseThrow();
            supermrecadoDb.setId(supermercado.getId());
            supermrecadoDb.setNombre(supermercado.getNombre());
            supermrecadoDb.setDireccion(supermercado.getDireccion());
            supermrecadoDb.setTelefono(supermercado.getTelefono());
            return Optional.of(supermercadoRepository.save(supermrecadoDb));
        }
        return supermercadoOptional;
    }


    @Override
    public Optional<Supermercado> delete(Long id) {
        Optional <Supermercado> supermercadoOptional = supermercadoRepository.findById(id);
        supermercadoOptional.ifPresent( supermercadoDb -> supermercadoRepository.delete(supermercadoDb));
        return supermercadoOptional;
    }

}
