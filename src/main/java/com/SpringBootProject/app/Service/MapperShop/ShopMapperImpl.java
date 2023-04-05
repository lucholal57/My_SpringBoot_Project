package com.SpringBootProject.app.Service.MapperShop;

import com.SpringBootProject.app.Entity.ProductEntity;
import com.SpringBootProject.app.Entity.ShopEntity;
import com.SpringBootProject.app.Repository.ShopRepository;
import com.SpringBootProject.app.Service.MapperProduct.ProductMapper;
import com.SpringBootProject.app.Service.MapperUser.UserMapper;
import com.SpringBootProject.app.Utils.DateUtils;
import com.SpringBootProject.app.model.ProductDTO;
import com.SpringBootProject.app.model.ShopDTO;
import com.SpringBootProject.app.model.ShopRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ShopMapperImpl implements ShopMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShopMapperImpl.class);
    private ShopRepository shopRepository;
    private ProductMapper productMapper;
    private UserMapper userMapper;

    public ShopMapperImpl(ShopRepository shopRepository,UserMapper userMapper
            , ProductMapper productMapper) {

        this.shopRepository = shopRepository;
        this.productMapper = productMapper;
        this.userMapper = userMapper;
    }


    @Override
    public ShopEntity mapShop(ShopRequestDTO theShop) {
        LOGGER.trace("Mapeo de ShopRequestDTO a ShopEntity");
        Double total = (double) 0;
        ShopEntity response = new ShopEntity();
        response.setDescription(theShop.getDescription());
        response.setUser(userMapper.mapUser(theShop.getUser()));
        response.setDate_created(new Date());
        Iterable<ProductDTO> Iproducts = theShop.getProducts();
        Iterator<ProductDTO> iter = Iproducts.iterator();
        List<ProductEntity> listProducts = new ArrayList<>();
        while (iter.hasNext()) {
            ProductDTO productoPrice = iter.next();
            listProducts.add(productMapper.mapProduct(productoPrice));
            total+=productoPrice.getPrice();
        }
        response.setPrice(BigDecimal.valueOf(total));
        response.setProductsSelect(listProducts);
        return response;
    }

    @Override
    public ShopEntity mapShop(ShopDTO theShop) {
        LOGGER.trace("Mapeo de ShopRequestDTO a ShopEntity");
        Double total = (double) 0;
        ShopEntity response = new ShopEntity();
        response.setDescription(theShop.getDescription());
        response.setUser(userMapper.mapUser(theShop.getUser()));
        response.setDate_created(new Date());
        if (theShop.getDateCreated() != null) {
            response.setDate_created(DateUtils.toDate(theShop.getDateCreated()));
        }
        Iterable<ProductDTO> Iproducts = theShop.getProducts();
        Iterator<ProductDTO> iter = Iproducts.iterator();
        List<ProductEntity> listProducts = new ArrayList<>();
        while (iter.hasNext()) {
            ProductDTO productoPrice = iter.next();
            listProducts.add(productMapper.mapProduct(productoPrice));
            total+=productoPrice.getPrice();
        }
        response.setPrice(BigDecimal.valueOf(total));
        response.setProductsSelect(listProducts);
        return response;
    }

    @Override
    public ShopDTO mapShop(ShopEntity theShop) {
        LOGGER.trace("Mapeo de ShopRequestDTO a ShopEntity");
        Double total = (double) 0;
        ShopDTO response = new ShopDTO();
        response.setDescription(theShop.getDescription());
        response.setUser(userMapper.mapUser(theShop.getUser()));
        response.setPrice(theShop.getPrice().doubleValue());
        response.setDateCreated(DateUtils.toLocalDate(new Date()));
        if (theShop.getDate_created() != null) {
            response.setDateCreated(DateUtils.toLocalDate(theShop.getDate_created()));
        }
        Iterable<ProductEntity> Iproducts = theShop.getProductsSelect();
        Iterator<ProductEntity> iter = Iproducts.iterator();
        List<ProductDTO> listProducts = new ArrayList<>();
        while (iter.hasNext()) {
            ProductEntity productoPrice = iter.next();
            listProducts.add(productMapper.mapProduct(productoPrice));
            total += productoPrice.getPrice().doubleValue();
        }
        response.setPrice(total.doubleValue());
        response.setProducts((listProducts));
        return response;
    }
}
