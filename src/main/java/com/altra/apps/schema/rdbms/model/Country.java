package com.altra.apps.schema.rdbms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "COUNTRY")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String pid;
    private String title, shortCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Curriculum_id")
    @JsonIgnore
    private Curriculum curriculum;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Country)) return false;
        return id != null && id.equals(((Country) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
