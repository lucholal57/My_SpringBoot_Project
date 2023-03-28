package com.SpringBootProject.app.controller;

import com.SpringBootProject.app.Service.Category.CategoryAdminService;
import com.SpringBootProject.app.Service.Product.ProductAdminService;
import com.SpringBootProject.app.api.ProductsApiDelegate;
import com.SpringBootProject.app.model.ProductDTO;
import com.SpringBootProject.app.model.ProductRequestDTO;
import com.SpringBootProject.app.model.ResponseContainerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
}
