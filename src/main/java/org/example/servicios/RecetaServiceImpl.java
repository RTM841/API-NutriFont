package org.example.servicios;

import org.example.entidades.Receta;
import org.example.entidades.Supermercado;
import org.example.entidades.Usuario;
import org.example.repositorios.RecetaRepository;
import org.example.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecetaServiceImpl implements RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Receta> findAll() {return recetaRepository.findAll();}

    @Override
    @Transactional(readOnly = true)
    public Optional<Receta> findById(Long id) {return recetaRepository.findById(id);}


    @Override
    @Transactional
    public Receta create(Receta receta) {
        //Crear con Usuario
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(Long.valueOf(receta.getUsuario()));
        List<Usuario>  usuarios = new ArrayList<>();
        optionalUsuario.ifPresent(usuarios::add);
        receta.setUsuarios(usuarios);

        return recetaRepository.save(receta);
    }

    @Override
    @Transactional
    public Optional<Receta> update(Long id, Receta receta) {
        Optional <Receta> recetaOptional = recetaRepository.findById(id);
        if(recetaOptional.isPresent()){
            Receta recetaDb = recetaOptional.orElseThrow();
            recetaDb.setNombre(receta.getNombre());
            recetaDb.setDescripcion(receta.getDescripcion());
            recetaDb.setTiempoPreparacion(receta.getTiempoPreparacion());
            recetaDb.setValoracion(receta.getValoracion());
            recetaDb.setPreparacion(receta.getPreparacion());
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
