package com.SpringBootProject.app.controller;


import com.SpringBootProject.app.Repository.ShopRepository;
import com.SpringBootProject.app.Service.Shop.ShopAdminService;
import com.SpringBootProject.app.api.ShopsApiDelegate;
import com.SpringBootProject.app.model.ShopDTO;
import com.SpringBootProject.app.model.ShopListResponseContainerDTO;
import com.SpringBootProject.app.model.ShopRequestDTO;
import com.SpringBootProject.app.model.ShopResponseContainerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ShopController implements ShopsApiDelegate {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShopController.class);

    private ShopAdminService shopAdminService;

    public ShopController(ShopAdminService shopAdminService) {
        this.shopAdminService = shopAdminService;
    }

    @Override
    public ResponseEntity<ShopResponseContainerDTO> createShop(ShopRequestDTO shopRequestDTO) {
        LOGGER.debug("CREAR");
        ShopResponseContainerDTO responseContainer = new ShopResponseContainerDTO();
        try {
            ShopDTO response = shopAdminService.create(shopRequestDTO);
            responseContainer.shop(response);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseContainer);
        } catch (Exception e) {
            LOGGER.error(String.format("Ocurrio un error al crear el shop: \"%s\" ", shopRequestDTO)
                    , e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseContainer);
        }
    }

    @Override
    public ResponseEntity<ShopListResponseContainerDTO> getAllShop() {
        LOGGER.debug("LISTAR SHOP");
        ShopListResponseContainerDTO responseContainer = new ShopListResponseContainerDTO();
        try{
            List<ShopDTO> listShop = shopAdminService.getAll();
            responseContainer.shops(listShop);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseContainer);
        }catch(Exception e){
            LOGGER.error("Ocurrio un error al listar shops", e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseContainer);
        }
    }
}
