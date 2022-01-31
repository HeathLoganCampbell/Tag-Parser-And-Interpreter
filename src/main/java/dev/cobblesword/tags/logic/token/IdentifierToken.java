package dev.cobblesword.tags.logic.token;

import dev.cobblesword.tags.node.Operator;

public class IdentifierToken extends Token
{
    private String identifier;

    public IdentifierToken(String identifier)
    {
        super(Operator.IDENTIFIER);
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
