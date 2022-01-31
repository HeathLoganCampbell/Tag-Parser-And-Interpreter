package dev.cobblesword.tags.logic;

import dev.cobblesword.tags.logic.token.IdentifierToken;
import dev.cobblesword.tags.logic.token.Token;
import dev.cobblesword.tags.node.BinaryOp;
import dev.cobblesword.tags.node.Identifier;
import dev.cobblesword.tags.node.Node;
import dev.cobblesword.tags.node.Operator;
import dev.cobblesword.tags.node.op.AndOp;
import dev.cobblesword.tags.node.op.OrOp;

import java.util.Stack;

public class Parser
{
    public static String OPEN_BRACKET = "(";
    public static String CLOSE_BRACKET = ")";


    public static Stack<Token> stringToTreeModel(String selector)
    {
        Stack<Token> executionStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();

        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < selector.length(); i++)
        {
            char c = selector.charAt(i);

            if( c == '(' )
            {
                operatorStack.add(OPEN_BRACKET);
                continue;
            }

            if( c == '&' || c == '|' )
            {
                executionStack.add(new IdentifierToken(buffer.toString().trim()));
                buffer.setLength(0);
            }

            if( c == ')')
            {
                if(buffer.length() > 0)
                {
                    executionStack.add(new IdentifierToken(buffer.toString().trim()));
                    buffer.setLength(0);
                }
            }

            if( c == ')' )
            {
                while (true)
                {
                    if(operatorStack.isEmpty()) break;
                    String pop = operatorStack.pop();
                    if (!pop.equals(OPEN_BRACKET))
                    {
                        if(pop.equals("&")) executionStack.add(new Token(Operator.AND));
                        if(pop.equals("|")) executionStack.add(new Token(Operator.OR));
                    }
                }
            }


            if( c == '&' || c == '|')
            {
                operatorStack.add("" + c);
                continue;
            }

            buffer.append(c);
        }


        return executionStack;
    }

    public static Node process(Stack<Token> executionStack, Token currentOp)
    {
        BinaryOp currentNode = null;
        boolean andOpToken = currentOp.getOperator() == Operator.AND;
        boolean orOpToken = currentOp.getOperator() == Operator.OR;
        if(andOpToken || orOpToken)
        {
            if(andOpToken ) currentNode = new AndOp();
            if(orOpToken) currentNode = new OrOp();

            currentNode.setRight(process(executionStack, executionStack.pop()));
            currentNode.setLeft(process(executionStack, executionStack.pop()));
            return currentNode;
        }

        return new Identifier(((IdentifierToken)currentOp).getIdentifier());
    }
}
