package io.refugeescode.hackboard.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import io.refugeescode.hackboard.domain.Project;
import io.refugeescode.hackboard.repository.ProjectRepository;
import io.refugeescode.hackboard.repository.UserRepository;
import io.refugeescode.hackboard.service.dto.ProjectDto;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class ProjectsControllerNewTest {
    @Test
    public void testAdding() {
        ProjectRepository repository = mock(ProjectRepository.class);
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(1L);
        projectDto.setDescription("foo");
        projectDto.setTitle("bar");

        new ProjectsController(repository, mock(UserRepository.class)).addProject(projectDto);
        ArgumentCaptor<Project> argumentCaptor = ArgumentCaptor.forClass(Project.class);

        verify(repository).save(argumentCaptor.capture());

        Project project = argumentCaptor.getValue();
        assertEquals("foo", project.getDescription());
        assertEquals("bar", project.getTitle());
        assertNull(project.getId());
    }

}
