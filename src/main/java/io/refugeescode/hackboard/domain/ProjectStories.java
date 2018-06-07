package io.refugeescode.hackboard.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "project_stories")
public class ProjectStories {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "project_id", nullable = false)
        private Project project;


        @Column(name = "description")
        private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectStories that = (ProjectStories) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(project, that.project) &&
            Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, project, description);
    }

    @Override
    public String toString() {
        return "ProjectStories{" +
            "id=" + id +
            ", project=" + project +
            ", description='" + description + '\'' +
            '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
