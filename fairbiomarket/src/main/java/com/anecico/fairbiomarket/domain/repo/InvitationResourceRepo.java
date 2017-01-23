package com.anecico.fairbiomarket.domain.repo;

import com.anecico.fairbiomarket.domain.model.Invitation;
import com.anecico.fairbiomarket.domain.repo.mongo.InvitationRepo;
import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.annotations.JsonApiFindAll;
import io.katharsis.repository.annotations.JsonApiFindOne;
import io.katharsis.repository.annotations.JsonApiResourceRepository;
import io.katharsis.repository.annotations.JsonApiSave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * Created by guerrpa on 23/01/2017.
 */
@Component
@JsonApiResourceRepository(Invitation.class)
@Validated
public class InvitationResourceRepo {
    @Autowired
    private InvitationRepo invitationRepo;

    @JsonApiSave
    public Invitation save(Invitation entity) {
        invitationRepo.save(entity);
        return entity;
    }

    @JsonApiFindOne
    public Invitation findByEmail(String email) {
        return  invitationRepo.findByEmail(email);
    }

    @JsonApiFindAll
    public Iterable<Invitation> findAll(QueryParams requestParams) {
        return invitationRepo.findAll();
    }
}
