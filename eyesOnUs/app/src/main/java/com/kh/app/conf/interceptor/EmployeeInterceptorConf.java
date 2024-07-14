package com.kh.app.conf.interceptor;

import com.kh.app.interceptor.EmployeeLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class EmployeeInterceptorConf implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new EmployeeLoginInterceptor())
                .addPathPatterns("/leave/**")
                .addPathPatterns("/sign/**")
                .addPathPatterns("/businessTrip/**")
                .addPathPatterns("/member/**")
                .addPathPatterns("/projectWork/**")
                .addPathPatterns("/api/**")
                .addPathPatterns("/project/**")
                .addPathPatterns("/teamRoom/**")
                .addPathPatterns("/personal/**")
                .addPathPatterns("/kpi/**")
                .addPathPatterns("/message/**")
                .addPathPatterns("/file/**")
                .addPathPatterns("/home/**")
                .addPathPatterns("/notice/**")
                .addPathPatterns("/attendance/**")
                .excludePathPatterns("/member/login");
    } // addInterceptors
} // class
