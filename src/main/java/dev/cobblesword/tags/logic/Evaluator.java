package dev.cobblesword.tags.logic;

import dev.cobblesword.tags.Taggable;
import dev.cobblesword.tags.dummy.Animal;
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
}
