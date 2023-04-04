package com.SpringBootProject.app.Service.MapperShop;

import com.SpringBootProject.app.Entity.ProductEntity;
import com.SpringBootProject.app.Entity.ShopEntity;
import com.SpringBootProject.app.model.ProductDTO;
import com.SpringBootProject.app.model.ProductRequestDTO;
import com.SpringBootProject.app.model.ShopDTO;
import com.SpringBootProject.app.model.ShopRequestDTO;

public interface ShopMapper {

    /*
   Metodo para mapear de RequestDTO a Entity
    */
    ShopEntity mapShop(final ShopRequestDTO theShop);
    /*
    Metodo para mapear de DTO a Entity
     */
    ShopEntity mapShop(final ShopDTO theShop);
    /*
    Metodo para mapear de Entity a DTO
     */
    ShopDTO mapShop(final ShopEntity theShop);
}
