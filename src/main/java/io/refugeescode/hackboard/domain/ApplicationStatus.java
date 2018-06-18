package io.refugeescode.hackboard.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="application_status")
public class ApplicationStatus {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    @Column(name="describe")
    private ApplicationStatusType describe;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApplicationStatusType getDescribe() {
        return describe;
    }

    public void setDescribe(ApplicationStatusType describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "ApplicationStatus{" +
            "id=" + id +
            ", describe=" + describe +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationStatus that = (ApplicationStatus) o;
        return Objects.equals(id, that.id) &&
            describe == that.describe;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, describe);
    }
}
