package io.refugeescode.hackboard.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import io.refugeescode.hackboard.domain.Project;
import io.refugeescode.hackboard.domain.Tag;
import io.refugeescode.hackboard.repository.ProjectRepository;
import io.refugeescode.hackboard.repository.ProjectRoleRepository;
import io.refugeescode.hackboard.repository.ProjectStoriesRepository;
import io.refugeescode.hackboard.repository.UserRepository;
import io.refugeescode.hackboard.service.dto.ProjectDto;
import io.refugeescode.hackboard.service.dto.ProjectRoleDto;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProjectsControllerNewTest {
    @Test
    public void testAdding() {
        ProjectRepository repository = mock(ProjectRepository.class);
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(1L);
        projectDto.setDescription("foo");
        projectDto.setTitle("bar");
        List<ProjectRoleDto> projectRoleDtos = new ArrayList<>();


        projectDto.setProjectRole(projectRoleDtos);

        List<String> tags = new ArrayList<>();

        projectDto.setTags(tags);

        List<String> listStories=new ArrayList<>();
        listStories.add("As User");
        projectDto.setProjectStories(listStories);
        new ProjectsController(repository, mock(UserRepository.class), mock(ProjectRoleRepository.class) , mock(ProjectStoriesRepository.class)).addProject(projectDto);
        ArgumentCaptor<Project> argumentCaptor = ArgumentCaptor.forClass(Project.class);

        verify(repository).save(argumentCaptor.capture());

        Project project = argumentCaptor.getValue();
        assertEquals("foo", project.getDescription());
        assertEquals("bar", project.getTitle());
        assertNull(project.getId());
    }

}
