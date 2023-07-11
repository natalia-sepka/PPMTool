package com.nataliasepka.ppmtool.services;

import com.nataliasepka.ppmtool.domain.Project;
import com.nataliasepka.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveUpdateProject(Project project) {
        return projectRepository.save(project);
    }
}
