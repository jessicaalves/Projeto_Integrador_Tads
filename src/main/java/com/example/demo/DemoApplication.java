package com.example.demo;

import com.example.demo.services.Filtro;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {
    @Bean
    public FilterRegistrationBean filtroJwt(){
        FilterRegistrationBean fr = new FilterRegistrationBean();
        fr.setFilter(new Filtro());
        fr.addUrlPatterns("/admin/*");
        return fr;
    }

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
