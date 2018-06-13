package io.refugeescode.hackboard.controller;

import io.refugeescode.hackboard.domain.*;
import io.refugeescode.hackboard.repository.*;
import io.refugeescode.hackboard.security.AuthoritiesConstants;
import io.refugeescode.hackboard.security.SecurityUtils;
import io.refugeescode.hackboard.service.dto.ApplicationDto;
import io.refugeescode.hackboard.service.mapper.ApplicationMapper;
import io.refugeescode.hackboard.web.api.controller.ApplicationApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ApplicationController implements ApplicationApi {

    private ProjectRepository projectRepository;
    private UserRepository userRepository;
    private ProjectRoleRepository projectRoleRepository;
    private ApplicationRepository applicationRepository;

    @Autowired
    private ApplicationMapper applicationMapper;

    @Autowired
    ApplicationStatusRepository applicationStatusRepository;

    public ApplicationController(ProjectRepository projectRepository, UserRepository userRepository, ProjectRoleRepository projectRoleRepository, ApplicationRepository applicationRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.projectRoleRepository = projectRoleRepository;
        this.applicationRepository = applicationRepository;

    }


    @Override
    @Secured({AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
    public ResponseEntity<Boolean> addapplication(@RequestBody ApplicationDto applicationDto) {

        long count = applicationRepository.findAll()
            .stream()
            .filter(e -> e.getProject().getId().equals(applicationDto.getProjectId()))
            .filter(e -> e.getApplicant().getId().equals(applicationDto.getApplicant()))
            .filter(e -> e.getRole().getId().equals(applicationDto.getRoleId()))
            .count();
        if (count== 0){
            Long projectId = applicationDto.getProjectId();
            Project currentProject = projectRepository.findOne(projectId);
            Application application = new Application();
            application.setProject(currentProject);
            ApplicationStatus applicationStatusrequest = applicationStatusRepository.findOne(1L);
            application.setStatus(applicationStatusrequest);
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
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

     @Override
     @Secured({AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
     public ResponseEntity<Boolean> delapplication(@PathVariable("projectId") Long projectId , @PathVariable("roleId") Long roleId) {
         User user = new User();
         if (SecurityUtils.getCurrentUserLogin().isPresent()) {
             String userlogin = SecurityUtils.getCurrentUserLogin().get();
             Optional<User> oneByLogin = userRepository.findOneByLogin(userlogin);
             if (oneByLogin.isPresent()) {
                 user = oneByLogin.get();
             }
         }
         final User user1 = user;
         Optional<Application> firstApplication = applicationRepository.findAll()
             .stream()
             .filter(e -> e.getProject().getId().equals(projectId))
             .filter(e -> e.getApplicant().getId().equals(user1.getId()))
             .filter(e -> e.getRole().getId().equals(roleId))
             .findFirst();
         if (firstApplication.isPresent())
                 applicationRepository.delete(firstApplication.get().getId());

         return new ResponseEntity<>(true, HttpStatus.OK);
     }

    @Override
    public ResponseEntity<Boolean> editstatusapplication(Long projectId, Long roleId, Long statusId) {
        User user = new User();
        if (SecurityUtils.getCurrentUserLogin().isPresent()) {
            String userlogin = SecurityUtils.getCurrentUserLogin().get();
            Optional<User> oneByLogin = userRepository.findOneByLogin(userlogin);
            if (oneByLogin.isPresent()) {
                user = oneByLogin.get();
            }
        }
        final User user1 = user;
        Optional<Application> firstApplication = applicationRepository.findAll()
            .stream()
            .filter(e -> e.getProject().getId().equals(projectId))
            .filter(e -> e.getApplicant().getId().equals(user1.getId()))
            .filter(e -> e.getRole().getId().equals(roleId))
            .findFirst();
        if (firstApplication.isPresent()) {
            ApplicationStatus applicationStatus = applicationStatusRepository.findOne(statusId);
            firstApplication.get().setStatus(applicationStatus);
            applicationRepository.save(firstApplication.get());

        }

        return new ResponseEntity<>(true, HttpStatus.OK);

    }

    //@Override
    public ResponseEntity<List<Long>> getRoleApplication(@PathVariable("projectId") Long projectId) {

        User user = new User();
        if (SecurityUtils.getCurrentUserLogin().isPresent()) {
            String userlogin = SecurityUtils.getCurrentUserLogin().get();
            Optional<User> oneByLogin = userRepository.findOneByLogin(userlogin);
            if (oneByLogin.isPresent()) {
                user = oneByLogin.get();
            }
        }
        final User user1 = user;


        List<Long> collect = applicationRepository.findAll()
            .stream()
            .filter(e -> e.getProject().getId().equals(projectId))
            .filter(e -> e.getApplicant().getId().equals(user1.getId()))
            .map(e -> e.getRole().getId())
            .collect(Collectors.toList());

        return new ResponseEntity<>(collect, HttpStatus.OK);

    }
}
