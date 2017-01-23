package com.anecico.web;

import io.katharsis.resource.registry.ResourceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by guerrpa on 23/01/2017.
 */
@RestController
public class TestController {
    @Autowired
    private ResourceRegistry resourceRegistry;

    @RequestMapping("/resourcesInfo")
    public Map<?, ?> getResources() {
        Map<String, String> result = new HashMap<>();
        // Add all resources (i.e. Project and Task)
        for (Class<?> clazz : resourceRegistry.getResources().keySet()) {
            result.put(resourceRegistry.getResourceType(clazz), resourceRegistry.getResourceUrl(clazz));
        }
        return result;
    }
}
