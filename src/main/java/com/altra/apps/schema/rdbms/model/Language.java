package com.altra.apps.schema.rdbms.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "LANGUAGE")
@Audited
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String shortCode, title;

    @OneToOne(mappedBy = "language", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Block block;
}
