package dev.cobblesword.tags.node.op;

import dev.cobblesword.tags.Taggable;
import dev.cobblesword.tags.node.BinaryOp;
import dev.cobblesword.tags.node.Node;
import dev.cobblesword.tags.node.Operator;

public class OrOp extends BinaryOp
{
    public OrOp(Node left, Node right)
    {
        super(Operator.OR, left, right);
    }

    public OrOp()
    {
        super(Operator.OR);
    }

    public boolean match(Taggable taggable)
    {
        boolean leftHasTag = left.match(taggable);
        boolean rightHasTag = right.match(taggable);

        return leftHasTag || rightHasTag;
    }
}
