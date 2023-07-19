package com.nataliasepka.ppmtool.services;

import com.nataliasepka.ppmtool.domain.Backlog;
import com.nataliasepka.ppmtool.domain.Project;
import com.nataliasepka.ppmtool.domain.ProjectTask;
import com.nataliasepka.ppmtool.exceptions.ProjectNotFoundException;
import com.nataliasepka.ppmtool.repositories.BacklogRepository;
import com.nataliasepka.ppmtool.repositories.ProjectRepository;
import com.nataliasepka.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {
    @Autowired
    private BacklogRepository backlogRepository;
    @Autowired
    private ProjectTaskRepository projectTaskRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){
        //Exceptions: Project not found
        try {
            //PTs to be added to a specific project, project != null, BL exists
            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

            //set the bl to pt
            projectTask.setBacklog(backlog);
            //we want our project sequence to be like this: IDPRO-1, IDPRO-2
            Integer BacklogSequence = backlog.getPTSequence();
            //update the BL SEQUENCE
            BacklogSequence++;
            backlog.setPTSequence(BacklogSequence);
            //Add Sequence to Project Task
            projectTask.setProjectSequence(projectIdentifier + "-" + BacklogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);
            //INITIAL priority when priority is null
            if (projectTask.getPriority() == null) {
                projectTask.setPriority(3);
            }
            // INITIAL status when status is null
            if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
                projectTask.setStatus("TO_DO");
            }
            return projectTaskRepository.save(projectTask);
        } catch (Exception e) {
            throw new ProjectNotFoundException("Project not found");
        }
        }

    public Iterable<ProjectTask>findBacklogById(String id) {
        Project project = projectRepository.findByProjectIdentifier(id);
        if (project == null) {
            throw new ProjectNotFoundException("Project wit ID: '" + id + "' does not exist");
        }
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }

    public ProjectTask findPTByProjectSequence(String backlog_id, String pt_id) {
        //make sure we are searching on the right backlog

        return projectTaskRepository.findByProjectSequence(pt_id);
    }
}
