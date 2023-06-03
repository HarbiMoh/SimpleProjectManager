package com.example.ProjectManager.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ProjectManager.Entity.Project;
import com.example.ProjectManager.Entity.Resource;
import com.example.ProjectManager.Repositories.ProjectRepository;
import com.example.ProjectManager.Repositories.ResourceRepository;
import com.example.ProjectManager.Request.ResourceEditRequest;
import com.example.ProjectManager.Request.ResourceRequest;
import com.example.ProjectManager.Response.ResourceResponse;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final ProjectRepository projectRepository;
    
    public List<ResourceResponse> getAllResources(String username, Long projectId){
        Optional<Project> project = projectRepository.findByUsernameAndId(username, projectId);
        if(project.isPresent())
            return resourceRepository.findByProject(project.get()).stream().map(resource -> ResourceResponse.mapToResourceResponse(resource)).toList();
        else
            return null;
    }

    public ResourceResponse createResource(String username, Long projectId, ResourceRequest request){
        Optional<Project> project = projectRepository.findByUsernameAndId(username, projectId);
        if(project.isPresent()){
            Resource resource = Resource.builder()
                .name(request.getName())
                .type(request.getType())
                .project(project.get())
                .build();
            resourceRepository.save(resource);
            return ResourceResponse.mapToResourceResponse(resource);
        }
        else
            return null;
    }

    public ResponseEntity<ResourceResponse> editResource(Long resourceId, ResourceEditRequest request){
        Optional<Resource> optionalResource = resourceRepository.findById(resourceId);
        if(optionalResource.isPresent()){
            Resource resource = optionalResource.get();
            if(request.getName() != null)
                resource.setName(request.getName());
            if(request.getType() != null)
                resource.setType(request.getType());
            resource = resourceRepository.save(resource);
            return ResponseEntity.ok().body(ResourceResponse.mapToResourceResponse(resource));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<String> deleteResource(Long resourceId){
        Optional<Resource> resource = resourceRepository.findById(resourceId);
        if(resource.isPresent()){
            resourceRepository.delete(resource.get());
            return ResponseEntity.ok().body("deleted");
        }
        return ResponseEntity.notFound().build();
    }
}
