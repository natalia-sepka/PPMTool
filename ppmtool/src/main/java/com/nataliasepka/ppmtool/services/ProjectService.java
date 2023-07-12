package com.nataliasepka.ppmtool.services;

import com.nataliasepka.ppmtool.domain.Project;
import com.nataliasepka.ppmtool.exceptions.ProjectIdException;
import com.nataliasepka.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase()+ "'already exists");
        }


    }
}
