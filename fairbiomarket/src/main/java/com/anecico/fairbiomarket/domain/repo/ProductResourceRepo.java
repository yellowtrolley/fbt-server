package com.anecico.fairbiomarket.domain.repo;

import com.anecico.fairbiomarket.domain.model.Product;
import com.anecico.fairbiomarket.domain.model.User;
import com.anecico.fairbiomarket.domain.repo.mongo.ProductRepo;
import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by guerrpa on 23/01/2017.
 */
@JsonApiResourceRepository(Product.class)
@Component
public class ProductResourceRepo {
    @Autowired
    private ProductRepo productRepo;

    @JsonApiSave
    public Product save(Product product) {
        productRepo.save(product);
        return product;
    }

    @JsonApiFindOne
    public Product findOne(String id, QueryParams requestParams) {
        return productRepo.findById(id);
    }

    @JsonApiFindAll
    public List<Product> findAll(QueryParams requestParams) {
        return productRepo.findAll();
    }

    @JsonApiFindAllWithIds
    public Iterable<Product> findAll(Iterable<String> ids, QueryParams requestParams) {
        return productRepo.findAll(ids);
    }

    public List<Product> findByUser(User user) {
        return productRepo.findBySeller(user);
    }

    @JsonApiDelete
    public void delete(String id) {
        productRepo.delete(id);
    }
}
