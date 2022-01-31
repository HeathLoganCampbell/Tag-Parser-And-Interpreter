package dev.cobblesword.tags.node.op;

import dev.cobblesword.tags.Taggable;
import dev.cobblesword.tags.node.BinaryOp;
import dev.cobblesword.tags.node.Node;
import dev.cobblesword.tags.node.Operator;

public class AndOp extends BinaryOp
{
    public AndOp(Node left, Node right)
    {
        super(Operator.AND, left, right);
    }

    public AndOp()
    {
        super(Operator.AND);
    }


    public boolean match(Taggable taggable)
    {
        boolean leftHasTag = left.match(taggable);
        boolean rightHasTag = right.match(taggable);

        return leftHasTag && rightHasTag;
    }
}
