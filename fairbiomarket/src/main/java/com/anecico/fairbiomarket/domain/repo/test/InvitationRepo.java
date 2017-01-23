package com.anecico.fairbiomarket.domain.repo.test;

import com.anecico.fairbiomarket.domain.model.test.Invitation;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by guerrpa on 23/01/2017.
 */
public interface InvitationRepo extends MongoRepository<Invitation, Long> {
    Invitation findByEmail(String email);

}
