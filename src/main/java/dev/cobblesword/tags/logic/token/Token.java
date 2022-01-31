package dev.cobblesword.tags.logic.token;

import dev.cobblesword.tags.node.Operator;

public class Token
{
    public Operator operator;

    public Token(Operator operator) {
        this.operator = operator;
    }

    public Operator getOperator() {
        return operator;
    }
}
