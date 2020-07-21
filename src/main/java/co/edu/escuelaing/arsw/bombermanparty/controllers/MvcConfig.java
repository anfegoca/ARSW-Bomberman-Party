package co.edu.escuelaing.arsw.bombermanparty.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/instrucciones").setViewName("instrucciones");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("juego").setViewName("juego");
	}

}