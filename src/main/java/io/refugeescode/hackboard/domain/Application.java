package io.refugeescode.hackboard.domain;


import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

import javax.persistence.*;

//@Entity
//@Table(name = "application")
public class Application {

  //  @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


/*
    @ManyToOne
    @JoinColumn(name = "user_id")
*/
    private User user_id;


/*
    @ManyToOne
    @JoinColumn(name = "project_id")
*/
    private Project project_id;


/*
    @ManyToOne
    @JoinColumn(name = "role_id")
*/
    private ProjectRole role_id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Project getProject_id() {
        return project_id;
    }

    public void setProject_id(Project project_id) {
        this.project_id = project_id;
    }

}
