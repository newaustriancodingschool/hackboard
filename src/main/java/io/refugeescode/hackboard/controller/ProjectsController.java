package io.refugeescode.hackboard.controller;


import io.refugeescode.hackboard.domain.Project;
import io.refugeescode.hackboard.repository.ProjectRepository;
import io.refugeescode.hackboard.service.dto.ProjectDto;
import io.refugeescode.hackboard.service.mapper.ProjectMapper;
import io.refugeescode.hackboard.web.api.controller.ProjectsApi;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectsController implements ProjectsApi {

    private ProjectRepository projectsRepository;

    public ProjectsController(ProjectRepository projectsRepository) {
        this.projectsRepository = projectsRepository;
    }

    @Override
    public ResponseEntity<Boolean> addProject(@RequestBody ProjectDto project) {
        Project entity = new Project();
        entity.setTitle(project.getTitle());
        entity.setDescription(project.getDescription());

        projectsRepository.save(entity);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> editProject(@RequestBody ProjectDto project) {
        Project entity = projectsRepository.findOne(project.getId());
        entity.setTitle(project.getTitle());
        entity.setDescription(project.getDescription());

        projectsRepository.save(entity);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProjectDto>> listProjects() {
        return new ResponseEntity<>(
            projectsRepository.findAll().stream()
                .map(ProjectMapper.INSTANCE::projectToProjectDto)
                .collect(Collectors.toList()),
            HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<ProjectDto> viewProject(@PathVariable("projectId") Long projectId) {
        return new ResponseEntity<>(
            ProjectMapper.INSTANCE.projectToProjectDto(
                projectsRepository.findOne(projectId)),
            HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<Boolean> deleteProject(@PathVariable("projectId") Long projectId) {
        projectsRepository.delete(projectId);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
