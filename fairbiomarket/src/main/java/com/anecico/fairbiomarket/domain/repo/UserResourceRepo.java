package com.anecico.fairbiomarket.domain.repo;

import com.anecico.fairbiomarket.domain.model.User;
import com.anecico.fairbiomarket.domain.repo.mongo.UserRepo;
import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by guerrpa on 23/01/2017.
 */
@JsonApiResourceRepository(User.class)
@Component
public class UserResourceRepo {
    @Autowired
    private UserRepo userRepo;

    @JsonApiSave
    public User save(User user) {
        userRepo.save(user);
        return user;
    }

    @JsonApiFindOne
    public User findOne(String id, QueryParams requestParams) {
        return userRepo.findById(id);
    }

    @JsonApiFindAll
    public List<User> findAll(QueryParams requestParams) {
        return userRepo.findAll();
    }

    @JsonApiFindAllWithIds
    public Iterable<User> findAll(Iterable<String> ids, QueryParams requestParams) {
        return userRepo.findAll(ids);
    }

    @JsonApiDelete
    public void delete(String id) {
        userRepo.delete(id);
    }
}
