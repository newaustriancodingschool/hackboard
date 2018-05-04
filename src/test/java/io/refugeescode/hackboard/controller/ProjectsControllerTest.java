package io.refugeescode.hackboard.controller;

import io.refugeescode.hackboard.HackboardApp;
import io.refugeescode.hackboard.domain.Project;
import io.refugeescode.hackboard.repository.ProjectRepository;
import io.refugeescode.hackboard.web.rest.TestUtil;
import io.refugeescode.hackboard.web.rest.errors.ExceptionTranslator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HackboardApp.class)
public class ProjectsControllerTest {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectsController projectsController;

    private MockMvc restProjectMockMvc;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;



    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.restProjectMockMvc = MockMvcBuilders.standaloneSetup(projectsController)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter)
            .build();
    }

    @Test
    @Transactional
    public void listProjects() throws Exception{
        restProjectMockMvc.perform(get("/projects")
            .accept(TestUtil.APPLICATION_JSON_UTF8)
            .contentType(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        List<Project> projectList = projectRepository.findAll();
        // why three 3 becusae i insert in Database only three project in LoadData file
        Assert.assertEquals(3,projectList.size());
    }

    @Test
    @Transactional
    public void addProject() throws Exception{

        int projectRepositorySize = projectRepository.findAll().size();

        String title = "Test Project ";
        String description = "in this Project I want to make test for Project using Junit4 Test";

        Project entity= new Project();
        entity.setTitle(title);
        entity.setDescription(description);

        restProjectMockMvc.perform(post("/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entity)))
            .andExpect(status().isCreated());

        // now i want to check if the user in the Database ????
        List<Project> projectList = projectRepository.findAll();

        assertThat(projectList).hasSize(projectRepositorySize+ 1);

        Project testProject = projectList.get(projectList.size() - 1);

        assertThat(testProject.getTitle()).isEqualTo(title);
        assertThat(testProject.getDescription()).isEqualTo(description);
    }


    @Test
    @Transactional
    public void editProject() throws Exception{

        int projectRepositorySize = projectRepository.findAll().size();

        String title = "Test Update Project ";
        String description = "I want to test edit endpoint";


        List<Project> allProjects = projectRepository.findAll();
        Project anyProject = allProjects.get(0);
        anyProject.setTitle(title);
        anyProject.setDescription(description);


        restProjectMockMvc.perform(put("/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anyProject)))
            .andExpect(status().isCreated());

        // now i want to check if the user in the Database ????
        Project oneProject = projectRepository.findOne(anyProject.getId());


        assertThat(oneProject.getTitle()).isEqualTo(title);
        assertThat(oneProject.getDescription()).isEqualTo(description);

    }
}
