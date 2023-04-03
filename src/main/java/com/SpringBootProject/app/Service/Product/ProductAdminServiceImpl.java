package com.SpringBootProject.app.Service.Product;

import com.SpringBootProject.app.Entity.ProductEntity;
import com.SpringBootProject.app.Repository.ProductRepository;
import com.SpringBootProject.app.Service.MapperProduct.ProductMapper;
import com.SpringBootProject.app.model.ProductDTO;
import com.SpringBootProject.app.model.ProductRequestDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ProductAdminServiceImpl implements ProductAdminService {
    public static final Logger LOGGER = LoggerFactory.getLogger(ProductAdminServiceImpl.class);

    private ProductRepository productRepository;
    private ProductMapper productMapper;

    public ProductAdminServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDTO create(ProductRequestDTO theProduct) throws RuntimeException {
        //Creacion de producto utilizando Mapper
        LOGGER.trace(String.format("Creacion del producto : %s ", theProduct.toString()));
        ProductEntity toCreate = productMapper.mapProduct(theProduct);
        ProductEntity created = productRepository.save(toCreate);
        return productMapper.mapProduct(created);
    }

    @Override
    public ProductDTO get(Long id) throws RuntimeException {
        LOGGER.trace("Busqueda de producto por ID");
        Optional<ProductEntity> optionalProduct = productRepository.findById(id);
        ProductEntity product = optionalProduct.orElseThrow(NoSuchElementException::new);
        return productMapper.mapProduct(product);
    }

    @Override
    public List<ProductDTO> getAll() throws RuntimeException {
        LOGGER.trace("Lista de productos");
        Iterable<ProductEntity> products = productRepository.findAll();
        Iterator<ProductEntity> iter = products.iterator();
        List<ProductDTO> respose = new ArrayList<>();
        while (iter.hasNext()) {
            respose.add(productMapper.mapProduct(iter.next()));
        }
        return respose;
    }

    @Override
    public ProductDTO update(ProductDTO theProduct) throws RuntimeException {
        LOGGER.trace("Busqueda de producto por ID");
        //Buscamos el producto por el id que recibimos
        Optional<ProductEntity> optionalProduct = productRepository.findById(theProduct.getId());
        /*
        Validamos que en la variable optionalUser contenga el usuario si es que existe en nuestra
        db de no existir lanzamos una exepcion
         */
        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("El producto no existe");
        }
        // Guardamos el producto que obtuvimos en la variable user de tipo UserEntity
        ProductEntity product = optionalProduct.get();
        //Utilizamos el Fill para setear en el userEntity el UserDTO
        ProductEntity productFilled = productMapper.fill(theProduct, product);
        //Una vez seteado guardamos el usuario con el metodo save del repository de usuario
        ProductEntity productSaved = productRepository.save(productFilled);
        //Y retornamos el usuario guardado pero usando el mapUser para pasar de Entity a DTO
        return productMapper.mapProduct(productSaved);
    }

    @Override
    public void delete(Long id) throws RuntimeException {
        LOGGER.trace("Busqueda de producto por ID para eliminar");
        //Buscamos el usuario por el id que recibimos
        Optional<ProductEntity> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("El producto no existe");
        }
        // Guardamos el usuario que obtuvimos en la variable user de tipo UserEntity
        ProductEntity product = optionalProduct.get();
        productRepository.delete(product);

    }

}
