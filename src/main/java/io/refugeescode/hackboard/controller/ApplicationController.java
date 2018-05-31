package io.refugeescode.hackboard.controller;

import io.refugeescode.hackboard.domain.Application;
import io.refugeescode.hackboard.domain.Project;
import io.refugeescode.hackboard.domain.ProjectRole;
import io.refugeescode.hackboard.domain.User;
import io.refugeescode.hackboard.repository.ProjectRepository;
import io.refugeescode.hackboard.repository.ProjectRoleRepository;
import io.refugeescode.hackboard.repository.UserRepository;
import io.refugeescode.hackboard.security.AuthoritiesConstants;
import io.refugeescode.hackboard.security.SecurityUtils;
import io.refugeescode.hackboard.service.dto.ApplicationDto;
import io.refugeescode.hackboard.service.mapper.ApplicationMapper;
import io.refugeescode.hackboard.web.api.controller.ApplicationApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
public class ApplicationController implements ApplicationApi{

    private ProjectRepository projectRepository;
    private UserRepository userRepository;
    private ProjectRoleRepository projectRoleRepository;

    @Autowired
    private ApplicationMapper applicationMapper;



    public ApplicationController(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }


    @Override
    public ResponseEntity<Boolean> addapplication(@RequestBody ApplicationDto applicationDto) {

        System.out.println("******************************************");
        System.out.println("******************************************");
        System.out.println(applicationDto.getApplicant());

        System.out.println("******************************************");
        System.out.println("******************************************");


        Long projectId = applicationDto.getProjectId();
        Project currentProject = projectRepository.findOne(projectId);

        Application application = new Application();

        if (SecurityUtils.getCurrentUserLogin().isPresent()) {
            String userlogin = SecurityUtils.getCurrentUserLogin().get();
            Optional<User> oneByLogin = userRepository.findOneByLogin(userlogin);
            if (oneByLogin.isPresent()) {
                application.setApplicant(oneByLogin.get());
            }
        }


        ProjectRole currentRole = projectRoleRepository.findOne(applicationDto.getRoleId());
        application.setRole(currentRole);

        List<Application> applications = currentProject.getApplications();
        applications.add(application);
        projectRepository.save(currentProject);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ApplicationDto>> viewApplication(Long projectId) {
        Project currentProject = projectRepository.findOne(projectId);
        List<Application> applications = currentProject.getApplications();
        List<ApplicationDto> collect = applications.stream()
            .filter(Objects::nonNull)
            .map(e -> applicationMapper.ApplicationToApplicationDto(e)).collect(Collectors.toList());
        return new ResponseEntity<>(
            collect,
            HttpStatus.OK
        );

    }


}
