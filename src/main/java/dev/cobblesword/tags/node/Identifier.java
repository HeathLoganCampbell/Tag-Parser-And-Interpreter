package dev.cobblesword.tags.node;

import dev.cobblesword.tags.Taggable;

public class Identifier extends Node
{
    private String tag;

    public Identifier(String tag)
    {
        super(Operator.IDENTIFIER);
        this.tag = tag;
    }

    public boolean match(Taggable taggable)
    {
        return taggable.hasTag(this.tag);
    }
}
