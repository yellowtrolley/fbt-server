package com.anecico.fairbiomarket.domain.repo;

import com.anecico.fairbiomarket.domain.model.Category;
import com.anecico.fairbiomarket.domain.repo.mongo.CategoryRepo;
import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by guerrpa on 23/01/2017.
 */
@JsonApiResourceRepository(Category.class)
@Component
public class CategoryResourceRepo {
    @Autowired
    private CategoryRepo categoryRepo;

    @JsonApiSave
    public Category save(Category category) {
        categoryRepo.save(category);
        return category;
    }

    @JsonApiFindOne
    public Category findOne(String id, QueryParams requestParams) {
        return categoryRepo.findById(id);
    }

    @JsonApiFindAll
    public List<Category> findAll(QueryParams requestParams) {
        return categoryRepo.findAll();
    }

    @JsonApiFindAllWithIds
    public Iterable<Category> findAll(Iterable<String> ids, QueryParams requestParams) {
        return categoryRepo.findAll(ids);
    }

    @JsonApiDelete
    public void delete(String id) {
        categoryRepo.delete(id);
    }
}
