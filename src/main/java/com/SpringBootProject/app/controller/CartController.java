package com.SpringBootProject.app.controller;

import com.SpringBootProject.app.api.CartsApiDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CartController extends BaseController implements CartsApiDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);

    public CartController() {
    }
}
