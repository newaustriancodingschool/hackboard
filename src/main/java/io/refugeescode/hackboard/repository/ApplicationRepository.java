package io.refugeescode.hackboard.repository;

import io.refugeescode.hackboard.domain.Application;
import io.refugeescode.hackboard.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application,Long> {
    List<Application> findAllByProject(Project project);
    //Optional<Application> findOneByProject(Project project);

}
