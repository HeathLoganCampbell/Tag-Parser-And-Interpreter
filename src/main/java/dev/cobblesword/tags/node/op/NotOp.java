package dev.cobblesword.tags.node.op;

import dev.cobblesword.tags.Taggable;
import dev.cobblesword.tags.node.Node;
import dev.cobblesword.tags.node.Operator;

public class NotOp extends Node
{
    private Node left;

    public NotOp(Node left)
    {
        super(Operator.NOT);
        this.left = left;
    }
    public Node getLeft() {
        return left;
    }
    public void setLeft(Node left) {
        this.left = left;
    }

    public boolean match(Taggable taggable)
    {
        boolean leftHasTag = left.match(taggable);
        return !leftHasTag;
    }
}
