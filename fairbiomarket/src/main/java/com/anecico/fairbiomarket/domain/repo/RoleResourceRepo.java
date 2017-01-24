package com.anecico.fairbiomarket.domain.repo;

import com.anecico.fairbiomarket.domain.model.Role;
import com.anecico.fairbiomarket.domain.repo.mongo.RoleRepo;
import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by guerrpa on 23/01/2017.
 */
@JsonApiResourceRepository(Role.class)
@Component
public class RoleResourceRepo {
    @Autowired
    private RoleRepo roleRepo;

    @JsonApiSave
    public Role save(Role role) {
        roleRepo.save(role);
        return role;
    }

    @JsonApiFindOne
    public Role findOne(String id, QueryParams requestParams) {
        return roleRepo.findById(id);
    }

    @JsonApiFindAll
    public List<Role> findAll(QueryParams requestParams) {
        return roleRepo.findAll();
    }

    @JsonApiFindAllWithIds
    public Iterable<Role> findAll(Iterable<String> ids, QueryParams requestParams) {
        return roleRepo.findAll(ids);
    }

    @JsonApiDelete
    public void delete(String id) {
        roleRepo.delete(id);
    }
}
