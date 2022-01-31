package dev.cobblesword.tags.logic;

import dev.cobblesword.tags.node.BinaryOp;
import dev.cobblesword.tags.node.Identifier;
import dev.cobblesword.tags.node.Node;
import dev.cobblesword.tags.node.op.AndOp;
import dev.cobblesword.tags.node.op.OrOp;

import java.util.Stack;

public class Parser
{
    public static String OPEN_BRACKET = "(";
    public static String CLOSE_BRACKET = ")";


    public static Stack<String> stringToTreeModel(String selector)
    {
        Stack<String> executionStack = new Stack<>();
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
                executionStack.add(buffer.toString().trim());
                buffer.setLength(0);
            }

            if( c == ')')
            {
                if(buffer.length() > 0)
                {
                    executionStack.add(buffer.toString().trim());
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
                        executionStack.add(pop);
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

    public static Node process(Stack<String> executionStack, String currentOp)
    {
        Node currentNode = null;
        if(currentOp.equals("&") || currentOp.equals("|"))
        {
            if(currentOp.equals("&")) currentNode = new AndOp();
            if(currentOp.equals("|")) currentNode = new OrOp();

            ((BinaryOp) currentNode).setRight(process(executionStack, executionStack.pop()));
            ((BinaryOp) currentNode).setLeft(process(executionStack, executionStack.pop()));
            return currentNode;
        }

        if(currentOp.equals("!"))
        {
        }

        return new Identifier(currentOp);
    }
}
