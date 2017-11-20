package com.example.admin;

import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.server.SpringVaadinServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.vaadin.spring.session.redis.VaadinSessionRewriteFilter;

import javax.servlet.Filter;

@SpringBootApplication
@EnableVaadin
@EnableDiscoveryClient
@EnableFeignClients
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@EnableRedisHttpSession
public class AdminApplication {

    public static void main(String... args) {
        SpringApplication.run(AdminApplication.class, args);
    }

    @Bean
    public Filter vaadinSessionRewriteFilter() {
        return new VaadinSessionRewriteFilter();
    }

    @Bean
    public ServletRegistrationBean<SpringVaadinServlet> springVaadinServlet() {
        ServletRegistrationBean<SpringVaadinServlet> registrationBean = new ServletRegistrationBean<>(new SpringVaadinServlet(), "/*");
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }

}
