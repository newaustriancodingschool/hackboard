package io.refugeescode.hackboard.repository;

import io.refugeescode.hackboard.domain.ApplicationStatus;
import io.refugeescode.hackboard.domain.ApplicationStatusType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationStatusRepository extends JpaRepository<ApplicationStatus,Long>{
}
