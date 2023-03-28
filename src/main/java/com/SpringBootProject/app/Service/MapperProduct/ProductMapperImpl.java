package com.SpringBootProject.app.Service.MapperProduct;

import com.SpringBootProject.app.Entity.ProductEntity;
import com.SpringBootProject.app.Service.MapperCategory.CategoryMapper;
import com.SpringBootProject.app.Utils.DateUtils;
import com.SpringBootProject.app.model.ProductDTO;
import com.SpringBootProject.app.model.ProductRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class ProductMapperImpl implements  ProductMapper{
    /*
    LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductMapperImpl.class);
    private CategoryMapper categoryMapper;

    public ProductMapperImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public ProductEntity mapProduct(ProductRequestDTO theProduct) {
        LOGGER.trace(String.format("Mapping ProductEntity with attributes: %s to: ProductDTO", theProduct.toString()));
        ProductEntity response = new ProductEntity();
        response.setName(theProduct.getName());
        response.setPrice(BigDecimal.valueOf(theProduct.getPrice()));
        response.setQty(theProduct.getQty());
        response.setDescription(theProduct.getName());
        response.setCategory(categoryMapper.mapCategory(theProduct.getCategory()));
        response.setDateCreated(new Date());
        return response;
    }

    @Override
    public ProductEntity mapProduct(ProductDTO theProduct) {

        LOGGER.trace(String.format("Mapping ProductEntity with attributes: %s to: ProductDTO", theProduct.toString()));
        ProductEntity response = new ProductEntity();
        response.setId(theProduct.getId());
        response.setName(theProduct.getName());
        //response.setPrice(BigDecimal.valueOf(theProduct.getPrice()));
        response.setQty(theProduct.getQty());
        response.setDescription(theProduct.getName());
        response.setCategory(categoryMapper.mapCategory(theProduct.getCategory()));
        if (theProduct.getDateCreated() != null) {
            response.setDateCreated(DateUtils.toDate(theProduct.getDateCreated()));
        }
        if (theProduct.getDateDeleted() != null) {
            response.setDateDeleted(DateUtils.toDate(theProduct.getDateDeleted()));
        }
        return response;
    }

    @Override
    public ProductDTO mapProduct(ProductEntity theProduct) {
        LOGGER.trace(String.format("Mapping ProductDTO with attributes: %s to: ProductEntity", theProduct.toString()));
        ProductDTO response = new ProductDTO();
        response.setId(theProduct.getId());
        response.setName(theProduct.getName());
        response.setPrice(theProduct.getPrice().doubleValue());
        response.setQty(theProduct.getQty());
        response.setDescription(theProduct.getName());
        response.setCategory(categoryMapper.mapCategory(theProduct.getCategory()));
        if (theProduct.getDateCreated() != null) {
            LocalDate createdLocalDate = DateUtils.toLocalDate(theProduct.getDateCreated());
            response.setDateCreated(createdLocalDate);
        }
        if (theProduct.getDateDeleted() != null) {
            LocalDate deletedLocalDate = DateUtils.toLocalDate(theProduct.getDateDeleted());
            response.setDateDeleted(deletedLocalDate);
        }

        return response;
    }
}
