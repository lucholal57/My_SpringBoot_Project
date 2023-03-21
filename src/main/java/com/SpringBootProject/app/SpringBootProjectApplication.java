package com.SpringBootProject.app;

import com.SpringBootProject.app.conf.AppConfig;
import org.openapitools.configuration.SpringDocConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
/*
En el import de la class no hace falta utilizar el AppConfig, ya que esa clase contiene el @configuration y al tener
el ComponentScan automaticamente lo incluye
 */
@Import({SpringDocConfiguration.class})
public class SpringBootProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootProjectApplication.class, args);

		
	}

}
