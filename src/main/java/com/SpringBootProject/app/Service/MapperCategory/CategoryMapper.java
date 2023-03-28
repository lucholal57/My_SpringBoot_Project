package com.SpringBootProject.app.Service.MapperCategory;

import com.SpringBootProject.app.Entity.CategoryEntity;
import com.SpringBootProject.app.model.CategoryDTO;
import com.SpringBootProject.app.model.CategoryRequestDTO;

public interface CategoryMapper {
    /*
    Metodo para mapear RequestDTO a entity
     */
    CategoryEntity mapCategory(final CategoryRequestDTO theCategory);
    /*
    Metodo para mapear de DTO a entity
     */
    CategoryEntity mapCategory(final CategoryDTO theCategory);
    /*
    Metodo para mapear de DTO a entity
     */
    CategoryDTO mapCategory(final CategoryEntity theCategory);
}
