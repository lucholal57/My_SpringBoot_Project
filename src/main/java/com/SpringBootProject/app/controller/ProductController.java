package com.SpringBootProject.app.controller;

import com.SpringBootProject.app.Service.Category.CategoryAdminService;
import com.SpringBootProject.app.Service.Product.ProductAdminService;
import com.SpringBootProject.app.api.ProductsApiDelegate;
import com.SpringBootProject.app.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ProductController extends BaseController implements ProductsApiDelegate {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private ProductAdminService productAdminService;
    private CategoryAdminService categoryAdminService;

    public ProductController(ProductAdminService productAdminService,
                             CategoryAdminService categoryAdminService) {
        this.productAdminService = productAdminService;
        this.categoryAdminService = categoryAdminService;
    }

    public ResponseEntity<ResponseContainerDTO> createProduct(ProductRequestDTO productRequestDTO) {
        Long start = System.currentTimeMillis();
        LOGGER.debug("CREAR");
        ResponseContainerDTO responseContainer = new ResponseContainerDTO();
        try {
            ProductDTO response = productAdminService.create(productRequestDTO);
            responseContainer.data(response);
            responseContainer.setMeta(buildMeta(start));
            return ResponseEntity.status(HttpStatus.CREATED).body(responseContainer);
        } catch (Exception e) {
            LOGGER.error(String.format("Ocurrio un error al crear el producto: \"%s\" ", productRequestDTO)
                    , e);
            return buildErrorResponse(responseContainer, HttpStatus.BAD_REQUEST, e, "A1", start);
        }
    }

    public ResponseEntity<ResponseContainerDTO> createCategory(CategoryRequestDTO categoryRequestDTO) {
        Long start = System.currentTimeMillis();
        LOGGER.debug("CREAR");
        ResponseContainerDTO responseContainer = new ResponseContainerDTO();
        try {
            CategoryDTO response = categoryAdminService.create(categoryRequestDTO);
            responseContainer.data(response);
            responseContainer.setMeta(buildMeta(start));
            return ResponseEntity.status(HttpStatus.CREATED).body(responseContainer);
        } catch (Exception e) {
            LOGGER.error(String.format("Ocurrio un error al crear la categoria: \"%s\" ", categoryRequestDTO)
                    , e);
            return buildErrorResponse(responseContainer, HttpStatus.BAD_REQUEST, e, "A1", start);
        }
    }
    public ResponseEntity<ResponseContainerDTO> getAllProducts() {
        Long start = System.currentTimeMillis();
        LOGGER.debug("LISTAR PRODUCTO");
        ResponseContainerDTO responseContainer = new ResponseContainerDTO();
        try{
            List<ProductDTO> listProduct = productAdminService.getAll();
            ProductListDTO response = new ProductListDTO();
            response.setItems(listProduct);
            responseContainer.data(response);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseContainer);
        } catch (Exception e){
            LOGGER.error("Ocurrio un error al listar productos",e);
            return buildErrorResponse(responseContainer,HttpStatus.NOT_IMPLEMENTED,e,"A2",start);
        }
    }

    public ResponseEntity<ResponseContainerDTO> getAllCategory() {
        Long start = System.currentTimeMillis();
        LOGGER.debug("LISTAR CATEGORIAS");
        ResponseContainerDTO responseContainer = new ResponseContainerDTO();
        try{
            List<CategoryDTO> listCategory = categoryAdminService.getAll();
            CategoryListDTO response = new CategoryListDTO();
            response.setItems(listCategory);
            responseContainer.data(response);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseContainer);
        } catch (Exception e){
            LOGGER.error("Ocurrio un error al listar categorias",e);
            return buildErrorResponse(responseContainer,HttpStatus.NOT_IMPLEMENTED,e,"A2",start);
        }
    }


}
