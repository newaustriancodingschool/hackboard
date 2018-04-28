package io.refugeescode.hackboard.controller;

import io.refugeescode.hackboard.domain.Project;
import io.refugeescode.hackboard.repository.ProjectRepository;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    private ProjectRepository projectRepository;


    //Return a list of all projects
    @RequestMapping("/projectsList")
    public String getProjects(Model model){
        List<Project> projects = projectRepository.findAll();
        model.addAttribute(projects);
        return "projectsList";
    }


    //return the detail data of a project
    @RequestMapping("/projectsList/viewProject/{projectId}")
    public String viewProduct(@PathVariable Long projectId, Model model)  {

        Project projects = projectRepository.findOne(projectId);
        model.addAttribute(projects);

        return "viewProduct";
    }


    //Remove a project
    @RequestMapping("/projectsList/viewProject/deleteProject/{projectId}")
    public String deleteProject(@PathVariable Long projectId, Model model){

        projectRepository.delete(projectId);
        return "redirect:/projectsList";
    }


    //Edit a project
    @RequestMapping("/projectsList/viewProject/editProduct/{projectId}")
    public String editProjectt(@PathVariable("projectId") Long projectId, Model model) {
        Project projects = projectRepository.findOne(projectId);

        model.addAttribute(projects);

        return "editProduct";
    }


    //Add a project
    @RequestMapping(value = "/projectsList/addProject", method = RequestMethod.POST)
    public String addProject(@ModelAttribute("project") Project projects) {
        projectRepository.save(projects);
        return "redirect:/projectsList";
    }

}
