package com.altra.apps.schema.type;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Data
public class MarkingSchemaType implements Serializable {
    private List<BlockTextType> generalGuidance = new LinkedList<>();
    private BlockTextType additionalGuidance;
    private List<BlockTextType> marksBreakdown = new LinkedList<>();
    private List<BlockTextType> exampleSolution = new LinkedList<>();
}
