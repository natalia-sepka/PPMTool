package com.nataliasepka.ppmtool.services;

import com.nataliasepka.ppmtool.domain.Backlog;
import com.nataliasepka.ppmtool.domain.Project;
import com.nataliasepka.ppmtool.exceptions.ProjectIdException;
import com.nataliasepka.ppmtool.repositories.BacklogRepository;
import com.nataliasepka.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    public Project saveUpdateProject(Project project) {

        String projectIdentifier = project.getProjectIdentifier().toUpperCase();

        try {
            project.setProjectIdentifier(projectIdentifier);

            if (project.getId() == null) {
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(projectIdentifier);
            }
            if (project.getId() != null) {
                project.setBacklog(backlogRepository.findByProjectIdentifier(projectIdentifier));
            }
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase()
                    + "'already exists");
        }
    }

    public Project findProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectIdException("Project ID '" + projectId + "' does not exist");
        }
        return project;
    }

    public Iterable<Project> findAllProject() {
        return projectRepository.findAll();
    }

    public void deleteProjectById(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectIdException("Cannot delete project with Id '" + projectId
                    + "'. This project does not exist");
        }
        projectRepository.delete(project);
    }
}
