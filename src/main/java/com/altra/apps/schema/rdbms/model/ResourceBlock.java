package com.altra.apps.schema.rdbms.model;

import com.altra.apps.schema.common.ExportFormatType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "RESOURCE_BLOCK")
public class ResourceBlock extends Block {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @Column(nullable = false, unique = true)
    private String pid;*/
    private String title, description;
    @Enumerated(EnumType.STRING)
    private ExportFormatType exportFormat;
    @OneToMany(mappedBy = "resourceBlock", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Language> languages = new HashSet<>();


    public void addLanguage(Language language) {
        this.languages.add(language);
        language.setResourceBlock(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ResourceBlock)) return false;
        return this.getId() != null && this.getId().equals(((Unit) o).getId());
    }

}
