package com.projects.crud.service;


import com.projects.crud.model.Resource;
import com.projects.crud.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {
    @Autowired
    private ResourceRepository resourceRepository;

    public Resource createResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    public Resource updateResource(Long id, Resource resource) {
        return resourceRepository.findById(id).map(existing -> {
            existing.setName(resource.getName());
            existing.setDescription(resource.getDescription());
            return resourceRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Resource not found"));
    }

    public Resource getResource(Long id) {
        return resourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found"));
    }

    public void deleteResource(Long id) {
        resourceRepository.deleteById(id);
    }

    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }
}
