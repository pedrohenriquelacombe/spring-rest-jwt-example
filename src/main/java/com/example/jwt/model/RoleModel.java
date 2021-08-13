package com.example.jwt.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "role")
public class RoleModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public RoleModel(String name) {
        this.name = name;
    }

    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

}
