package com.SpringBootProject.app.Service.Product;

import com.SpringBootProject.app.Entity.ProductEntity;
import com.SpringBootProject.app.Repository.ProductRepository;
import com.SpringBootProject.app.Service.MapperProduct.ProductMapper;
import com.SpringBootProject.app.model.ProductDTO;
import com.SpringBootProject.app.model.ProductRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        return null;
    }
    @Override
    public List<ProductDTO> getAll() throws RuntimeException {
        LOGGER.trace("Lista de productos");
        Iterable<ProductEntity> products = productRepository.findAll();
        Iterator<ProductEntity> iter = products.iterator();
        List<ProductDTO> respose = new ArrayList<>();
        while (iter.hasNext()){
            respose.add(productMapper.mapProduct(iter.next()));
        }
        return respose;
    }
    @Override
    public ProductDTO update(ProductDTO element) throws RuntimeException {
        return null;
    }

    @Override
    public void delete(Long id) throws RuntimeException {

    }
}
