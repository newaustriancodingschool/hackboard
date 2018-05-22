package io.refugeescode.hackboard.service.mapper;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;

import io.refugeescode.hackboard.domain.Project;
import io.refugeescode.hackboard.service.dto.ProjectDto;
import org.junit.Test;

public class ProjectMapperTest {
    @Test
    public void testMapping() {
        Project project = new Project();
        project.setId(5L);
        project.setTitle("foo");
        project.setDescription("some description");

        ProjectDto projectDto = ProjectMapper.INSTANCE.projectToProjectDto(project);

        assertThat(projectDto, allOf(
            hasProperty("id", equalTo(5L)),
            hasProperty("title", equalTo("foo")),
            hasProperty("description", equalTo("some description"))
        ));
    }

}
