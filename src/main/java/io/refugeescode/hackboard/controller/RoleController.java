package io.refugeescode.hackboard.controller;


import io.refugeescode.hackboard.repository.ProjectRoleRepository;

import io.refugeescode.hackboard.service.dto.ProjectRoleDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class RoleController {
    private ProjectRoleRepository projectRoleRepository;

    public RoleController(ProjectRoleRepository projectRoleRepository) {
        this.projectRoleRepository = projectRoleRepository;
    }


    public ResponseEntity<List<ProjectRoleDto>> listProjectRoles() {

        return new ResponseEntity<List<ProjectRoleDto>>(
            (MultiValueMap<String, String>) projectRoleRepository.findAll().stream().collect(Collectors.toList()),

            HttpStatus.OK
        );

    }


}

