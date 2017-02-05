package com.anecico.fairbiomarket.domain.repo.mongo;

import com.anecico.fairbiomarket.domain.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by guerrpa on 23/01/2017.
 */
public interface RoleRepo extends MongoRepository<Role, String> {
    public Role findById(String id);
    public Role findByName(String name);
}
