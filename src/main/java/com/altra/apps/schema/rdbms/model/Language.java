package com.altra.apps.schema.rdbms.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "LANGUAGE")
//@Audited
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false, unique = true)
    private String code, name;

    @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
    private List<Block> blocks = new LinkedList<>();
}
