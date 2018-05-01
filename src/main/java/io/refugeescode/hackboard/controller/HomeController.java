package io.refugeescode.hackboard.controller;


import io.refugeescode.hackboard.domain.Project;
import io.refugeescode.hackboard.repository.ProjectRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {

    private ProjectRepository projectsRepository;

    public HomeController(ProjectRepository projectsRepository) {
        this.projectsRepository = projectsRepository;
    }

    //Return a list of all projects
    @GetMapping("/projects")
    public List<Project> getProjects() {

        List<Project> projects = projectsRepository.findAll();
        return projects;
    }


    //return the detail data of a project
    @GetMapping ("/projects/{projectId}")
    public Project viewProject(@PathVariable Long projectId) {

        Project project = projectsRepository.findOne(projectId);
        return project;
    }


    //Remove a project
    @DeleteMapping("/projects/{projectId}")
    public void deleteProject(@PathVariable Long projectId) {

        projectsRepository.delete(projectId);
    }


    //Edit a project
    @PutMapping("/projects/{projectId}")
    public Project editProject(@PathVariable Long projectId) {

        Project project = projectsRepository.findOne(projectId);
        return project;
    }


    //Add a project
    @PostMapping("/projects")
    public void addProject(@RequestBody Project project) {

        projectsRepository.save(project);
    }

}
