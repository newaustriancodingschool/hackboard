package io.refugeescode.hackboard.controller;


import io.refugeescode.hackboard.domain.Project;
import io.refugeescode.hackboard.domain.ProjectRole;
import io.refugeescode.hackboard.domain.User;
import io.refugeescode.hackboard.repository.ProjectRepository;
import io.refugeescode.hackboard.repository.ProjectRoleRepository;
import io.refugeescode.hackboard.repository.UserRepository;
import io.refugeescode.hackboard.security.AuthoritiesConstants;
import io.refugeescode.hackboard.security.SecurityUtils;
import io.refugeescode.hackboard.service.dto.ProjectDto;
import io.refugeescode.hackboard.service.dto.ProjectRoleDto;
import io.refugeescode.hackboard.service.mapper.ProjectMappers;
import io.refugeescode.hackboard.service.mapper.ProjectRoleMapper;
import io.refugeescode.hackboard.web.api.controller.ProjectsApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import io.refugeescode.hackboard.web.rest.util.HeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static io.refugeescode.hackboard.security.AuthoritiesConstants.ADMIN;
import static io.refugeescode.hackboard.security.AuthoritiesConstants.USER;
import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

@RestController
public class ProjectsController implements ProjectsApi {

    private ProjectRepository projectsRepository;
    private UserRepository userRepository;
    private ProjectRoleRepository projectRoleRepository;
    @Autowired
    private ProjectMappers projectMappers;

    @Autowired
    private ProjectRoleMapper projectRoleMapper;

    public ProjectsController(ProjectRepository projectsRepository, UserRepository userRepository ,ProjectRoleRepository projectRoleRepository) {

        this.projectsRepository = projectsRepository;
        this.userRepository = userRepository;
        this.projectRoleRepository = projectRoleRepository;
    }

    @Override
    @Secured({AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
    public ResponseEntity<Boolean> addProject(@RequestBody ProjectDto project) {

        Project entity = new Project();
        entity.setTitle(project.getTitle());
        entity.setGithub(project.getGithub());
        entity.setDescription(project.getDescription());

        List<ProjectRole> projectRoleList = new ArrayList<>();
        if (! project.getProjectRole().isEmpty()) {
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

/*
        List<ProjectRoleDto> projectRole = project.getProjectRole();

        List<ProjectRole> collect = projectRole.stream().map(e -> projectRoleMapper.projectRoleDtoToProjectRole(e)).collect(Collectors.toList());
        entity.setProjectRoles(collect);*/


        if (SecurityUtils.getCurrentUserLogin().isPresent()) {
            String userlogin = SecurityUtils.getCurrentUserLogin().get();
            Optional<User> oneByLogin = userRepository.findOneByLogin(userlogin);
            if (oneByLogin.isPresent()) {
                entity.setOwner(oneByLogin.get());
            }
        }
        projectsRepository.save(entity);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @Override
    @Secured({AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
    public ResponseEntity<Boolean> editProject(@RequestBody ProjectDto project) {
        Project entity = projectsRepository.findOne(project.getId());
        entity.setTitle(project.getTitle());
        entity.setDescription(project.getDescription());
        entity.setGithub(project.getGithub());
        entity.setTags(entity.getTags());
        projectsRepository.save(entity);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @Override
    @Secured({AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN, AuthoritiesConstants.ANONYMOUS})
    public ResponseEntity<List<ProjectDto>> listProjects() {
        return new ResponseEntity<>(
            projectsRepository.findAll().stream()
                .map(project -> projectMappers.projectToProjectDto(project))
                .collect(Collectors.toList()),
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
    public ResponseEntity<Boolean> deleteProject(@PathVariable("projectId") Long projectId) {
        projectsRepository.delete(projectId);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
