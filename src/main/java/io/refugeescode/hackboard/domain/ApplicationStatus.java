package io.refugeescode.hackboard.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name="application_status")
public class ApplicationStatus {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    @Column(name="describe")
    private ApplicationStatusType describe;

}
