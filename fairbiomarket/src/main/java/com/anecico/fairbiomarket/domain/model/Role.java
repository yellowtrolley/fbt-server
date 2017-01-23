package com.anecico.fairbiomarket.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import org.springframework.data.annotation.Id;

/**
 * Created by guerrpa on 23/01/2017.
 */
@JsonApiResource(type = "roles")
public class Role {
    @Id
    @JsonApiId
    private String id;

    @JsonProperty
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
