package org.example.servicios;

import org.example.entidades.Producto;
import org.example.entidades.Receta;
import org.example.repositorios.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.lang.model.element.RecordComponentElement;
import java.util.List;
import java.util.Optional;

@Service
public class RecetaServiceImpl implements RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Receta> findAll() {return recetaRepository.findAll();}

    @Override
    @Transactional(readOnly = true)
    public Optional<Receta> findById(Long id) {return recetaRepository.findById(id);}


    @Override
    @Transactional
    public Receta create(Receta receta) {
        return recetaRepository.save(receta);
    }

    @Override
    @Transactional
    public Optional<Receta> update(Long id, Receta receta) {
        Optional <Receta> recetaOptional = recetaRepository.findById(id);
        if(recetaOptional.isPresent()){
            Receta recetaDb = recetaOptional.orElseThrow();
            recetaDb.setId(receta.getId());
            recetaDb.setNomrbe(receta.getNomrbe());
            recetaDb.setDescripcion(receta.getDescripcion());
            recetaDb.setTiempoPreparacion(receta.getTiempoPreparacion());
            recetaDb.setValoracion(receta.getValoracion());
            recetaDb.setPreparacion(receta.getPreparacion());
            recetaDb.setUsuario(receta.getUsuario());
            return Optional.of(recetaRepository.save(recetaDb));
        }
        return recetaOptional;
    }


    @Override
    public Optional<Receta> delete(Long id) {
        Optional <Receta> recetaOptional = recetaRepository.findById(id);
        recetaOptional.ifPresent( recetaDb -> recetaRepository.delete(recetaDb));
        return recetaOptional;
    }

}
