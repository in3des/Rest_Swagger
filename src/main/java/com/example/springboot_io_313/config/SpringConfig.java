//package com.example.springboot_io_313.config;
//
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.filter.HiddenHttpMethodFilter;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
////@Configuration
////@ComponentScan("com.example.springboot_io_312")
////@EnableWebMvc
//@SpringBootApplication
//public class SpringConfig implements WebMvcConfigurer {
//
//    @Bean
//    HiddenHttpMethodFilter hiddenHttpMethodFilter() {
//        return new HiddenHttpMethodFilter();
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
//        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/META-INF/resources/static/js/");
//    }
//
//
//
//
//}
