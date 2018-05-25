package io.refugeescode.hackboard.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "applicant")
@Data
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long user_id;
    @Column
    private Long project_id;
    @Column
    private Long role_id;


    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
