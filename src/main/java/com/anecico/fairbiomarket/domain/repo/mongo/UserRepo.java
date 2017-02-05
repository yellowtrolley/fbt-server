package com.anecico.fairbiomarket.domain.repo.mongo;

import com.anecico.fairbiomarket.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by guerrpa on 23/01/2017.
 */
public interface UserRepo extends MongoRepository<User, String> {
    public User findById(String id);
    public User findByEmail(String email);
}
