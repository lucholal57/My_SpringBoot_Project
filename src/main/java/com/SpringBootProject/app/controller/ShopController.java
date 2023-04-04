package com.SpringBootProject.app.controller;


import com.SpringBootProject.app.Repository.ShopRepository;
import com.SpringBootProject.app.api.ShopsApiDelegate;
import com.SpringBootProject.app.model.ShopDTO;
import com.SpringBootProject.app.model.ShopRequestDTO;
import org.springframework.http.ResponseEntity;

public class ShopController implements ShopsApiDelegate {

    private ShopRepository shopRepository;


    @Override
    public ResponseEntity<ShopDTO> createShop(ShopRequestDTO shopRequestDTO) {
        return ShopsApiDelegate.super.createShop(shopRequestDTO);
    }
}
