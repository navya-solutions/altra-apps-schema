package com.altra.apps.schema.rdbms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "BLOCK_TAG")
public class BlockTag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String topicRefPid;
    private String topicTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "block_id", foreignKey=@ForeignKey(name="block_block_tag"))
    @JsonIgnore
    private Block block;
}
