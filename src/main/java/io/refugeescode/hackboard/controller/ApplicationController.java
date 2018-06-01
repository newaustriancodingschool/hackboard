package io.refugeescode.hackboard.controller;

import io.refugeescode.hackboard.domain.Application;
import io.refugeescode.hackboard.domain.Project;
import io.refugeescode.hackboard.domain.ProjectRole;
import io.refugeescode.hackboard.domain.User;
import io.refugeescode.hackboard.repository.ApplicationRepository;
import io.refugeescode.hackboard.repository.ProjectRepository;
import io.refugeescode.hackboard.repository.ProjectRoleRepository;
import io.refugeescode.hackboard.repository.UserRepository;
import io.refugeescode.hackboard.security.SecurityUtils;
import io.refugeescode.hackboard.service.dto.ApplicationDto;
import io.refugeescode.hackboard.service.mapper.ApplicationMapper;
import io.refugeescode.hackboard.web.api.controller.ApplicationApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.synth.SynthRootPaneUI;
import javax.xml.bind.SchemaOutputResolver;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ApplicationController implements ApplicationApi{

    private ProjectRepository projectRepository;
    private UserRepository userRepository;
    private ProjectRoleRepository projectRoleRepository;
    private ApplicationRepository applicationRepository;

    @Autowired
    private ApplicationMapper applicationMapper;



    public ApplicationController(ProjectRepository projectRepository, UserRepository userRepository ,ProjectRoleRepository projectRoleRepository,ApplicationRepository applicationRepository ) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.projectRoleRepository = projectRoleRepository;
        this.applicationRepository = applicationRepository;

    }


    @Override
    public ResponseEntity<Boolean> addapplication(@RequestBody ApplicationDto applicationDto) {

        Long projectId = applicationDto.getProjectId();
        Project currentProject = projectRepository.findOne(projectId);
        Application application = new Application();
        application.setProject(currentProject);
        if (SecurityUtils.getCurrentUserLogin().isPresent()) {
            String userlogin = SecurityUtils.getCurrentUserLogin().get();
            Optional<User> oneByLogin = userRepository.findOneByLogin(userlogin);
            if (oneByLogin.isPresent()) {
                application.setApplicant(oneByLogin.get());
            }
        }


        ProjectRole currentRole = projectRoleRepository.findOne(applicationDto.getRoleId());
        application.setRole(currentRole);

        applicationRepository.save(application);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }




}
