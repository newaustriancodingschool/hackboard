package io.refugeescode.hackboard.domain;


import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

import javax.persistence.*;

@Entity
@Table(name = "project_applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User applicant;

    @ManyToOne
    private ProjectRole role;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public ProjectRole getRole() {
        return role;
    }

    public void setRole(ProjectRole role) {
        this.role = role;
    }
}
