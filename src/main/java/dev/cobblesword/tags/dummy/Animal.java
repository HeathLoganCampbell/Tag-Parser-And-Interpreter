package dev.cobblesword.tags.dummy;

import dev.cobblesword.tags.Taggable;

import java.util.HashSet;

public class Animal implements Taggable
{
    private HashSet<String> tags = new HashSet<>();

    public void addTag(String tag)
    {
        this.tags.add(tag);
    }

    @Override
    public boolean hasTag(String tag)
    {
        return this.tags.contains(tag);
    }
}
