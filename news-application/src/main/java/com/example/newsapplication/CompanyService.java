package com.example.newsapplication;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "${biz-application.name:biz-application}", fallback = CompanyServiceFallback.class)
@Primary
public interface CompanyService {

    @RequestMapping("/companies")
    Resources<Company> findAll();

}
