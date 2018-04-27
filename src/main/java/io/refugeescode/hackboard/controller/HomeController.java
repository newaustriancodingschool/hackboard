package io.refugeescode.hackboard.controller;

import io.refugeescode.hackboard.model.Projects;
import io.refugeescode.hackboard.repository.ProjectsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    private ProjectsRepository projectsRepository;


    //Return a list of all projects
    @RequestMapping("/projectsList")
    public String getProjects(Model model){
        List<Projects> projects = projectsRepository.findAll();
        model.addAttribute(projects);
        return "projectsList";
    }


    //return the detail data of a project
    @RequestMapping("/projectsList/viewProject/{projectId}")
    public String viewProduct(@PathVariable Long projectId, Model model)  {

        Projects projects = projectsRepository.findOne(projectId);
        model.addAttribute(projects);

        return "viewProduct";
    }


    //Remove a project
    @RequestMapping("/projectsList/viewProject/deleteProject/{projectId}")
    public String deleteProject(@PathVariable Long projectId, Model model){

        projectsRepository.delete(projectId);
        return "redirect:/projectsList";
    }


    //Edit a project
    @RequestMapping("/projectsList/viewProject/editProduct/{projectId}")
    public String editProjectt(@PathVariable("projectId") Long projectId, Model model) {
        Projects projects =projectsRepository.findOne(projectId);

        model.addAttribute(projects);

        return "editProduct";
    }


    //Add a project
    @RequestMapping(value = "/projectsList/addProject", method = RequestMethod.POST)
    public String addProject(@ModelAttribute("project") Projects projects) {
        projectsRepository.save(projects);
        return "redirect:/projectsList";
    }

}
