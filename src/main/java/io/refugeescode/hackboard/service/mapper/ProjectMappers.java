package io.refugeescode.hackboard.service.mapper;

import io.refugeescode.hackboard.domain.Project;
import io.refugeescode.hackboard.domain.ProjectRole;
import io.refugeescode.hackboard.repository.ProjectRoleRepository;
import io.refugeescode.hackboard.repository.UserRepository;
import io.refugeescode.hackboard.service.dto.ApplicationDto;
import io.refugeescode.hackboard.service.dto.ProjectDto;
import io.refugeescode.hackboard.service.dto.ProjectRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectMappers {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRoleRepository projectRoleRepository;



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
            projectDto.setGithub(project.getGithub());
            List<ProjectRoleDto> collect = project.getProjectRoles().stream()
                .map(e -> convertToProjectDTORoles(e)).collect(Collectors.toList());

            //project.getApplications().stream().

            boolean found= false;
            Integer itemIndex = 0;
            List<ProjectRoleDto> dtoRolesSet = new ArrayList<>();
            for (ProjectRoleDto item:collect) {
                found = false;
                Integer currentIndex = 0;
                for(ProjectRoleDto item1 : dtoRolesSet) {
                    if (item.getRoleName().equalsIgnoreCase(item1.getRoleName())) {
                        found = true;
                        itemIndex = currentIndex;
                    }
                    currentIndex = currentIndex +1;
                }
                if (found == true){
                    ProjectRoleDto projectDtoRoles = dtoRolesSet.get(itemIndex);
                    projectDtoRoles.setRoleName(projectDtoRoles.getRoleName());
                    projectDtoRoles.setColor(projectDtoRoles.getColor());
                    projectDtoRoles.setCount(projectDtoRoles.getCount()+1);
                    dtoRolesSet.set(itemIndex,projectDtoRoles);
                }
                else{
                    ProjectRoleDto projectDtoRoles = new ProjectRoleDto();
                    projectDtoRoles.setColor(item.getColor());
                    projectDtoRoles.setRoleName(item.getRoleName());
                    projectDtoRoles.setCount(1L);
                    dtoRolesSet.add(projectDtoRoles);
                }

            }
            projectDto.setProjectRole(dtoRolesSet);


            return projectDto;
        }
    }

    private ProjectRoleDto convertToProjectDTORoles(ProjectRole e) {
        ProjectRoleDto projectDtoRoles = new ProjectRoleDto();
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
            project.setGithub(projectDto.getGithub());

            int size = projectDto.getProjectRole().size();
            List<ProjectRole> projectRoleslist = new ArrayList<>();
            for(int i=0 ; i< size ; i++){
                String roleName = projectDto.getProjectRole().get(i).getRoleName();
                Optional<ProjectRole> oneByRoleName = projectRoleRepository.findOneByRoleName(roleName);
                if (oneByRoleName.isPresent()) {
                    Long count = projectDto.getProjectRole().get(i).getCount();
                    ProjectRole projectRole = oneByRoleName.get();
                    for (int index = 0; index < count; index = index++) {
                        projectRoleslist.add(projectRole);
                    }
                }

            }
            project.setProjectRoles(projectRoleslist);
            return project;
        }
    }

}
