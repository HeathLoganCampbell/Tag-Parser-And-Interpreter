package dev.cobblesword.tags.node;

import dev.cobblesword.tags.Taggable;
import dev.cobblesword.tags.dummy.Animal;

public class Node
{
    private Operator operator;

    public Node(Operator operator)
    {
        this.operator = operator;
    }

    public Operator getOperator()
    {
        return operator;
    }

    public boolean match(Taggable taggable)
    {
        return true;
    }
}
