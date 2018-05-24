package io.refugeescode.hackboard.controller;


import io.refugeescode.hackboard.repository.ProjectRoleRepository;

import io.refugeescode.hackboard.security.AuthoritiesConstants;
import io.refugeescode.hackboard.service.dto.ProjectRoleDto;
import io.refugeescode.hackboard.service.mapper.ProjectRoleMapper;
import io.refugeescode.hackboard.web.api.controller.ProjectRoleApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.MultiValueMap;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class RoleController implements ProjectRoleApi{

    private ProjectRoleRepository projectRoleRepository;

    @Autowired
    private ProjectRoleMapper projectRoleMapper;

    public RoleController(ProjectRoleRepository projectRoleRepository) {
        this.projectRoleRepository = projectRoleRepository;
    }

    @Override
    @Secured({AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
    public ResponseEntity<List<ProjectRoleDto>> listProjectRoles() {
        System.out.println(projectRoleRepository.findAll());
        return new ResponseEntity<>(
            projectRoleRepository.findAll().stream()
                .map(projectrole -> projectRoleMapper.projectRoleToProjectRoleDto(projectrole))
                .collect(Collectors.toList()),
            HttpStatus.OK
        );
    }


}

