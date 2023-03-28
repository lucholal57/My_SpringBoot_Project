package com.SpringBootProject.app.Service.Product;

import com.SpringBootProject.app.model.ProductDTO;
import com.SpringBootProject.app.model.ProductRequestDTO;
import com.SpringBootProject.app.Service.Crud.CrudAdminService;

public interface ProductAdminService extends CrudAdminService<ProductDTO, ProductRequestDTO,Long> {
}
