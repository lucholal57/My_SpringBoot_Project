package com.SpringBootProject.app.Service.Category;

import com.SpringBootProject.app.Service.Crud.CrudAdminService;
import com.SpringBootProject.app.model.CategoryDTO;
import com.SpringBootProject.app.model.CategoryRequestDTO;

public interface CategoryAdminService extends CrudAdminService<CategoryDTO, CategoryRequestDTO,Long> {
}
