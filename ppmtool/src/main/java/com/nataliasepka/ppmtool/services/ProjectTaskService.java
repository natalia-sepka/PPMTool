package com.nataliasepka.ppmtool.services;

import com.nataliasepka.ppmtool.domain.Backlog;
import com.nataliasepka.ppmtool.domain.ProjectTask;
import com.nataliasepka.ppmtool.repositories.BacklogRepository;
import com.nataliasepka.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {
    @Autowired
    private BacklogRepository backlogRepository;
    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){
        //Exceptions: Project not found
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
    }
}
