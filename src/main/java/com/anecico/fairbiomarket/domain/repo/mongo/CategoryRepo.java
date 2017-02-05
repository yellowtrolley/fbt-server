package com.anecico.fairbiomarket.domain.repo.mongo;

import com.anecico.fairbiomarket.domain.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by guerrpa on 23/01/2017.
 */
public interface CategoryRepo extends MongoRepository<Category, String> {
    public Category findById(String id);
    public Category findByName(String name);
}
