package com.SpringBootProject.app.Configuracion;

import com.SpringBootProject.app.Repository.CategoryRepository;
import com.SpringBootProject.app.Repository.ProductRepository;
import com.SpringBootProject.app.Service.Category.CategoryAdminService;
import com.SpringBootProject.app.Service.Category.CategoryAdminServiceImpl;
import com.SpringBootProject.app.Service.MapperCategory.CategoryMapper;
import com.SpringBootProject.app.Service.MapperCategory.CategoryMapperImpl;
import com.SpringBootProject.app.Service.MapperProduct.ProductMapper;
import com.SpringBootProject.app.Service.MapperProduct.ProductMapperImpl;
import com.SpringBootProject.app.Service.Product.ProductAdminService;
import com.SpringBootProject.app.Service.Product.ProductAdminServiceImpl;
import com.SpringBootProject.app.controller.CartController;
import com.SpringBootProject.app.controller.ProductController;
import com.SpringBootProject.app.controller.TokenController;
import com.SpringBootProject.app.controller.UserController;
import com.SpringBootProject.app.Repository.UserRepository;
import com.SpringBootProject.app.Service.JWT.JWTService;
import com.SpringBootProject.app.Service.JWT.JWTServiceImpl;
import com.SpringBootProject.app.Service.MapperUser.UserMapper;
import com.SpringBootProject.app.Service.MapperUser.UserMapperImpl;
import com.SpringBootProject.app.Service.User.*;
import com.SpringBootProject.app.Utils.JwtTokenUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {
    /*
    Metodo para saltar el login de Swagger
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable();
        return http.build();
    }
 */

    /*
    Creamos el Bean de UserController para poder acceder al Controller quien es el encargado de manejar
    las peticiones, y este usa como parametro el UserAdminService quien se encarga de darnos el CRUDservice
    que creamos y lo implementa el UserAdminImpl
     */
    @Bean
    public UserController getUserController(UserAdminService userAdminService) {
        return new UserController(userAdminService);
    }
    @Primary
    @Bean
    public UserAdminService getUserAdminService(UserMapper userMapper,
                                                UserRepository userRepository) {
        return new UserAdminServiceImpl(userMapper, userRepository);
    }
    @Bean
    public UserMapper getUserMapper(PasswordEncoder encoder){
        return new UserMapperImpl(encoder);
    }

    //Beans de Producto
    @Bean
    public ProductController getProductController(ProductAdminService productAdminService,
                                                  CategoryAdminService categoryAdminService){
        return new ProductController(productAdminService,categoryAdminService);
    }
    @Bean
    public ProductAdminService getProductAdminService(ProductRepository productRepository,
                                                      ProductMapper productMapper){
        return new ProductAdminServiceImpl(productRepository,
                productMapper);
    }
    @Bean
    public ProductMapper getProductMapper(CategoryMapper categoryMapper, CategoryRepository categoryRepository){
        return new ProductMapperImpl(categoryMapper,categoryRepository);
    }

    //Beans de Category
    @Bean
    public CategoryMapper getCategoryMapper(CategoryRepository categoryRepository) {
        return new CategoryMapperImpl(categoryRepository);
    }
    @Bean
    public CategoryAdminService getCategoryAdminService(CategoryRepository categoryRepository,
                                                        CategoryMapper categoryMapper){
        return new CategoryAdminServiceImpl(categoryRepository,categoryMapper);
    }


    @Bean
    public CartController getCartController(){
        return new CartController();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public TokenController getTokenController(UserAuthService userAuthService,
                                              JWTService jwtService) {
        return new TokenController(userAuthService, jwtService);
    }

    @Bean
    public JWTService getJwtService(UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
        return new JWTServiceImpl(userDetailsService, jwtTokenUtil);
    }

    @Bean
    public UserAuthService getUserAuthenticationService(PasswordEncoder passwordEncoder,
                                                        JWTService jwtService, UserDetailsService userDetailsService) {
        return new UserAuthServiceImpl(passwordEncoder, jwtService, userDetailsService);
    }

    @Bean
    public UserDetailsService getUserDetailService(UserRepository userRepository) {
        return new UserDetailServiceImpl(userRepository);
    }



}
