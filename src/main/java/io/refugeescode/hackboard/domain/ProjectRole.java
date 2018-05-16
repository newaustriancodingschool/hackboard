package io.refugeescode.hackboard.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "project_role")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProjectRole {

    @Id
    @GeneratedValue
    Long id;
    @Column(name = "color")
    String color;
    @Column(name = "roleName")
    String roleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "ProjectRole{" +
            "id=" + id +
            ", color='" + color + '\'' +
            ", roleName='" + roleName + '\'' +
            '}';
    }
}
