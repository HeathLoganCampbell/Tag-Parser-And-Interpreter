package dev.cobblesword.tags.node;

import dev.cobblesword.tags.Taggable;

public class BinaryOp extends Node
{
    protected Node left;
    protected Node right;

    public BinaryOp(Operator operator, Node left, Node right)
    {
        super(operator);
        this.left = left;
        this.right = right;
    }

    public BinaryOp(Operator operator)
    {
        super(operator);
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

}
