package io.refugeescode.hackboard.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "project_role")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProjectRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
    private String color;

    @Column(name = "role_name")
    private String roleName;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectRole that = (ProjectRole) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(color, that.color) &&
            Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, color, roleName);
    }
}
