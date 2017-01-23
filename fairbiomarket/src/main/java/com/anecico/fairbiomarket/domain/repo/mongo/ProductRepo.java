package com.anecico.fairbiomarket.domain.repo.mongo;

import com.anecico.fairbiomarket.domain.model.Product;
import com.anecico.fairbiomarket.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by guerrpa on 23/01/2017.
 */
public interface ProductRepo extends MongoRepository<Product, String> {
    public Product findById(String id);
    public List<Product>  findByNameLike(String name);
    public List<Product> findBySeller(User user);
}
