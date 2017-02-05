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

import com.anecico.fairbiomarket.domain.model.Product;
import com.anecico.fairbiomarket.domain.model.User;
import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.annotations.JsonApiFindManyTargets;
import io.katharsis.repository.annotations.JsonApiFindOneTarget;
import io.katharsis.repository.annotations.JsonApiRelationshipRepository;
import io.katharsis.repository.annotations.JsonApiSetRelation;
import io.katharsis.utils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@JsonApiRelationshipRepository(source = Product.class, target = User.class)
@Component
public class ProductToUserRepo {
    @Autowired
    private ProductResourceRepo productResourceRepo;

    @Autowired
    private UserResourceRepo userResourceRepo;


    @JsonApiSetRelation
    public void setRelation(Product product, String userId, String fieldName) {
        User user = userResourceRepo.findOne(userId, null);
        try {
            PropertyUtils.setProperty(product, fieldName, user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        productResourceRepo.save(product);
    }

//    @JsonApiSetRelations
//    public void setRelations(Product product, Iterable<String> userIds, String fieldName) {
//        Iterable<User> users = userResourceRepo.findAll(userIds, null);
//        try {
//            PropertyUtils.setProperty(product, fieldName, users);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        productResourceRepo.save(product);
//    }

//    @JsonApiAddRelations
//    public void addRelations(Task task, Iterable<Long> projectIds, String fieldName) {
//        List<Project> newProjectList = new LinkedList<>();
//        Iterable<Project> projectsToAdd = projectRepository.findAll(projectIds, null);
//        projectsToAdd.forEach(newProjectList::add);
//        try {
//            if (PropertyUtils.getProperty(task, fieldName) != null) {
//                Iterable<Project> projects = (Iterable<Project>) PropertyUtils.getProperty(task, fieldName);
//                projects.forEach(newProjectList::add);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            PropertyUtils.setProperty(task, fieldName, newProjectList);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        taskRepository.save(task);
//
//    }

//    @JsonApiRemoveRelations
//    public void removeRelations(Task task, Iterable<Long> projectIds, String fieldName) {
//        try {
//            if (PropertyUtils.getProperty(task, fieldName) != null) {
//                Iterable<Project> projects = (Iterable<Project>) PropertyUtils.getProperty(task, fieldName);
//                Iterator<Project> iterator = projects.iterator();
//                while (iterator.hasNext()) {
//                    for (Long projectIdToRemove : projectIds) {
//                        if (iterator.next().getId().equals(projectIdToRemove)) {
//                            iterator.remove();
//                            break;
//                        }
//                    }
//                }
//                List<Project> newProjectList = new LinkedList<>();
//                projects.forEach(newProjectList::add);
//
//                PropertyUtils.setProperty(task, fieldName, newProjectList);
//                taskRepository.save(task);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    @JsonApiFindOneTarget
    public User findOneTarget(String productId, String fieldName, QueryParams requestParams) {
        Product product = productResourceRepo.findOne(productId, requestParams);
        try {
            return (User) PropertyUtils.getProperty(product, fieldName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @JsonApiFindManyTargets
    public Iterable<User> findManyTargets(String productId, String fieldName, QueryParams requestParams) {
        Product product = productResourceRepo.findOne(productId, requestParams);
        try {
            return (Iterable<User>) PropertyUtils.getProperty(product, fieldName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
