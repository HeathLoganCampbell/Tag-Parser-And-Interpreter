package dev.cobblesword.tags.logic;

import dev.cobblesword.tags.Taggable;
import dev.cobblesword.tags.node.Node;

import java.util.List;
import java.util.Stack;

public class Evaluator
{
    public static List<Taggable> evaluate(String selector, List<Taggable> animals)
    {
        Stack<String> executionStack = Parser.stringToTreeModel(selector);
        Node modelNodes = Parser.process(executionStack, executionStack.pop());
        return Interpreter.extractMatchingTags(modelNodes, animals);
    }

    public static Node precompileSelector(String selector)
    {
        Stack<String> executionStack = Parser.stringToTreeModel(selector);
        return Parser.process(executionStack, executionStack.pop());
    }

    public static List<Taggable> evaluateWithPrecompiledDataStructure(Node modelNodes, List<Taggable> animals)
    {
        return Interpreter.extractMatchingTags(modelNodes, animals);
    }
}
