package io.refugeescode.hackboard.controller;


import io.refugeescode.hackboard.domain.*;
import io.refugeescode.hackboard.repository.*;
import io.refugeescode.hackboard.security.AuthoritiesConstants;
import io.refugeescode.hackboard.security.SecurityUtils;
import io.refugeescode.hackboard.service.ApplicationService;
import io.refugeescode.hackboard.service.dto.ProjectDto;
import io.refugeescode.hackboard.service.mapper.ProjectMappers;
import io.refugeescode.hackboard.service.mapper.ProjectRoleMapper;
import io.refugeescode.hackboard.web.api.controller.ProjectsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class ProjectsController implements ProjectsApi {

    private ProjectRepository projectsRepository;
    private UserRepository userRepository;
    private ProjectRoleRepository projectRoleRepository;
    private ProjectStoriesRepository projectStoriesRepository;


    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private TagsRepository tagsRepository;

    @Autowired
    private ProjectMappers projectMappers;

    @Autowired
    private ProjectRoleMapper projectRoleMapper;

    public ProjectsController(ProjectRepository projectsRepository, UserRepository userRepository, ProjectRoleRepository projectRoleRepository, ProjectStoriesRepository projectStoriesRepository) {

        this.projectsRepository = projectsRepository;
        this.userRepository = userRepository;
        this.projectRoleRepository = projectRoleRepository;
        this.projectStoriesRepository = projectStoriesRepository;
    }

    @Override
    @Secured({AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
    public ResponseEntity<Boolean> addProject(@RequestBody ProjectDto project) {
        Project entity = new Project();
        entity.setTitle(project.getTitle());
        entity.setGithub(project.getGithub());
        entity.setDescription(project.getDescription());

        List<ProjectRole> projectRoleList = new ArrayList<>();
        if (!project.getProjectRole().isEmpty()) {
            int size = project.getProjectRole().size();
            for (int i = 0; i < size; i++) {
                Long count = project.getProjectRole().get(i).getCount();
                for (int j = 0; j < count; j++) {
                    Optional<ProjectRole> oneByRoleName = projectRoleRepository.findOneByRoleName(project.getProjectRole().get(i).getRoleName());
                    if (oneByRoleName.isPresent()) {
                        projectRoleList.add(oneByRoleName.get());
                    }
                }
            }
        }
        entity.setProjectRoles(projectRoleList);
        if (SecurityUtils.getCurrentUserLogin().isPresent()) {
            String userlogin = SecurityUtils.getCurrentUserLogin().get();
            Optional<User> oneByLogin = userRepository.findOneByLogin(userlogin);
            if (oneByLogin.isPresent()) {
                entity.setOwner(oneByLogin.get());
            }
        }

        Set<Tag> collect = new HashSet<>();
        if (!project.getTags().isEmpty()) {
            int size = project.getTags().size();
            for (int i = 0; i < size; i++) {
                Optional<Tag> oneBytagIgnoreCase = tagsRepository.findOneBytagIgnoreCase(project.getTags().get(0));
                if (oneBytagIgnoreCase.isPresent())
                    collect.add(oneBytagIgnoreCase.get());
            }
            //collect = project.getTags().stream().map(e -> tagsRepository.findOneBytagIgnoreCase(e)).map(e -> e.get()).collect(Collectors.toSet());
        }
        entity.setTags(collect);


        projectsRepository.save(entity);

        project.getProjectStories().stream().forEach(story -> {
            ProjectStories projectStories = new ProjectStories();
            projectStories.setDescription(story);
            projectStories.setProject(entity);
            projectStoriesRepository.save(projectStories);
        });


        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @Override
    @Secured({AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
    @PreAuthorize("(#project.ownerLoginName == authentication.name) or (hasRole('ADMIN')) ")
    public ResponseEntity<Boolean> editProject(@RequestBody ProjectDto project) {
        Project entity = projectsRepository.findOne(project.getId());
        entity.setTitle(project.getTitle());
        entity.setDescription(project.getDescription());
        entity.setGithub(project.getGithub());


        List<ProjectRole> projectRoleList = new ArrayList<>();
        if (!project.getProjectRole().isEmpty()) {
            int size = project.getProjectRole().size();
            for (int i = 0; i < size; i++) {
                Long count = project.getProjectRole().get(i).getCount();
                for (int j = 0; j < count; j++) {
                    Optional<ProjectRole> oneByRoleName = projectRoleRepository.findOneByRoleName(project.getProjectRole().get(i).getRoleName());
                    if (oneByRoleName.isPresent()) {
                        projectRoleList.add(oneByRoleName.get());
                    }
                }
            }
        }
        entity.setProjectRoles(projectRoleList);
        Set<Tag> collect = new HashSet<>();
        collect = project.getTags().stream().map(e -> tagsRepository.findOneBytagIgnoreCase(e)).map(e -> e.get()).collect(Collectors.toSet());
        entity.setTags(collect);

        projectsRepository.save(entity);
        projectStoriesRepository.findAll()
            .stream()
            .filter(stories -> stories.getProject().getId().equals(entity.getId()))
            .forEach(story -> projectStoriesRepository.delete(story.getId()));

        project.getProjectStories().stream().forEach(story -> {
            ProjectStories projectStories = new ProjectStories();
            projectStories.setDescription(story);
            projectStories.setProject(entity);
            projectStoriesRepository.save(projectStories);
        });


        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @Override
    @Secured({AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN, AuthoritiesConstants.ANONYMOUS})
    public ResponseEntity<List<ProjectDto>> listProjects() {
        List<ProjectDto> listProjectDto = projectsRepository.findAll()
            .stream()
            .map(project -> projectMappers.projectToProjectDto(project))
            .sorted(Comparator.comparing(ProjectDto::getColor).reversed())
            .collect(Collectors.toList());
        return new ResponseEntity<>(
            listProjectDto,
            HttpStatus.OK
        );

    }

    @Override
    @Secured({AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
    public ResponseEntity<ProjectDto> viewProject(@PathVariable("projectId") Long projectId) {
        return new ResponseEntity<>(
            projectMappers.projectToProjectDto(
                projectsRepository.findOne(projectId)),
            HttpStatus.OK
        );
    }

    @Override
    @Secured({AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
    @PreAuthorize("(@applicationService.hasPermission(#projectId , authentication.name)) or (hasRole('ADMIN')) ")
    public ResponseEntity<Boolean> deleteProject(@PathVariable("projectId") Long projectId) {

        List<ProjectStories> currentProjectStrories = projectStoriesRepository.findAll().stream()
            .filter(story -> story.getProject().getId().equals(projectId)).collect(Collectors.toList());
        currentProjectStrories.stream()
            .forEach(stories -> projectStoriesRepository.delete(stories));

        projectsRepository.delete(projectId);

        return new ResponseEntity<>(true, HttpStatus.OK);

    }


}
