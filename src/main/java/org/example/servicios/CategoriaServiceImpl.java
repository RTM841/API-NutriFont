package org.example.servicios;

import org.example.entidades.Categoria;
import org.example.repositorios.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> findAll() {return categoriaRepository.findAll();}
    @Override
    @Transactional(readOnly = true)
    public Optional<Categoria> findById(Long id) {return categoriaRepository.findById(id);}



    @Override
    @Transactional
    public Categoria create(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    @Transactional
    public Optional<Categoria> update(Long id, Categoria categoria) {
        Optional <Categoria> categoriaOptional = categoriaRepository.findById(id);
        if(categoriaOptional.isPresent()){
            Categoria categoriaDb = categoriaOptional.orElseThrow();
            categoriaDb.setId(categoria.getId());
            categoriaDb.setNomrbe(categoria.getNomrbe());
            return Optional.of(categoriaRepository.save(categoriaDb));
        }
        return categoriaOptional;
    }


    @Override
    public Optional<Categoria> delete(Long id) {
        Optional <Categoria> categoriaOptional = categoriaRepository.findById(id);
        categoriaOptional.ifPresent( canionDb -> categoriaRepository.delete(canionDb));
        return categoriaOptional;
    }

}
