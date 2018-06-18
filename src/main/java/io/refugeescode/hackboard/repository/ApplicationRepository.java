package io.refugeescode.hackboard.repository;

import io.refugeescode.hackboard.domain.Application;
import io.refugeescode.hackboard.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@SuppressWarnings("unused")
@Repository
public interface ApplicationRepository extends JpaRepository<Application,Long> {
    List<Application> findAllByProject(Project project);
    //Optional<Application> findOneByProject(Project project);

}
