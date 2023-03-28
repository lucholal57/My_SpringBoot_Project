package com.SpringBootProject.app.Service.MapperCategory;

import com.SpringBootProject.app.Entity.CategoryEntity;
import com.SpringBootProject.app.Repository.CategoryRepository;
import com.SpringBootProject.app.Utils.DateUtils;
import com.SpringBootProject.app.model.CategoryDTO;
import com.SpringBootProject.app.model.CategoryRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public class CategoryMapperImpl implements CategoryMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryMapperImpl.class);
    private CategoryRepository categoryRepository;

    public CategoryMapperImpl(CategoryRepository theCategoryRepository) {
        this.categoryRepository = theCategoryRepository;
    }

    @Override
    public CategoryEntity mapCategory(CategoryRequestDTO theCategory) {
        LOGGER.trace(String.format("Mapeo de CategoryEntity a DTO : %s", theCategory.toString()));
        CategoryEntity response = new CategoryEntity();
        response.setName(theCategory.getName());
        Long parentCategoryId = theCategory.getParent();
        if(parentCategoryId !=null && parentCategoryId > 0){
            Optional<CategoryEntity> opParent = categoryRepository.findById(parentCategoryId);
            if(opParent.isEmpty()){
                throw new RuntimeException(String.format("No se puede setear subcategoria por que no existe" +
                        "categoria padre"));
            }
            CategoryEntity parent = opParent.get();
            response.setParent(parent);
        }
        response.setDate_created(new Date());
        return response;
    }

    @Override
    public CategoryEntity mapCategory(CategoryDTO theCategory) {
        LOGGER.trace(String.format("Mapeo de CategoryRequestDTO a Entity:  %s",
                theCategory.toString()));
        CategoryEntity response = new CategoryEntity();
        response.setId(theCategory.getId());
        response.setName(theCategory.getName());
        Long parentCategoryId = theCategory.getParent();
        if(parentCategoryId !=null && parentCategoryId > 0){
            Optional<CategoryEntity> opParent = categoryRepository.findById(parentCategoryId);
            if(opParent.isEmpty()){
                throw new RuntimeException(String.format("No se puede setear subcategoria por que no existe" +
                        "categoria padre"));
            }
            CategoryEntity parent = opParent.get();
            response.setParent(parent);
        }
        if(theCategory.getDateCreated() != null){
            response.setDate_created(DateUtils.toDate(theCategory.getDateCreated()));
        }
        if(theCategory.getDateDeleted() != null){
            response.setDate_deleted(DateUtils.toDate(theCategory.getDateDeleted()));
        }
        return response;
    }

    @Override
    public CategoryDTO mapCategory(CategoryEntity theCategory) {
        LOGGER.trace(String.format("Mapping CategoryDTO with attributes: %s to: CategoryEntity", theCategory.toString()));
        CategoryDTO response = new CategoryDTO();
        response.setId(theCategory.getId());
        response.setName(theCategory.getName());
        if (theCategory.getParent() != null) {
            response.setParent(theCategory.getParent().getId());
        }
        if (theCategory.getDate_created() != null) {
            LocalDate createdLocalDate = DateUtils.toLocalDate(theCategory.getDate_created());
            response.setDateCreated(createdLocalDate);
        }
        if (theCategory.getDate_deleted() != null) {
            LocalDate deletedLocalDate = DateUtils.toLocalDate(theCategory.getDate_deleted());
            response.setDateDeleted(deletedLocalDate);
        }

        return response;
    }
}
