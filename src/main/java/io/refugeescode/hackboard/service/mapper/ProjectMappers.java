package io.refugeescode.hackboard.service.mapper;

import io.refugeescode.hackboard.domain.Project;
import io.refugeescode.hackboard.repository.UserRepository;
import io.refugeescode.hackboard.service.dto.ProjectDto;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
            System.out.println(project.getProjectRoles());

            List<String> collect = project.getProjectRoles().stream().map(e -> e.getRoleName()).collect(Collectors.toList());
            projectDto.setRoles(collect);
            System.out.println(projectDto);

            return projectDto;
        }
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
