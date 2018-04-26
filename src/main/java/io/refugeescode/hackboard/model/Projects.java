package io.refugeescode.hackboard.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Projects {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String description;
    @Override
    public String toString() {
        return "Projects{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }





}
