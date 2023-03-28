package com.SpringBootProject.app.Service.Category;

import com.SpringBootProject.app.Entity.CategoryEntity;
import com.SpringBootProject.app.Repository.CategoryRepository;
import com.SpringBootProject.app.Service.Crud.CrudAdminService;
import com.SpringBootProject.app.Service.MapperCategory.CategoryMapper;
import com.SpringBootProject.app.model.CategoryDTO;
import com.SpringBootProject.app.model.CategoryRequestDTO;
import com.SpringBootProject.app.model.UserDTO;
import com.SpringBootProject.app.model.UserRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CategoryAdminServiceImpl implements CategoryAdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryAdminServiceImpl.class);
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    public CategoryAdminServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }
    @Override
    public CategoryDTO create(CategoryRequestDTO theCategory) throws RuntimeException {
        LOGGER.trace(String.format("Creacion de Categoria: %s", theCategory.toString()));
        CategoryEntity toCreate = categoryMapper.mapCategory(theCategory);
        CategoryEntity created = categoryRepository.save(toCreate);
        return categoryMapper.mapCategory(created);
    }
    @Override
    public CategoryDTO get(Long id) throws RuntimeException {
        return null;
    }

    /*
   Metodo para mostrar todas las Categorias
    */
    @Override
    public List<CategoryDTO> getAll() throws RuntimeException {
        LOGGER.trace(String.format("Listado de Categorias"));
        Iterable<CategoryEntity> products = categoryRepository.findAll();
        Iterator<CategoryEntity> iter = products.iterator();
        List<CategoryDTO> response = new ArrayList<>();
        while (iter.hasNext()){
            response.add(categoryMapper.mapCategory(iter.next()));
        }
        return response;
    }
    @Override
    public CategoryDTO update(CategoryDTO element) throws RuntimeException {
        return null;
    }

    @Override
    public void delete(Long id) throws RuntimeException {

    }
}
