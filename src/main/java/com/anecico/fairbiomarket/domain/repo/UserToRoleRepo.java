/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.anecico.fairbiomarket.domain.repo;

import com.anecico.fairbiomarket.domain.model.Role;
import com.anecico.fairbiomarket.domain.model.User;
import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.annotations.*;
import io.katharsis.utils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@JsonApiRelationshipRepository(source = User.class, target = Role.class)
@Component
public class UserToRoleRepo {

    private final UserResourceRepo userResourceRepo;
    private final RoleResourceRepo roleResourceRepo;

    @Autowired
    public UserToRoleRepo(UserResourceRepo userResourceRepo, RoleResourceRepo roleResourceRepo) {
        this.userResourceRepo = userResourceRepo;
        this.roleResourceRepo = roleResourceRepo;
    }

    @JsonApiSetRelation
    public void setRelation(User user, String roleId, String fieldName) {
        Role role = roleResourceRepo.findOne(roleId, null);
        try {
            PropertyUtils.setProperty(user, fieldName, role);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        userResourceRepo.save(user);
    }

    @JsonApiSetRelations
    public void setRelations(User user, Iterable<String> roleIds, String fieldName) {
        Iterable<Role> roles = roleResourceRepo.findAll(roleIds, null);
        try {
            PropertyUtils.setProperty(user, fieldName, roles);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        userResourceRepo.save(user);
    }

    @JsonApiAddRelations
    public void addRelations(User user, Iterable<String> roleIds, String fieldName) {
        List<Role> newRoleList = new LinkedList<>();
        Iterable<Role> rolesToAdd = roleResourceRepo.findAll(roleIds, null);
        rolesToAdd.forEach(newRoleList::add);
        try {
            if (PropertyUtils.getProperty(user, fieldName) != null) {
                Iterable<Role> users = (Iterable<Role>) PropertyUtils.getProperty(user, fieldName);
                users.forEach(newRoleList::add);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            PropertyUtils.setProperty(user, fieldName, newRoleList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        userResourceRepo.save(user);

    }

    @JsonApiRemoveRelations
    public void removeRelations(User user, Iterable<String> roleIds, String fieldName) {
        try {
            if (PropertyUtils.getProperty(user, fieldName) != null) {
                Iterable<Role> roles = (Iterable<Role>) PropertyUtils.getProperty(user, fieldName);
                Iterator<Role> iterator = roles.iterator();
                while (iterator.hasNext()) {
                    for (String roleIdToRemove : roleIds) {
                        if (iterator.next().getId().equals(roleIdToRemove)) {
                            iterator.remove();
                            break;
                        }
                    }
                }
                List<Role> newRoleList = new LinkedList<>();
                roles.forEach(newRoleList::add);

                PropertyUtils.setProperty(user, fieldName, newRoleList);
                userResourceRepo.save(user);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @JsonApiFindOneTarget
    public Role findOneTarget(String userId, String fieldName, QueryParams requestParams) {
        User user = userResourceRepo.findOne(userId, requestParams);
        try {
            return (Role) PropertyUtils.getProperty(user, fieldName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @JsonApiFindManyTargets
    public Iterable<Role> findManyTargets(String userId, String fieldName, QueryParams requestParams) {
        User user = userResourceRepo.findOne(userId, requestParams);
        try {
            return (Iterable<Role>) PropertyUtils.getProperty(user, fieldName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
