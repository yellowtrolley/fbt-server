package com.anecico.fairbiomarket.domain.repo.mongo;

import com.anecico.fairbiomarket.domain.model.Item;
import com.anecico.fairbiomarket.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by guerrpa on 23/01/2017.
 */
public interface ItemRepo extends MongoRepository<Item, String> {
    public Item findById(String id);
    public List<Item>  findByNameLike(String name);
    public List<Item> findBySeller(User user);
}
