package com.altra.apps.schema.mongodb.model;

import com.altra.apps.schema.mongodb.type.CountryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "curriculum")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Curriculum implements Serializable {
    @Id
    private String id;
    private boolean hasPublicAccess;
    private String title, shortTitle, description;

    @DBRef(lazy = true)
    private Set<CurriculumChangeRequest> curriculumSuggestions = new HashSet<>();

    private Set<CountryType> countries = new HashSet<>();

    @DBRef(lazy = true)
    private Set<Topic> topics = new HashSet<>();

    @DBRef(lazy = true)
    private Institution owner;

    private String refCurriculumPid;


    public void addCurriculumSuggestions(CurriculumChangeRequest suggestions) {
        this.curriculumSuggestions.add(suggestions);
        suggestions.setCurriculum(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Curriculum)) return false;
        return id != null && id.equals(((Curriculum) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
