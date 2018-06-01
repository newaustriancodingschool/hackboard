package io.refugeescode.hackboard.service.mapper;

import io.refugeescode.hackboard.domain.Application;
import io.refugeescode.hackboard.domain.ProjectRole;
import io.refugeescode.hackboard.domain.User;
import io.refugeescode.hackboard.repository.ProjectRoleRepository;
import io.refugeescode.hackboard.repository.UserRepository;
import io.refugeescode.hackboard.service.dto.ApplicationDto;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRoleRepository projectRoleRepository;

    public ApplicationDto applicationToApplicationDto(Application application ){
        if (application == null){
            return  null;
        }
        else
        {
            ApplicationDto applicationDto= new ApplicationDto();
            applicationDto.setApplicant(application.getApplicant().getId());
            applicationDto.setRoleId(application.getRole().getId());
            applicationDto.setProjectId(application.getId());

            ProjectRole role = projectRoleRepository.findOne(application.getRole().getId());
            applicationDto.setRoleName(role.getRoleName());
            applicationDto.setRoleColor(role.getColor());

            User applicant = userRepository.findOne(application.getApplicant().getId());
            applicationDto.setApplicantFullName(applicant.getFirstName()+" "+applicant.getLastName());
            applicationDto.setUserGithub(applicant.getGithub());

            return applicationDto;
        }
    }



}
