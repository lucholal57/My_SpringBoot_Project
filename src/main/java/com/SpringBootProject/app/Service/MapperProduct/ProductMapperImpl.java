package com.SpringBootProject.app.Service.MapperProduct;

import com.SpringBootProject.app.Entity.CategoryEntity;
import com.SpringBootProject.app.Entity.ProductEntity;
import com.SpringBootProject.app.Entity.UserEntity;
import com.SpringBootProject.app.Repository.CategoryRepository;
import com.SpringBootProject.app.Service.Category.CategoryAdminService;
import com.SpringBootProject.app.Service.Category.CategoryAdminServiceImpl;
import com.SpringBootProject.app.Service.MapperCategory.CategoryMapper;
import com.SpringBootProject.app.Utils.DateUtils;
import com.SpringBootProject.app.model.ProductDTO;
import com.SpringBootProject.app.model.ProductRequestDTO;
import com.SpringBootProject.app.model.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ProductMapperImpl implements  ProductMapper{
    /*
    LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductMapperImpl.class);

    private CategoryMapper categoryMapper;
    private CategoryRepository categoryRepository;

    public ProductMapperImpl(CategoryMapper theCategoryMapper, CategoryRepository theCategoryRepository) {
        this.categoryMapper = theCategoryMapper;
        this.categoryRepository = theCategoryRepository;
    }

    @Override
    public ProductEntity mapProduct(ProductRequestDTO theProduct) {
        LOGGER.trace(String.format("Mapping ProductEntity with attributes: %s to: ProductDTO", theProduct.toString()));
        ProductEntity response = new ProductEntity();
        response.setName(theProduct.getName());
        response.setPrice(BigDecimal.valueOf(theProduct.getPrice()));
        response.setQty(theProduct.getQty());
        response.setDescription(theProduct.getDescription());
        CategoryEntity category = categoryRepository.findByName(theProduct.getCategory().getName())
                .orElseGet(() -> categoryRepository.save(categoryMapper.mapCategory(theProduct.getCategory())));
        response.setCategory(category);

        response.setDateCreated(new Date());
        return response;
    }

    @Override
    public ProductEntity mapProduct(ProductDTO theProduct) {

        LOGGER.trace(String.format("Mapping ProductEntity with attributes: %s to: ProductDTO", theProduct.toString()));
        ProductEntity response = new ProductEntity();
        response.setId(theProduct.getId());
        response.setName(theProduct.getName());
        response.setPrice(BigDecimal.valueOf(theProduct.getPrice()));
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
        response.setDescription(theProduct.getDescription());
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



    public ProductEntity fill (final ProductDTO source, final ProductEntity target){
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setPrice(BigDecimal.valueOf(source.getPrice()));
        target.setQty(source.getQty());
        return target;
    }
}
