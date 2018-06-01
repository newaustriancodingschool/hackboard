package io.refugeescode.hackboard.repository;

import io.refugeescode.hackboard.domain.Application;
import io.refugeescode.hackboard.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application,Long> {
    Optional<Application> findOneByProject(Project project);
}
