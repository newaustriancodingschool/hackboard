package io.refugeescode.hackboard.domain;


import lombok.Data;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "project_application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User applicant ;

    @ManyToOne(fetch = FetchType.EAGER)
    private ProjectRole role ;

    @ManyToOne(fetch = FetchType.EAGER)
    private Project project;

    @OneToOne
    @JoinColumn(name="status")
    private ApplicationStatus status;

}
