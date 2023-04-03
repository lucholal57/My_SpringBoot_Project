package com.SpringBootProject.app.controller;

import com.SpringBootProject.app.Service.Category.CategoryAdminService;
import com.SpringBootProject.app.Service.Product.ProductAdminService;


import com.SpringBootProject.app.api.ProductsApiDelegate;
import com.SpringBootProject.app.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

public class ProductController implements ProductsApiDelegate {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private ProductAdminService productAdminService;
    private CategoryAdminService categoryAdminService;

    public ProductController(ProductAdminService productAdminService,
                             CategoryAdminService categoryAdminService) {
        this.productAdminService = productAdminService;
        this.categoryAdminService = categoryAdminService;
    }


    public ResponseEntity<ProductResponseContainerDTO> createProduct(ProductRequestDTO productRequestDTO) {
        LOGGER.debug("CREAR");
        ProductResponseContainerDTO responseContainer = new ProductResponseContainerDTO();
        try {
            ProductDTO response = productAdminService.create(productRequestDTO);
            responseContainer.product(response);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseContainer);
        } catch (Exception e) {
            LOGGER.error(String.format("Ocurrio un error al crear el producto: \"%s\" ", productRequestDTO)
                    , e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseContainer);
        }
    }

    @Override
    public ResponseEntity<EmptyResponseDTO> deleteCategory(Long categoryId) {
        LOGGER.debug("BORRAR");
        EmptyResponseDTO responseContainer = new EmptyResponseDTO();
        try {
            categoryAdminService.delete(categoryId);
            EmptyResponseDTO response = new EmptyResponseDTO();
            response.setDate(OffsetDateTime.now());
            return ResponseEntity.status(HttpStatus.OK).body(responseContainer);
        } catch (Exception e) {
            LOGGER.error("Ocurrio un error al eliminar categoria");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseContainer);
        }
    }


    @Override
    public ResponseEntity<EmptyResponseDTO> deleteProduct(Long productId) {
        LOGGER.debug("BORRAR");
        EmptyResponseDTO responseContainer = new EmptyResponseDTO();
        try {
            productAdminService.delete(productId);
            //responseContainer.setType("Delete");
            responseContainer.setDate(OffsetDateTime.now());
            return ResponseEntity.status(HttpStatus.OK).body(responseContainer);
        } catch (Exception e) {
            LOGGER.error("Ocurrio un error al eliminar producto");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseContainer);
        }
    }


    public ResponseEntity<CategoryResponseContainerDTO> createCategory(CategoryRequestDTO categoryRequestDTO) {
        LOGGER.debug("CREAR");
        CategoryResponseContainerDTO responseContainer = new CategoryResponseContainerDTO();
        try {
            CategoryDTO response = categoryAdminService.create(categoryRequestDTO);
            responseContainer.category(response);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseContainer);
        } catch (Exception e) {
            LOGGER.error(String.format("Ocurrio un error al crear la categoria: \"%s\" ", categoryRequestDTO)
                    , e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseContainer);
        }
    }


    public ResponseEntity<ProductListResponseContainerDTO> getAllProducts() {
        LOGGER.debug("LISTAR PRODUCTO");
        ProductListResponseContainerDTO responseContainer = new ProductListResponseContainerDTO();
        try {
            List<ProductDTO> listProduct = productAdminService.getAll();
            responseContainer.products(listProduct);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseContainer);
        } catch (Exception e) {
            LOGGER.error("Ocurrio un error al listar productos", e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseContainer);
        }
    }


    public ResponseEntity<CategoryListResponseContainerDTO> getAllCategory() {
        LOGGER.debug("LISTAR CATEGORIAS");
        CategoryListResponseContainerDTO responseContainer = new CategoryListResponseContainerDTO();
        try {
            List<CategoryDTO> listCategory = categoryAdminService.getAll();
            responseContainer.categories(listCategory);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseContainer);
        } catch (Exception e) {
            LOGGER.error("Ocurrio un error al listar categorias", e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseContainer);
        }
    }

    public ResponseEntity<CategoryResponseContainerDTO> getCategory(Long categoryId) {
        LOGGER.trace("BUSQUEDA POR ID");
        CategoryResponseContainerDTO responseContainer = new CategoryResponseContainerDTO();
        try {
            CategoryDTO response = categoryAdminService.get(categoryId);
            responseContainer.category(response);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseContainer);
        } catch (Exception e) {
            LOGGER.error("Ocurrio un error al buscar categoria", e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseContainer);
        }
    }

    public ResponseEntity<ProductResponseContainerDTO> getProduct(Long productId) {
        LOGGER.trace("BUSQUEDA POR ID");
        ProductResponseContainerDTO responseContainer = new ProductResponseContainerDTO();
        try {
            ProductDTO response = productAdminService.get(productId);
            responseContainer.product(response);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseContainer);
        } catch (Exception e) {
            LOGGER.error("Ocurrio un error al buscar producto", e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseContainer);
        }
    }


    public ResponseEntity<CategoryResponseContainerDTO> updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        LOGGER.debug("UPDATE");
        CategoryResponseContainerDTO responseContainer = new CategoryResponseContainerDTO();
        //Validamos que el id que estamos buscando sea el mismo al que vamos a actualizar desde swagger
        if (!Objects.equals(categoryId, categoryDTO.getId())) {
            //Si son distintos lo inforamos en un LOG
            LOGGER.error("El id que busca es distinto a la categoria que quiere actualizar");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseContainer);
        }
        try {
            CategoryDTO response = categoryAdminService.update(categoryDTO);
            responseContainer.category(response);
            return ResponseEntity.status(HttpStatus.OK).body(responseContainer);
        } catch (Exception e) {
            LOGGER.error("Ocurrio un error al actualizar category");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseContainer);
        }
    }

    @Override
    public ResponseEntity<ProductResponseContainerDTO> updateProduct(Long productId, ProductDTO productDTO) {
        LOGGER.debug("UPDATE");
        ProductResponseContainerDTO responseContainer = new ProductResponseContainerDTO();
        //Validamos que el id que estamos buscando sea el mismo al que vamos a actualizar desde swagger
        if (!Objects.equals(productId, productDTO.getId())) {
            //Si son distintos lo inforamos en un LOG
            LOGGER.error("El id que busca es distinto al producto que quiere actualizar");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseContainer);
        }
        try {
            ProductDTO response = productAdminService.update(productDTO);
            responseContainer.product(response);
            return ResponseEntity.status(HttpStatus.OK).body(responseContainer);
        } catch (Exception e) {
            LOGGER.error("Ocurrio un error al actualizar producto");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseContainer);
        }
    }

}
