package com.SpringBootProject.app.Service.Shop;

import com.SpringBootProject.app.Service.Crud.CrudAdminService;
import com.SpringBootProject.app.model.ShopDTO;
import com.SpringBootProject.app.model.ShopRequestDTO;

public interface ShopService extends CrudAdminService<ShopDTO, ShopRequestDTO,Long>  {
}
