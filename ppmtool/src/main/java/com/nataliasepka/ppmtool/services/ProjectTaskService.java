package com.nataliasepka.ppmtool.services;

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

    public ProjectTask addProjectTask(){
        //PTs to be added to a specific project, project != null, BL exists
        //set the bl to pt
        //we want our project sequence to be like this: IDPRO-1, IDPRO-2
        //update the BL SEQUENCE

        //INITIAL priority when priority is null
        // INITIAL status when status is null
    }
}
