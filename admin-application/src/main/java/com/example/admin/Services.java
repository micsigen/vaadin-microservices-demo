package com.example.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Alejandro Duarte.
 */
@Component
public class Services {

    private static CompanyService companyService;

    @Autowired
    public Services(CompanyService companyService) {
        this.companyService = companyService;
    }

    public static CompanyService companyService() {
        return companyService;
    }

}
