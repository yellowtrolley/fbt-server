package com.anecico.fairbiomarket.domain.repo;

import com.anecico.fairbiomarket.domain.model.Item;
import com.anecico.fairbiomarket.domain.model.User;
import com.anecico.fairbiomarket.domain.repo.mongo.ItemRepo;
import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by guerrpa on 23/01/2017.
 */
@JsonApiResourceRepository(Item.class)
@Component
public class ItemResourceRepo {
    @Autowired
    private ItemRepo itemRepo;

    @JsonApiSave
    public Item save(Item item) {
        itemRepo.save(item);
        return item;
    }

    @JsonApiFindOne
    public Item findOne(String id, QueryParams requestParams) {
        return itemRepo.findById(id);
    }

    @JsonApiFindAll
    public List<Item> findAll(QueryParams requestParams) {
        return itemRepo.findAll();
    }

    @JsonApiFindAllWithIds
    public Iterable<Item> findAll(Iterable<String> ids, QueryParams requestParams) {
        return itemRepo.findAll(ids);
    }

    public List<Item> findByUser(User user) {
        return itemRepo.findBySeller(user);
    }

    @JsonApiDelete
    public void delete(String id) {
        itemRepo.delete(id);
    }
}
