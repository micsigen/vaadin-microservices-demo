package com.example.admin;

import org.springframework.stereotype.Component;

/**
 * @author Alejandro Duarte.
 */
@Component
public class Services {

    private static CompanyService companyService;

    public Services(CompanyService companyService) {
        this.companyService = companyService;
    }

    public static CompanyService companyService() {
        return companyService;
    }

}
