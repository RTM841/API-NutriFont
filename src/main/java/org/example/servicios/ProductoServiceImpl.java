package org.example.servicios;

import org.example.entidades.Categoria;
import org.example.entidades.Producto;
import org.example.entidades.Supermercado;
import org.example.repositorios.CategoriaRepository;
import org.example.repositorios.ProductoRepository;
import org.example.repositorios.SupermercadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProdcutoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private SupermercadoRepository supermercadoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {return productoRepository.findAll();}

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> findById(Long id) {return productoRepository.findById(id);}


    @Override
    @Transactional
    public Producto create(Producto producto) {
        //Añadir supermecado
        Optional<Supermercado> optionalSupermercado = supermercadoRepository.findById(Long.valueOf(1));
        List<Supermercado>  supermercados = new ArrayList<>();
        optionalSupermercado.ifPresent(supermercados::add);
        producto.setSupermercados(supermercados);

        //Añadir categoria
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(Long.valueOf(producto.getCategoria()));
        List<Categoria>  categorias = new ArrayList<>();
        optionalCategoria.ifPresent(categorias::add);
        producto.setCategorias(categorias);

        return productoRepository.save(producto);
    }

    @Override
    @Transactional
    public Optional<Producto> update(Long id, Producto producto) {
        Optional <Producto> productoOptional = productoRepository.findById(id);
        if(productoOptional.isPresent()){
            Producto productoDb = productoOptional.orElseThrow();
            productoDb.setNombre(producto.getNombre());
            productoDb.setImgPath(producto.getImgPath());
            productoDb.setDescripcion(producto.getDescripcion());
            productoDb.setDisponibilidad(producto.getDisponibilidad());
            productoDb.setPrecio(producto.getPrecio());
            productoDb.setCalorias(producto.getCalorias());
            productoDb.setGrasaSaturada(producto.getGrasaSaturada());
            productoDb.setHidratos(producto.getHidratos());
            productoDb.setAzucares(producto.getAzucares());
            productoDb.setProteinas(producto.getProteinas());
            productoDb.setSal(producto.getSal());
            productoDb.setGluten(producto.isGluten());
            productoDb.setCrustaceo(producto.isCrustaceo());
            productoDb.setHuevo(producto.isHuevo());
            productoDb.setPescado(producto.isPescado());
            productoDb.setCacahuetes(producto.isCacahuetes());
            productoDb.setSoja(producto.isSoja());
            productoDb.setLeche(producto.isLeche());
            productoDb.setProteinaLeche(producto.isProteinaLeche());
            productoDb.setLactosa(producto.isLactosa());
            productoDb.setFrutosCascara(producto.isFrutosCascara());
            productoDb.setApio(producto.isApio());
            productoDb.setMostaza(producto.isMostaza());
            productoDb.setSesamo(producto.isSesamo());
            productoDb.setSulfitos(producto.isSulfitos());
            productoDb.setAltramuces(producto.isAltramuces());
            productoDb.setMoluscos(producto.isMoluscos());
            productoDb.setCategoria(producto.getCategoria());
            return Optional.of(productoRepository.save(productoDb));
        }
        return productoOptional;
    }


    @Override
    public Optional<Producto> delete(Long id) {
        // Primero, eliminar las relaciones en las tablas intermedias
        productoRepository.deleteSupermercadoRelations(id);
        productoRepository.deleteCategoriaRelations(id);

        Optional <Producto> productoOptional = productoRepository.findById(id);
        productoOptional.ifPresent( productoDb -> productoRepository.delete(productoDb));
        return productoOptional;
    }

}
