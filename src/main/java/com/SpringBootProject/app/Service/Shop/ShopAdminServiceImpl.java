package com.SpringBootProject.app.Service.Shop;

import com.SpringBootProject.app.Entity.ShopEntity;
import com.SpringBootProject.app.Repository.ShopRepository;
import com.SpringBootProject.app.Service.MapperShop.ShopMapper;
import com.SpringBootProject.app.Service.Product.ProductAdminServiceImpl;
import com.SpringBootProject.app.model.ShopDTO;
import com.SpringBootProject.app.model.ShopRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShopAdminServiceImpl implements ShopAdminService{
    public static final Logger LOGGER = LoggerFactory.getLogger(ShopAdminServiceImpl.class);
    private ShopRepository shopRepository;
    private ShopMapper shopMapper;

    public ShopAdminServiceImpl(ShopRepository shopRepository, ShopMapper shopMapper) {
        this.shopRepository = shopRepository;
        this.shopMapper = shopMapper;
    }

    @Override
    public List<ShopDTO> getAll() throws RuntimeException {
        LOGGER.trace("Lista de shop");
        Iterable<ShopEntity> shops = shopRepository.findAll();
        Iterator<ShopEntity> iter = shops.iterator();
        List<ShopDTO> response = new ArrayList<>();
        while (iter.hasNext()){
            response.add(shopMapper.mapShop(iter.next()));
        }
        return response;
    }

    @Override
    public ShopDTO get(Long id) throws RuntimeException {
        return null;
    }

    @Override
    public ShopDTO create(ShopRequestDTO theShop) throws RuntimeException {
        LOGGER.trace(String.format("Creacion de Shop "));
        ShopEntity toCreate = shopMapper.mapShop(theShop);
        ShopEntity created = shopRepository.save(toCreate);
        return shopMapper.mapShop(created);
    }

    @Override
    public ShopDTO update(ShopDTO element) throws RuntimeException {
        return null;
    }

    @Override
    public void delete(Long id) throws RuntimeException {

    }
}
