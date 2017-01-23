package com.anecico.fairbiomarket.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import org.springframework.data.annotation.Id;

/**
 * Created by guerrpa on 23/01/2017.
 */
@JsonApiResource(type = "invitations")
public class Invitation {
    @Id
    @JsonApiId
    private String id;

    @JsonProperty
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
