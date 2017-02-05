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

import com.anecico.fairbiomarket.domain.model.Category;
import com.anecico.fairbiomarket.domain.model.Item;
import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.annotations.*;
import io.katharsis.utils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@JsonApiRelationshipRepository(source = Item.class, target = Category.class)
@Component
public class ItemToCategoryRepo {

    private final ItemResourceRepo itemResourceRepo;
    private final CategoryResourceRepo categoryResourceRepo;

    @Autowired
    public ItemToCategoryRepo(ItemResourceRepo itemResourceRepo, CategoryResourceRepo categoryResourceRepo) {
        this.itemResourceRepo = itemResourceRepo;
        this.categoryResourceRepo = categoryResourceRepo;
    }

    @JsonApiSetRelation
    public void setRelation(Item item, String categoryId, String fieldName) {
        Category category = categoryResourceRepo.findOne(categoryId, null);
        try {
            PropertyUtils.setProperty(item, fieldName, category);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        itemResourceRepo.save(item);
    }

    @JsonApiSetRelations
    public void setRelations(Item item, Iterable<String> categoryIds, String fieldName) {
        Iterable<Category> categorys = categoryResourceRepo.findAll(categoryIds, null);
        try {
            PropertyUtils.setProperty(item, fieldName, categorys);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        itemResourceRepo.save(item);
    }

    @JsonApiAddRelations
    public void addRelations(Item item, Iterable<String> categoryIds, String fieldName) {
        List<Category> newCategoryList = new LinkedList<>();
        Iterable<Category> categorysToAdd = categoryResourceRepo.findAll(categoryIds, null);
        categorysToAdd.forEach(newCategoryList::add);
        try {
            if (PropertyUtils.getProperty(item, fieldName) != null) {
                Iterable<Category> categorys = (Iterable<Category>) PropertyUtils.getProperty(item, fieldName);
                categorys.forEach(newCategoryList::add);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            PropertyUtils.setProperty(item, fieldName, newCategoryList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        itemResourceRepo.save(item);

    }

    @JsonApiRemoveRelations
    public void removeRelations(Item item, Iterable<Long> categoryIds, String fieldName) {
        try {
            if (PropertyUtils.getProperty(item, fieldName) != null) {
                Iterable<Category> categorys = (Iterable<Category>) PropertyUtils.getProperty(item, fieldName);
                Iterator<Category> iterator = categorys.iterator();
                while (iterator.hasNext()) {
                    for (Long categoryIdToRemove : categoryIds) {
                        if (iterator.next().getId().equals(categoryIdToRemove)) {
                            iterator.remove();
                            break;
                        }
                    }
                }
                List<Category> newCategoryList = new LinkedList<>();
                categorys.forEach(newCategoryList::add);

                PropertyUtils.setProperty(item, fieldName, newCategoryList);
                itemResourceRepo.save(item);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @JsonApiFindOneTarget
    public Category findOneTarget(String itemId, String fieldName, QueryParams requestParams) {
        Item item = itemResourceRepo.findOne(itemId, requestParams);
        try {
            return (Category) PropertyUtils.getProperty(item, fieldName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @JsonApiFindManyTargets
    public Iterable<Category> findManyTargets(String itemId, String fieldName, QueryParams requestParams) {
        Item item = itemResourceRepo.findOne(itemId, requestParams);
        try {
            return (Iterable<Category>) PropertyUtils.getProperty(item, fieldName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
