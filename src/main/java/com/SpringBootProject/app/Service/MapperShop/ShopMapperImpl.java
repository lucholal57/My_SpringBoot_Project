package com.SpringBootProject.app.Service.MapperShop;

import com.SpringBootProject.app.Entity.ShopEntity;
import com.SpringBootProject.app.Repository.ShopRepository;
import com.SpringBootProject.app.model.ShopDTO;
import com.SpringBootProject.app.model.ShopRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShopMapperImpl implements ShopMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShopMapperImpl.class);

    private ShopMapper shopMapper;
    private ShopRepository shopRepository;

    public ShopMapperImpl(ShopMapper shopMapper, ShopRepository shopRepository) {
        this.shopMapper = shopMapper;
        this.shopRepository = shopRepository;
    }


    @Override
    public ShopEntity mapShop(ShopRequestDTO theShop) {

        return null;
    }

    @Override
    public ShopEntity mapShop(ShopDTO theShop) {
        return null;
    }

    @Override
    public ShopDTO mapShop(ShopEntity theShop) {
        return null;
    }
}
