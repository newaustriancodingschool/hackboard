package io.refugeescode.hackboard.repository;

import io.refugeescode.hackboard.domain.Project;
import io.refugeescode.hackboard.domain.ProjectStories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectStoriesRepository extends JpaRepository<ProjectStories, Long>{


    List<ProjectStories> findAllByProjectId(Project project);
}
