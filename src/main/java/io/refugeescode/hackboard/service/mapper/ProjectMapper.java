package io.refugeescode.hackboard.service.mapper;

import io.refugeescode.hackboard.domain.Project;
import io.refugeescode.hackboard.service.dto.ProjectDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectDto projectToProjectDto(Project project);
}
