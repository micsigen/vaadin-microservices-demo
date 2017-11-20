package com.example.admin;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.vaadin.crudui.crud.impl.GridCrud;

@SpringUI
public class VaadinUI extends UI {

    private final CompanyService companyService;

    public VaadinUI(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    protected void init(VaadinRequest request) {
        GridCrud<Company> crud = new GridCrud<>(Company.class);
        crud.getGrid().setColumns("name", "phone", "email", "address");
        crud.getCrudFormFactory().setVisibleProperties("name", "phone", "email", "address");
        crud.setOperations(
                () -> Services.companyService().findAll().getContent(),
                company -> Services.companyService().add(company),
                company -> Services.companyService().update(company.getId(), company),
                company -> Services.companyService().delete(company.getId())
        );

        setContent(crud);
    }

}
