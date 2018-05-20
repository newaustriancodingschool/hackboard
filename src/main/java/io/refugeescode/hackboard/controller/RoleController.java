package io.refugeescode.hackboard.controller;


import io.refugeescode.hackboard.domain.ProjectRole;
import io.refugeescode.hackboard.repository.ProjectRoleRepository;
import io.refugeescode.hackboard.security.SecurityUtils;
import io.refugeescode.hackboard.service.dto.ProjectRoleDto;
import io.refugeescode.hackboard.web.rest.util.HeaderUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.acl.Owner;
import java.util.List;
import java.util.stream.Collectors;

import static io.refugeescode.hackboard.security.AuthoritiesConstants.ADMIN;
import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

@RestController
public class RoleController {
    private ProjectRoleRepository projectRoleRepository;

    public RoleController(ProjectRoleRepository projectRoleRepository) {
        this.projectRoleRepository = projectRoleRepository;
    }

    public ResponseEntity<Boolean> addRole(@RequestBody ProjectRoleDto projectRole) {

        ProjectRole role = new ProjectRole();

        role.setRoleName(role.getRoleName());
        role.setColor(role.getColor());
        projectRoleRepository.save(role);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }


    public ResponseEntity<List<ProjectRoleDto>> listProjectRoles() {

        return new ResponseEntity<List<ProjectRoleDto>>(
            (MultiValueMap<String, String>) projectRoleRepository.findAll().stream().collect(Collectors.toList()),

            HttpStatus.OK
        );
    }


    public ResponseEntity<Boolean> deleteProject(@PathVariable("projectRoleId") Long projectRoleId) {
        //||!SecurityUtils.isCurrentUserInRole(Owner) not working
        if (!SecurityUtils.isCurrentUserInRole(ADMIN)) {
            return ResponseEntity.badRequest().header(String.valueOf(HeaderUtil.createFailureAlert(ENTITY_NAME, "Not authenticated", "You need to be n admin or owner to perform this action "))).body(null);
        }
        projectRoleRepository.delete(projectRoleId);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}

