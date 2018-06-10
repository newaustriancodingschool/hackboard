package io.refugeescode.hackboard.service;

import io.refugeescode.hackboard.domain.Project;
import io.refugeescode.hackboard.domain.User;
import io.refugeescode.hackboard.repository.ProjectRepository;
import io.refugeescode.hackboard.repository.ProjectStoriesRepository;
import io.refugeescode.hackboard.repository.UserRepository;
import io.refugeescode.hackboard.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProjectRepository projectRepository;



    public Optional<User> getCurrentUser() {

        if (SecurityUtils.getCurrentUserLogin().isPresent()) {
            String userlogin = SecurityUtils.getCurrentUserLogin().get();
            Optional<User> oneByLogin = userRepository.findOneByLogin(userlogin);
            if (oneByLogin.isPresent()) {
                return oneByLogin;
            }
        }
        return null;
    }



    public Boolean hasPermission(Long projectId,String loginName){
        System.out.println("*************************************************");
        System.out.println("this is in hasPermission function");
        System.out.println("*************************************************");
        Optional<User> userLogin = userRepository.findOneByLogin(loginName);
        if (userLogin.isPresent()) {
            User user = userLogin.get();
            Project project = projectRepository.findOne(projectId);
            if (project.getOwner().equals(user)){
                return true;
            }
        }
        return false;
    }


}
