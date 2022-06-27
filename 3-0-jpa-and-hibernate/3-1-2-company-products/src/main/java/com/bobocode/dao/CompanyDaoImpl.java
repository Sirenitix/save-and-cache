package com.bobocode.dao;

import com.bobocode.model.Company;
import com.bobocode.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CompanyDaoImpl implements CompanyDao {
    private EntityManagerFactory entityManagerFactory;

    public CompanyDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Company findByIdFetchProducts(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager(); // todo
        Company company = entityManager.find(Company.class, id);
        Product product = entityManager.find(Product.class, company.getId());
        company.addProduct(product);
        return company;
    }
}
