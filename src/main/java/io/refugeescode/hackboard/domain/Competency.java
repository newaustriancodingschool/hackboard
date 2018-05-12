package io.refugeescode.hackboard.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class Competency {

    @Id
    @GeneratedValue
    private Long id;

    @Override
    public String toString() {
        return "Competency{" +
            "id=" + id +
            ", title='" + title + '\'' +
            '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
