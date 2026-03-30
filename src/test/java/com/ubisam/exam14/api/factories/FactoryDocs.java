package com.ubisam.exam14.api.factories;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ubisam.exam14.domain.Factory;

import io.u2ware.common.docs.MockMvcRestDocs;

@Component
public class FactoryDocs extends MockMvcRestDocs {

    public Factory newEntity(String... entity) {

        Factory f = new Factory();
        f.setFactoryName(entity.length > 0 ? entity[0] : super.randomText("factoryName"));
        f.setFactoryDescription(entity.length > 2 ? entity[2] : super.randomText("factoryDescription"));
        return f;
    }

    public Map<String, Object> updateEntity(Map<String, Object> entity, String name) {
        entity.put("factoryName", name);
        return entity;
    }

    public Map<String, Object> setSearchName(String name) {
        Map<String, Object> entity = new HashMap<>();
        entity.put("factoryName", name);
        return entity;
    }

    public Map<String, Object> setSearchDescription(String description) {
        Map<String, Object> entity = new HashMap<>();
        entity.put("factoryDescription", description);
        return entity;
    }

}
