package io.refugeescode.hackboard.service.mapper;

import io.refugeescode.hackboard.domain.Project;
import io.refugeescode.hackboard.domain.ProjectRole;
import io.refugeescode.hackboard.repository.UserRepository;
import io.refugeescode.hackboard.service.dto.ProjectDto;
import io.refugeescode.hackboard.service.dto.ProjectDtoRoles;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectMappers {

    @Autowired
    private UserRepository userRepository;



    public ProjectDto projectToProjectDto(Project project) {
        if (project == null){
            return null;
        }
        else{
            ProjectDto projectDto = new ProjectDto();
            projectDto.setId(project.getId());
            projectDto.setDescription(project.getDescription());
            projectDto.setTitle(project.getTitle());
            projectDto.setOwnerId(project.getOwner().getId());
            projectDto.setOwnerFirstName(project.getOwner().getFirstName());
            projectDto.setOwnerLastName(project.getOwner().getLastName());

            List<ProjectDtoRoles> collect = project.getProjectRoles().stream()
                .map(e -> convertToProjectDTORoles(e)).collect(Collectors.toList());

            boolean found= false;
            Integer itemIndex = 0;
            List<ProjectDtoRoles> dtoRolesSet = new ArrayList<>();
            for (ProjectDtoRoles item:collect) {
                found = false;
                Integer currentIndex = 0;
                for(ProjectDtoRoles item1 : dtoRolesSet) {
                    if (item.getRoleName().equalsIgnoreCase(item1.getRoleName())) {
                        found = true;
                        itemIndex = currentIndex;
                    }
                    currentIndex = currentIndex +1;
                }
                if (found == true){
                    ProjectDtoRoles projectDtoRoles = dtoRolesSet.get(itemIndex);
                    projectDtoRoles.setRoleName(projectDtoRoles.getRoleName());
                    projectDtoRoles.setColor(projectDtoRoles.getColor());
                    projectDtoRoles.setCount(projectDtoRoles.getCount()+1);
                    dtoRolesSet.set(itemIndex,projectDtoRoles);
                    //dtoRolesSet.remove(itemIndex);
                    //dtoRolesSet.add(projectDtoRoles);
                }
                else{
                    ProjectDtoRoles projectDtoRoles = new ProjectDtoRoles();
                    projectDtoRoles.setColor(item.getColor());
                    projectDtoRoles.setRoleName(item.getRoleName());
                    projectDtoRoles.setCount(1L);
                    dtoRolesSet.add(projectDtoRoles);
                }
                ;
            }
            //List<Project>
            projectDto.setRoles(dtoRolesSet);

            return projectDto;
        }
    }

    private ProjectDtoRoles convertToProjectDTORoles(ProjectRole e) {
        ProjectDtoRoles projectDtoRoles = new ProjectDtoRoles();
        projectDtoRoles.setColor(e.getColor());
        projectDtoRoles.setRoleName(e.getRoleName());
        projectDtoRoles.setCount(1L);
        return projectDtoRoles;
    }


    public List<ProjectDto> listprojectTolistProjectDTO(List<Project> projectList){
        List<ProjectDto> projectDtoList = projectList.stream()
            .filter(Objects::nonNull)
            .map(this::projectToProjectDto)
            .collect(Collectors.toList());
        return projectDtoList;

    }


    public Project projectDTOToProject(ProjectDto projectDto){
        if (projectDto == null) {
            return null;
        }
        else{
            Project project = new Project();
            project.setId(projectDto.getId());
            project.setTitle(projectDto.getTitle());
            project.setDescription(projectDto.getDescription());
            project.setId(projectDto.getId());
            return project;
        }
    }

}
