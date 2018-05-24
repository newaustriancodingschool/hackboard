package io.refugeescode.hackboard.service.mapper;

import com.codahale.metrics.jvm.GarbageCollectorMetricSet;
import io.refugeescode.hackboard.domain.ProjectRole;
import io.refugeescode.hackboard.repository.ProjectRoleRepository;
import io.refugeescode.hackboard.service.dto.ProjectRoleDto;
import org.hibernate.hql.internal.CollectionSubqueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProjectRoleMapper {


    @Autowired
    private ProjectRoleRepository projectRoleRepository;

    public ProjectRole projectRoleDtoToProjectRole(ProjectRoleDto projectRoleDto){
        if (projectRoleDto == null){
            return  null;
        }
        else
        {
            ProjectRole projectRole = new ProjectRole();
            projectRole.setRoleName(projectRoleDto.getRoleName());
            projectRole.setColor(projectRoleDto.getColor());
            return projectRole;
        }
    }

    public ProjectRoleDto projectRoleToProjectRoleDto(ProjectRole projectRole){
        if (projectRole == null){
            return  null;
        }
        else
        {
            ProjectRoleDto projectRoleDto = new ProjectRoleDto();
            projectRoleDto.setRoleName(projectRole.getRoleName());
            projectRoleDto.setColor(projectRole.getColor());
            return projectRoleDto;
        }
    }


    public List<ProjectRoleDto> listProjectRoleToProjectRoleDto(List<ProjectRole> projectRoleList){
        return projectRoleList.stream()
            .filter(Objects::nonNull)
            .map(this::projectRoleToProjectRoleDto)
            .collect(Collectors.toList());
    }
}
