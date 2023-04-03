package com.SpringBootProject.app.Service.MapperProduct;

import com.SpringBootProject.app.Entity.ProductEntity;
import com.SpringBootProject.app.model.ProductDTO;
import com.SpringBootProject.app.model.ProductRequestDTO;


public interface ProductMapper {
    /*
    Metodo para mapear de RequestDTO a Entity
     */
    ProductEntity mapProduct(final ProductRequestDTO theProduct);
    /*
    Metodo para mapear de DTO a Entity
     */
    ProductEntity mapProduct(final ProductDTO theProduct);
    /*
    Metodo para mapear de Entity a DTO
     */
    ProductDTO mapProduct(final ProductEntity theProduct);

    ProductEntity fill (final ProductDTO source, final ProductEntity target);


}
