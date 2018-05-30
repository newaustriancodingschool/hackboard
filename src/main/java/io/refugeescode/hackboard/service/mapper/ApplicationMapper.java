package io.refugeescode.hackboard.service.mapper;

import io.refugeescode.hackboard.domain.Application;
import io.refugeescode.hackboard.domain.User;
import io.refugeescode.hackboard.service.dto.ApplicationDto;
import org.springframework.stereotype.Service;

@Service
public class ApplicationMapper {


    public ApplicationDto ApplicationToApplicationDto(Application application ){
        if (application == null){
            return  null;
        }
        else
        {
            ApplicationDto applicationDto= new ApplicationDto();
            applicationDto.setApplicant(application.getId());
            applicationDto.setRoleId(application.getRole().getId());
            applicationDto.setProjectId(application.getId());
            return applicationDto;
        }
    }



}
