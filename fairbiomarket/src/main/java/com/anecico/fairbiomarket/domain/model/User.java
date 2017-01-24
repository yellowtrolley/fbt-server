package com.anecico.fairbiomarket.domain.model;

import com.anecico.fairbiomarket.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by guerrpa on 23/01/2017.
 */
@JsonApiResource(type = "users")
public class User {
    @Id
    @JsonApiId
    private String id;

    @Indexed(unique = true)
    @JsonProperty
    private String email;

    @DateTimeFormat(style="M-")
    @JsonProperty
    private Date activationDate;

    @DBRef
//    @JsonApiToMany
    private List<Role> roles;

    @JsonProperty
    private String password;

    @JsonProperty
    private String name;

    @JsonProperty
    private String activationKey;

    @JsonProperty
    private boolean enabled;

    @JsonProperty
    private boolean locked;

    @JsonProperty
    private String photoId;

    @JsonProperty
    private String phone;

    // TODO Refactor User -> Account -> AccountType
    private AccountType accountType;

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

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
