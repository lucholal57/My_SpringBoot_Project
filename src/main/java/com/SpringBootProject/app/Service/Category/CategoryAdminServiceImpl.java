package com.SpringBootProject.app.Service.Category;

import com.SpringBootProject.app.Entity.CategoryEntity;
import com.SpringBootProject.app.Entity.UserEntity;
import com.SpringBootProject.app.Repository.CategoryRepository;
import com.SpringBootProject.app.Service.Crud.CrudAdminService;
import com.SpringBootProject.app.Service.MapperCategory.CategoryMapper;
import com.SpringBootProject.app.model.CategoryDTO;
import com.SpringBootProject.app.model.CategoryRequestDTO;
import com.SpringBootProject.app.model.UserDTO;
import com.SpringBootProject.app.model.UserRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

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
        LOGGER.trace("Busqueda de categoria por ID");
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(id);
        CategoryEntity category = optionalCategory.orElseThrow(NoSuchElementException::new);
        return categoryMapper.mapCategory(category);
    }

    /*
   Metodo para mostrar todas las Categorias
    */
    @Override
    public List<CategoryDTO> getAll() throws RuntimeException {
        LOGGER.trace(String.format("Listado de Categorias"));
        Iterable<CategoryEntity> categories = categoryRepository.findAll();
        Iterator<CategoryEntity> iter = categories.iterator();
        List<CategoryDTO> response = new ArrayList<>();
        while (iter.hasNext()){
            response.add(categoryMapper.mapCategory(iter.next()));
        }
        return response;
    }
    @Override
    public CategoryDTO update(CategoryDTO theCategory) throws RuntimeException {
        LOGGER.trace("Busqueda de categoria por ID");
        //Buscamos el usuario por el id que recibimos
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(theCategory.getId());
        /*
        Validamos que en la variable optionalCategory contenga la categoria si es que existe en nuestra
        db de no existir lanzamos una excepcion
         */
        if(optionalCategory.isEmpty()){
            throw new RuntimeException("La Categoria no existe");
        }
        // Guardamos el usuario que obtuvimos en la variable user de tipo UserEntity
        CategoryEntity category = optionalCategory.get();
        //Utilizamos el Fill para setear en el userEntity el UserDTO
        CategoryEntity categoryFilled =  categoryMapper.fill(theCategory,category);
        //Una vez seteado guardamos el usuario con el metodo save del repository de usuario
        CategoryEntity categorySaved = categoryRepository.save(categoryFilled);
        //Y retornamos el usuario guardado pero usando el mapUser para pasar de Entity a DTO
        return categoryMapper.mapCategory(categorySaved);
    }

    @Override
    public void delete(Long id) throws RuntimeException {
        LOGGER.trace("Busqueda de categoria por ID para eliminar");
        //Buscamos el usuario por el id que recibimos
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isEmpty()){
            throw new RuntimeException("La Categoria no existe");
        }
        // Guardamos el usuario que obtuvimos en la variable user de tipo UserEntity
        CategoryEntity category = optionalCategory.get();
        categoryRepository.delete(category);
    }
}
