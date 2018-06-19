package io.refugeescode.hackboard.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "tag")
    String tag;

    public Tag() {
    }
}
