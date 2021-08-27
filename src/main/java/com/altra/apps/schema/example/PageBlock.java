package com.altra.apps.schema.example;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

@ToString
public class PageBlock {
    private String type = "PageBlock";
    private List<Block> blocks = new LinkedList<>();

    public static void main(String[] args) {
        PageBlock pb = new PageBlock();
        Block block1 = new Block("1", "type1");
        Block block2 = new Block("2", "type2");

        Block block22 = new Block("22", "type22");
        Block block23 = new Block("23", "type23");
        block2.addChildBlocks(block22);
        block2.addChildBlocks(block23);

        pb.addBlock(block1);
        pb.addBlock(block2);
        System.out.println(pb);
    }

    public void addBlock(Block block) {
        this.blocks.add(block);
    }
}

@Setter
@Getter
@ToString
class Block {
    private String id, type;
    private boolean hasChildren;
    //private Block parentBlock;
    //@ToString.Exclude
    private List<Block> childBlocks = new LinkedList<>();

    public Block(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public void addChildBlocks(Block block) {
        this.childBlocks.add(block);
        //block.setParentBlock(this);
        this.setHasChildren(true);
    }


}


