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
import java.util.Objects;

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
    public ResponseEntity<ResponseContainerDTO> getCategory(Long categoryId) {
        Long start = System.currentTimeMillis();
        LOGGER.trace("BUSQUEDA POR ID");
        ResponseContainerDTO responseContainer = new ResponseContainerDTO();
        try{
           CategoryDTO response = categoryAdminService.get(categoryId);
           responseContainer.data(response);
           responseContainer.setMeta(buildMeta(start));
           return ResponseEntity.status(HttpStatus.CREATED).body(responseContainer);
        }catch (Exception e) {
            LOGGER.error("Ocurrio un error al buscar categoria", e);
            return buildErrorResponse(responseContainer, HttpStatus.NO_CONTENT, e, "A2", start);
        }
    }

    public ResponseEntity<ResponseContainerDTO> getProduct(Long productId) {
        Long start = System.currentTimeMillis();
        LOGGER.trace("BUSQUEDA POR ID");
        ResponseContainerDTO responseContainer = new ResponseContainerDTO();
        try{
            ProductDTO response = productAdminService.get(productId);
            responseContainer.data(response);
            responseContainer.setMeta(buildMeta(start));
            return ResponseEntity.status(HttpStatus.CREATED).body(responseContainer);
        }catch (Exception e) {
            LOGGER.error("Ocurrio un error al buscar producto", e);
            return buildErrorResponse(responseContainer, HttpStatus.NO_CONTENT, e, "A2", start);
        }
    }

    public ResponseEntity<ResponseContainerDTO> updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        Long start = System.currentTimeMillis();
        LOGGER.debug("UPDATE");
        ResponseContainerDTO responseContainer =  new ResponseContainerDTO();
        //Validamos que el id que estamos buscando sea el mismo al que vamos a actualizar desde swagger
        if(!Objects.equals(categoryId, categoryDTO.getId())){
            //Si son distintos lo inforamos en un LOG
            LOGGER.error("El id que busca es distinto a la categoria que quiere actualizar");
            return buildErrorResponse(responseContainer,HttpStatus.BAD_REQUEST,null,"A2",start);
        }
        try{
            CategoryDTO response = categoryAdminService.update(categoryDTO);
            responseContainer.data(response);
            responseContainer.setMeta(buildMeta(start));
            return ResponseEntity.status(HttpStatus.OK).body(responseContainer);
        }catch (Exception e){
            LOGGER.error("Ocurrio un error al actualizar category");
            return buildErrorResponse(responseContainer,HttpStatus.BAD_REQUEST,e,"A2",start);
        }
    }

    public ResponseEntity<ResponseContainerDTO> updateProduct(Long productId, ProductDTO productDTO) {
        Long start = System.currentTimeMillis();
        LOGGER.debug("UPDATE");
        ResponseContainerDTO responseContainer =  new ResponseContainerDTO();
        //Validamos que el id que estamos buscando sea el mismo al que vamos a actualizar desde swagger
        if(!Objects.equals(productId, productDTO.getId())){
            //Si son distintos lo inforamos en un LOG
            LOGGER.error("El id que busca es distinto al producto que quiere actualizar");
            return buildErrorResponse(responseContainer,HttpStatus.BAD_REQUEST,null,"A2",start);
        }
        try{
            ProductDTO response = productAdminService.update(productDTO);
            responseContainer.data(response);
            responseContainer.setMeta(buildMeta(start));
            return ResponseEntity.status(HttpStatus.OK).body(responseContainer);
        }catch (Exception e){
            LOGGER.error("Ocurrio un error al actualizar producto");
            return buildErrorResponse(responseContainer,HttpStatus.BAD_REQUEST,e,"A2",start);
        }
    }

    public ResponseEntity<EmptyResponseDTO> deleteCategory(Long categoryId) {
        return ProductsApiDelegate.super.deleteCategory(categoryId);
    }

    public ResponseEntity<EmptyResponseDTO> deleteProduct(Long productId) {
        return ProductsApiDelegate.super.deleteProduct(productId);
    }




}
