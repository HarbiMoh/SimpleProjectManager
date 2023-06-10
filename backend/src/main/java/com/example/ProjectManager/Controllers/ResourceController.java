package com.example.ProjectManager.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjectManager.Request.ResourceEditRequest;
import com.example.ProjectManager.Request.ResourceRequest;
import com.example.ProjectManager.Response.ResourceResponse;
import com.example.ProjectManager.Service.ResourceService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/{username}/{projectId}/resource")
@AllArgsConstructor
@CrossOrigin("http://localhost:5173")
public class ResourceController {

    private final ResourceService resourceService;
    
    @GetMapping
    public List<ResourceResponse> getAllResources(@PathVariable String username, @PathVariable Long projectId){
        return resourceService.getAllResources(username, projectId);
    }
    
    @PostMapping("create")
    public ResourceResponse createResource(@PathVariable String username, @PathVariable Long projectId, @RequestBody ResourceRequest request){
        return resourceService.createResource(username, projectId, request);
    }

    @PostMapping("{resourceId}/edit")
    public ResponseEntity<ResourceResponse> editResource(@PathVariable Long resourceId, @RequestBody ResourceEditRequest request){
        return resourceService.editResource(resourceId, request);
    }

    @GetMapping("{resourceId}/delete")
    public ResponseEntity<String> deleteResource(@PathVariable Long resourceId){
        return resourceService.deleteResource(resourceId);
    }
}
